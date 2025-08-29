package com.astrology.MultipDivCharts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.ZodiacSign;

public class NorthIndianChartImageGenerator {

    public static void generateChartImage(DivisionalChartData d1, DivisionalChartData d9, String filePath, String name, String dob, String tob, String pob) throws IOException {
        int width = 1200;
        int height = 700;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // Fill background
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Draw person's details
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Name: " + name, 50, 30);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString("Date of Birth: " + dob, 50, 50);
        g2d.drawString("Time of Birth: " + tob, 50, 70);
        g2d.drawString("Place of Birth: " + pob, 50, 90);

        // Draw D1 Chart
        drawChart(g2d, d1, "D1 Chart", 50, 120);

        // Draw D9 Chart
        if (d9 != null) {
            drawChart(g2d, d9, "D9 Chart", 650, 120);
        }

        g2d.dispose();

        // Save as PNG
        File file = new File(filePath);
        ImageIO.write(bufferedImage, "png", file);
    }

    private static void drawChart(Graphics2D g2d, DivisionalChartData chartData, String title, int startX, int startY) {
        int chartSize = 500;
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString(title, startX + (chartSize / 2) - (g2d.getFontMetrics().stringWidth(title) / 2), startY);

        // Draw chart outline
        g2d.setColor(Color.BLACK);
        g2d.drawRect(startX, startY + 20, chartSize, chartSize);
        g2d.drawLine(startX, startY + 20, startX + chartSize, startY + 20 + chartSize);
        g2d.drawLine(startX, startY + 20 + chartSize, startX + chartSize, startY + 20);
        g2d.drawLine(startX, startY + 20 + chartSize / 2, startX + chartSize / 2, startY + 20);
        g2d.drawLine(startX + chartSize / 2, startY + 20, startX + chartSize, startY + 20 + chartSize / 2);
        g2d.drawLine(startX + chartSize, startY + 20 + chartSize / 2, startX + chartSize / 2, startY + 20 + chartSize);
        g2d.drawLine(startX + chartSize / 2, startY + 20 + chartSize, startX, startY + 20 + chartSize / 2);

        // Get houses
        ZodiacSign[] houses = new ZodiacSign[12];
        ZodiacSign ascendant = chartData.getAscendant();
        if (ascendant == null) {
            g2d.drawString("Ascendant not found", startX + 150, startY + 300);
            return;
        }
        houses[0] = ascendant;
        for (int i = 1; i < 12; i++) {
            houses[i] = ZodiacSign.values()[(ascendant.ordinal() + i) % 12];
        }

        // Draw houses and planets
        drawHouse(g2d, houses[0], chartData, startX + 250, startY + 120, true);
        drawHouse(g2d, houses[1], chartData, startX + 125, startY + 120, false);
        drawHouse(g2d, houses[2], chartData, startX + 50, startY + 195, false);
        drawHouse(g2d, houses[3], chartData, startX + 50, startY + 320, false);
        drawHouse(g2d, houses[4], chartData, startX + 50, startY + 445, false);
        drawHouse(g2d, houses[5], chartData, startX + 125, startY + 520, false);
        drawHouse(g2d, houses[6], chartData, startX + 250, startY + 520, false);
        drawHouse(g2d, houses[7], chartData, startX + 375, startY + 520, false);
        drawHouse(g2d, houses[8], chartData, startX + 450, startY + 445, false);
        drawHouse(g2d, houses[9], chartData, startX + 450, startY + 320, false);
        drawHouse(g2d, houses[10], chartData, startX + 450, startY + 195, false);
        drawHouse(g2d, houses[11], chartData, startX + 375, startY + 120, false);
    }

    private static void drawHouse(Graphics2D g2d, ZodiacSign sign, DivisionalChartData chartData, int x, int y, boolean isAscendant) {
        List<String> planets = ChartUtils.getPlanetsInSign(sign, chartData.getPlanetPositions());
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        String signString = "[" + (sign.ordinal() + 1) + "] " + sign.name().substring(0, 2);
        g2d.drawString(signString, x - 20, y - 20);

        if (!planets.isEmpty()) {
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            int currentY = y;
            for (String planetAbbreviation : planets) { // Changed planetName to planetAbbreviation
                Planet planet = getPlanetFromAbbreviation(planetAbbreviation); // New helper method
                g2d.setColor(getPlanetColor(planet));
                // Center the planet text
                int textWidth = g2d.getFontMetrics().stringWidth(planetAbbreviation); // Use abbreviation for width
                g2d.drawString(planetAbbreviation, x - textWidth / 2, currentY);
                currentY += 15; // Move down for the next planet
            }
        }
        if (isAscendant) {
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.setColor(Color.RED); // Color for Ascendant
            g2d.drawString("As", x + 20, y - 20);
        }
    }

    private static Color getPlanetColor(Planet planet) {
        switch (planet) {
            case SUN: return Color.ORANGE;
            case MOON: return Color.BLUE;
            case MARS: return Color.RED;
            case MERCURY: return Color.GREEN;
            case JUPITER: return Color.MAGENTA;
            case VENUS: return Color.PINK;
            case SATURN: return Color.DARK_GRAY;
            case RAHU: return Color.CYAN;
            case KETU: return Color.LIGHT_GRAY;
            default: return Color.BLACK;
        }
    }

    // New helper method to convert abbreviation to Planet enum
    private static Planet getPlanetFromAbbreviation(String abbreviation) {
        String cleanAbbreviation = abbreviation.replaceAll("[^a-zA-Z]", "").toUpperCase(); // Remove non-alphabetic characters
        switch (cleanAbbreviation) {
            case "SU": return Planet.SUN;
            case "MO": return Planet.MOON;
            case "MA": return Planet.MARS;
            case "ME": return Planet.MERCURY;
            case "JU": return Planet.JUPITER;
            case "VE": return Planet.VENUS;
            case "SA": return Planet.SATURN;
            case "RA": return Planet.RAHU;
            case "KE": return Planet.KETU;
            default: throw new IllegalArgumentException("Unknown planet abbreviation: " + abbreviation); // Use original abbreviation in error message
        }
    }
}
