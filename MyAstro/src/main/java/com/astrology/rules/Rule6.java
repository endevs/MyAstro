package com.astrology.rules;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.astrology.utils.Utils;
import com.astrology.vo.House;
import com.astrology.vo.NatalChart;
import com.astrology.vo.Planet;
import com.astrology.vo.Prediction;
import com.astrology.vo.Sign;

public class Rule6 {
	public Prediction execute(LinkedHashMap<String, NatalChart> natalChartS) {
		
		//Preparation for General information for Prediction Object =============================== Start
		Prediction prediction = new Prediction();
		prediction.setRuleName("NAVAMSHA (D9 CHART)");
		prediction.setReference("https://astrology-endevs.blogspot.com/2019/02/navamsha-d9-chart.html");
		prediction.setDescription( new StringBuffer("Description NA"));
		LinkedHashMap<Integer,StringBuffer> predictionResult = new LinkedHashMap<Integer,StringBuffer>();
		StringBuffer sbString = new StringBuffer();
		
		NatalChart natalChartD1 = natalChartS.get("D1");
		NatalChart natalChartD9 = natalChartS.get("D9");
		//Preparation for General information for Prediction Object =============================== End
		
		sbString.append(System.getProperty("line.separator"));
		sbString.append("======================== Section Rule 6 =========================");
		sbString.append(System.getProperty("line.separator"));
		
		//Fast Rule ================================================================================================= Start
		List<Planet> planetAt7thHouse = natalChartD1.getHouses().get(7).getPlanetsContainHouse();
		Sign sign7th = natalChartD1.getHouses().get(7).getHouseSign();
		if(sign7th.getSignNumber().equals(5)) {
			sbString.append("7 th House Sign is Leo So - ");
			sbString.append("The person will like partner who look like him/herself");
		}
		
		if(planetAt7thHouse.contains(Utils.isThisPlanetContain(5,natalChartD1))){
			sbString.append(System.getProperty("line.separator"));
			sbString.append("SUN is at 7th House So - ");
			sbString.append("The person will like partner who look like him/herself");
		}
		
		predictionResult.put(natalChartD1.getHouses().get(7).getHouseNumber(), sbString);
		//Fast Rule ================================================================================================= End
		
		
		
		//Return Statement =================================================== Start
		
		prediction.setPredictionResult(predictionResult);
		//Return Statement =================================================== Start
		return prediction;
	}
	
	
	public Prediction execute_1(LinkedHashMap<String, NatalChart> natalChartS) {
			
			//Preparation for General information for Prediction Object =============================== Start
			Prediction prediction = new Prediction();
			prediction.setRuleName("NAVAMSHA (D9 CHART)");
			prediction.setReference("https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing");
			prediction.setDescription( new StringBuffer("Description NA"));
			LinkedHashMap<Integer,StringBuffer> predictionResult = new LinkedHashMap<Integer,StringBuffer>();
			StringBuffer sbString = new StringBuffer();
			
			NatalChart natalChartD1 = natalChartS.get("D1");
			NatalChart natalChartD9 = natalChartS.get("D9");
			//Preparation for General information for Prediction Object =============================== End
			
			sbString.append(System.getProperty("line.separator"));
			sbString.append("======================== Section Rule 6_1 =========================");
			sbString.append(System.getProperty("line.separator"));
			
			//Fast Rule ================================================================================================= Start
			List<Planet> planetAt7thHouseD1 = natalChartD1.getHouses().get(7).getPlanetsContainHouse();
			List<Planet> planetAt7thHouseD9 = natalChartD9.getHouses().get(7).getPlanetsContainHouse();
						if(planetAt7thHouseD1.contains(Utils.isThisPlanetContain(5,natalChartD1))
								&&
								planetAt7thHouseD9.contains(Utils.isThisPlanetContain(10,natalChartD1)) 
								&& 
								planetAt7thHouseD9.contains(Utils.isThisPlanetContain(3,natalChartD1))) {
							sbString.append(System.getProperty("line.separator"));
							sbString.append("Chances Homo sexulity");
							sbString.append(System.getProperty("line.separator"));
							sbString.append("Reason - D1 - 7th House Sun and D9 7th House Sat and Mer");
						}
			predictionResult.put(natalChartD1.getHouses().get(7).getHouseNumber(), sbString);
			//Fast Rule ================================================================================================= End
			
			//Return Statement =================================================== Start
			
			prediction.setPredictionResult(predictionResult);
			//Return Statement =================================================== Start
			return prediction;
		}
	
	public Prediction execute_2(LinkedHashMap<String, NatalChart> natalChartS) {
		
		//Preparation for General information for Prediction Object =============================== Start
		Prediction prediction = new Prediction();
		prediction.setRuleName("NAVAMSHA (D9 CHART)");
		prediction.setReference("https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing");
		prediction.setDescription( new StringBuffer("Description NA"));
		LinkedHashMap<Integer,StringBuffer> predictionResult = new LinkedHashMap<Integer,StringBuffer>();
		StringBuffer sbString = new StringBuffer();
		
		NatalChart natalChartD1 = natalChartS.get("D1");
		NatalChart natalChartD9 = natalChartS.get("D9");
		//Preparation for General information for Prediction Object =============================== End
		
		sbString.append(System.getProperty("line.separator"));
		sbString.append("======================== Section Rule 6_2 =========================");
		sbString.append(System.getProperty("line.separator"));
		
		//Fast Rule ================================================================================================= Start
		List<Planet> planetAt7thHouseD1 = natalChartD1.getHouses().get(7).getPlanetsContainHouse();
		List<Planet> planetAt7thHouseD9 = natalChartD9.getHouses().get(7).getPlanetsContainHouse();
					if(planetAt7thHouseD1.contains(Utils.isThisPlanetContain(8,natalChartD1))
							||
							planetAt7thHouseD1.contains(Utils.isThisPlanetContain(11,natalChartD1)) 
							) {
						sbString.append(System.getProperty("line.separator"));
						sbString.append("The person will not commited any relationship until a son bourn. Why Ketu because ketu is ‘Kulasya Unati’");
						sbString.append(System.getProperty("line.separator"));
						sbString.append("Reason - D1 - 7th House Contain Ketu or Rahu");
						sbString.append(System.getProperty("line.separator"));
						sbString.append("Can create problems in marriage because the person go to another spouse.\r\n" + 
								"\r\n" + 
								"Is there any Yoga between D9 - 1st House and 8th House - Divorce\r\n" + 
								"Is there any yoga between D9 - 7th and 8th House - Spouse is asking for divorce.\r\n" + 
								"\r\n" + 
								"Is there any yoga between D9 - 8th and 2nd House - Living the Spouse because of second spouse, because 2nd is the 8th from 7th house which is second spouse, in this case a third person entered.\r\n" + 
								"\r\n" + 
								"If 6th house has involved then separation from physical partner.\r\n" + 
								"\r\n" + 
								"If only D1 7th House contain Rahu or Ketu and no above combination present at D9 then there could be a chance that boy or girl friend before marriage.\r\n" + 
								"");
					}
					
		predictionResult.put(natalChartD1.getHouses().get(7).getHouseNumber(), sbString);
		//Fast Rule ================================================================================================= End
		
		//Return Statement =================================================== Start
		
		prediction.setPredictionResult(predictionResult);
		//Return Statement =================================================== Start
		return prediction;
	}
			
		public Prediction execute_3(LinkedHashMap<String, NatalChart> natalChartS) {
				
			//Preparation for General information for Prediction Object =============================== Start
			Prediction prediction = new Prediction();
			prediction.setRuleName("NAVAMSHA (D9 CHART)");
			prediction.setReference("https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing");
			prediction.setDescription( new StringBuffer("D1 and D9 - ASD Lord at 6,8,12"));
			LinkedHashMap<Integer,StringBuffer> predictionResult = new LinkedHashMap<Integer,StringBuffer>();
			StringBuffer sbString = new StringBuffer();
			
			NatalChart natalChartD1 = natalChartS.get("D1");
			NatalChart natalChartD9 = natalChartS.get("D9");
			//Preparation for General information for Prediction Object =============================== End
			
			sbString.append(System.getProperty("line.separator"));
			sbString.append("======================== Section Rule 6_3 =========================");
			sbString.append(System.getProperty("line.separator"));
			
			//Fast Rule ================================================================================================= Start
			ArrayList<Integer> dustana = new ArrayList<Integer>();
			dustana.add(6);
			dustana.add(8);
			dustana.add(12);
			
			ArrayList<Integer> triens = new ArrayList<Integer>();
			triens.add(1);
			triens.add(5);
			triens.add(9);
			
			Planet lagnaLordD1 = Utils.getLagnaLord(natalChartD1);
			Planet lagnaLordD9 = Utils.getLagnaLord(natalChartD9);
			if(dustana.contains(Utils.getHouseWherePlanetPlaced(natalChartD1, lagnaLordD1).getHouseNumber())) {
				sbString.append(System.getProperty("line.separator"));
				sbString.append("D1 Lagna Lord is "+lagnaLordD1.getPlanetName()+" Placed AT "+Utils.getHouseWherePlanetPlaced(natalChartD1, lagnaLordD1).getHouseNumber());
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Poor thoughts, ill healths (depend on sun strength also)");
			}
			
			if(dustana.contains(Utils.getHouseWherePlanetPlaced(natalChartD9, lagnaLordD9).getHouseNumber())) {
				sbString.append(System.getProperty("line.separator"));
				sbString.append("D1 Lagna Lord is "+lagnaLordD9.getPlanetName()+" Placed AT "+Utils.getHouseWherePlanetPlaced(natalChartD9, lagnaLordD9).getHouseNumber());
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Shows who does not want to marry or problem in marriage");
			}
			
			if(dustana.contains(Utils.getHouseWherePlanetPlaced(natalChartD9, lagnaLordD1).getHouseNumber())) {
				sbString.append(System.getProperty("line.separator"));
				sbString.append("D1 Lagna Lord is ["+lagnaLordD1.getPlanetName()+"] Placed AT D9 Dustahna ["+Utils.getHouseWherePlanetPlaced(natalChartD9, lagnaLordD1).getHouseNumber()+"]");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Person Skinny, specially when this happened at D9 8th house");
			}
			
			if(triens.contains(Utils.getHouseWherePlanetPlaced(natalChartD9, lagnaLordD1).getHouseNumber())) {
				sbString.append(System.getProperty("line.separator"));
				sbString.append("D1 Lagna Lord is ["+lagnaLordD1.getPlanetName()+"] Placed AT D9 trine(1,5,9) ["+Utils.getHouseWherePlanetPlaced(natalChartD9, lagnaLordD1).getHouseNumber()+"]");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Person is Fat");
			}
			
			if(dustana.contains(Utils.getHouseWherePlanetPlaced(natalChartD1, lagnaLordD9).getHouseNumber())) {
				sbString.append(System.getProperty("line.separator"));
				sbString.append("D9 Lagna Lord is ["+lagnaLordD9.getPlanetName()+"] Placed AT D1 Dustana(6,8,12) ["+Utils.getHouseWherePlanetPlaced(natalChartD1, lagnaLordD9).getHouseNumber()+"]");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Long Term ill health and risk development issues can be academic or physical development.");
			}
			
			sbString.append(System.getProperty("line.separator"));
			sbString.append("======================== Section Rule 6_3_1 =========================");
			sbString.append(System.getProperty("line.separator"));
			
			//=====
			for (Integer dsustanaInteger : dustana) {
				//D1 6,8,12 House Owner placed at 10th House
				if(10 == Utils.getHouseWherePlanetPlaced(natalChartD1, natalChartD1.getHouses().get(dsustanaInteger).getHouseOwner()).getHouseNumber()) {
					sbString.append(System.getProperty("line.separator"));
					sbString.append("D1 Dusthana lord is in 10th house  -- Person is going to lose wealth");
					sbString.append(System.getProperty("line.separator"));
				}
			}
			if(dustana.contains(Utils.getHouseWherePlanetPlaced(natalChartD1,natalChartD1.getHouses().get(10).getHouseOwner()).getHouseNumber())) {
				sbString.append(System.getProperty("line.separator"));
				sbString.append("D1 10th lord in Dustan -- Person is going to lose wealth");
				sbString.append(System.getProperty("line.separator"));
			}
			//=====
			
			sbString.append(System.getProperty("line.separator"));
			sbString.append("======================== Section Rule 6_3_2 =========================");
			sbString.append(System.getProperty("line.separator"));
			
			ArrayList<Integer> twoSigns = new ArrayList<Integer>();
			twoSigns.add(8);
			twoSigns.add(11);
			
			if(twoSigns.contains(natalChartD9.getHouses().get(1).getHouseSign().getSignNumber())) {
				sbString.append(System.getProperty("line.separator"));
				sbString.append("D9 Lagna Lord is ["+lagnaLordD9.getPlanetName()+"] Placed AT D9 Sign ["+Utils.getHouseWherePlanetPlaced(natalChartD9, lagnaLordD9).getHouseSign().getSignName()+"]");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Means you are like that both sign, Scorpio Cruelty in life,Aquarius where you see lost means disappear from your life");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Venus Placed in Scorpio ASD at D9  -> Cruelty from or towards women. (Because Scorpio is Lagna so he himself is cruel towards women)");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("If Scorpio is in 4th house that does not mean cruel towards its mother, but if Moon is in scorpio then cruel towards its mother regardless where is scorpio sign.");
			}
			
			if(twoSigns.contains(Utils.getHouseWherePlanetPlaced(natalChartD9, lagnaLordD1).getHouseSign().getSignNumber())) {
				sbString.append(System.getProperty("line.separator"));
				sbString.append("D1 Lagna Lord is ["+lagnaLordD1.getPlanetName()+"] Placed AT D9 Sign ["+Utils.getHouseWherePlanetPlaced(natalChartD9, lagnaLordD1).getHouseSign().getSignName()+"]");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Means you are like that both sign, Scorpio Cruelty in life,Aquarius where you see lost means disappear from your life");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Venus Placed in Scorpio ASD at D9  -> Cruelty from or towards women. (Because Scorpio is Lagna so he himself is cruel towards women)");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("If Scorpio is in 4th house that does not mean cruel towards its mother, but if Moon is in scorpio then cruel towards its mother regardless where is scorpio sign.");
			}
			
			//Get Planets at 1,5,9 from ASD
			List<Planet> planetAt1stHouseD9 = natalChartD9.getHouses().get(1).getPlanetsContainHouse();
			List<Planet> planetAt5thHouseD9 = natalChartD9.getHouses().get(5).getPlanetsContainHouse();
			List<Planet> planetAt9thHouseD9 = natalChartD9.getHouses().get(9).getPlanetsContainHouse();
			
			LinkedHashMap<Integer,List<Planet>> planetsAtTriens = new LinkedHashMap<Integer,List<Planet>>();
			
			planetsAtTriens.put(1, planetAt1stHouseD9);
			planetsAtTriens.put(5, planetAt5thHouseD9);
			planetsAtTriens.put(9, planetAt9thHouseD9);
			if(planetsAtTriens.size()>0) {
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Details :- D9 Any planet triens from ASD shows");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Persons skills and Shows capability not knowledge, means you are capable for something but not knowledge.");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Neither of these can be applied for professionally that depend on D1 and D10 analyses.");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Persons skills of below planets if found D9 Planet triens from ASD, if below planets are debilitated means they like the idea but may not passing "
						+ "below skills and lack of confidence development is not strong.");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Only when the planets placed in triens not by any planet aspecting or owner of triens influence the skils.");
				sbString.append(System.getProperty("line.separator"));
			}
			
			LinkedHashMap<String,StringBuffer> resultAccordingPlanet = new LinkedHashMap<String,StringBuffer>();
			resultAccordingPlanet.put("SUN", new StringBuffer("loves reading,very organized, leader, studies veda and gita, like reydan singings and musical instruments."));
			resultAccordingPlanet.put("MOON", new StringBuffer("Fond of history, cosmology, genes, melodic voice, singers"));
			resultAccordingPlanet.put("MARS", new StringBuffer("Fond of martial arts, yoga, weapons, good at handling fires, worship god and good cook."));
			resultAccordingPlanet.put("MERCURY", new StringBuffer(" writing , drawing, designing framework , archetech, if with moon then medical doctor or excellent writer , moon strong doctor mer strong writer"));
			resultAccordingPlanet.put("JUPITER", new StringBuffer("very singular focus, will study vedanta, excelling in writing and grammar. He says i will learn whole vedant, If with Mars or sun then goes for Astrology, if Jup is rahu or sat then goes for mantra shastra. If Jup is Scorpio or Ares then astrologh \r\n" + 
					"If Jup is at Trikon or ASD then look for Uchanatha is Moon were placed if placed at Scorpio then good in astrology, lots of knowledge in scolor. Is like a living database of information.\r\n" + 
					"If any planet for example jup debilitated means  \r\n" + 
					""));
			resultAccordingPlanet.put("VENUS", new StringBuffer("precision eye for details, , art / painter, poeital, ofter hums songs and tunes, great writer even better orator , very artistic , government servant, if with moon then earn form courses and education."));
			resultAccordingPlanet.put("SATURN", new StringBuffer("Shyness and / or stage fight early in life , its like a private person. Introward or hide."
					+ " very skilled, especially in traditional science, bowmanship, pragmatic and can easily adapt to new circumstances, can be shy and suffer stage fight unless sun mitigates this"));
			resultAccordingPlanet.put("RAHU", new StringBuffer("very innovative imaginative , often occupied with thoughts and things several thoughts at a time , and mix up thoughts.\r\n" + 
					"Can have trouble sleeping , Excellent in mantra, yantra, handling chemicals, and in hunting\r\n" + 
					""));
			resultAccordingPlanet.put("KETU", new StringBuffer("Ketu in trains from D9 Lagna then that person is very good in math. If this Ketu link with D1 ASD then this person can be methmeticion."
					+ "absolute perfectionist, can be in mathematics, working with precision and small. Machine / microscopy ,\r\n" + 
					"If with Ven spiritual and perform intense penance / tapaswi yoga if both sat + ven join.\r\n" + 
					""));
			
			
			if(planetAt1stHouseD9.size()>0) {
				sbString.append("Result :- If planet is at 1st then he has to learn by himself");
				sbString.append(System.getProperty("line.separator"));
			}
			if(planetAt5thHouseD9.size()>0) {
				sbString.append("Result :- If planet is at 5th then knowledge is come from past life just open the book and all knowledge will recall.");
				sbString.append(System.getProperty("line.separator"));
			}
			if(planetAt9thHouseD9.size()>0) {
				sbString.append("Result :- If planet is at 9th then he has to learn from guru");
				sbString.append(System.getProperty("line.separator"));
			}
			
			for(Map.Entry listOfPlanets:planetsAtTriens.entrySet()) {
				List<Planet> currentHousePlanets = (List<Planet>) listOfPlanets.getValue();
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Planets of House ["+listOfPlanets.getKey()+"]");
				sbString.append(System.getProperty("line.separator"));
				for (Planet planet : currentHousePlanets) {
					sbString.append(System.getProperty("line.separator"));
					sbString.append("["+planet.getPlanetName()+"]");
					sbString.append(System.getProperty("line.separator"));
					sbString.append(resultAccordingPlanet.get(planet.getPlanetName()));
				}
			}
			
			sbString.append(System.getProperty("line.separator"));
			sbString.append("======================== Section Rule 6_3_3 =========================");
			sbString.append(System.getProperty("line.separator"));
			
			//D9 7th house planets placed - Resule - Means those planets skills you like but do not know how to do. 
			List<Planet> planetAt7thHouseD9 = natalChartD9.getHouses().get(7).getPlanetsContainHouse();
			for (Planet planet : planetAt7thHouseD9) {
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Planets Placed at 7th House - Means those planets skills you like but do not know how to do. ");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("["+planet.getPlanetName()+"]");
				sbString.append(System.getProperty("line.separator"));
				sbString.append(resultAccordingPlanet.get(planet.getPlanetName()));
				sbString.append(System.getProperty("line.separator"));
			}
			
			//D9 If SAT in Lagna then 
			Planet lagnaLordD9Asd = Utils.getLagnaLord(natalChartD9);
			if(lagnaLordD9Asd.getPlanetName().equals("SATURN")) {
				sbString.append(System.getProperty("line.separator"));
				sbString.append("Result :- D9 If SAT in Lagna lord then - Say introdward");
				sbString.append(System.getProperty("line.separator"));
			}
			
			predictionResult.put(0, sbString);
			//Fast Rule ================================================================================================= End
			
			//Return Statement =================================================== Start
			
			prediction.setPredictionResult(predictionResult);
			//Return Statement =================================================== Start
			return prediction;
		}
		
		
		public Prediction execute_4(LinkedHashMap<String, NatalChart> natalChartS) {
			
			//Preparation for General information for Prediction Object =============================== Start
			Prediction prediction = new Prediction();
			prediction.setRuleName("Kendra D9 - 4th House of D9");
			prediction.setReference("https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing");
			prediction.setDescription( new StringBuffer("Description NA"));
			LinkedHashMap<Integer,StringBuffer> predictionResult = new LinkedHashMap<Integer,StringBuffer>();
			StringBuffer sbString = new StringBuffer();
			
			NatalChart natalChartD1 = natalChartS.get("D1");
			NatalChart natalChartD9 = natalChartS.get("D9");
			//Preparation for General information for Prediction Object =============================== End
			
			sbString.append(System.getProperty("line.separator"));
			sbString.append("======================== Section Rule 6_4 =========================");
			sbString.append(System.getProperty("line.separator"));
			
			//Fast Rule ================================================================================================= Start
			List<Planet> planetAt7thHouseD1 = natalChartD1.getHouses().get(7).getPlanetsContainHouse();
			List<Planet> planetAt4thHouseD9 = natalChartD9.getHouses().get(4).getPlanetsContainHouse();
			
				
			if(planetAt4thHouseD9.size()>0) {
				prediction.setDescription( new StringBuffer("Real wealth of human being\r\n" + 
						"Gives Wealth\r\n" + 
						"Planets in kendra in D9 support you and benefit you\r\n" + 
						"Lords of kendra in D9 can increase wealth when well placed \r\n" + 
						"4th house in D9 is not normal kendra it can give you punishment even if small mistakes so 4th house in D9 is very dangerous in char.It is always looking for “are you innocent ?” if no then ready  for punishment. Depend on how you trite with weaker person then you,  how you trite with children and women.\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"If 4th House having benefic planet congratulation good life.\r\n" + 
						"If 4th House having malefic planet very toff remedies are required.\r\n" + 
						"\r\n" + 
						"If malefic then its an event and its imprint on the mind that imprint state the memory of that state and its usually emotional trauma.\r\n" + 
						"\r\n" + 
						"Result will be emotional trauma.\r\n" + 
						"D9 10th house give money.to Langa\r\n" + 
						"D9 5th house give knowledge\r\n" + 
						"D9 7th house give what you desiring\r\n" + 
						""));
				for (Planet planet : planetAt4thHouseD9) {
					sbString.append(System.getProperty("line.separator"));
					sbString.append("D9 4th House Planets are ["+planet.getPlanetName()+"]");
					sbString.append(System.getProperty("line.separator"));
				}
				
			}
			
			sbString.append(System.getProperty("line.separator"));
			sbString.append("======================== Section Rule 6_4_1 =========================");
			sbString.append(System.getProperty("line.separator"));
			
			//Venus and Mars yoga is gandanta yoga toff yoga at D9, D1
			ArrayList<Integer> listOf3rd7th11thHouses = new ArrayList<Integer>();
			listOf3rd7th11thHouses.add(3);
			listOf3rd7th11thHouses.add(7);
			listOf3rd7th11thHouses.add(11);
			
			Integer houseNumberD1 = Utils.getThisPlanetCombinationPresentInThisChart(new String[] {"MARS","VENUS"},natalChartD1);
			if(listOf3rd7th11thHouses.contains(houseNumberD1)) {
				sbString.append(System.getProperty("line.separator"));
				sbString.append("D1 - Venus and Mars yoga is gandanta yoga toff yoga");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("3rd , 7th,11th mars and ven means hight sexulity, high libidos, many affairs, \r\n" + 
						"Even Venus is in these houses having many affairs\r\n" + 
						"");
			}
			
			Integer houseNumberD9 = Utils.getThisPlanetCombinationPresentInThisChart(new String[] {"MARS","VENUS"},natalChartD9);
			if(listOf3rd7th11thHouses.contains(houseNumberD9)) {
				sbString.append(System.getProperty("line.separator"));
				sbString.append("D9 - Venus and Mars yoga is gandanta yoga toff yoga");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("3rd , 7th,11th mars and ven means hight sexulity, high libidos, many affairs, \r\n" + 
						"Even Venus is in these houses having many affairs\r\n" + 
						"");
			}
			predictionResult.put(0, sbString);
			//Fast Rule ================================================================================================= End
			
			//Return Statement =================================================== Start
			
			prediction.setPredictionResult(predictionResult);
			//Return Statement =================================================== Start
			return prediction;
		}
		
	public Prediction execute_5(LinkedHashMap<String, NatalChart> natalChartS) {
				
				//Preparation for General information for Prediction Object =============================== Start
				Prediction prediction = new Prediction();
				prediction.setRuleName("Association of Planets - Why this planet is in your life?");
				prediction.setReference("https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing");
				prediction.setDescription( new StringBuffer("Why this planet is in your life?\r\n" + 
						"It's not planets intention its your association leading to that focus.\r\n" + 
						"\r\n" + 
						"For Example :\r\n" + 
						"What is the primary association of my kids? \r\n" + 
						"Or what is your primary conversation with kids? \r\n" + 
						"Or prime karma with kids?\r\n" + 
						"\r\n" + 
						"Take 5th Lord at D1\r\n" + 
						"Check where this planet is in D9 and which sign that is.\r\n" + 
						"See that sign back in D1 chart.\r\n" + 
						"Then you know why you are associating.\r\n" + 
						""));
				LinkedHashMap<Integer,StringBuffer> predictionResult = new LinkedHashMap<Integer,StringBuffer>();
				StringBuffer sbString = new StringBuffer();
				
				NatalChart natalChartD1 = natalChartS.get("D1");
				NatalChart natalChartD9 = natalChartS.get("D9");
				//Preparation for General information for Prediction Object =============================== End
				
				sbString.append(System.getProperty("line.separator"));
				sbString.append("======================== Section Rule 6_5 =========================");
				sbString.append(System.getProperty("line.separator"));
				
				//Fast Rule ================================================================================================= Start
				for(Map.Entry house:natalChartD1.getHouses().entrySet()){
					House houseD1 = (House) house.getValue();
					sbString.append("D1 House Number = "+houseD1.getHouseNumber());
					sbString.append(System.getProperty("line.separator"));
					sbString.append("D1 House Owner = "+houseD1.getHouseOwner().getPlanetName());
					sbString.append(System.getProperty("line.separator"));
					House finalHouse = Utils.getHousebySign(Utils.getHouseWherePlanetPlaced(natalChartD9, houseD1.getHouseOwner()).getHouseSign(), natalChartD1);
					sbString.append("D1 House Sign Back from D9 = "+finalHouse.getHouseSign().getSignName());
					sbString.append(System.getProperty("line.separator"));
					sbString.append(System.getProperty("line.separator"));
				}
				
				predictionResult.put(0, sbString);
				//Fast Rule ================================================================================================= End
				
				//Return Statement =================================================== Start
				
				prediction.setPredictionResult(predictionResult);
				//Return Statement =================================================== Start
				return prediction;
			}
	
	public Prediction execute_6(LinkedHashMap<String, NatalChart> natalChartS) {
		
		//Preparation for General information for Prediction Object =============================== Start
		Prediction prediction = new Prediction();
		prediction.setRuleName("Moon is at 12th - Touches people's heart much more easily");
		prediction.setReference("https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing");
		prediction.setDescription( new StringBuffer("Not Defined"));
		LinkedHashMap<Integer,StringBuffer> predictionResult = new LinkedHashMap<Integer,StringBuffer>();
		StringBuffer sbString = new StringBuffer();
		
		NatalChart natalChartD1 = natalChartS.get("D1");
		NatalChart natalChartD9 = natalChartS.get("D9");
		//Preparation for General information for Prediction Object =============================== End
		
		sbString.append(System.getProperty("line.separator"));
		sbString.append("======================== Section Rule 6_6 =========================");
		sbString.append(System.getProperty("line.separator"));
		
		//Fast Rule ================================================================================================= Start
		
		Integer houseNumberD1Moon = Utils.getThisPlanetCombinationPresentInThisChart(new String[] {"MOON"},natalChartD1);
		Integer houseNumberD9Moon = Utils.getThisPlanetCombinationPresentInThisChart(new String[] {"MOON"},natalChartD9);
		if(houseNumberD1Moon == 12) {
			sbString.append("D1 Moon is at 12th - Touches people's heart much more easily");
			sbString.append(System.getProperty("line.separator"));
		}
		if(houseNumberD9Moon == 12) {
			sbString.append("D9 Moon is at 12th - Touches people's heart much more easily");
			sbString.append(System.getProperty("line.separator"));
		}
		
		
		predictionResult.put(0, sbString);
		//Fast Rule ================================================================================================= End
		
		//Return Statement =================================================== Start
		
		prediction.setPredictionResult(predictionResult);
		//Return Statement =================================================== Start
		return prediction;
	}
	
	public Prediction execute_7(LinkedHashMap<String, NatalChart> natalChartS) {
			
			//Preparation for General information for Prediction Object =============================== Start
			Prediction prediction = new Prediction();
			prediction.setRuleName("D9 10th House is telling where the real wealth is coming from , wealth means not salary.");
			prediction.setReference("https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing");
			prediction.setDescription( new StringBuffer("Not Defined"));
			LinkedHashMap<Integer,StringBuffer> predictionResult = new LinkedHashMap<Integer,StringBuffer>();
			StringBuffer sbString = new StringBuffer();
			
			NatalChart natalChartD1 = natalChartS.get("D1");
			NatalChart natalChartD9 = natalChartS.get("D9");
			//Preparation for General information for Prediction Object =============================== End
			
			sbString.append(System.getProperty("line.separator"));
			sbString.append("======================== Section Rule 6_7 =========================");
			sbString.append(System.getProperty("line.separator"));
			
			//Fast Rule ================================================================================================= Start
			List<Planet> planetAt10thHouseD9 = natalChartD9.getHouses().get(10).getPlanetsContainHouse();
			boolean descriptingSet = false;
			
			if(natalChartD9.getHouses().get(10).getHouseOwner().isExalted()) {
				sbString.append("If the D9 10th house lord exalted planet then this person is very very wealthy.");
				sbString.append(System.getProperty("line.separator"));
				descriptingSet = true;
			}
			if(natalChartD9.getHouses().get(10).getHouseOwner().isOwnSign()) {
				sbString.append("If the D9 10th house lord placed own sign then this person is very very wealthy.");
				sbString.append(System.getProperty("line.separator"));
				descriptingSet = true;
			}
			
			for (Planet planet : planetAt10thHouseD9) {
				if(planet.isExalted()) {
					sbString.append("If the D9 10th house contain exalted planet then this person is very very wealthy.");
					sbString.append(System.getProperty("line.separator"));
					descriptingSet = true;
				}
				if(planet.isOwnSign()) {
					sbString.append("If the D9 10th house contain own sign planet then this person is very very wealthy.");
					sbString.append(System.getProperty("line.separator"));
					descriptingSet = true;
				}
			}
			if(descriptingSet) {
				sbString.append("D9 10 house Sign ["+natalChartD9.getHouses().get(10).getHouseSign().getSignName()+"]");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("D9 10 house Lord ["+natalChartD9.getHouses().get(10).getHouseOwner().getPlanetName()+"]");
				sbString.append(System.getProperty("line.separator"));
				sbString.append("D9 10 house Lord Placed At Sign ["+Utils.getHouseWherePlanetPlaced(natalChartD9, natalChartD9.getHouses().get(10).getHouseOwner()).getHouseSign().getSignName()+"]");
				sbString.append(System.getProperty("line.separator"));
				prediction.setDescription( new StringBuffer("D9 10th house is telling where the real wealth is coming from , wealth means not salary.\r\n" + 
						"If the 10th house contain exalted planet or own sign or its lord is having own sign or exalted then this person is very very wealthy.\r\n" + 
						""));
			}
			predictionResult.put(0, sbString);
			//Fast Rule ================================================================================================= End
			
			//Return Statement =================================================== Start
			
			prediction.setPredictionResult(predictionResult);
			//Return Statement =================================================== Start
			return prediction;
		}
	
	public Prediction execute_8(LinkedHashMap<String, NatalChart> natalChartS) {
			
			//Preparation for General information for Prediction Object =============================== Start
			Prediction prediction = new Prediction();
			prediction.setRuleName("D1 Dusthana lord is in 10th house or 10th Lord in Dustan");
			prediction.setReference("https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing");
			prediction.setDescription( new StringBuffer("Not Defined"));
			LinkedHashMap<Integer,StringBuffer> predictionResult = new LinkedHashMap<Integer,StringBuffer>();
			StringBuffer sbString = new StringBuffer();
			
			NatalChart natalChartD1 = natalChartS.get("D1");
			NatalChart natalChartD9 = natalChartS.get("D9");
			//Preparation for General information for Prediction Object =============================== End
			
			sbString.append(System.getProperty("line.separator"));
			sbString.append("======================== Section Rule 6_8 =========================");
			sbString.append(System.getProperty("line.separator"));
			
			//Fast Rule ================================================================================================= Start
			
			Integer houseNumberD1Moon = Utils.getThisPlanetCombinationPresentInThisChart(new String[] {"MOON"},natalChartD1);
			Integer houseNumberD9Moon = Utils.getThisPlanetCombinationPresentInThisChart(new String[] {"MOON"},natalChartD9);
			if(houseNumberD1Moon == 12) {
				sbString.append("D1 Moon is at 12th - Touches people's heart much more easily");
				sbString.append(System.getProperty("line.separator"));
			}
			if(houseNumberD9Moon == 12) {
				sbString.append("D9 Moon is at 12th - Touches people's heart much more easily");
				sbString.append(System.getProperty("line.separator"));
			}
			
			
			predictionResult.put(0, sbString);
			//Fast Rule ================================================================================================= End
			
			//Return Statement =================================================== Start
			
			prediction.setPredictionResult(predictionResult);
			//Return Statement =================================================== Start
			return prediction;
		}
}
