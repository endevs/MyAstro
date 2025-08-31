package com.astro.web.service;

import com.astro.web.model.Chart;
import com.astro.web.model.Rule;
import com.astro.web.repository.ChartRepository;
import com.astro.web.repository.RuleRepository;
import com.astrology.MultipDivCharts.*;
import com.astrology.RuleEngine.Nakshatra;
import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.RuleResult;
import com.astrology.RuleEngine.ZodiacSign;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AstrologyService {

    private final ChartRepository chartRepository;
    private final RuleRepository ruleRepository;

    public AstrologyService(ChartRepository chartRepository, RuleRepository ruleRepository) {
        this.chartRepository = chartRepository;
        this.ruleRepository = ruleRepository;
    }

    public Map<String, Object> applyRules(Long chartId, List<Long> ruleIds) {
        Chart chart = chartRepository.findById(chartId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid chart Id:" + chartId));

        ChartData chartData = convertToChartData(chart);
        CompleteChart completeChart = createCompleteChart(chartData);

        AdvancedRuleEngine ruleEngine = new AdvancedRuleEngine();
        Map<String, List<RuleResult>> results = ruleEngine.evaluateCompleteChart(completeChart, DivisionalChart.D9);

        String chartImage = generateChartImage(completeChart, chart.getName());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("results", results);
        resultMap.put("chartImage", chartImage);

        return resultMap;
    }

    private String generateChartImage(CompleteChart completeChart, String chartName) {
        try {
            File outputDir = new File("target/classes/static/generated-charts");
            outputDir.mkdirs();
            String fileName = chartName.replace(" ", "_") + "_chart.png";
            String imagePath = new File(outputDir, fileName).getAbsolutePath();
            NorthIndianChartImageGenerator.generateChartImage(completeChart.getChart(DivisionalChart.D1), completeChart.getChart(DivisionalChart.D9), imagePath, chartName, "", "", "");
            return "/generated-charts/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ChartData convertToChartData(Chart chart) {
        String[] housePositions = chart.getHousePositions() != null ? chart.getHousePositions().split(";") : new String[0];
        String[] d9HousePositions = chart.getD9HousePositions() != null ? chart.getD9HousePositions().split(";") : new String[0];

        return new ChartData(chart.getName(), chart.getDob(), chart.getBirthTime(), chart.getBirthPlace(), housePositions, d9HousePositions);
    }

    private CompleteChart createCompleteChart(ChartData chartData) {
        CompleteChart completeChart = new CompleteChart();
        DivisionalChartData d1 = new DivisionalChartData(DivisionalChart.D1);

        String[] firstHouse = chartData.getHousePositions()[0].split("-");
        d1.setAscendant(getZodiacSign(Integer.parseInt(firstHouse[0])));

        int houseNumber = 1;
        for (String housePositionString : chartData.getHousePositions()) {
            String[] parts = housePositionString.split("-");
            ZodiacSign sign = getZodiacSign(Integer.parseInt(parts[0]));

            for (int i = 1; i < parts.length; i++) {
                String planetString = parts[i];
                Planet planet = getPlanet(planetString);
                boolean isRetro = isRetrograde(planetString);
                d1.addPlanetPosition(planet, new PlanetPosition(planet, houseNumber, sign, 0.0, Nakshatra.ASHWINI, isRetro));
            }
            houseNumber++;
        }

        completeChart.addDivisionalChart(DivisionalChart.D1, d1);

        if (chartData.getD9HousePositions() != null && chartData.getD9HousePositions().length > 0 && !chartData.getD9HousePositions()[0].trim().isEmpty()) {
            try {
                String[] firstHouseD9 = chartData.getD9HousePositions()[0].split("-");
                if (firstHouseD9.length > 0 && !firstHouseD9[0].trim().isEmpty()) {
                    DivisionalChartData d9 = new DivisionalChartData(DivisionalChart.D9);
                    d9.setAscendant(getZodiacSign(Integer.parseInt(firstHouseD9[0])));

                    int houseNumberD9 = 1;
                    for (String housePositionString : chartData.getD9HousePositions()) {
                        String[] parts = housePositionString.split("-");
                        if (parts.length > 0 && !parts[0].trim().isEmpty()) {
                            ZodiacSign sign = getZodiacSign(Integer.parseInt(parts[0]));

                            for (int i = 1; i < parts.length; i++) {
                                String planetString = parts[i];
                                if (planetString != null && !planetString.trim().isEmpty()) {
                                    Planet planet = getPlanet(planetString);
                                    boolean isRetro = isRetrograde(planetString);
                                    d9.addPlanetPosition(planet, new PlanetPosition(planet, houseNumberD9, sign, 0.0, Nakshatra.ASHWINI, isRetro));
                                }
                            }
                        }
                        houseNumberD9++;
                    }

                    completeChart.addDivisionalChart(DivisionalChart.D9, d9);
                }
            } catch (NumberFormatException e) {
                System.out.println("Skipping D9 chart for " + chartData.getName() + " due to invalid data.");
            }
        }
        return completeChart;
    }

    public String formatResults(Map<String, List<RuleResult>> results) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<RuleResult>> entry : results.entrySet()) {
            sb.append("Category: ").append(entry.getKey()).append("\n");
            for (RuleResult result : entry.getValue()) {
                sb.append("  - ").append(result.getDescription()).append("\n");
            }
        }
        return sb.toString();
    }

    private ZodiacSign getZodiacSign(int signNumber) {
        return ZodiacSign.values()[signNumber - 1];
    }

    private Planet getPlanet(String planetAbbreviation) {
        String planetName = planetAbbreviation.replaceAll("[^a-zA-Z]", "").toUpperCase();
        if (planetName.length() > 2) { // Handle cases like 'SUN'
        	return Planet.valueOf(planetName);
        }
        switch (planetName) {
            case "SU": return Planet.SUN;
            case "MO": return Planet.MOON;
            case "MA": return Planet.MARS;
            case "ME": return Planet.MERCURY;
            case "JU": return Planet.JUPITER;
            case "VE": return Planet.VENUS;
            case "SA": return Planet.SATURN;
            case "RA": return Planet.RAHU;
            case "KE": return Planet.KETU;
            default: throw new IllegalArgumentException("Unknown planet abbreviation: " + planetAbbreviation);
        }
    }

    private boolean isRetrograde(String planetString) {
        return planetString.contains("(");
    }

    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }

}
