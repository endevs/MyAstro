package com.astrology.RuleEngine.Rules;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.astrology.RuleEngine.AstrologyRule;
import com.astrology.RuleEngine.BirthChart;
import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.RuleResult;

public class GajakesariYogaRule implements AstrologyRule {
    @Override
    public RuleResult evaluate(BirthChart chart) {
        PlanetPosition moon = chart.getPlanetPosition(Planet.MOON);
        PlanetPosition jupiter = chart.getPlanetPosition(Planet.JUPITER);
        
        if (moon != null && jupiter != null) {
            int moonHouse = moon.getHouse();
            int jupiterHouse = jupiter.getHouse();
            
            // Calculate kendras from Moon (1,4,7,10 houses)
            Set<Integer> kendrasFromMoon = new HashSet<>();
            kendrasFromMoon.add(moonHouse);
            kendrasFromMoon.add((moonHouse + 3) % 12);
            kendrasFromMoon.add((moonHouse + 6) % 12);
            kendrasFromMoon.add((moonHouse + 9) % 12);
            
            if (kendrasFromMoon.contains(jupiterHouse)) {
                return new RuleResult(
                    "Gajakesari Yoga formed - brings intelligence, wealth and success",
                    0.9,
                    Arrays.asList("yoga", "prosperity", "intelligence")
                );
            }
        }
        return null;
    }
}
