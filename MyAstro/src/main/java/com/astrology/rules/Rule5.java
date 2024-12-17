package com.astrology.rules;

import java.util.LinkedHashMap;
import java.util.Map;

import com.astrology.utils.Utils;
import com.astrology.vo.House;
import com.astrology.vo.NatalChart;
import com.astrology.vo.Planet;
import com.astrology.vo.Prediction;
import com.astrology.vo.Sign;

public class Rule5 {
	public Prediction execute(LinkedHashMap<String, NatalChart> natalChartS) {
		//Details
		Prediction prediction = new Prediction();
		prediction.setRuleName("Secrets of Navansh Part 1-Rasi Tulya Navansh");
		prediction.setReference("https://astrology-endevs.blogspot.com/2019/06/secrets-of-navansh-part-1-rasi-tulya.html");
		prediction.setDescription( new StringBuffer("Description NA"));
		LinkedHashMap<Integer,StringBuffer> predictionResult = new LinkedHashMap<Integer,StringBuffer>();
		StringBuffer sbString;
		
		NatalChart natalChartD1 = natalChartS.get("D1");
		NatalChart natalChartD9 = natalChartS.get("D9");
		
		//Step - 1 - Find D1 Lagna Sign
		Sign lagnaSignD1 = natalChartD1.getHouses().get(1).getHouseSign();
		//Step - 2 - Step 1 Lagna Sign Make D9 as Lagna and Calculate 6,8,12
		LinkedHashMap<Integer,House> housesAt6812AtD9Chart = Utils.getHousesFromThisSign(lagnaSignD1,natalChartD9,new Integer[] {6,8,12});
		
		//Results
		//=================================================================================== Prepare Result Set 
		LinkedHashMap<Integer, String> predictionResultList = new LinkedHashMap<Integer, String>();
		predictionResultList.put(6, "6th House placed planet give diseases, troubles, and enemies, 6th house problems is solvable.");
		predictionResultList.put(8,"8th House placed planet gives unsolvable problems and great transformation is that relation.");
		predictionResultList.put(12, "12th House losses through that significations comes in.");
		
		StringBuffer venStringBuffer = new StringBuffer();
		venStringBuffer.append("Ven - in 6,8,12 in navamsa spouse, DNA,Health, Married life will have problem.").append(System.getProperty("line.separator"));
		venStringBuffer.append(" If ven 8th house problem in marriage , unsolvable throughout life.").append(System.getProperty("line.separator"));
		venStringBuffer.append("  If ven 12th house he/she will have losses from spouse or wife specifically.").append(System.getProperty("line.separator"));
		
		StringBuffer jupStringBuffer = new StringBuffer();
		jupStringBuffer.append("Jup - in 6,8,12 in kids, education, wisdom, happiness of life is missing.").append(System.getProperty("line.separator"));
		jupStringBuffer.append("If jup 6th house kids became your enemy.").append(System.getProperty("line.separator"));
		
		StringBuffer marStringBuffer = new StringBuffer();
		marStringBuffer.append("Mars - Brothers property, friends, courage will be become problem.").append(System.getProperty("line.separator"));
		
		StringBuffer merStringBuffer = new StringBuffer();
		merStringBuffer.append("Mer - Childhood, young kids, speech, communication ability").append(System.getProperty("line.separator"));
		merStringBuffer.append(" If mars in 6th house then brother suffering from health issue or became your enemy.").append(System.getProperty("line.separator"));
		
		
		StringBuffer moonStringBuffer = new StringBuffer();
		moonStringBuffer.append("Moon - Mother, mental peace.").append(System.getProperty("line.separator"));
		
		StringBuffer satStringBuffer = new StringBuffer();
		satStringBuffer.append("Saturn - Long term illness, miseries in life, work is in trouble.").append(System.getProperty("line.separator"));
		satStringBuffer.append("If Sat is at 12 house then losses shall come from employees.").append(System.getProperty("line.separator"));
		satStringBuffer.append("If sat is at 6th house then you will get a rare diseases which is very difficult to solve.").append(System.getProperty("line.separator"));
		satStringBuffer.append("If sat is at 8th house then it will give you long age but continues trouble in your profession.").append(System.getProperty("line.separator"));
		
		
		
		LinkedHashMap<String, StringBuffer> predictionResultListPlanets = new LinkedHashMap<String, StringBuffer>();
		predictionResultListPlanets.put("VENUS", venStringBuffer);
		predictionResultListPlanets.put("JUPITER", jupStringBuffer);
		predictionResultListPlanets.put("MARS", marStringBuffer);
		predictionResultListPlanets.put("MERCURY", merStringBuffer);
		predictionResultListPlanets.put("MOON", moonStringBuffer);
		predictionResultListPlanets.put("SATURN", satStringBuffer);
		predictionResultListPlanets.put("SUN", new StringBuffer("Sun Not Defined"));
		predictionResultListPlanets.put("RAHU", new StringBuffer("RAHU Not Defined"));
		predictionResultListPlanets.put("KETU", new StringBuffer("KETU Not Defined"));
		
		//Loop
		  //If 6th House Planets places the / 8th / 12th
		for (Map.Entry housesAt6812AtD9 : housesAt6812AtD9Chart.entrySet()) {
			sbString = new StringBuffer();
			House currentHouse = (House) housesAt6812AtD9.getValue();
			
			sbString.append(currentHouse.getHouseNumber());
			sbString.append(" House is ");
			sbString.append(housesAt6812AtD9.getKey());
			sbString.append(" From ");
			sbString.append(lagnaSignD1.getSignName());
			sbString.append(" D9 ").append(System.getProperty("line.separator"));
			
			if(currentHouse.getPlanetsContainHouse().size()>0) { //If house contain planets
				sbString.append(predictionResultList.get(housesAt6812AtD9.getKey()));
				for (Planet planet : currentHouse.getPlanetsContainHouse()) {
					sbString.append(predictionResultListPlanets.get(planet.getPlanetName()));
				}
			}else {
				sbString.append(currentHouse.getHouseNumber());
				sbString.append(" House ");
				sbString.append("Does Not Contain Any Planet");
			}
				
			predictionResult.put(currentHouse.getHouseNumber(), sbString);
		}
		prediction.setPredictionResult(predictionResult);
		
		return prediction;
	}
}
