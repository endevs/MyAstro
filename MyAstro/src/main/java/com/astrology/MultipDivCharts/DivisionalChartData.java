package com.astrology.MultipDivCharts;

import java.util.HashMap;
import java.util.Map;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.ZodiacSign;

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

    // Add methods to manage planet positions and house lords
    public void addPlanetPosition(Planet planet, PlanetPosition position) {
        planetPositions.put(planet, position);
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
    
    // Getters omitted for brevity
    
}
