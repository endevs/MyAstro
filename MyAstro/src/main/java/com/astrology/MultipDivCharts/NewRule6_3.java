package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.RuleResult;
import com.astrology.RuleEngine.ZodiacSign;

import java.util.ArrayList;
import java.util.List;

public class NewRule6_3 implements AstrologyRule {

    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d1 = completeChart.getChart(DivisionalChart.D1);
        DivisionalChartData d9 = completeChart.getChart(DivisionalChart.D9);

        if (d1 == null || d9 == null) {
            return null;
        }

        String ruleName = "NAVAMSHA (D9 CHART) - Lagna Lord and Skills";
        String reference = "https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing";
        List<String> predictions = new ArrayList<>();

        // D1/D9 lagna lord in Dustana
        Planet d1LagnaLord = d1.getLagnaLord();
        int d1LagnaLordHouse = d1.getHouseOfPlanet(d1LagnaLord);
        if (isDustana(d1LagnaLordHouse)) {
            predictions.add("D1 Lagna Lord (" + d1LagnaLord + ") is in a Dustana house (" + d1LagnaLordHouse + "). This can indicate poor thoughts and ill health.");
        }

        Planet d9LagnaLord = d9.getLagnaLord();
        int d9LagnaLordHouse = d9.getHouseOfPlanet(d9LagnaLord);
        if (isDustana(d9LagnaLordHouse)) {
            predictions.add("D9 Lagna Lord (" + d9LagnaLord + ") is in a Dustana house (" + d9LagnaLordHouse + "). This can show a person who does not want to marry or has problems in marriage.");
        }

        // D1 lagna lord in D9 Dustana/Trine
        int d1LagnaLordInD9House = d9.getHouseOfPlanet(d1LagnaLord);
        if (isDustana(d1LagnaLordInD9House)) {
            predictions.add("D1 Lagna Lord (" + d1LagnaLord + ") is in a Dustana house (" + d1LagnaLordInD9House + ") in D9. This can indicate a skinny person, especially if in the 8th house.");
        } else if (isTrine(d1LagnaLordInD9House)) {
            predictions.add("D1 Lagna Lord (" + d1LagnaLord + ") is in a Trine house (" + d1LagnaLordInD9House + ") in D9. This can indicate a tendency to be fat.");
        }

        // D9 lagna lord in D1 Dustana
        int d9LagnaLordInD1House = d1.getHouseOfPlanet(d9LagnaLord);
        if (isDustana(d9LagnaLordInD1House)) {
            predictions.add("D9 Lagna Lord (" + d9LagnaLord + ") is in a Dustana house (" + d9LagnaLordInD1House + ") in D1. This can indicate long-term ill health and developmental issues.");
        }

        if (predictions.isEmpty()) {
            return null;
        }

        String combinedDescription = String.join("\n", predictions);
        List<String> categories = new ArrayList<>();
        categories.add("NAVAMSHA (D9 CHART)");

        return new RuleResult(combinedDescription, 1.0, categories, ruleName, reference);
    }

    private boolean isDustana(int house) {
        return house == 6 || house == 8 || house == 12;
    }

    private boolean isTrine(int house) {
        return house == 1 || house == 5 || house == 9;
    }
}
