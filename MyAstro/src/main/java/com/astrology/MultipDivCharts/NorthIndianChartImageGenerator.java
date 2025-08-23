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

    public static void generateChartImage(DivisionalChartData chartData, String filePath) throws IOException {
        int width = 600;
        int height = 600;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // Fill background
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Draw chart outline
        g2d.setColor(Color.BLACK);
        g2d.drawLine(50, 300, 300, 50);
        g2d.drawLine(300, 50, 550, 300);
        g2d.drawLine(550, 300, 300, 550);
        g2d.drawLine(300, 550, 50, 300);
        g2d.drawLine(50, 50, 550, 550);
        g2d.drawLine(50, 550, 550, 50);

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
        drawHouse(g2d, houses[0], chartData, 275, 100);
        drawHouse(g2d, houses[1], chartData, 150, 100);
        drawHouse(g2d, houses[2], chartData, 75, 175);
        drawHouse(g2d, houses[3], chartData, 75, 300);
        drawHouse(g2d, houses[4], chartData, 75, 425);
        drawHouse(g2d, houses[5], chartData, 150, 500);
        drawHouse(g2d, houses[6], chartData, 275, 500);
        drawHouse(g2d, houses[7], chartData, 400, 500);
        drawHouse(g2d, houses[8], chartData, 475, 425);
        drawHouse(g2d, houses[9], chartData, 475, 300);
        drawHouse(g2d, houses[10], chartData, 475, 175);
        drawHouse(g2d, houses[11], chartData, 400, 100);

        g2d.dispose();

        // Save as PNG
        File file = new File(filePath);
        ImageIO.write(bufferedImage, "png", file);
    }

    private static void drawHouse(Graphics2D g2d, ZodiacSign sign, DivisionalChartData chartData, int x, int y) {
        List<String> planets = ChartUtils.getPlanetsInSign(sign, chartData.getPlanetPositions());
        String houseString = "[" + sign.name().substring(0, 2) + "]";
        if (!planets.isEmpty()) {
            houseString += " " + String.join(",", planets);
        }
        g2d.drawString(houseString, x, y);
    }
}
