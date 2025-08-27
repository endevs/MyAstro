package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.RuleResult;

import java.util.ArrayList;
import java.util.List;

public class NewRule6_7 implements AstrologyRule {

    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d9 = completeChart.getChart(DivisionalChart.D9);

        if (d9 == null) {
            return null;
        }

        String ruleName = "D9 10th House - Wealth";
        String reference = "https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing";
        List<String> predictions = new ArrayList<>();

        Planet tenthLord = d9.getLordOfHouse(10);
        if (tenthLord != null) {
            if (d9.isPlanetExalted(tenthLord)) {
                predictions.add("The lord of the 10th house in D9 (" + tenthLord + ") is exalted, indicating great wealth.");
            }
            if (d9.isPlanetInOwnSign(tenthLord)) {
                predictions.add("The lord of the 10th house in D9 (" + tenthLord + ") is in its own sign, indicating great wealth.");
            }
        }

        List<Planet> planetsIn10th = d9.getPlanetsInHouse(10);
        for (Planet planet : planetsIn10th) {
            if (d9.isPlanetExalted(planet)) {
                predictions.add("Exalted planet (" + planet + ") in the 10th house of D9 indicates great wealth.");
            }
            if (d9.isPlanetInOwnSign(planet)) {
                predictions.add("Planet in its own sign (" + planet + ") in the 10th house of D9 indicates great wealth.");
            }
        }

        if (predictions.isEmpty()) {
            return null;
        }

        String combinedDescription = String.join("\n", predictions);
        List<String> categories = new ArrayList<>();
        categories.add("Wealth from D9");

        return new RuleResult(combinedDescription, 1.0, categories, ruleName, reference);
    }
}
