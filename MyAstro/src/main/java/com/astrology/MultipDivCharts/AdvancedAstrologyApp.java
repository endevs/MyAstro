package com.astrology.MultipDivCharts;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.astrology.RuleEngine.Nakshatra;
import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.RuleResult;
import com.astrology.RuleEngine.ZodiacSign;

public class AdvancedAstrologyApp {

    public static void main(String[] args) {
        try {
            List<ChartData> chartDataList = ExcelChartParser.parse("Charts.csv");

            for (ChartData chartData : chartDataList) {
                processChart(chartData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processChart(ChartData chartData) {
        CompleteChart completeChart = new CompleteChart();
        DivisionalChartData d1 = new DivisionalChartData(DivisionalChart.D1);

        // The first house position determines the ascendant
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
                // Using dummy values for degrees and Nakshatra as they are not in the CSV
                d1.addPlanetPosition(planet, new PlanetPosition(planet, houseNumber, sign, 0.0, Nakshatra.ASHWINI, isRetro));
            }
            houseNumber++;
        }

        completeChart.addDivisionalChart(DivisionalChart.D1, d1);

        if (ChartValidator.validate(d1)) {
            try {
                String personName = chartData.getName();
                String imagePath = "E:\\STUDY\\Astrology\\GeneratePDF\\"+personName.replace(" ", "_") + "_chart.png";
                NorthIndianChartImageGenerator.generateChartImage(d1, null, imagePath, personName, chartData.getDob(), chartData.getBirthTime(), chartData.getBirthPlace());
                //System.out.println("Chart image generated at: " + new File(imagePath).getAbsolutePath());

                AdvancedRuleEngine engine = new AdvancedRuleEngine();
                Map<String, List<RuleResult>> results = engine.evaluateCompleteChart(completeChart, DivisionalChart.D1);

                NewRule1 newRule1 = new NewRule1();
                List<RuleResult> rule1Results = newRule1.evaluate(d1);
                results.put("House Lord Placement", rule1Results);

                generatePdf(personName, imagePath, results, chartData);
                printResultsToConsole(personName, results);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void generatePdf(String personName, String imagePath, Map<String, List<RuleResult>> results, ChartData chartData) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("Natal Chart for: " + personName);
                contentStream.endText();

                PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
                contentStream.drawImage(pdImage, 50, 450, pdImage.getWidth() / 2, pdImage.getHeight() / 2);

                float yPosition = 400;
                for (Map.Entry<String, List<RuleResult>> entry : results.entrySet()) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    contentStream.newLineAtOffset(50, yPosition);
                    contentStream.showText("=== " + entry.getKey().toUpperCase() + " ===");
                    contentStream.endText();
                    yPosition -= 20;

                    for (RuleResult result : entry.getValue()) {
                        contentStream.beginText();
                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        contentStream.newLineAtOffset(50, yPosition);
                        contentStream.showText(String.format("- %s (Confidence: %.0f%%)", result.getDescription(), result.getConfidence() * 100));
                        contentStream.endText();
                        yPosition -= 15;
                    }
                    yPosition -= 10; // Add some space between categories
                }
            }

            String pdfPath = "E:\\STUDY\\Astrology\\GeneratePDF\\"+personName.replace(" ", "_") + "_chart.pdf";
            document.save(pdfPath);
            System.out.println("PDF generated at: " + new File(pdfPath).getAbsolutePath());
        }
    }

    private static void printResultsToConsole(String personName, Map<String, List<RuleResult>> results) {
        System.out.println("\n=== Astrological Report for " + personName + " ===");
        results.forEach((category, ruleResults) -> {
            System.out.println("\n=== " + category.toUpperCase() + " ===");
            ruleResults.forEach(result ->
                System.out.printf("- %s (Confidence: %.0f%%)\\%n",
                    result.getDescription(), result.getConfidence() * 100)
            );
        });
    }

    private static ZodiacSign getZodiacSign(int signNumber) {
        return ZodiacSign.values()[signNumber - 1];
    }

    private static Planet getPlanet(String planetAbbreviation) {
        String planetName = planetAbbreviation.replaceAll("[()]", "").toUpperCase();
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

    private static boolean isRetrograde(String planetString) {
        return planetString.contains("(");
    }
}
