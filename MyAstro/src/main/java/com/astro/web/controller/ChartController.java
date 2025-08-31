package com.astro.web.controller;

import com.astro.web.model.Chart;
import com.astro.web.service.AstrologyService;
import com.astro.web.service.ChartService;
import com.astro.web.service.PdfService;
import com.astrology.MultipDivCharts.DivisionalChart;
import com.astrology.MultipDivCharts.DivisionalChartData;
import com.astrology.MultipDivCharts.NorthIndianChartImageGenerator;
import com.astrology.RuleEngine.Nakshatra;
import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.RuleResult;
import com.astrology.RuleEngine.ZodiacSign;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ChartController {

    private final ChartService chartService;
    private final AstrologyService astrologyService;
    private final PdfService pdfService;

    public ChartController(ChartService chartService, AstrologyService astrologyService, PdfService pdfService) {
        this.chartService = chartService;
        this.astrologyService = astrologyService;
        this.pdfService = pdfService;
    }

    @GetMapping("/")
    public String showChartList(Model model) {
        model.addAttribute("charts", chartService.getAllCharts());
        return "index";
    }

    @GetMapping("/add")
    public String showAddChartForm(Model model) {
        model.addAttribute("chart", new Chart());
        return "add-chart";
    }

    @PostMapping("/add")
    public String addChart(@ModelAttribute("chart") Chart chart) {
        chartService.saveChart(chart);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditChartForm(@PathVariable("id") Long id, Model model) {
        Chart chart = chartService.getChartById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid chart Id:" + id));
        model.addAttribute("chart", chart);
        return "edit-chart";
    }

    @PostMapping("/update/{id}")
    public String updateChart(@PathVariable("id") Long id, @ModelAttribute("chart") Chart chart) {
        chart.setId(id);
        chartService.saveChart(chart);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteChart(@PathVariable("id") Long id) {
        chartService.deleteChart(id);
        return "redirect:/";
    }

    @GetMapping("/chart/{id}")
    public String showChartDetails(@PathVariable("id") Long id, Model model) {
        Chart chart = chartService.getChartById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid chart Id:" + id));
        model.addAttribute("chart", chart);
        model.addAttribute("rules", astrologyService.getAllRules());
        return "chart-details";
    }

    @PostMapping("/apply-rules/{id}")
    public String applyRules(@PathVariable("id") Long id, @RequestParam(value = "ruleIds", required = false) List<Long> ruleIds, Model model) {
        Map<String, Object> result = astrologyService.applyRules(id, ruleIds);
        Chart chart = chartService.getChartById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid chart Id:" + id));
        model.addAttribute("chart", chart);
        model.addAttribute("rules", astrologyService.getAllRules());
        model.addAttribute("results", astrologyService.formatResults((Map<String, List<RuleResult>>) result.get("results")));
        model.addAttribute("chartImage", result.get("chartImage"));
        return "chart-details";
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable("id") Long id) throws IOException {
        Chart chart = chartService.getChartById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid chart Id:" + id));

        Map<String, Object> result = astrologyService.applyRules(id, null);
        String chartImagePath = (String) result.get("chartImage");
        String absoluteChartImagePath = new File("target/classes/static" + chartImagePath).getAbsolutePath();

        byte[] pdfBytes = pdfService.generatePdf(chart, (Map<String, List<RuleResult>>) result.get("results"), absoluteChartImagePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", chart.getName() + "_chart.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/api/chart/generate")
    @ResponseBody
    public ResponseEntity<byte[]> generateChartImage(@RequestParam("ascendant") int ascendant, @RequestParam("positions") String positions, @RequestParam("title") String title) throws IOException {
        DivisionalChart divisionalChartType = title.startsWith("D1") ? DivisionalChart.D1 : DivisionalChart.D9;
        DivisionalChartData chartData = new DivisionalChartData(divisionalChartType);
        chartData.setAscendant(ZodiacSign.values()[ascendant]);

        String[] pairs = positions.split(";");
        for (String pair : pairs) {
            String[] parts = pair.split("=");
            if (parts.length == 2) {
                int houseNumber = Integer.parseInt(parts[0]);
                ZodiacSign sign = ZodiacSign.values()[(ascendant + houseNumber - 1) % 12];
                String[] planets = parts[1].split("-");
                for (String planetName : planets) {
                    if (!planetName.isEmpty()) {
                        Planet planet = Planet.valueOf(planetName);
                        PlanetPosition planetPosition = new PlanetPosition(planet, houseNumber, sign, 0.0, Nakshatra.ASHWINI, false);
                        chartData.addPlanetPosition(planet, planetPosition);
                    }
                }
            }
        }

        BufferedImage chartImage = NorthIndianChartImageGenerator.generateSingleChartImage(chartData, title);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(chartImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(imageBytes.length);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
