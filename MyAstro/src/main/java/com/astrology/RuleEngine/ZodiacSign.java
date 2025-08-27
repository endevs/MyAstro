package com.astrology.RuleEngine;

public enum ZodiacSign {
    ARIES(Planet.MARS),
    TAURUS(Planet.VENUS),
    GEMINI(Planet.MERCURY),
    CANCER(Planet.MOON),
    LEO(Planet.SUN),
    VIRGO(Planet.MERCURY),
    LIBRA(Planet.VENUS),
    SCORPIO(Planet.MARS),
    SAGITTARIUS(Planet.JUPITER),
    CAPRICORN(Planet.SATURN),
    AQUARIUS(Planet.SATURN),
    PISCES(Planet.JUPITER);

    private final Planet lord;

    ZodiacSign(Planet lord) {
        this.lord = lord;
    }

    public Planet getLord() {
        return lord;
    }
}