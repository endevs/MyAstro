package com.astrology.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.astrology.vo.House;
import com.astrology.vo.HousePlanetDetailsForPredictionVO;
import com.astrology.vo.NatalChart;
import com.astrology.vo.Planet;
import com.astrology.vo.Prediction;
import com.astrology.vo.Sign;

public class Utils {
	
	static LinkedHashMap<Integer,Sign> signs = new LinkedHashMap<Integer,Sign>();
	static LinkedHashMap<String,Planet> planets = new LinkedHashMap<String,Planet>();
	static {
		signs = prepareStaticSignsData();
		planets = prepareStaticPlanetsData();
	}

public static LinkedHashMap<Integer,Sign> prepareStaticSignsData() {
		
		LinkedHashMap<Integer,Sign> signs = new LinkedHashMap<Integer,Sign>();
		LinkedHashMap<Integer,String> signMovableFixedDuel = new LinkedHashMap<Integer,String>();
		signMovableFixedDuel.put(1, "Movable");
		signMovableFixedDuel.put(2, "Fixed");
		signMovableFixedDuel.put(3, "Duel");
		
		LinkedHashMap<Integer,String> signElementsFieryEarthyAiryWatery = new LinkedHashMap<Integer,String>();
		signElementsFieryEarthyAiryWatery.put(1, "Fire");
		signElementsFieryEarthyAiryWatery.put(2, "Earth");
		signElementsFieryEarthyAiryWatery.put(3, "Air");
		signElementsFieryEarthyAiryWatery.put(4, "Water");
		
		//1
		Sign aries = new Sign();
		aries.setSignName("Aries");
		aries.setSignNumber(1);
		aries.setSignMovableFixedDuel(signMovableFixedDuel.get(1));
		aries.setSignElementsFieryEarthyAiryWatery(signElementsFieryEarthyAiryWatery.get(1));
		
		//2
		Sign taurus = new Sign();
		taurus.setSignName("Taurus");
		taurus.setSignNumber(2);
		taurus.setSignMovableFixedDuel(signMovableFixedDuel.get(2));
		taurus.setSignElementsFieryEarthyAiryWatery(signElementsFieryEarthyAiryWatery.get(2));
		
		//3
		Sign gemini = new Sign();
		gemini.setSignName("Gemini");
		gemini.setSignNumber(3);
		gemini.setSignMovableFixedDuel(signMovableFixedDuel.get(3));
		gemini.setSignElementsFieryEarthyAiryWatery(signElementsFieryEarthyAiryWatery.get(3));
		
		//4
		Sign cancer = new Sign();
		cancer.setSignName("Cancer");
		cancer.setSignNumber(4);
		cancer.setSignMovableFixedDuel(signMovableFixedDuel.get(1));
		cancer.setSignElementsFieryEarthyAiryWatery(signElementsFieryEarthyAiryWatery.get(4));
		
		//5
		Sign leo = new Sign();
		leo.setSignName("Leo");
		leo.setSignNumber(5);
		leo.setSignMovableFixedDuel(signMovableFixedDuel.get(2));
		leo.setSignElementsFieryEarthyAiryWatery(signElementsFieryEarthyAiryWatery.get(1));
		
		//6
		Sign virgo = new Sign();
		virgo.setSignName("Virgo");
		virgo.setSignNumber(6);
		virgo.setSignMovableFixedDuel(signMovableFixedDuel.get(3));
		virgo.setSignElementsFieryEarthyAiryWatery(signElementsFieryEarthyAiryWatery.get(2));
		
		//7
		Sign libra = new Sign();
		libra.setSignName("Libra");
		libra.setSignNumber(7);
		libra.setSignMovableFixedDuel(signMovableFixedDuel.get(1));
		libra.setSignElementsFieryEarthyAiryWatery(signElementsFieryEarthyAiryWatery.get(3));
		
		//8
		Sign scorpio = new Sign();
		scorpio.setSignName("Scorpio");
		scorpio.setSignNumber(8);
		scorpio.setSignMovableFixedDuel(signMovableFixedDuel.get(2));
		scorpio.setSignElementsFieryEarthyAiryWatery(signElementsFieryEarthyAiryWatery.get(4));
		
		//9
		Sign sagittarius = new Sign();
		sagittarius.setSignName("Sagittarius");
		sagittarius.setSignNumber(9);
		sagittarius.setSignMovableFixedDuel(signMovableFixedDuel.get(3));
		sagittarius.setSignElementsFieryEarthyAiryWatery(signElementsFieryEarthyAiryWatery.get(1));
		
		//10
		Sign capricorn = new Sign();
		capricorn.setSignName("Capricorn");
		capricorn.setSignNumber(10);
		capricorn.setSignMovableFixedDuel(signMovableFixedDuel.get(1));
		capricorn.setSignElementsFieryEarthyAiryWatery(signElementsFieryEarthyAiryWatery.get(2));
		
		//11
		Sign aquarius = new Sign();
		aquarius.setSignName("Aquarius");
		aquarius.setSignNumber(11);
		aquarius.setSignMovableFixedDuel(signMovableFixedDuel.get(2));
		aquarius.setSignElementsFieryEarthyAiryWatery(signElementsFieryEarthyAiryWatery.get(3));
		
		//12
		Sign pisces = new Sign();
		pisces.setSignName("Pisces");
		pisces.setSignNumber(12);
		pisces.setSignMovableFixedDuel(signMovableFixedDuel.get(3));
		pisces.setSignElementsFieryEarthyAiryWatery(signElementsFieryEarthyAiryWatery.get(4));
		
		
		//LinkedHashSet
		signs.put(1,aries);
		signs.put(2,taurus);
		signs.put(3,gemini);
		signs.put(4,cancer);
		signs.put(5,leo);
		signs.put(6,virgo);
		signs.put(7,libra);
		signs.put(8,scorpio);
		signs.put(9,sagittarius);
		signs.put(10,capricorn);
		signs.put(11,aquarius);
		signs.put(12,pisces);
		
		/*for(Map.Entry signs1:signs.entrySet()){  
			   Sign sign = (Sign) signs1.getValue();
			   System.out.println("Key = "+signs1.getKey()+" LinkedHashMap ="+sign.getSignName()+" Number ="+sign.getSignNumber());
		}*/ 
		return signs;
	}
	

	private static LinkedHashMap<String, Planet> prepareStaticPlanetsData() {
		LinkedHashMap<String,Planet> planets = new LinkedHashMap<String,Planet>();
		
		//SUN
		Planet sun = new Planet();
		sun.setPlanetName("SUN");
			LinkedHashMap<Integer,Integer> sunAspect = new LinkedHashMap<Integer,Integer>(); 
			sunAspect.put(1, 7);
		sun.setDefaultAspect(sunAspect);
			ArrayList<House> sunHouseOwnerList = new ArrayList<House>();
				House sunHouse = new House();
				sunHouse.setHouseSign(signs.get(5));
				sunHouseOwnerList.add(sunHouse);
		sun.setDefaultHouseOwner(sunHouseOwnerList);
			House sunHouseExaltation = new House();
			sunHouseExaltation.setHouseSign(signs.get(1));
		sun.setExaltationHouse(sunHouseExaltation);
			House sunDebilitationHouse = new House();
			sunDebilitationHouse.setHouseSign(signs.get(7));
		sun.setDebilitationHouse(sunDebilitationHouse);
		
		planets.put("SUN", sun);
		
		//MOON
		Planet moon = new Planet();
		moon.setPlanetName("MOON");
			LinkedHashMap<Integer,Integer> moonAspect = new LinkedHashMap<Integer,Integer>(); 
			moonAspect.put(1, 7);
		moon.setDefaultAspect(moonAspect);
			ArrayList<House> moonHouseOwnerList = new ArrayList<House>();
				House moonHouse = new House();
				moonHouse.setHouseSign(signs.get(4));
				moonHouseOwnerList.add(moonHouse);
		moon.setDefaultHouseOwner(moonHouseOwnerList);
			House moonHouseExaltation = new House();
			moonHouseExaltation.setHouseSign(signs.get(2));
		moon.setExaltationHouse(moonHouseExaltation);
			House moonDebilitationHouse = new House();
			moonDebilitationHouse.setHouseSign(signs.get(8));
		moon.setDebilitationHouse(moonDebilitationHouse);
		
		planets.put("MOON", moon);
		
		//MARS
		Planet mars = new Planet();
		mars.setPlanetName("MARS");
			LinkedHashMap<Integer,Integer> marsAspect = new LinkedHashMap<Integer,Integer>(); 
			marsAspect.put(1, 4);
			marsAspect.put(2, 7);
			marsAspect.put(3, 8);
		mars.setDefaultAspect(marsAspect);
			ArrayList<House> marsHouseOwnerList = new ArrayList<House>();
				House marsHouse1 = new House();
				marsHouse1.setHouseSign(signs.get(1));
				marsHouseOwnerList.add(marsHouse1);
				House marsHouse2 = new House();
				marsHouse2.setHouseSign(signs.get(8));
				marsHouseOwnerList.add(marsHouse2);
		mars.setDefaultHouseOwner(marsHouseOwnerList);
			House marsHouseExaltation = new House();
			marsHouseExaltation.setHouseSign(signs.get(10));
		mars.setExaltationHouse(marsHouseExaltation);
			House marsDebilitationHouse = new House();
			marsDebilitationHouse.setHouseSign(signs.get(4));
		mars.setDebilitationHouse(marsDebilitationHouse);
		
		planets.put("MARS", mars);
		
		//MERCURY
		Planet mercury = new Planet();
		mercury.setPlanetName("MERCURY");
			LinkedHashMap<Integer,Integer> mercuryAspect = new LinkedHashMap<Integer,Integer>(); 
			mercuryAspect.put(1, 7);
		mercury.setDefaultAspect(mercuryAspect);
			ArrayList<House> mercuryHouseOwnerList = new ArrayList<House>();
				House mercury1 = new House();
				mercury1.setHouseSign(signs.get(3));
				mercuryHouseOwnerList.add(mercury1);
				House mercury2 = new House();
				mercury2.setHouseSign(signs.get(6));
				mercuryHouseOwnerList.add(mercury2);
		mercury.setDefaultHouseOwner(mercuryHouseOwnerList);
			House mercuryHouseExaltation = new House();
			mercuryHouseExaltation.setHouseSign(signs.get(6));
		mercury.setExaltationHouse(mercuryHouseExaltation);
			House mercuryDebilitationHouse = new House();
			mercuryDebilitationHouse.setHouseSign(signs.get(12));
		mercury.setDebilitationHouse(mercuryDebilitationHouse);
		
		planets.put("MERCURY", mercury);
		
		
		//JUPITER
		Planet jupiter = new Planet();
		jupiter.setPlanetName("JUPITER");
			LinkedHashMap<Integer,Integer> jupiterAspect = new LinkedHashMap<Integer,Integer>(); 
			jupiterAspect.put(1, 5);
			jupiterAspect.put(2, 7);
			jupiterAspect.put(3, 9);
		jupiter.setDefaultAspect(jupiterAspect);
			ArrayList<House> jupiterHouseOwnerList = new ArrayList<House>();
				House jupiter1 = new House();
				jupiter1.setHouseSign(signs.get(9));
				jupiterHouseOwnerList.add(jupiter1);
				House jupiter2 = new House();
				jupiter2.setHouseSign(signs.get(12));
				jupiterHouseOwnerList.add(jupiter2);
		jupiter.setDefaultHouseOwner(jupiterHouseOwnerList);
			House jupiterHouseExaltation = new House();
			jupiterHouseExaltation.setHouseSign(signs.get(4));
		jupiter.setExaltationHouse(jupiterHouseExaltation);
			House jupiterDebilitationHouse = new House();
			jupiterDebilitationHouse.setHouseSign(signs.get(10));
		jupiter.setDebilitationHouse(jupiterDebilitationHouse);
		
		planets.put("JUPITER", jupiter);
		
		
		
		//VENUS
		Planet venus = new Planet();
		venus.setPlanetName("VENUS");
			LinkedHashMap<Integer,Integer> venusAspect = new LinkedHashMap<Integer,Integer>(); 
			venusAspect.put(1, 7);
		venus.setDefaultAspect(venusAspect);
			ArrayList<House> venusHouseOwnerList = new ArrayList<House>();
				House venus1 = new House();
				venus1.setHouseSign(signs.get(2));
				venusHouseOwnerList.add(venus1);
				House venus2 = new House();
				venus2.setHouseSign(signs.get(7));
				venusHouseOwnerList.add(venus2);
		venus.setDefaultHouseOwner(venusHouseOwnerList);
			House venusHouseExaltation = new House();
			venusHouseExaltation.setHouseSign(signs.get(12));
		venus.setExaltationHouse(venusHouseExaltation);
			House venusDebilitationHouse = new House();
			venusDebilitationHouse.setHouseSign(signs.get(6));
		venus.setDebilitationHouse(venusDebilitationHouse);
		
		planets.put("VENUS", venus);
		
		
		//SATURN
		Planet saturn = new Planet();
		saturn.setPlanetName("SATURN");
			LinkedHashMap<Integer,Integer> saturnAspect = new LinkedHashMap<Integer,Integer>(); 
			saturnAspect.put(1, 3);
			saturnAspect.put(2, 7);
			saturnAspect.put(3, 10);
		saturn.setDefaultAspect(saturnAspect);
			ArrayList<House> saturnHouseOwnerList = new ArrayList<House>();
				House saturn1 = new House();
				saturn1.setHouseSign(signs.get(10));
				saturnHouseOwnerList.add(saturn1);
				House saturn2 = new House();
				saturn2.setHouseSign(signs.get(11));
				saturnHouseOwnerList.add(saturn2);
		saturn.setDefaultHouseOwner(saturnHouseOwnerList);
			House saturnHouseExaltation = new House();
			saturnHouseExaltation.setHouseSign(signs.get(7));
		saturn.setExaltationHouse(saturnHouseExaltation);
			House saturnDebilitationHouse = new House();
			saturnDebilitationHouse.setHouseSign(signs.get(1));
		saturn.setDebilitationHouse(saturnDebilitationHouse);
		
		planets.put("SATURN", saturn);
		
		
		//RAHU
		Planet rahu = new Planet();
		rahu.setPlanetName("RAHU");
			LinkedHashMap<Integer,Integer> rahuAspect = new LinkedHashMap<Integer,Integer>(); 
			rahuAspect.put(1, 5);
			rahuAspect.put(2, 7);
			rahuAspect.put(3, 9);
		rahu.setDefaultAspect(rahuAspect);
			ArrayList<House> rahuHouseOwnerList = new ArrayList<House>();
				House rahu1 = new House();
				rahu1.setHouseSign(signs.get(11));
				rahuHouseOwnerList.add(rahu1);
		rahu.setDefaultHouseOwner(rahuHouseOwnerList);
			House rahuHouseExaltation = new House();
			rahuHouseExaltation.setHouseSign(signs.get(3));
		rahu.setExaltationHouse(rahuHouseExaltation);
			House rahuDebilitationHouse = new House();
			rahuDebilitationHouse.setHouseSign(signs.get(9));
		rahu.setDebilitationHouse(rahuDebilitationHouse);
		
		planets.put("RAHU", rahu);
		
		
		//KETU
		Planet ketu = new Planet();
		ketu.setPlanetName("KETU");
			LinkedHashMap<Integer,Integer> ketuAspect = new LinkedHashMap<Integer,Integer>(); 
			ketuAspect.put(1, 5);
			ketuAspect.put(2, 7);
			ketuAspect.put(3, 9);
		ketu.setDefaultAspect(ketuAspect);
			ArrayList<House> ketuHouseOwnerList = new ArrayList<House>();
				House ketu1 = new House();
				ketu1.setHouseSign(signs.get(8));
				ketuHouseOwnerList.add(ketu1);
		ketu.setDefaultHouseOwner(ketuHouseOwnerList);
			House ketuHouseExaltation = new House();
			ketuHouseExaltation.setHouseSign(signs.get(9));
		ketu.setExaltationHouse(ketuHouseExaltation);
			House ketuDebilitationHouse = new House();
			ketuDebilitationHouse.setHouseSign(signs.get(3));
		ketu.setDebilitationHouse(ketuDebilitationHouse);
		
		planets.put("KETU", ketu);
		
		/*for(Map.Entry planet:planets.entrySet()){
			   System.out.println("==============================================================================");
			   Planet planet1 = (Planet) planet.getValue();
			   System.out.println("Key = "+planet.getKey()+" Planet Name ="+planet1.getPlanetName());
			   System.out.println("Planet ExaltationHouse="+planet1.getExaltationHouse().getHouseSign().getSignName());
			   System.out.println("Planet DebilitationHouse="+planet1.getDebilitationHouse().getHouseSign().getSignName());
				for (House houseOwner : planet1.getDefaultHouseOwner()) {
					System.out.println("Planet Default House Owner ="+houseOwner.getHouseSign().getSignName());
				}
				
				
				for(Map.Entry planetAspect:planet1.getDefaultAspect().entrySet()){  
					   System.out.println("Key Aspect = "+planetAspect.getKey()+" Aspect ="+planetAspect.getValue());
				}
		}*/
		
		return planets;
	}


	private static Planet getSignOwner(Integer signNumber) {
	return null;
	}


	/**
	 * Get the House Owner
	 */
	public static Planet getHouseOwner(Sign sign) {
		Planet planetOwner = new Planet();
		for(Map.Entry planet:planets.entrySet()){
			   Planet planet1 = (Planet) planet.getValue();
				for (House houseOwner : planet1.getDefaultHouseOwner()) {
					//System.out.println("Planet Default House Owner Sign inside getHouseOwner Method ="+houseOwner.getHouseSign().getSignName());
						if(sign.getSignName().equalsIgnoreCase(houseOwner.getHouseSign().getSignName())) {
							planetOwner = planet1;
						}
				}
		}
		return planetOwner ;
	}



	//Getter and Setter
	public static LinkedHashMap<Integer, Sign> getSigns() {
		return signs;
	}


	public static void setSigns(LinkedHashMap<Integer, Sign> signs) {
		Utils.signs = signs;
	}
	
	public static LinkedHashMap<String, Planet> getPlanets() {
		return planets;
	}


	public static void setPlanets(LinkedHashMap<String, Planet> planets) {
		Utils.planets = planets;
	}


	public static void horoscopeRetrive(LinkedHashMap<String, NatalChart> natalCharts) {
		System.out.println(":: Utils :: horoscopeRetrive :: Start");
		
		for(Map.Entry natalChart:natalCharts.entrySet()){
			StringBuffer sb = new StringBuffer();
			sb.append("==============================================").append(System.getProperty("line.separator"));
			NatalChart currentNatalChart = (NatalChart) natalChart.getValue();
			sb.append("Natal Chart Name ="+currentNatalChart.getNatalChartName()).append(System.getProperty("line.separator"));
			sb.append("Natal Chart Division Symbol ="+currentNatalChart.getDivisionSymbol()).append(System.getProperty("line.separator"));
			//Loop Chart
			for(Map.Entry house:currentNatalChart.getHouses().entrySet()){
				sb.append(/*"==========="+currentNatalChart.getDivisionSymbol()*/" - ").append(System.getProperty("line.separator"));
				House house1 = (House) house.getValue();
				sb.append(" House Numer = "+house.getKey());
				sb.append(" House Sign = "+"[ "+house1.getHouseSign().getSignName()+" ]");
				
				if(house1.isAscendant()) {
					sb.append(" This House is Ascendant = "+house1.getHouseSign().getSignName()+"******ASD");
				}
							
					if(house1.getPlanetsContainHouse().size() > 0) {
						for (Planet planetContain : house1.getPlanetsContainHouse()) {
							sb.append(" Planet Contain House = "+"[ "+planetContain.getPlanetName()+" ]");
						}
					}else {
						sb.append( "No Planet Contain House ");
					}
			}
			System.out.println("Print "+sb);
		}
		
		
		System.out.println(":: Utils :: horoscopeRetrive :: End");
	}


	public static Integer houseToHouseOwner(Integer houseNumberIs, Integer houseOwnerPlacedAt) {//11,3
		Integer sings12[] = {1,2,3,4,5,6,7,8,9,10,11,12};
		int start = houseNumberIs;
		int end = houseOwnerPlacedAt;
		int result = 1;
				for (int i = 0; i < sings12.length; i++) {
					result++;
					//System.out.println("=="+result++);
				    //System.out.println("houseToHouseOwner F= "+sings12[(start + i) % sings12.length]);
				    if(sings12[(start + i) % sings12.length] == end) {
				    	break;
				    }
				}
				if(result>12) {
					result=0;
				}
				for (int i = 0; i < sings12.length; i++) {
				    //System.out.println("houseToHouseOwner B= "+sings12[(start - i) % sings12.length]);
				}
		return result;//5
	}


	public static Integer houseOwnerToHouse(Integer houseNumberIs, Integer houseOwnerPlacedAt) {//3,11
		Integer sings12[] = {1,2,3,4,5,6,7,8,9,10,11,12};
		int start = houseOwnerPlacedAt;
		int end = houseNumberIs;
		int result = 1;
				for (int i = 0; i < sings12.length; i++) {
					result++;
				    if(sings12[(start + i) % sings12.length] == end) {
				    	break;
				    }
				}
				if(result>12) {
					result=0;
				}
		return result;
	}


	@SuppressWarnings("rawtypes")
	public static void loopHousePlanetDetailsForPredictionList(
			LinkedHashMap<Integer, HousePlanetDetailsForPredictionVO> housePlanetDetailsForPredictionList) {
			
		for(Map.Entry housePlanetDetailsForPrediction:housePlanetDetailsForPredictionList.entrySet()){
			HousePlanetDetailsForPredictionVO housePlanetDetailsForPredictionVO = (HousePlanetDetailsForPredictionVO) housePlanetDetailsForPrediction.getValue();
			   System.out.println("Key = "+housePlanetDetailsForPrediction.getKey());
			   System.out.println("House Number = "+housePlanetDetailsForPredictionVO.getHouseNumber());
			   System.out.println("House Sign Name = "+housePlanetDetailsForPredictionVO.getHouseSignName());
			   System.out.println("House Owner Name = "+housePlanetDetailsForPredictionVO.getHouseOwner());
			   System.out.println("House Owner Placed At House = "+housePlanetDetailsForPredictionVO.getHouseOwnerPlacedAtHouse());
			   System.out.println("House Owner To House Distance = "+housePlanetDetailsForPredictionVO.getHouseOwnerToHouseDistance());
			   System.out.println("House To House Owner Distance = "+housePlanetDetailsForPredictionVO.getHouseToHouseOwnerDistance());
		}
	}


	public static Sign getNextSign(Sign sign) {
		Integer nextSignNumber = sign.getSignNumber();
		if(nextSignNumber == 12) {
			nextSignNumber = 1;
		}else {
			nextSignNumber+=1;
		}
		return signs.get(nextSignNumber);
	}

	/**
	 * This Method help while preparing the chart at initial stage
	 * @param planetsAtHouse
	 * @param houseNumber
	 * @return
	 */
	public static List<Planet> getHouseContainPlanet(LinkedHashMap<Integer, String[]> planetsAtHouse, Integer houseNumber) {
		List<Planet> houseContainPlanet = new ArrayList<Planet>();
		String[] planetsName = planetsAtHouse.get(houseNumber);
		for (String planetName : planetsName) {
			//System.out.println("===+=== Planet Name"+planetName+"House Number"+houseNumber);
			if(!planetName.equalsIgnoreCase("-")) {
				houseContainPlanet.add(planets.get(planetName));
			}
		}
		return houseContainPlanet;
	}

	//LinkedHashMap<String, Sign>  signMovableFixedDuel = new LinkedHashMap<Integer, Prediction>();
	//Asd is which kind of sign Movable , Fixed , Duel
	public static String getSignMovableFixedDuel(House house) {
		return signs.get(house.getHouseSign().getSignNumber()).getSignMovableFixedDuel();
	}
	
	
	public static NatalChart prepareNatalChartData(String natalChartName,Integer ascendant,LinkedHashMap<Integer, String[]> planetsAtHouse, String divisionSymbol) {
		NatalChart natalChart = new NatalChart();
		
		natalChart.setNatalChartName(natalChartName);
		natalChart.setDivisionSymbol(divisionSymbol);
		//List<House> myHouses = new ArrayList<House>();
		LinkedHashMap<Integer, House> myHouses = new LinkedHashMap<Integer, House>();
		//1st House
		House house1st = new House();
		house1st.setAscendant(true);
		house1st.setHouseNumber(1);
		house1st.setHouseSign(signs.get(ascendant));
		house1st.setPlanetsContainHouse(Utils.getHouseContainPlanet(planetsAtHouse,house1st.getHouseNumber()));
		myHouses.put(1, house1st);
		
		//2nd House
		House house2nd = new House();
		house2nd.setHouseNumber(2);
		//house2nd.setHouseSign(signs.get(12));
		house2nd.setHouseSign(Utils.getNextSign(house1st.getHouseSign()));
		/*List<Planet> house2ndContainPlanet = new ArrayList<Planet>();
		house2ndContainPlanet.add(planets.get("SUN"));
		house2ndContainPlanet.add(planets.get("MERCURY"));*/
		house2nd.setPlanetsContainHouse(Utils.getHouseContainPlanet(planetsAtHouse,house2nd.getHouseNumber()));
		myHouses.put(2,house2nd);
		
		//3rd House
		House house3rd = new House();
		house3rd.setHouseNumber(3);
		house3rd.setHouseSign(Utils.getNextSign(house2nd.getHouseSign()));
		house3rd.setPlanetsContainHouse(Utils.getHouseContainPlanet(planetsAtHouse,house3rd.getHouseNumber()));
		myHouses.put(3,house3rd);
		
		//4th House
		House house4th = new House();
		house4th.setHouseNumber(4);
		house4th.setHouseSign(Utils.getNextSign(house3rd.getHouseSign()));
		//List<Planet> house4thContainPlanet = new ArrayList<Planet>();
		//house4thContainPlanet.add(planets.get("MARS"));
		//house4thContainPlanet.add(planets.get("VENUS"));
		house4th.setPlanetsContainHouse(Utils.getHouseContainPlanet(planetsAtHouse,house4th.getHouseNumber()));
		
		myHouses.put(4,house4th);
		
		//5th House
		House house5th = new House();
		house5th.setHouseNumber(5);
		house5th.setHouseSign(Utils.getNextSign(house4th.getHouseSign()));
		//List<Planet> house5thContainPlanet = new ArrayList<Planet>();
		//house5thContainPlanet.add(planets.get("RAHU"));
		//house4thContainPlanet.add(planets.get("VENUS"));
		house5th.setPlanetsContainHouse(Utils.getHouseContainPlanet(planetsAtHouse,house5th.getHouseNumber()));
		
		myHouses.put(5,house5th);
		
		
		//6th House
		House house6th = new House();
		house6th.setHouseNumber(6);
		house6th.setHouseSign(Utils.getNextSign(house5th.getHouseSign()));
		//List<Planet> house6thContainPlanet = new ArrayList<Planet>();
		//house6thContainPlanet.add(planets.get("RAHU"));
		//house4thContainPlanet.add(planets.get("VENUS"));
		house6th.setPlanetsContainHouse(Utils.getHouseContainPlanet(planetsAtHouse,house6th.getHouseNumber()));
		
		myHouses.put(6,house6th);
		
		
		//7th House
		House house7th = new House();
		house7th.setHouseNumber(7);
		house7th.setHouseSign(Utils.getNextSign(house6th.getHouseSign()));
		//List<Planet> house7thContainPlanet = new ArrayList<Planet>();
		//house6thContainPlanet.add(planets.get("RAHU"));
		//house4thContainPlanet.add(planets.get("VENUS"));
		house7th.setPlanetsContainHouse(Utils.getHouseContainPlanet(planetsAtHouse,house7th.getHouseNumber()));
		
		myHouses.put(7,house7th);
		
		
		//8th House
		House house8th = new House();
		house8th.setHouseNumber(8);
		house8th.setHouseSign(Utils.getNextSign(house7th.getHouseSign()));
		//List<Planet> house8thContainPlanet = new ArrayList<Planet>();
		//house6thContainPlanet.add(planets.get("RAHU"));
		//house4thContainPlanet.add(planets.get("VENUS"));
		house8th.setPlanetsContainHouse(Utils.getHouseContainPlanet(planetsAtHouse,house8th.getHouseNumber()));
		
		myHouses.put(8,house8th);
		
		//9th House
		House house9th = new House();
		house9th.setHouseNumber(9);
		house9th.setHouseSign(Utils.getNextSign(house8th.getHouseSign()));
		//List<Planet> house9thContainPlanet = new ArrayList<Planet>();
		//house9thContainPlanet.add(planets.get("SATURN"));
		//house4thContainPlanet.add(planets.get("VENUS"));
		house9th.setPlanetsContainHouse(Utils.getHouseContainPlanet(planetsAtHouse,house9th.getHouseNumber()));
		
		myHouses.put(9,house9th);
		
		//10th House
		House house10th = new House();
		house10th.setHouseNumber(10);
		house10th.setHouseSign(Utils.getNextSign(house9th.getHouseSign()));
		//List<Planet> house10thContainPlanet = new ArrayList<Planet>();
		//house10thContainPlanet.add(planets.get("JUPITER"));
		//house4thContainPlanet.add(planets.get("VENUS"));
		house10th.setPlanetsContainHouse(Utils.getHouseContainPlanet(planetsAtHouse,house10th.getHouseNumber()));
		
		myHouses.put(10,house10th);
		
		//11th House
		House house11th = new House();
		house11th.setHouseNumber(11);
		house11th.setHouseSign(Utils.getNextSign(house10th.getHouseSign()));
		//List<Planet> house11thContainPlanet = new ArrayList<Planet>();
		//house11thContainPlanet.add(planets.get("MOON"));
		//house11thContainPlanet.add(planets.get("KETU"));
		house11th.setPlanetsContainHouse(Utils.getHouseContainPlanet(planetsAtHouse,house11th.getHouseNumber()));
		
		myHouses.put(11,house11th);
		
		//12th House
		House house12th = new House();
		house12th.setHouseNumber(12);
		house12th.setHouseSign(Utils.getNextSign(house11th.getHouseSign()));
		//List<Planet> house12thContainPlanet = new ArrayList<Planet>();
		//house11thContainPlanet.add(planets.get("MOON"));
		//house11thContainPlanet.add(planets.get("KETU"));
		house12th.setPlanetsContainHouse(Utils.getHouseContainPlanet(planetsAtHouse,house12th.getHouseNumber()));
		
		myHouses.put(12,house12th);
		
		setPlanetExaltedAndDebilitation(myHouses);
		
		natalChart.setHouses(myHouses);
		return natalChart;
	}
	
	

	/**
	 * Set Planets Exalted and Debilitation
	 * @param myHouses
	 */
	@SuppressWarnings("unlikely-arg-type")
	private static void setPlanetExaltedAndDebilitation(LinkedHashMap<Integer, House> myNatalChart) {
		for(Map.Entry house:myNatalChart.entrySet()){
			House houseCurrent = (House) house.getValue();
			List<Planet> listOfCurrentHousePlanets =  houseCurrent.getPlanetsContainHouse();
			for (Planet planet : listOfCurrentHousePlanets) {
				if(planet.getExaltationHouse().getHouseSign().equals(houseCurrent.getHouseSign())) {
					planet.setExalted(true);
				}
				if(planet.getDebilitationHouse().getHouseSign().equals(houseCurrent.getHouseSign())) {
					planet.setDebilitation(true);
				}
				
				for (House planetHouseOwner : planet.getDefaultHouseOwner()) {//Ven 2,7 sign
					if(houseCurrent.getHouseSign().getSignName().equals(planetHouseOwner.getHouseSign().getSignName())) {
						planet.setOwnSign(true);
					}
				}
			}
		}
	}


	public static StringBuffer printPredictionResult(Prediction prediction, String ruleNumber) {
		StringBuffer sb = new StringBuffer();
		sb.append(System.getProperty("line.separator"));
		sb.append("========######==============######==========@@@@@@@@=======######========"+ruleNumber+"=========########=====@@@@@@@@====$$$$$$$$$$$$$$====START");
		sb.append(System.getProperty("line.separator"));
		sb.append("--------------------").append(System.getProperty("line.separator"));
		sb.append("Rule Name").append(System.getProperty("line.separator"));
		sb.append("--------------------").append(System.getProperty("line.separator"));
		sb.append(prediction.getRuleName()).append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
		sb.append("--------------------");
		sb.append(System.getProperty("line.separator"));
		sb.append("Rule Reference");
		sb.append(System.getProperty("line.separator"));
		sb.append("--------------------");
		sb.append(System.getProperty("line.separator"));
		sb.append(prediction.getReference());
		sb.append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
		sb.append("--------------------");
		sb.append(System.getProperty("line.separator"));
		sb.append("Rule Description");
		sb.append(System.getProperty("line.separator"));
		sb.append("--------------------");
		sb.append(System.getProperty("line.separator"));
		sb.append(prediction.getDescription());
		sb.append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
		sb.append("--------------------");
		sb.append(System.getProperty("line.separator"));
		sb.append("Rule Single Line Prediction");
		sb.append(System.getProperty("line.separator"));
		sb.append("--------------------");
		sb.append(System.getProperty("line.separator"));
		sb.append(prediction.getSingleLinePrediction());
		sb.append(System.getProperty("line.separator"));
			for(Map.Entry predictionResult:prediction.getPredictionResult().entrySet()) {
				sb.append(System.getProperty("line.separator"));
				sb.append("--------------------").append(System.getProperty("line.separator"));
				sb.append("Result Key").append(System.getProperty("line.separator"));
				sb.append("--------------------").append(System.getProperty("line.separator"));
				sb.append(predictionResult.getKey()).append(System.getProperty("line.separator"));
				
				sb.append(System.getProperty("line.separator"));
				sb.append("--------------------").append(System.getProperty("line.separator"));
				sb.append("Result Details").append(System.getProperty("line.separator"));
				sb.append("--------------------").append(System.getProperty("line.separator"));
				sb.append(predictionResult.getValue()).append(System.getProperty("line.separator"));
			}
		sb.append(System.getProperty("line.separator"));
		sb.append("========######==============######==========@@@@@@@@=======######========"+ruleNumber+"=========########=====@@@@@@@@====$$$$$$$$$$$$$$====END");
		sb.append(System.getProperty("line.separator"));
		System.out.println(sb);
		//writeToFile("C:\\DHARMARAJ\\RAJ_Personal\\ASTRO\\Prediction File\\test.txt",sb);
		return sb;
	}
	
	

	public static void writeToFile(String pFilename, StringBuffer pData) {
		try {
			BufferedWriter out = new BufferedWriter(new java.io.FileWriter(pFilename));
			out.write(pData.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	public static Planet getLagnaLord(NatalChart natalChart) {
		System.out.println(":: Utils :: getLagnaLord :: Start");
		House lagnaHouse = natalChart.getHouses().get(1);
		Planet houseOwner = lagnaHouse.getHouseOwner();
		
		if(houseOwner.getPlanetName().equals("RAHU")) {
			System.out.println(":: Utils :: getLagnaLord :: RAHU Found");
			houseOwner = planets.get("SATURN");
		}
		System.out.println(":: Utils :: getLagnaLord :: End");
		return houseOwner;
	}

	/**
	 * Find out which house this planet placed
	 * @param natalChart
	 * @param lagnaLord
	 * @return
	 */
	public static House getHouseWherePlanetPlaced(NatalChart natalChart, Planet lagnaLord) {
		House resultHouse = null;
		for(Map.Entry house:natalChart.getHouses().entrySet()){
			House houseCurrent = (House) house.getValue();
			if(houseCurrent.getPlanetsContainHouse().contains(lagnaLord)) {
				resultHouse = houseCurrent;
			}
		}
		return resultHouse;
	}

	/**
	 * This Method to Find out A planet placed at house how far this planet is from other house in second parameter house
	 * @param planetPlacedHouse
	 * @param house
	 * @return
	 */
	public static Integer getHowFarThisPlanetPlacedFromHouse(House planetPlacedHouse, House house) {
		return houseToHouseOwner(house.getHouseNumber(),planetPlacedHouse.getHouseNumber());//1,9 = 9;
	}


	public static Integer add1stParameterHouseto2ndParameterHouse(Integer house12ToLagnaLordDistance, House planetPlacedHouse) {//10,
		//houseOwnerToHouse(house12ToLagnaLordDistance,planetPlacedHouse.getHouseNumber());
		
		Integer sings12[] = {1,2,3,4,5,6,7,8,9,10,11,12};
		int start = planetPlacedHouse.getHouseNumber();
		int add = house12ToLagnaLordDistance-1;
		int result = 0;
				for (int i = 0; i < add; i++) {
					result = sings12[(start + i) % sings12.length];
				    				}
				/*if(result>12) {
					result=0;
				}*/
		
		return result;
	}

	/**
	 * Get House by passing parameter of any Sign
	 * @param houseSign
	 * @param natalChart
	 * @return
	 */
	public static House getHousebySign(Sign houseSign, NatalChart natalChart) {
		House resultHouse = null;
		for(Map.Entry house:natalChart.getHouses().entrySet()){
			House houseCurrent = (House) house.getValue();
			if(houseCurrent.getHouseSign().getSignName().equalsIgnoreCase(houseSign.getSignName())) {
				resultHouse = houseCurrent;
			}
		}
		return resultHouse;
	}

	/**
	 * From a Sign looking for 6,8,12 houses from given NatalChart
	 * @param lagnaSign
	 * @param natalChart
	 * @param hosueNumbers
	 * @return
	 */
	public static LinkedHashMap<Integer, House> getHousesFromThisSign(Sign lagnaSign, NatalChart natalChart,Integer[] hosueNumbers) {
		LinkedHashMap<Integer, House> houses = new LinkedHashMap<Integer, House>();
			for (Integer integer : hosueNumbers) {
				houses.put(integer,natalChart.getHouses().get(add1stParameterHouseto2ndParameterHouse(integer,getHousebySign(lagnaSign,natalChart))));
			}
		return houses;
	}


	/**
	 * Get Planet Object by Passing Sign Number and Natal Chart
	 * @param signNumberToFetchSignOwner
	 * @param natalChartD1
	 * @return
	 */
	public static Planet isThisPlanetContain(int signNumberToFetchSignOwner, NatalChart natalChartD1) {
		return getHousebySign(Utils.getSigns().get(signNumberToFetchSignOwner), natalChartD1).getHouseOwner();
	}

	
	public static Integer getThisPlanetCombinationPresentInThisChart(String[] strings, NatalChart natalChartD1) {
		HashSet<Integer> houseNumber = new HashSet<Integer>();
		Integer resultHouse = 0;
		for (String string : strings) {
			houseNumber.add(getHouseWherePlanetPlaced(natalChartD1,planets.get(string)).getHouseNumber());
		}
		if(houseNumber.size()==1) {
			for (Integer integer : houseNumber) {
				resultHouse=integer;
			}
		}
		return resultHouse;
	}
	
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null) return true;
		if (obj instanceof String) return ((String) obj).trim().equals("");
		return false;
	}
}
