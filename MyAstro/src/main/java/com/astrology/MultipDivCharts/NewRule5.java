package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.RuleResult;
import com.astrology.vo.House;
import com.astrology.vo.NatalChart;
import com.astrology.vo.Planet;
import com.astrology.vo.Sign;
import com.astrology.RuleEngine.ZodiacSign;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NewRule5 implements AstrologyRule {

    private final String ruleName = "Secrets of Navansh Part 1-Rasi Tulya Navansh";
    private final String reference = "https://astrology-endevs.blogspot.com/2019/06/secrets-of-navansh-part-1-rasi-tulya.html";

    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d1 = completeChart.getChart(DivisionalChart.D1);
        DivisionalChartData d9 = completeChart.getChart(DivisionalChart.D9);

        if (d1 == null || d9 == null) {
            return null;
        }

        List<String> predictions = new ArrayList<>();

        // Step 1: Find D1 Lagna Sign
        ZodiacSign d1LagnaSign = d1.getAscendant();

        // Step 2: Step 1 Lagna Sign Make D9 as Lagna and Calculate 6,8,12
        NatalChart d9NatalChart = NewUtils.createNatalChartFromDivisionalChartData(d9);
        LinkedHashMap<Integer, House> housesAt6812AtD9Chart = NewUtils.getHousesFromThisSign(NewUtils.convertZodiacSignToSign(d1LagnaSign), d9NatalChart, new Integer[]{6, 8, 12});

        for (Map.Entry<Integer, House> entry : housesAt6812AtD9Chart.entrySet()) {
            int houseNumber = entry.getKey();
            House currentHouse = entry.getValue();

            if (!currentHouse.getPlanetsContainHouse().isEmpty()) {
                String housePrediction = getHousePrediction(houseNumber);
                for (Planet planet : currentHouse.getPlanetsContainHouse()) {
                    String planetPrediction = getPlanetPrediction(planet.getPlanetName(), houseNumber);
                    if (planetPrediction != null) {
                        predictions.add(housePrediction + " " + planetPrediction);
                    }
                }
            }
        }

        if (predictions.isEmpty()) {
            return null;
        }

        String combinedDescription = String.join("\n", predictions);
        List<String> categories = new ArrayList<>();
        categories.add("Rasi Tulya Navansh");

        return new RuleResult(combinedDescription, 1.0, categories, ruleName, reference);
    }

    private String getHousePrediction(int houseNumber) {
        switch (houseNumber) {
            case 6:
                return "In the 6th house, it can give diseases, troubles, and enemies, but these problems are often solvable.";
            case 8:
                return "In the 8th house, it can give unsolvable problems and great transformation in that relation.";
            case 12:
                return "In the 12th house, losses through that significations comes in.";
            default:
                return "";
        }
    }

    private String getPlanetPrediction(String planetName, int houseNumber) {
        switch (planetName.toUpperCase()) {
            case "VENUS":
                return "Venus in house " + houseNumber + " indicates problems in marriage, spouse, DNA, health, and married life.";
            case "JUPITER":
                return "Jupiter in house " + houseNumber + " indicates missing kids, education, wisdom, and happiness of life.";
            case "MARS":
                return "Mars in house " + houseNumber + " indicates problems with brothers, property, friends, and courage.";
            case "MERCURY":
                return "Mercury in house " + houseNumber + " indicates problems in childhood, young kids, speech, and communication ability.";
            case "MOON":
                return "Moon in house " + houseNumber + " indicates problems with mother and mental peace.";
            case "SATURN":
                return "Saturn in house " + houseNumber + " indicates long-term illness, miseries in life, and trouble in work.";
            default:
                return null;
        }
    }
}
