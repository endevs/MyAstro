package com.astrology.MultipDivCharts;

import java.util.List;
import java.util.Map;

import com.astrology.RuleEngine.Nakshatra;
import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.RuleResult;
import com.astrology.RuleEngine.ZodiacSign;

public class AdvancedAstrologyApp {
    public static void main(String[] args) {
        // Create complete chart with multiple divisional charts
        CompleteChart completeChart = new CompleteChart();
        
        // Create and populate D1 chart
        DivisionalChartData d1 = new DivisionalChartData(DivisionalChart.D1);
        d1.addPlanetPosition(Planet.SUN, new PlanetPosition(Planet.SUN, 10, ZodiacSign.LEO, 15.5, Nakshatra.PURVA_PHALGUNI));
        d1.addPlanetPosition(Planet.VENUS, new PlanetPosition(Planet.VENUS, 2, ZodiacSign.TAURUS, 8.0, Nakshatra.KRITTIKA));
        d1.setAscendant(ZodiacSign.SCORPIO);
        // Set house lords for D1...
        completeChart.addDivisionalChart(DivisionalChart.D1, d1);
        
        // Create and populate D9 chart
        DivisionalChartData d9 = new DivisionalChartData(DivisionalChart.D9);
        d9.addPlanetPosition(Planet.SUN, new PlanetPosition(Planet.SUN, 5, ZodiacSign.LEO, 15.5, Nakshatra.PURVA_PHALGUNI));
        d9.addPlanetPosition(Planet.VENUS, new PlanetPosition(Planet.VENUS, 7, ZodiacSign.LIBRA, 8.0, Nakshatra.SWATI));
        d9.setAscendant(ZodiacSign.GEMINI);
        // Set house lords for D9...
        completeChart.addDivisionalChart(DivisionalChart.D9, d9);
        
        // Evaluate with focus on D1 chart
        AdvancedRuleEngine engine = new AdvancedRuleEngine();
        Map<String, List<RuleResult>> results = engine.evaluateCompleteChart(completeChart, DivisionalChart.D1);
        
        // Display results
        results.forEach((category, ruleResults) -> {
            System.out.println("\n=== " + category.toUpperCase() + " ===");
            ruleResults.forEach(result -> 
                System.out.printf("- %s (Confidence: %.0f%%)%n", 
                    result.getDescription(), result.getConfidence() * 100)
            );
        });
    }
}