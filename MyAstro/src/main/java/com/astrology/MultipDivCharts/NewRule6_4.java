package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.RuleResult;

import java.util.ArrayList;
import java.util.List;

public class NewRule6_4 implements AstrologyRule {

    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d1 = completeChart.getChart(DivisionalChart.D1);
        DivisionalChartData d9 = completeChart.getChart(DivisionalChart.D9);

        if (d1 == null || d9 == null) {
            return null;
        }

        String ruleName = "Kendra D9 - 4th House of D9";
        String reference = "https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing";
        List<String> predictions = new ArrayList<>();

        // Planets in 4th house of D9
        List<Planet> planetsInD9_4th = d9.getPlanetsInHouse(4);
        if (!planetsInD9_4th.isEmpty()) {
            predictions.add("Planets in the 4th house of D9 can give wealth, but also punishment for mistakes. It is a sensitive house that demands innocence.");
            for (Planet planet : planetsInD9_4th) {
                predictions.add("Planet in 4th house of D9: " + planet + ". Benefics are good, malefics can bring emotional trauma.");
            }
        }

        // Mars-Venus conjunction in 3, 7, 11 of D1 or D9
        int[] housesToCheck = {3, 7, 11};
        for (int house : housesToCheck) {
            if (d1.arePlanetsInHouse(new Planet[]{Planet.MARS, Planet.VENUS}, house)) {
                predictions.add("Mars-Venus conjunction in house " + house + " of D1 can indicate high sexuality and many affairs.");
            }
            if (d9.arePlanetsInHouse(new Planet[]{Planet.MARS, Planet.VENUS}, house)) {
                predictions.add("Mars-Venus conjunction in house " + house + " of D9 can indicate high sexuality and many affairs.");
            }
        }

        if (predictions.isEmpty()) {
            return null;
        }

        String combinedDescription = String.join("\n", predictions);
        List<String> categories = new ArrayList<>();
        categories.add("Kendra D9 - 4th House of D9");

        return new RuleResult(combinedDescription, 1.0, categories, ruleName, reference);
    }
}
