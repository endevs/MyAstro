package com.astrology.MultipDivCharts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
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
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString(title, startX + 200, startY);

        // Draw chart outline
        g2d.setColor(Color.BLACK);
        g2d.drawRect(startX, startY + 50, 500, 500);
        g2d.drawLine(startX, startY + 50, startX + 500, startY + 550);
        g2d.drawLine(startX, startY + 550, startX + 500, startY + 50);
        g2d.drawLine(startX, startY + 300, startX + 250, startY + 50);
        g2d.drawLine(startX + 250, startY + 50, startX + 500, startY + 300);
        g2d.drawLine(startX + 500, startY + 300, startX + 250, startY + 550);
        g2d.drawLine(startX + 250, startY + 550, startX, startY + 300);

        // Get houses
        ZodiacSign[] houses = new ZodiacSign[12];
        ZodiacSign ascendant = chartData.getAscendant();
        houses[0] = ascendant;
        for (int i = 1; i < 12; i++) {
            houses[i] = ZodiacSign.values()[(ascendant.ordinal() + i) % 12];
        }

        // Draw houses and planets
        drawHouse(g2d, houses[0], chartData, startX + 225, startY + 100, true);
        drawHouse(g2d, houses[1], chartData, startX + 100, startY + 100, false);
        drawHouse(g2d, houses[2], chartData, startX + 25, startY + 175, false);
        drawHouse(g2d, houses[3], chartData, startX + 25, startY + 300, false);
        drawHouse(g2d, houses[4], chartData, startX + 25, startY + 425, false);
        drawHouse(g2d, houses[5], chartData, startX + 100, startY + 500, false);
        drawHouse(g2d, houses[6], chartData, startX + 225, startY + 500, false);
        drawHouse(g2d, houses[7], chartData, startX + 350, startY + 500, false);
        drawHouse(g2d, houses[8], chartData, startX + 425, startY + 425, false);
        drawHouse(g2d, houses[9], chartData, startX + 425, startY + 300, false);
        drawHouse(g2d, houses[10], chartData, startX + 425, startY + 175, false);
        drawHouse(g2d, houses[11], chartData, startX + 350, startY + 100, false);
    }

    private static void drawHouse(Graphics2D g2d, ZodiacSign sign, DivisionalChartData chartData, int x, int y, boolean isAscendant) {
        List<String> planets = ChartUtils.getPlanetsInSign(sign, chartData.getPlanetPositions());
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        String signString = "[" + (sign.ordinal() + 1) + "] " + sign.name().substring(0, 2);
        g2d.drawString(signString, x - 20, y - 20);

        if (!planets.isEmpty()) {
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            String planetsString = String.join(",", planets);
            g2d.drawString(planetsString, x, y);
        }
        if (isAscendant) {
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.drawString("As", x + 20, y - 20);
        }
    }
}
