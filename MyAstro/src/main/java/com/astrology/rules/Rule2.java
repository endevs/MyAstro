package com.astrology.rules;

import java.util.LinkedHashMap;
import java.util.Map;

import com.astrology.utils.Utils;
import com.astrology.vo.House;
import com.astrology.vo.NatalChart;
import com.astrology.vo.Planet;
import com.astrology.vo.Prediction;

public class Rule2 {
	/**
	 * Secrets of Badhak Planets (How to use them)
	 * @param natalChart
	 */
	public Prediction execute(NatalChart natalChart) {
		System.out.println(":: Rule2 :: execute :: Start");
		StringBuffer sbResult=new StringBuffer();
		sbResult.append("12th Lord in Badhakesh or Badhakesh in 12th housed");
		Prediction prediction = new Prediction();
		prediction.setRuleName("Secrets of Badhak Planets (How to use them)");
		prediction.setDescription(sbResult);
		prediction.setReference("https://astrology-endevs.blogspot.com/2018/07/secrets-of-badhak-planets-how-to-use.html");
		
			//Logic
			LinkedHashMap<String, Integer> badhakSign = new LinkedHashMap<String, Integer>();
			badhakSign.put("Movable", 11);
			badhakSign.put("Fixed", 9);
			badhakSign.put("Duel", 7);
			//Asd is which kind of sign Movable , Fixed , Duel
			//String kindOfAsd = Utils.getSignMovableFixedDuel(natalChart.getHouses().get(1));//Sending Fast ASD House
			Integer badhaHouseNumber = badhakSign.get(Utils.getSignMovableFixedDuel(natalChart.getHouses().get(1)));
			Planet badhakeshPlanet = natalChart.getHouses().get(badhaHouseNumber).getHouseOwner();
			Planet lord12th = natalChart.getHouses().get(12).getHouseOwner();
			StringBuffer sbString = new StringBuffer();
			sbString.append("Badha House Number = "+badhaHouseNumber);
			sbString.append(" Badhekesh Planet = "+badhakeshPlanet.getPlanetName());
			sbString.append(" Planet ruling the Badhak house");
			sbString.append(System.getProperty("line.separator"));
			
			if(natalChart.getHouses().get(12).getPlanetsContainHouse().contains(badhakeshPlanet)) {
				sbString.append("Badhakesh in 12th house - suppose to separation of spouse, good combination for others aspects like income, only problem in marriage.");
				sbString.append(System.getProperty("line.separator"));
			}
			if(natalChart.getHouses().get(badhaHouseNumber).getPlanetsContainHouse().contains(lord12th)) {
				sbString.append("12th Lord in Badhakesh - suppose to separation of spouse, good combination for others aspects like income, only problem in marriage.");
				sbString.append(System.getProperty("line.separator"));
			}
			prediction.setSingleLinePrediction(sbString);
		    
		LinkedHashMap<Integer, StringBuffer> predictionList = new LinkedHashMap<Integer, StringBuffer>();
		prediction.setPredictionResult(predictionList);
		
		//predictions.put(1, prediction);
		
		System.out.println(":: Rule2 :: execute :: End");
		return prediction;
	}//End of Execute
	
}
