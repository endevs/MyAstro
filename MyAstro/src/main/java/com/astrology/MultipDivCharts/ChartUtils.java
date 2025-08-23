package com.astrology.MultipDivCharts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.ZodiacSign;

public class ChartUtils {

    public static List<String> getPlanetsInSign(ZodiacSign sign, Map<Planet, PlanetPosition> planetPositions) {
        List<String> planets = new ArrayList<>();
        for (Map.Entry<Planet, PlanetPosition> entry : planetPositions.entrySet()) {
            if (entry.getValue().getSign() == sign) {
                planets.add(entry.getKey().name().substring(0, 2));
            }
        }
        return planets;
    }
}
