package com.astrology.RuleEngine.Rules;

import java.util.Arrays;

import com.astrology.RuleEngine.AstrologyRule;
import com.astrology.RuleEngine.BirthChart;
import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.RuleResult;

// Example rule: Sun in 10th house
public class SunInTenthHouseRule implements AstrologyRule {
    @Override
    public RuleResult evaluate(BirthChart chart) {
        PlanetPosition sun = chart.getPlanetPosition(Planet.SUN);
        if (sun != null && sun.getHouse() == 10) {
            return new RuleResult(
                "Sun in 10th house indicates career success and government favor",
                0.8,
                Arrays.asList("career", "fame")
            );
        }
        return null;
    }
}
