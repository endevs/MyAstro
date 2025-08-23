package com.astrology.MultipDivCharts;

import java.util.Arrays;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.RuleResult;
import com.astrology.RuleEngine.ZodiacSign;
//Rule 1: Checking Planet Position in D1 and D9
public class PlanetInRasiAndNavamsaRule implements AstrologyRule {
    private final Planet planet;
    private final ZodiacSign rasiSign;
    private final ZodiacSign navamsaSign;
    
    public PlanetInRasiAndNavamsaRule(Planet planet, ZodiacSign rasiSign, ZodiacSign navamsaSign) {
        this.planet = planet;
        this.rasiSign = rasiSign;
        this.navamsaSign = navamsaSign;
    }
    
    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d1 = completeChart.getChart(DivisionalChart.D1);
        DivisionalChartData d9 = completeChart.getChart(DivisionalChart.D9);
        
        if (d1 == null || d9 == null) return null;
        
        PlanetPosition d1Pos = d1.getPlanetPosition(planet);
        PlanetPosition d9Pos = d9.getPlanetPosition(planet);
        
        if (d1Pos != null && d9Pos != null 
            && d1Pos.getSign() == rasiSign 
            && d9Pos.getSign() == navamsaSign) {
            
            return new RuleResult(
                String.format("%s in %s rasi and %s navamsa gives special results", 
                    planet, rasiSign, navamsaSign),
                0.85,
                Arrays.asList("special_yoga", planet.toString().toLowerCase())
            );
        }
        return null;
    }
}
