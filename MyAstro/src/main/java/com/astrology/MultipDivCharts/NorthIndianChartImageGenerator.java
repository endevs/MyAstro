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

    public static void generateChartImage(DivisionalChartData chartData, String filePath, String name, String dob, String tob, String pob) throws IOException {
        int width = 600;
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

        // Draw chart outline
        g2d.setColor(Color.BLACK);
        g2d.drawLine(50, 400, 300, 150);
        g2d.drawLine(300, 150, 550, 400);
        g2d.drawLine(550, 400, 300, 650);
        g2d.drawLine(300, 650, 50, 400);
        g2d.drawLine(50, 150, 550, 650);
        g2d.drawLine(50, 650, 550, 150);

        // Get houses
        ZodiacSign[] houses = new ZodiacSign[12];
        ZodiacSign ascendant = chartData.getAscendant();
        houses[0] = ascendant;
        for (int i = 1; i < 12; i++) {
            houses[i] = ZodiacSign.values()[(ascendant.ordinal() + i) % 12];
        }

        // Set font
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));

        // Draw houses and planets
        drawHouse(g2d, houses[0], chartData, 275, 200);
        drawHouse(g2d, houses[1], chartData, 150, 200);
        drawHouse(g2d, houses[2], chartData, 75, 275);
        drawHouse(g2d, houses[3], chartData, 75, 400);
        drawHouse(g2d, houses[4], chartData, 75, 525);
        drawHouse(g2d, houses[5], chartData, 150, 600);
        drawHouse(g2d, houses[6], chartData, 275, 600);
        drawHouse(g2d, houses[7], chartData, 400, 600);
        drawHouse(g2d, houses[8], chartData, 475, 525);
        drawHouse(g2d, houses[9], chartData, 475, 400);
        drawHouse(g2d, houses[10], chartData, 475, 275);
        drawHouse(g2d, houses[11], chartData, 400, 200);

        g2d.dispose();

        // Save as PNG
        File file = new File(filePath);
        ImageIO.write(bufferedImage, "png", file);
    }

    private static void drawHouse(Graphics2D g2d, ZodiacSign sign, DivisionalChartData chartData, int x, int y) {
        List<String> planets = ChartUtils.getPlanetsInSign(sign, chartData.getPlanetPositions());
        String houseString =  sign.name().substring(0, 2) + " [" + (sign.ordinal() + 1) + "] ";
        if (!planets.isEmpty()) {
            houseString += " " + String.join(",", planets);
        }
        g2d.drawString(houseString, x, y);
    }
}