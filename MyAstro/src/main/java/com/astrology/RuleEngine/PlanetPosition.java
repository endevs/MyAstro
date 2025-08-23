package com.astrology.RuleEngine;

public class PlanetPosition {
	private Planet planet;
    private int house;
    private ZodiacSign sign;
    private double degree;
    private Nakshatra nakshatra;

    // Constructor, getters, setters
    public PlanetPosition(Planet planet, int house, ZodiacSign sign, double degree, Nakshatra nakshatra) {
        if (house < 1 || house > 12) {
            throw new IllegalArgumentException("House number must be between 1 and 12.");
        }
        this.planet = planet;
        this.house = house;
        this.sign = sign;
        this.degree = degree;
        this.nakshatra = nakshatra;
    }

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}

	public int getHouse() {
		return house;
	}

	public void setHouse(int house) {
        if (house < 1 || house > 12) {
            throw new IllegalArgumentException("House number must be between 1 and 12.");
        }
		this.house = house;
	}

	public ZodiacSign getSign() {
		return sign;
	}

	public void setSign(ZodiacSign sign) {
		this.sign = sign;
	}

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public Nakshatra getNakshatra() {
		return nakshatra;
	}

	public void setNakshatra(Nakshatra nakshatra) {
		this.nakshatra = nakshatra;
	}
    
    
}