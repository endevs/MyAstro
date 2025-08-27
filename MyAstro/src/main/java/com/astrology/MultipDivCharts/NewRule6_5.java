package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.RuleResult;
import com.astrology.RuleEngine.ZodiacSign;

import java.util.ArrayList;
import java.util.List;

public class NewRule6_5 implements AstrologyRule {

    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d1 = completeChart.getChart(DivisionalChart.D1);
        DivisionalChartData d9 = completeChart.getChart(DivisionalChart.D9);

        if (d1 == null || d9 == null) {
            return null;
        }

        String ruleName = "Association of Planets - Why this planet is in your life?";
        String reference = "https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing";
        List<String> predictions = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            Planet houseLord = d1.getLordOfHouse(i);
            if (houseLord != null) {
                ZodiacSign signInD9 = d9.getSignOfPlanet(houseLord);
                if (signInD9 != null) {
                    int houseInD1 = d1.getHouseOfSign(signInD9);
                    predictions.add("The lord of house " + i + " in D1 (" + houseLord + ") is in " + signInD9 + " in D9. This sign corresponds to house " + houseInD1 + " in D1, revealing the association.");
                }
            }
        }

        if (predictions.isEmpty()) {
            return null;
        }

        String combinedDescription = String.join("\n", predictions);
        List<String> categories = new ArrayList<>();
        categories.add("Association of Planets");

        return new RuleResult(combinedDescription, 1.0, categories, ruleName, reference);
    }
}
