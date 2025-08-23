package com.astrology.MultipDivCharts;

import java.util.List;
import com.astrology.RuleEngine.ZodiacSign;

public class NorthIndianChartPrinter {

    public static void printChart(DivisionalChartData chartData) {
        ZodiacSign[] houses = new ZodiacSign[12];
        ZodiacSign ascendant = chartData.getAscendant();
        houses[0] = ascendant;
        for (int i = 1; i < 12; i++) {
            houses[i] = ZodiacSign.values()[(ascendant.ordinal() + i) % 12];
        }

        String[] chartHouses = new String[12];
        for (int i = 0; i < 12; i++) {
            chartHouses[i] = formatHouse(houses[i], chartData);
        }

        System.out.println("North Indian Chart: " + chartData.getType().getName());
        System.out.println("┌─────────────────┬─────────────────┬─────────────────┐");
        System.out.println("│ " + chartHouses[0] + " │ " + chartHouses[1] + " │ " + chartHouses[2] + " │");
        System.out.println("├─────────────────┼─────────────────┼─────────────────┤");
        System.out.println("│ " + chartHouses[11] + " │       \\       /       │ " + chartHouses[3] + " │");
        System.out.println("│                 │        \\     /        │                 │");
        System.out.println("│                 │         \\   /         │                 │");
        System.out.println("│ " + chartHouses[10] + " │          X          │ " + chartHouses[4] + " │");
        System.out.println("│                 │         /   \\         │                 │");
        System.out.println("│                 │        /     \\        │                 │");
        System.out.println("│ " + chartHouses[9] + " │       /       \\       │ " + chartHouses[5] + " │");
        System.out.println("├─────────────────┼─────────────────┼─────────────────┤");
        System.out.println("│ " + chartHouses[8] + " │ " + chartHouses[7] + " │ " + chartHouses[6] + " │");
        System.out.println("└─────────────────┴─────────────────┴─────────────────┘");
    }

    private static String formatHouse(ZodiacSign sign, DivisionalChartData chartData) {
        List<String> planets = ChartUtils.getPlanetsInSign(sign, chartData.getPlanetPositions());
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%s]", sign.name().substring(0, 2)));
        if (!planets.isEmpty()) {
            sb.append(" ").append(String.join(",", planets));
        }
        String houseString = sb.toString();
        return String.format("%-15s", houseString);
    }
}
