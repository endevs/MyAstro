package com.astrology.MultipDivCharts;

import java.util.Arrays;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.RuleResult;
import com.astrology.RuleEngine.ZodiacSign;

//Rule: Determine the "Sign of the Chart" based on the Moon's position in the D1 chart.
public class Rule_SignOfTheChart implements AstrologyRule {
    
    public Rule_SignOfTheChart() {
        // No constructor parameters needed for this rule
    }
    
    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d1 = completeChart.getChart(DivisionalChart.D1);
        
        if (d1 == null) return null;
        
        PlanetPosition moonPosition = d1.getPlanetPosition(Planet.MOON);
        
        if (moonPosition != null) {
            ZodiacSign moonSign = moonPosition.getSign();
            
            return new RuleResult(
                String.format("The Sign of the Chart (based on Moon in D1) is: %s", moonSign.name()),
                1.0, // High confidence as it's a direct observation
                Arrays.asList("chart_sign", "moon_sign")
            );
        }
        return null;
    }
}
