package com.astrology.RuleEngine;

import java.util.HashMap;
import java.util.Map;

public class BirthChart {
	private Map<Planet, PlanetPosition> planets;
	private Map<Integer, Planet> houseLords; // House 1-12 to Planet
	private ZodiacSign ascendant;
	private ZodiacSign moonSign;

	public BirthChart() {
		this.planets = new HashMap<>();
		this.houseLords = new HashMap<>();
	}

	// Methods to add planetary positions
	public void addPlanetPosition(Planet planet, PlanetPosition position) {
		planets.put(planet, position);
	}

	// Getters and setters
	public PlanetPosition getPlanetPosition(Planet planet) {
		return planets.get(planet);
	}

	public ZodiacSign getAscendant() {
		return ascendant;
	}

	public void setAscendant(ZodiacSign ascendant) {
		this.ascendant = ascendant;
	}
}