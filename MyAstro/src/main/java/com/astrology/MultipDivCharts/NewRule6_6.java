package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.RuleResult;

import java.util.ArrayList;
import java.util.List;

public class NewRule6_6 implements AstrologyRule {

    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d1 = completeChart.getChart(DivisionalChart.D1);
        DivisionalChartData d9 = completeChart.getChart(DivisionalChart.D9);

        if (d1 == null) {
            return null;
        }

        String ruleName = "Moon in 12th House";
        String reference = "https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing";
        List<String> predictions = new ArrayList<>();

        if (d1.isPlanetInHouse(Planet.MOON, 12)) {
            predictions.add("Moon in the 12th house of D1 suggests a person who can touch people's hearts much more easily.");
        }

        if (d9 != null && d9.isPlanetInHouse(Planet.MOON, 12)) {
            predictions.add("Moon in the 12th house of D9 suggests a person who can touch people's hearts much more easily.");
        }

        if (predictions.isEmpty()) {
            return null;
        }

        String combinedDescription = String.join("\n", predictions);
        List<String> categories = new ArrayList<>();
        categories.add("Moon Placement");

        return new RuleResult(combinedDescription, 1.0, categories, ruleName, reference);
    }
}
