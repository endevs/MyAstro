package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.RuleResult;

import java.util.ArrayList;
import java.util.List;

public class NewRule6_1 implements AstrologyRule {

    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d1 = completeChart.getChart(DivisionalChart.D1);
        DivisionalChartData d9 = completeChart.getChart(DivisionalChart.D9);

        if (d1 == null || d9 == null) {
            return null;
        }

        String ruleName = "NAVAMSHA (D9 CHART) - Sexuality";
        String reference = "https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing";

        boolean sunInD1_7th = d1.isPlanetInHouse(Planet.SUN, 7);
        boolean satInD9_7th = d9.isPlanetInHouse(Planet.SATURN, 7);
        boolean merInD9_7th = d9.isPlanetInHouse(Planet.MERCURY, 7);

        if (sunInD1_7th && satInD9_7th && merInD9_7th) {
            String description = "Chances of Homo sexuality.\nReason - D1 - 7th House Sun and D9 7th House Sat and Mer";
            List<String> categories = new ArrayList<>();
            categories.add("NAVAMSHA (D9 CHART)");
            return new RuleResult(description, 1.0, categories, ruleName, reference);
        }

        return null;
    }
}
