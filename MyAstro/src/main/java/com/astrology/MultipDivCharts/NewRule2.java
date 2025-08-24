package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.RuleResult;
import com.astrology.vo.House;
import com.astrology.vo.NatalChart;
import com.astrology.vo.Planet;
import com.astrology.vo.Sign;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NewRule2 implements AstrologyRule {

    private final String ruleName = "Secrets of Badhak Planets (How to use them)";
    private final String reference = "https://astrology-endevs.blogspot.com/2018/07/secrets-of-badhak-planets-how-to-use.html";

    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d1 = completeChart.getChart(DivisionalChart.D1);
        if (d1 == null) {
            return null;
        }

        List<RuleResult> results = evaluateD1(d1);

        if (results.isEmpty()) {
            return null;
        }

        if (results.size() == 1) {
            return results.get(0);
        } else {
            StringBuilder combinedDescription = new StringBuilder();
            for (RuleResult result : results) {
                combinedDescription.append(result.getDescription()).append("\n");
            }
            return new RuleResult(combinedDescription.toString(), 1.0, getCategories(), ruleName, reference);
        }
    }

    public List<RuleResult> evaluateD1(DivisionalChartData d1) {
        List<RuleResult> results = new ArrayList<>();
        NatalChart natalChart = NewUtils.createNatalChartFromDivisionalChartData(d1);

        // Logic from Rule2.java
        LinkedHashMap<String, Integer> badhakSign = new LinkedHashMap<String, Integer>();
        badhakSign.put("Movable", 11);
        badhakSign.put("Fixed", 9);
        badhakSign.put("Duel", 7);

        House ascendantHouse = natalChart.getHouses().get(1);
        String ascendantSignType = ascendantHouse.getHouseSign().getSignMovableFixedDuel();
        Integer badhaHouseNumber = badhakSign.get(ascendantSignType);

        if (badhaHouseNumber != null) {
            Planet badhakeshPlanet = NewUtils.getHouseOwner(natalChart.getHouses().get(badhaHouseNumber).getHouseSign());
            Planet lord12th = NewUtils.getHouseOwner(natalChart.getHouses().get(12).getHouseSign());

            String predictionText = "Badha House Number = " + badhaHouseNumber + ", Badhekesh Planet = " + badhakeshPlanet.getPlanetName() + ". ";

            if (natalChart.getHouses().get(12).getPlanetsContainHouse().contains(badhakeshPlanet)) {
                String prediction = predictionText + "Badhakesh in 12th house - suppose to separation of spouse, good combination for others aspects like income, only problem in marriage.";
                results.add(new RuleResult(prediction, 1.0, getCategories(), ruleName, reference));
            }

            if (natalChart.getHouses().get(badhaHouseNumber).getPlanetsContainHouse().contains(lord12th)) {
                String prediction = predictionText + "12th Lord in Badhakesh - suppose to separation of spouse, good combination for others aspects like income, only problem in marriage.";
                results.add(new RuleResult(prediction, 1.0, getCategories(), ruleName, reference));
            }
        }

        return results;
    }

    private List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Badhak Planets");
        return categories;
    }
}