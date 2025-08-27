package com.astrology.RuleEngine;

public enum Planet {
    SUN(ZodiacSign.ARIES),
    MOON(ZodiacSign.TAURUS),
    MARS(ZodiacSign.CAPRICORN),
    MERCURY(ZodiacSign.VIRGO),
    JUPITER(ZodiacSign.CANCER),
    VENUS(ZodiacSign.PISCES),
    SATURN(ZodiacSign.LIBRA),
    RAHU(null), // Rahu and Ketu do not have exaltation signs in traditional astrology
    KETU(null);

    private final ZodiacSign exaltationSign;

    Planet(ZodiacSign exaltationSign) {
        this.exaltationSign = exaltationSign;
    }

    public ZodiacSign getExaltationSign() {
        return exaltationSign;
    }
}