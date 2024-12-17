package com.astrology.rules;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.astrology.utils.Utils;
import com.astrology.vo.House;
import com.astrology.vo.HousePlanetDetailsForPredictionVO;
import com.astrology.vo.NatalChart;
import com.astrology.vo.Planet;
import com.astrology.vo.Prediction;

public class Rule1 {
	
	/**
	 * If a house lord placed in dustana like 6,8,12 from ASD then means house is lost
	 * @return 
	 */
	
	public Prediction execute(NatalChart natalChart) {
		System.out.println(":: Rule1 :: execute :: Start");
		LinkedHashMap<Integer, HousePlanetDetailsForPredictionVO> housePlanetDetailsForPredictionList = new LinkedHashMap<Integer, HousePlanetDetailsForPredictionVO>();
		Prediction prediction = new Prediction();
		prediction.setRuleName("House to House Planet disctance");
		prediction.setReference("No Reference");
		prediction.setDescription(new StringBuffer("For Example :- 5th Lord is at 10th house means , 5th lord placed at 6 from its house means its not available, support children and education is not available or results are not easily available ."
				+ "5th lord is at 10th house may be good for lagna but not 5th house."));
		LinkedHashMap<Integer,StringBuffer> predictionResult = new LinkedHashMap<Integer,StringBuffer>();
		
		ArrayList<Integer> dustana = new ArrayList<Integer>();
		dustana.add(6);
		dustana.add(8);
		dustana.add(12);
		
		ArrayList<Integer> upachaya  = new ArrayList<Integer>();
		upachaya.add(3);
		upachaya.add(11);
		
		ArrayList<Integer> kendra = new ArrayList<Integer>();
		kendra.add(1);
		kendra.add(4);
		kendra.add(7);
		kendra.add(10);
		
		ArrayList<Integer> konas  = new ArrayList<Integer>();
		konas.add(5);
		konas.add(9);
		
		for(Map.Entry house:natalChart.getHouses().entrySet()){
			StringBuffer sbString = new StringBuffer();
			//Find current House
			House currentHhouse = (House) house.getValue();
				//Find Current House Owner
				Planet currentHouseOwnerPlanet = currentHhouse.getHouseOwner();
				HousePlanetDetailsForPredictionVO  housePlanetDetailsForPredictionVo = new HousePlanetDetailsForPredictionVO();
				sbString.append("House Number ="+currentHhouse.getHouseNumber());
				sbString.append(System.getProperty("line.separator"));
				//How Far current House Owner from House
					//Loop all Houses to Find Current House Owner Planet Placed
				for(Map.Entry houseFind:natalChart.getHouses().entrySet()){
					House findThisHouse = (House) houseFind.getValue();
					if(findThisHouse.getPlanetsContainHouse().contains(currentHouseOwnerPlanet)) {
						//System.out.println("House Sign is "+currentHhouse.getHouseSign().getSignName());
						housePlanetDetailsForPredictionVo.setHouseSignName(currentHhouse.getHouseSign().getSignName());
						sbString.append("House Sign is ="+currentHhouse.getHouseSign().getSignName());
						sbString.append(System.getProperty("line.separator"));
						//System.out.println("House Number is "+currentHhouse.getHouseNumber()); //4th House
						housePlanetDetailsForPredictionVo.setHouseNumber(currentHhouse.getHouseNumber());
						sbString.append("House Number is  ="+currentHhouse.getHouseNumber());
						sbString.append(System.getProperty("line.separator"));
						//System.out.println("House Owner Is "+currentHouseOwnerPlanet.getPlanetName()); //Moon
						housePlanetDetailsForPredictionVo.setHouseOwner(currentHouseOwnerPlanet.getPlanetName());
						sbString.append("House Owner Is  ="+currentHouseOwnerPlanet.getPlanetName());
						sbString.append(System.getProperty("line.separator"));
						//System.out.println("House Owner Placed At House "+findThisHouse.getHouseNumber()); //11th House //5th House
						housePlanetDetailsForPredictionVo.setHouseOwnerPlacedAtHouse(findThisHouse.getHouseNumber());
						sbString.append("House Owner Placed At House  ="+findThisHouse.getHouseNumber());
						sbString.append(System.getProperty("line.separator"));
						
						Integer houseNumberIs = currentHhouse.getHouseNumber();
						Integer houseOwnerPlacedAt = findThisHouse.getHouseNumber();

						Integer houseToHouseOwnerOutPut = null;
						Integer houseOwnerToHouseOutPut = null;
						
						
						//House to House Owner Distance
						houseToHouseOwnerOutPut = Utils.houseToHouseOwner(houseNumberIs,houseOwnerPlacedAt);
						//System.out.println("House to House Owner Distance = "+houseToHouseOwnerOutPut);
						housePlanetDetailsForPredictionVo.setHouseToHouseOwnerDistance(houseToHouseOwnerOutPut);
						sbString.append("House to House Owner Distance ="+houseToHouseOwnerOutPut);
						sbString.append(System.getProperty("line.separator"));
						
						houseOwnerToHouseOutPut = Utils.houseOwnerToHouse(houseNumberIs,houseOwnerPlacedAt);
						//System.out.println("House Owner to House Distance = "+houseOwnerToHouseOutPut);
						housePlanetDetailsForPredictionVo.setHouseOwnerToHouseDistance(houseOwnerToHouseOutPut);
						sbString.append("House Owner to House Distance  ="+houseOwnerToHouseOutPut);
						//sbString.append(System.getProperty("line.separator"));
						if(dustana.contains(houseOwnerToHouseOutPut)) {
							sbString.append(System.getProperty("line.separator"));
							sbString.append("If Lord of the House is placed Dustana 6,8,12 from House");
							sbString.append(System.getProperty("line.separator"));
							sbString.append(" = House is not Available");
							sbString.append(System.getProperty("line.separator"));
						}
						
						
						if(upachaya.contains(houseOwnerToHouseOutPut)) {
							sbString.append(System.getProperty("line.separator"));
							sbString.append("If Lord of the House is placed at Upachaya house 3rd and 11th house");
							sbString.append(System.getProperty("line.separator"));
							sbString.append("If Malefic then (May be planets) you will be punished. criticizing.");
							sbString.append(System.getProperty("line.separator"));
							sbString.append("If Benefic then upachaya means you are growing, encouraging you to grow.");
							sbString.append(System.getProperty("line.separator"));
						}
						
						if(kendra.contains(houseOwnerToHouseOutPut)) {
							sbString.append(System.getProperty("line.separator"));
							sbString.append("If Lord of the House is placed at 4th,10th,1st and 7th house from itself.");
							sbString.append(System.getProperty("line.separator"));
							sbString.append("Are give clean pure support.");
							sbString.append(System.getProperty("line.separator"));
						}
						
						if(konas.contains(houseOwnerToHouseOutPut)) {
							sbString.append(System.getProperty("line.separator"));
							sbString.append("If Lord of the House is placed at 5th and 9th house from itself.");
							sbString.append(System.getProperty("line.separator"));
							sbString.append("Konas (5,9) means edge those planets are supporting you but having criteria or candition.");
							sbString.append(System.getProperty("line.separator"));
							
							if(houseOwnerToHouseOutPut.equals(5)) {
								sbString.append(System.getProperty("line.separator"));
								sbString.append("For 5th House they must listen to you.");
							}
							
							if(houseOwnerToHouseOutPut.equals(9)) {
								sbString.append(System.getProperty("line.separator"));
								sbString.append("For 9th House you must listen to them.");
							}
						}
						
						if(houseOwnerToHouseOutPut.equals(2)) {
							sbString.append(System.getProperty("line.separator"));
							sbString.append("If Lord of the house is placed 2nd ");
							sbString.append(System.getProperty("line.separator"));
							sbString.append("Is good and bad that dependent on planets placed there. But this house always sustains you.");
							sbString.append(System.getProperty("line.separator"));
						}
						
						
					}
				}
				//housePlanetDetailsForPredictionList.put(housePlanetDetailsForPredictionVo.getHouseNumber(), housePlanetDetailsForPredictionVo);
				predictionResult.put(currentHhouse.getHouseNumber(), sbString);
				//predictionResult.put(housePlanetDetailsForPredictionVo.getHouseNumber(), )
		}
		prediction.setPredictionResult(predictionResult);
		//prediction.setPredictionResult(predictionResult);
		
		System.out.println(":: Rule1 :: execute :: End");
		return prediction;
	}
}
