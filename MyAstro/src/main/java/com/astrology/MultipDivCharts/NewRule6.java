package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.RuleResult;
import com.astrology.RuleEngine.ZodiacSign;

import java.util.ArrayList;
import java.util.List;

public class NewRule6 implements AstrologyRule {

    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d1 = completeChart.getChart(DivisionalChart.D1);
        if (d1 == null) {
            return null;
        }

        List<String> predictions = new ArrayList<>();
        String ruleName = "NAVAMSHA (D9 CHART)";
        String reference = "https://astrology-endevs.blogspot.com/2019/02/navamsha-d9-chart.html";

        // Check if 7th house sign is Leo
        ZodiacSign seventhHouseSign = d1.getZodiacSignForHouse(7);
        if (seventhHouseSign == ZodiacSign.LEO) {
            predictions.add("7th House Sign is Leo - The person will like a partner who looks like him/herself.");
        }

        // Check if Sun is in the 7th house
        if (d1.isPlanetInHouse(Planet.SUN, 7)) {
            predictions.add("SUN is at 7th House - The person will like a partner who looks like him/herself.");
        }

        if (predictions.isEmpty()) {
            return null;
        }

        String combinedDescription = String.join("\n", predictions);
        List<String> categories = new ArrayList<>();
        categories.add("NAVAMSHA (D9 CHART)");

        return new RuleResult(combinedDescription, 1.0, categories, ruleName, reference);
    }
}
