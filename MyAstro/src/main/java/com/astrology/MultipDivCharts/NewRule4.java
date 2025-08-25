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

public class NewRule4 implements AstrologyRule {

    private final String ruleName = "House Owner placed at Which House";
    private final String reference = "Not Available";

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
            categories.add("House Placement Details");
            return new RuleResult(combinedDescription.toString(), 1.0, categories, ruleName, reference);
        }
    }

    public List<RuleResult> evaluateD1(DivisionalChartData d1) {
        List<RuleResult> results = new ArrayList<>();
        NatalChart natalChart = NewUtils.createNatalChartFromDivisionalChartData(d1);

        for (Map.Entry<Integer, House> houseEntry : natalChart.getHouses().entrySet()) {
            StringBuilder sbString = new StringBuilder();
            House currentHhouse = houseEntry.getValue();
            
            // Find Current House Owner
            Planet currentHouseOwnerPlanet = currentHhouse.getHouseOwner();
            sbString.append(currentHhouse.getHouseNumber()); 
            sbString.append(" House Owner ");
            sbString.append(currentHouseOwnerPlanet.getPlanetName());
            sbString.append(" Placed at House ");
            
            // Find House Owner Placed at Which house
            House houseOwnerPlacedAtHouse = NewUtils.getHouseWherePlanetPlaced(natalChart, currentHouseOwnerPlanet);
            sbString.append(houseOwnerPlacedAtHouse.getHouseNumber());
            
            // Check if Any other Planets placed at This House
            if(natalChart.getHouses().get(houseOwnerPlacedAtHouse.getHouseNumber()).getPlanetsContainHouse().size() > 1) {
                sbString.append(" With ");
                for (Planet planets : natalChart.getHouses().get(houseOwnerPlacedAtHouse.getHouseNumber()).getPlanetsContainHouse()) {
                    if(!planets.getPlanetName().equalsIgnoreCase(currentHouseOwnerPlanet.getPlanetName())) {
                        List<House>  planetHouseOwner = planets.getDefaultHouseOwner();
                        // Commenting out the problematic lines for debugging
                        // for (House house2 : planetHouseOwner) {
                        //     House houseAsSign = NewUtils.getHousebySign(house2.getHouseSign(), natalChart);
                        //     sbString.append(houseAsSign.getHouseNumber());
                        //     sbString.append(" House Lord ");
                        //     sbString.append(planets.getPlanetName()).append(" ");   
                        // }
                    }
                }
            } else {
                sbString.append(" Alone ");
            }
            
            List<String> categories = new ArrayList<>();
            categories.add("House Placement Details");
            results.add(new RuleResult(sbString.toString(), 1.0, categories, ruleName, reference));
        }
        return results;
    }
}
