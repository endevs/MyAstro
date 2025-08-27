package com.astrology.MultipDivCharts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        System.out.println("Processing chart for: " + chartData.getName());
        System.out.println("D1 House Positions: " + java.util.Arrays.toString(chartData.getHousePositions()));
        System.out.println("D9 House Positions: " + java.util.Arrays.toString(chartData.getD9HousePositions()));

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
                                    // Using dummy values for degrees and Nakshatra as they are not in the CSV
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

        if (ChartValidator.validate(d1)) {
            try {
                String personName = chartData.getName();
                File outputDir = new File("target/generated-charts");
                outputDir.mkdirs();
                String imagePath = new File(outputDir, personName.replace(" ", "_") + "_chart.png").getAbsolutePath();
                NorthIndianChartImageGenerator.generateChartImage(d1, completeChart.getChart(DivisionalChart.D9), imagePath, personName, chartData.getDob(), chartData.getBirthTime(), chartData.getBirthPlace());

                AdvancedRuleEngine engine = new AdvancedRuleEngine();
                Map<String, List<RuleResult>> results = engine.evaluateCompleteChart(completeChart, DivisionalChart.D1);

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

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText("Natal Chart for: " + personName);
            contentStream.endText();

            PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
            contentStream.drawImage(pdImage, 50, 450, pdImage.getWidth() / 2, pdImage.getHeight() / 2);

            float yPosition = 400;
            float margin = 50;
            float width = page.getMediaBox().getWidth() - 2 * margin;
            float lineHeight = 15; // Approximate line height

            for (Map.Entry<String, List<RuleResult>> entry : results.entrySet()) {
                // Check if there's enough space for the category header
                if (yPosition < margin + 20) { // 20 is arbitrary space for header
                    contentStream.close();
                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = page.getMediaBox().getHeight() - margin; // Reset yPosition for new page
                }

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("=== " + entry.getKey().toUpperCase() + " ===");
                contentStream.endText();
                yPosition -= 20;

                for (RuleResult result : entry.getValue()) {
                    // Print Rule Name and Reference if available
                    if (result.getRuleName() != null && !result.getRuleName().isEmpty()) {
                        contentStream.beginText();
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                        contentStream.newLineAtOffset(margin, yPosition);
                        contentStream.showText("Rule: " + result.getRuleName());
                        contentStream.endText();
                        yPosition -= 12;
                    }
                    if (result.getReference() != null && !result.getReference().isEmpty()) {
                        contentStream.beginText();
                        contentStream.setFont(PDType1Font.HELVETICA, 8);
                        contentStream.newLineAtOffset(margin, yPosition);
                        contentStream.showText("Reference: " + result.getReference());
                        contentStream.endText();
                        yPosition -= 10;
                    }

                    String[] descriptionLines = result.getDescription().split("\\n");
                    for (String line : descriptionLines) {
                        String text = String.format("- %s (Confidence: %.0f%%)", line, result.getConfidence() * 100);
                        
                        // Estimate lines needed for this result
                        float textHeight = (float) Math.ceil(PDType1Font.HELVETICA.getStringWidth(text) / 1000 * 10 / width) * lineHeight;

                        if (yPosition < margin + textHeight) {
                            contentStream.close();
                            page = new PDPage();
                            document.addPage(page);
                            contentStream = new PDPageContentStream(document, page);
                            yPosition = page.getMediaBox().getHeight() - margin;
                        }

                        yPosition = writeWrappedText(contentStream, text, PDType1Font.HELVETICA, 10, margin, yPosition, width);
                        yPosition -= 5; // Add some space between results
                    }
                }
                yPosition -= 10; // Add some space between categories
            }
            contentStream.close(); // Close the last content stream

            File outputDir = new File("target/generated-charts");
            outputDir.mkdirs();
            String pdfPath = new File(outputDir, personName.replace(" ", "_") + "_chart.pdf").getAbsolutePath();
            document.save(pdfPath);
            System.out.println("PDF generated at: " + new File(pdfPath).getAbsolutePath());
        }
    }

    private static float writeWrappedText(PDPageContentStream contentStream, String text, PDType1Font font, float fontSize, float x, float y, float maxWidth) throws IOException {
        float yPosition = y;
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            float wordWidth = font.getStringWidth(word) / 1000 * fontSize;
            float lineWidth = font.getStringWidth(line.toString()) / 1000 * fontSize;

            if (lineWidth + wordWidth > maxWidth) {
                lines.add(line.toString());
                line = new StringBuilder(word + " ");
            } else {
                line.append(word + " ");
            }
        }
        lines.add(line.toString());

        for (String l : lines) {
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.newLineAtOffset(x, yPosition);
            contentStream.showText(l);
            contentStream.endText();
            yPosition -= 15;
        }
        return yPosition;
    }

    private static void printResultsToConsole(String personName, Map<String, List<RuleResult>> results) {
        System.out.println("\n=== Astrological Report for " + personName + " ===");
        results.forEach((category, ruleResults) -> {
            System.out.println("\n=== " + category.toUpperCase() + " ===");
            ruleResults.forEach(result ->
                System.out.printf("- %s (Confidence: %.0f%%)\\n",
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