package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.ZodiacSign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChartUtils {

    public static List<String> getPlanetsInSign(ZodiacSign sign, Map<Planet, PlanetPosition> planetPositions) {
        List<String> planets = new ArrayList<>();
        for (Map.Entry<Planet, PlanetPosition> entry : planetPositions.entrySet()) {
            if (entry.getValue().getSign() == sign) {
                String planetAbbreviation = entry.getKey().name().substring(0, 2);
                if (entry.getValue().isRetrograde()) {
                    planetAbbreviation = "(" + planetAbbreviation + ")";
                }
                planets.add(planetAbbreviation);
            }
        }
        return planets;
    }
}