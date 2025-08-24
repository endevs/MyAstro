package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.RuleResult;
import com.astrology.vo.House;
import com.astrology.vo.NatalChart;
import com.astrology.vo.Planet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NewRule3 implements AstrologyRule {

    private final String ruleName = "UPAPADA LAGNA - spouse and partners";
    private final String reference = "https://astrology-endevs.blogspot.com/2018/11/upapada-lagna-spouse-and-partners.html";

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
            List<String> categories = new ArrayList<>();
            categories.add("Upapada Lagna");
            return new RuleResult(combinedDescription.toString(), 1.0, categories, ruleName, reference);
        }
    }

    public List<RuleResult> evaluateD1(DivisionalChartData d1) {
        List<RuleResult> results = new ArrayList<>();
        NatalChart natalChart = NewUtils.createNatalChartFromDivisionalChartData(d1);

        // Logic from Rule3.java
        Planet lagnaLord = NewUtils.getLagnaLord(natalChart);
        House planetPlacedHouse = NewUtils.getHouseWherePlanetPlaced(natalChart, lagnaLord);
        Integer house12ToLagnaLordDistance = NewUtils.houseToHouseOwner(natalChart.getHouses().get(12).getHouseNumber(), planetPlacedHouse.getHouseNumber());
        Integer upapadaLagnaNumber = NewUtils.add1stParameterHouseto2ndParameterHouse(house12ToLagnaLordDistance, planetPlacedHouse);
        House upapadaLagnaHouse = natalChart.getHouses().get(upapadaLagnaNumber);

        LinkedHashMap<Integer, String> predictionResultList = new LinkedHashMap<>();
        predictionResultList.put(6, "6th house so arguments between the couple.");
        predictionResultList.put(8, "8th house then tempary separation or extermarital affiers or problem from sudden event.");
        predictionResultList.put(12, "12th house problem creates which will untimate serparation, if it is so badly aspaced then no marriage also possible");

        StringBuilder sbString = new StringBuilder();
        sbString.append("UPAPADA Lagna Lord is ==>>").append(upapadaLagnaHouse.getHouseOwner().getPlanetName()).append("\n");
        sbString.append("UPAPADA Lagna House Number ==>>").append(upapadaLagnaHouse.getHouseNumber()).append("\n");
        
        String specificPrediction = predictionResultList.get(upapadaLagnaHouse.getHouseNumber());
        if (specificPrediction != null) {
            sbString.append("Result shall be ==>>").append(specificPrediction);
        } else {
            sbString.append("Result shall be ==>> No specific prediction for this house.");
        }

        List<String> categories = new ArrayList<>();
        categories.add("Upapada Lagna");
        results.add(new RuleResult(sbString.toString(), 1.0, categories, ruleName, reference));

        return results;
    }
}