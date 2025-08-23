package com.astrology.MultipDivCharts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.RuleResult;
import com.astrology.RuleEngine.ZodiacSign;

//Rule: Identify Vargottama planets (same sign in D1 and D9)
public class Rule_VargottamaPlanet implements AstrologyRule {
    
    public Rule_VargottamaPlanet() {
        // No constructor parameters needed for this general rule
    }
    
    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d1 = completeChart.getChart(DivisionalChart.D1);
        DivisionalChartData d9 = completeChart.getChart(DivisionalChart.D9);
        
        if (d1 == null || d9 == null) return null;
        
        List<Planet> vargottamaPlanets = new ArrayList<>();
        
        for (Map.Entry<Planet, PlanetPosition> entry : d1.getPlanetPositions().entrySet()) {
            Planet planet = entry.getKey();
            ZodiacSign rasiSign = entry.getValue().getSign();
            
            PlanetPosition d9Pos = d9.getPlanetPosition(planet);
            
            if (d9Pos != null && rasiSign == d9Pos.getSign()) {
                vargottamaPlanets.add(planet);
            }
        }
        
        if (!vargottamaPlanets.isEmpty()) {
            StringBuilder description = new StringBuilder("Vargottama Planets: ");
            for (int i = 0; i < vargottamaPlanets.size(); i++) {
                description.append(vargottamaPlanets.get(i).name());
                if (i < vargottamaPlanets.size() - 1) {
                    description.append(", ");
                }
            }
            description.append(" - These planets are in the same sign in both Rasi and Navamsa charts, indicating strength and consistency.");
            
            return new RuleResult(
                description.toString(),
                0.95, // High confidence for Vargottama
                Arrays.asList("vargottama", "strength")
            );
        }
        return null;
    }
}