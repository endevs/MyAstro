package com.astrology.RuleEngine.Rules;

import java.util.Arrays;

import com.astrology.RuleEngine.AstrologyRule;
import com.astrology.RuleEngine.BirthChart;
import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.RuleResult;

//Example rule: Mars aspecting 7th house
public class MarsAspectSeventhHouseRule implements AstrologyRule {
	 @Override
	    public RuleResult evaluate(BirthChart chart) {
	        PlanetPosition mars = chart.getPlanetPosition(Planet.MARS);
	        if (mars != null) {
	            int marsHouse = mars.getHouse();
	            // Mars aspects 4th, 7th, 8th from its position
	            if (marsHouse == 1 || marsHouse == 4 || marsHouse == 7 || marsHouse == 8 || marsHouse == 10) {
	                return new RuleResult(
	                    "Mars in " + marsHouse + " house aspects 7th house - may cause marital conflicts",
	                    0.7,
	                    Arrays.asList("marriage", "relationships")
	                );
	            }
	        }
	        return null;
	    }
}
