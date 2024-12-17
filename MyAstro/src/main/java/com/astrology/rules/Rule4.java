package com.astrology.rules;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.astrology.utils.Utils;
import com.astrology.vo.House;
import com.astrology.vo.NatalChart;
import com.astrology.vo.Planet;
import com.astrology.vo.Prediction;

public class Rule4 {
	/**
	 * Which planet placed at which house
	 * @param natalChart
	 * @return
	 */
	public Prediction execute(NatalChart natalChart) {
		//@1st@ House Owner @SAT@ Places at House @9th@ if(9th House Planet Count == 1) { Alone }else{loop all planets}
		//with @7th@ House Lord , 
		//@Ven@ is @Debilated / Exalted / Friends Sign@ 
		Prediction prediction = new Prediction();
		prediction.setRuleName("House Owner placed at Which House");
		prediction.setReference("Not Available");
		LinkedHashMap<Integer,StringBuffer> predictionResult = new LinkedHashMap<Integer,StringBuffer>();
		for(Map.Entry house:natalChart.getHouses().entrySet()){
			StringBuffer sbString = new StringBuffer();
			House currentHhouse = (House) house.getValue();//3
			//Find Current House Owner
			Planet currentHouseOwnerPlanet = currentHhouse.getHouseOwner();
			sbString.append(currentHhouse.getHouseNumber()); //3
			sbString.append(" House Owner ");
			sbString.append(currentHouseOwnerPlanet.getPlanetName());//Mars
			sbString.append(" Placed at House ");
			//Find House Owner Placed at Which house
			House houseOwnerPlacedAtHouse = Utils.getHouseWherePlanetPlaced(natalChart,currentHouseOwnerPlanet);//3
			sbString.append(houseOwnerPlacedAtHouse.getHouseNumber());
			//Check if Any other Planets placed at This House
			if(natalChart.getHouses().get(houseOwnerPlacedAtHouse.getHouseNumber()).getPlanetsContainHouse().size()>1) {
				sbString.append(" With ");
				for (Planet planets : natalChart.getHouses().get(houseOwnerPlacedAtHouse.getHouseNumber()).getPlanetsContainHouse()) {
					if(!planets.getPlanetName().equalsIgnoreCase(currentHouseOwnerPlanet.getPlanetName())) {
						//List<House>  houseOwnerPlacedAtHouse1 = Utils.getThisPlanetOwnerOfWhichHouses(natalChart,planets);
						List<House>  planetHouseOwner = planets.getDefaultHouseOwner();
						for (House house2 : planetHouseOwner) {
							House houseAsSign = Utils.getHousebySign(house2.getHouseSign(),natalChart);
							sbString.append(houseAsSign.getHouseNumber());
							sbString.append(" House Lord ");
							sbString.append(planets.getPlanetName()+" ");   
						}
					}
				}
			}else {
				sbString.append(" Alone ");
			}
			predictionResult.put(currentHhouse.getHouseNumber(), sbString);
		}
		prediction.setPredictionResult(predictionResult);
		return prediction;
	}
}
