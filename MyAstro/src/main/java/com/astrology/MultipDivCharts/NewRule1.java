package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.RuleResult;
import com.astrology.MultipDivCharts.DivisionalChartData;
import com.astrology.vo.House;
import com.astrology.vo.NatalChart;
import com.astrology.vo.Planet;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class NewRule1 {

    public List<RuleResult> evaluate(DivisionalChartData d1) {
        List<RuleResult> results = new ArrayList<>();
        NatalChart natalChart = NewUtils.createNatalChartFromDivisionalChartData(d1);

        for (Map.Entry<Integer, House> houseEntry : natalChart.getHouses().entrySet()) {
            House currentHouse = houseEntry.getValue();
            Planet houseLord = NewUtils.getHouseOwner(currentHouse.getHouseSign());

            int houseLordPlacedAt = 0;
            for (Map.Entry<Integer, House> innerHouseEntry : natalChart.getHouses().entrySet()) {
                if (innerHouseEntry.getValue().getPlanetsContainHouse().contains(houseLord)) {
                    houseLordPlacedAt = innerHouseEntry.getKey();
                    break;
                }
            }

            if (houseLordPlacedAt != 0) {
                int distance = NewUtils.calculateHouseToLordDistance(currentHouse.getHouseNumber(), houseLordPlacedAt);
                String predictionText = getPredictionText(distance, currentHouse.getHouseNumber(), houseLord.getPlanetName(), houseLordPlacedAt);
                if (predictionText != null) {
                    List<String> categories = new ArrayList<>();
                    categories.add("House Lord Placement");
                    results.add(new RuleResult(predictionText, 1.0, categories));
                }
            }
        }
        return results;
    }

    private String getPredictionText(int distance, int houseNumber, String houseLord, int houseLordPlacedAt) {
        String prediction = "Lord of house " + houseNumber + " (" + houseLord + ") is placed in house " + houseLordPlacedAt + ", which is " + distance + " houses away. ";

        switch (distance) {
            case 6:
            case 8:
            case 12:
                return prediction + "This is a Dustana placement, which can indicate challenges for the matters of house " + houseNumber + ".";
            case 3:
            case 11:
                return prediction + "This is an Upachaya placement, which can indicate growth and improvement over time for the matters of house " + houseNumber + ".";
            case 1:
            case 4:
            case 7:
            case 10:
                return prediction + "This is a Kendra placement, which provides strength and support to the matters of house " + houseNumber + ".";
            case 5:
            case 9:
                return prediction + "This is a Kona placement, which is auspicious and indicates blessings for the matters of house " + houseNumber + ".";
            case 2:
                return prediction + "This placement indicates that the matters of house " + houseNumber + " will sustain you.";
            default:
                return null;
        }
    }
}
