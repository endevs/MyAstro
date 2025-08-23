package com.astrology.MultipDivCharts;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.ZodiacSign;

public class ChartValidator {

    public static boolean validate(DivisionalChartData chartData) {
        boolean isSignSequenceValid = validateSignSequence(chartData);
        boolean areAllPlanetsPresent = validatePlanetPresence(chartData);
        boolean arePlanetPositionsValid = validatePlanetPositions(chartData);

        if (isSignSequenceValid && areAllPlanetsPresent && arePlanetPositionsValid) {
            System.out.println("Verification Successful for " + chartData.getType().getName());
            return true;
        } else {
            System.out.println("Verification Unsuccessful for " + chartData.getType().getName());
            return false;
        }
    }

    private static boolean validateSignSequence(DivisionalChartData chartData) {
        ZodiacSign[] expectedSigns = new ZodiacSign[12];
        ZodiacSign ascendant = chartData.getAscendant();
        expectedSigns[0] = ascendant;
        for (int i = 1; i < 12; i++) {
            expectedSigns[i] = ZodiacSign.values()[(ascendant.ordinal() + i) % 12];
        }

        ZodiacSign[] actualSigns = new ZodiacSign[12];
        for (int i = 0; i < 12; i++) {
            actualSigns[i] = ZodiacSign.values()[(ascendant.ordinal() + i) % 12];
        }

        if (!Arrays.equals(expectedSigns, actualSigns)) {
            System.out.println("Error: Zodiac signs are not in the correct sequence.");
            return false;
        }

        return true;
    }

    private static boolean validatePlanetPresence(DivisionalChartData chartData) {
        Set<Planet> expectedPlanets = new HashSet<>(Arrays.asList(Planet.values()));
        Set<Planet> actualPlanets = chartData.getPlanetPositions().keySet();

        if (!actualPlanets.containsAll(expectedPlanets)) {
            System.out.println("Error: Not all planets are present in the chart.");
            return false;
        }

        return true;
    }

    private static boolean validatePlanetPositions(DivisionalChartData chartData) {
        for (PlanetPosition position : chartData.getPlanetPositions().values()) {
            if (position.getHouse() < 1 || position.getHouse() > 12) {
                System.out.println("Error: Invalid house number for planet " + position.getPlanet() + ".");
                return false;
            }
        }
        return true;
    }
}