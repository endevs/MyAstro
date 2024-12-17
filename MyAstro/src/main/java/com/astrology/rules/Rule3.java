package com.astrology.rules;

import java.util.LinkedHashMap;

import com.astrology.utils.Utils;
import com.astrology.vo.House;
import com.astrology.vo.NatalChart;
import com.astrology.vo.Planet;
import com.astrology.vo.Prediction;

public class Rule3 {
	/**
	 * Link -- https://astrology-endevs.blogspot.com/2018/11/upapada-lagna-spouse-and-partners.html
	 * Rule Name -- UPAPADA LAGNA - spouse and partners
	 * @param natalChart
	 * @return
	 */
	public Prediction execute(NatalChart natalChart) {
		//====================================================================================== Analysis
		StringBuffer sbResult=new StringBuffer();
		
		sbResult.append("Step 1 - Find the Lagna Lord if Rahu found then considered as Sat as Lagna Lord \n");
		Planet lagnaLord = Utils.getLagnaLord(natalChart);
		
		sbResult.append("Step 2 - Found where Lagna Lord Placed / Planet Placed \n");
		House planetPlacedHouse = Utils.getHouseWherePlanetPlaced(natalChart,lagnaLord); 
		
		sbResult.append("Step 3 - House 12 to Lagna Lord Distance \n");
		Integer house12ToLagnaLordDistance = Utils.getHowFarThisPlanetPlacedFromHouse(planetPlacedHouse,natalChart.getHouses().get(12));
		
		sbResult.append("Step 4 - Result of (Step 3) add to Result (Step 2) \n");
		Integer upapadaLagnaNumber = Utils.add1stParameterHouseto2ndParameterHouse(house12ToLagnaLordDistance,planetPlacedHouse);
		
		sbResult.append("Step 5 - Result \n");
		House upapadaLagnaHouse = natalChart.getHouses().get(upapadaLagnaNumber);
		//System.out.println("======"+upapadaLagnaHouse.getHouseOwner().getPlanetName());
		
		//=================================================================================== Prepare Result Set 
		LinkedHashMap<Integer, String> predictionResultList = new LinkedHashMap<Integer, String>();
		predictionResultList.put(6, "6th house so arguments between the couple.");
		predictionResultList.put(8,"8th house then tempary separation or extermarital affiers or problem from sudden event.");
		predictionResultList.put(12, "12th house problem creates which will untimate serparation, if it is so badly aspaced then no marriage also possible");
		
		//=================================================================================== Send to Print
		//LinkedHashMap<Integer, Prediction> resultPrediction = new LinkedHashMap<Integer, Prediction>();
		Prediction resultPredictionObj = new Prediction();
		resultPredictionObj.setRuleName("UPAPADA LAGNA - spouse and partners");
		resultPredictionObj.setDescription(sbResult);
		resultPredictionObj.setReference("https://astrology-endevs.blogspot.com/2018/11/upapada-lagna-spouse-and-partners.html");
		StringBuffer sbString = new StringBuffer();
		sbString.append("UPAPADA Lagna Lord is ==>>"+upapadaLagnaHouse.getHouseOwner().getPlanetName());
			sbString.append(System.getProperty("line.separator"));
		sbString.append("UPAPADA Lagna House Number ==>>"+upapadaLagnaHouse.getHouseNumber());
			sbString.append(System.getProperty("line.separator"));
		sbString.append("Result shall be ==>>"+predictionResultList.get(upapadaLagnaHouse.getHouseNumber()));
		resultPredictionObj.setSingleLinePrediction(sbString);
		LinkedHashMap<Integer, StringBuffer> predictionList = new LinkedHashMap<Integer, StringBuffer>();
		resultPredictionObj.setPredictionResult(predictionList);
		
		return resultPredictionObj;
	}
}
