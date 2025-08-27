package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.ZodiacSign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DivisionalChartData {
    private final DivisionalChart type;
    private final Map<Planet, PlanetPosition> planetPositions;
    private final Map<Integer, Planet> houseLords;
    private ZodiacSign ascendant;

    public DivisionalChartData(DivisionalChart type) {
        this.type = type;
        this.planetPositions = new HashMap<>();
        this.houseLords = new HashMap<>();
    }

    public void addPlanetPosition(Planet planet, PlanetPosition position) {
        if (planet != null && position != null) {
            planetPositions.put(planet, position);
        }
    }

    public PlanetPosition getPlanetPosition(Planet planet) {
        return planetPositions.get(planet);
    }

    public ZodiacSign getAscendant() {
        return ascendant;
    }

    public void setAscendant(ZodiacSign ascendant) {
        this.ascendant = ascendant;
    }

    public DivisionalChart getType() {
        return type;
    }

    public Map<Planet, PlanetPosition> getPlanetPositions() {
        return planetPositions;
    }

    public Map<Integer, Planet> getHouseLords() {
        return houseLords;
    }

    public ZodiacSign getZodiacSignForHouse(int houseNumber) {
        if (ascendant == null) {
            return null;
        }
        return ZodiacSign.values()[(ascendant.ordinal() + houseNumber - 1) % 12];
    }

    public boolean isPlanetInHouse(Planet planet, int houseNumber) {
        PlanetPosition position = planetPositions.get(planet);
        return position != null && position.getHouse() == houseNumber;
    }

    public Planet getLagnaLord() {
        return getLordOfHouse(1);
    }

    public int getHouseOfPlanet(Planet planet) {
        PlanetPosition position = planetPositions.get(planet);
        return (position != null) ? position.getHouse() : -1;
    }

    public List<Planet> getPlanetsInHouse(int houseNumber) {
        List<Planet> planets = new ArrayList<>();
        for (Map.Entry<Planet, PlanetPosition> entry : planetPositions.entrySet()) {
            if (entry.getValue().getHouse() == houseNumber) {
                planets.add(entry.getKey());
            }
        }
        return planets;
    }

    public boolean arePlanetsInHouse(Planet[] planets, int houseNumber) {
        for (Planet planet : planets) {
            if (!isPlanetInHouse(planet, houseNumber)) {
                return false;
            }
        }
        return true;
    }

    public Planet getLordOfHouse(int houseNumber) {
        ZodiacSign sign = getZodiacSignForHouse(houseNumber);
        return (sign != null) ? sign.getLord() : null;
    }

    public ZodiacSign getSignOfPlanet(Planet planet) {
        PlanetPosition position = planetPositions.get(planet);
        return (position != null) ? position.getSign() : null;
    }

    public int getHouseOfSign(ZodiacSign sign) {
        if (ascendant == null) {
            return -1;
        }
        int diff = sign.ordinal() - ascendant.ordinal();
        if (diff < 0) {
            diff += 12;
        }
        return diff + 1;
    }

    public boolean isPlanetExalted(Planet planet) {
        PlanetPosition position = planetPositions.get(planet);
        return position != null && position.getSign() == planet.getExaltationSign();
    }

    public boolean isPlanetInOwnSign(Planet planet) {
        PlanetPosition position = planetPositions.get(planet);
        return position != null && position.getSign().getLord() == planet;
    }
}
