package com.astrology.MultipDivCharts;

import com.astrology.MultipDivCharts.DivisionalChartData;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.vo.NatalChart;
import com.astrology.vo.House;
import com.astrology.vo.Planet;
import com.astrology.vo.Sign;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NewUtils {

    static LinkedHashMap<Integer,Sign> signs = new LinkedHashMap<Integer,Sign>();
    static LinkedHashMap<String,Planet> planets = new LinkedHashMap<String,Planet>();
    static {
        signs = prepareStaticSignsData();
        planets = prepareStaticPlanetsData();
    }

    public static int calculateHouseToLordDistance(int houseNumber, int houseOwnerPlacedAt) {
        if (houseOwnerPlacedAt >= houseNumber) {
            return houseOwnerPlacedAt - houseNumber + 1;
        } else {
            return 12 - houseNumber + houseOwnerPlacedAt + 1;
        }
    }

    public static NatalChart createNatalChartFromDivisionalChartData(DivisionalChartData d1) {
        NatalChart natalChart = new NatalChart();
        natalChart.setNatalChartName("D1");
        natalChart.setDivisionSymbol("D1");

        LinkedHashMap<Integer, House> houses = new LinkedHashMap<>();
        for (int i = 1; i <= 12; i++) {
            House house = new House();
            house.setHouseNumber(i);
            // This is a simplification. The real sign depends on the ascendant.
            // We will need to adjust this based on the ascendant from d1.
            house.setHouseSign(signs.get(i));
            house.setPlanetsContainHouse(new ArrayList<>());
            houses.put(i, house);
        }

        for (Map.Entry<com.astrology.RuleEngine.Planet, PlanetPosition> entry : d1.getPlanetPositions().entrySet()) {
            PlanetPosition planetPosition = entry.getValue();
            House house = houses.get(planetPosition.getHouse());
            List<Planet> planetsInHouse = house.getPlanetsContainHouse();
            if (planetsInHouse == null) {
                planetsInHouse = new ArrayList<>();
            }
            planetsInHouse.add(planets.get(planetPosition.getPlanet().name()));
            house.setPlanetsContainHouse(planetsInHouse);
        }

        natalChart.setHouses(houses);
        return natalChart;
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

        return signs;
    }


    public static LinkedHashMap<String, Planet> prepareStaticPlanetsData() {
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

        return planets;
    }

    public static Planet getHouseOwner(Sign sign) {
        Planet planetOwner = new Planet();
        for(Map.Entry planet:planets.entrySet()){
               Planet planet1 = (Planet) planet.getValue();
                for (House houseOwner : planet1.getDefaultHouseOwner()) {
                    if(sign.getSignName().equalsIgnoreCase(houseOwner.getHouseSign().getSignName())) {
                        planetOwner = planet1;
                    }
                }
        }
        return planetOwner ;
    }

    public static Planet getLagnaLord(NatalChart natalChart) {
        House lagnaHouse = natalChart.getHouses().get(1);
        Planet houseOwner = lagnaHouse.getHouseOwner();

        if(houseOwner.getPlanetName().equals("RAHU")) {
            houseOwner = planets.get("SATURN");
        }
        return houseOwner;
    }

    public static House getHouseWherePlanetPlaced(NatalChart natalChart, Planet planet) {
        House resultHouse = null;
        for(Map.Entry<Integer, House> entry : natalChart.getHouses().entrySet()){
            House houseCurrent = entry.getValue();
            if(houseCurrent.getPlanetsContainHouse().contains(planet)) {
                resultHouse = houseCurrent;
                break; // Assuming a planet is in only one house
            }
        }
        return resultHouse;
    }

    public static Integer houseToHouseOwner(Integer startHouseNumber, Integer endHouseNumber) {
        int distance = (endHouseNumber - startHouseNumber + 12) % 12;
        if (distance == 0) { // If it's the same house, distance is 1
            return 1;
        }
        return distance + 1; // +1 because distance is inclusive of the end house
    }

    public static Integer add1stParameterHouseto2ndParameterHouse(Integer distance, House startHouse) {
        int startHouseNumber = startHouse.getHouseNumber();
        int targetHouseNumber = ((startHouseNumber - 1 + distance -1) % 12) + 1; // -1 for 0-indexed, +1 for 1-indexed. distance-1 because distance is inclusive.
        if (targetHouseNumber <= 0) { // Handle cases where result might be 0 or negative due to modulo
            targetHouseNumber += 12;
        }
        return targetHouseNumber;
    }

    public static House getHousebySign(Sign houseSign, NatalChart natalChart) {
        House resultHouse = null;
        for(Map.Entry<Integer, House> entry : natalChart.getHouses().entrySet()){
            House houseCurrent = entry.getValue();
            if(houseCurrent.getHouseSign().getSignName().equalsIgnoreCase(houseSign.getSignName())) {
                resultHouse = houseCurrent;
                break;
            }
        }
        return resultHouse;
    }
}