package com.astrology.RuleEngine.Main;

import java.util.List;
import java.util.Map;

import com.astrology.RuleEngine.BirthChart;
import com.astrology.RuleEngine.Nakshatra;
import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.RuleResult;
import com.astrology.RuleEngine.ZodiacSign;

public class AstrologyApp {
    public static void main(String[] args) {
        // Create a sample birth chart
        BirthChart chart = new BirthChart();
        chart.addPlanetPosition(Planet.SUN, 
            new PlanetPosition(Planet.SUN, 10, ZodiacSign.LEO, 15.5, Nakshatra.PURVA_PHALGUNI));
        chart.addPlanetPosition(Planet.MARS, 
            new PlanetPosition(Planet.MARS, 4, ZodiacSign.ARIES, 10.2, Nakshatra.ASHWINI));
        chart.addPlanetPosition(Planet.MOON,
            new PlanetPosition(Planet.MOON, 7, ZodiacSign.CANCER, 12.0, Nakshatra.PUSHYA));
        chart.addPlanetPosition(Planet.JUPITER,
            new PlanetPosition(Planet.JUPITER, 10, ZodiacSign.LEO, 18.0, Nakshatra.UTTARA_PHALGUNI));
        chart.setAscendant(ZodiacSign.SCORPIO);

        // Evaluate the chart
        RuleEngine engine = new RuleEngine();
        Map<String, List<RuleResult>> results = engine.evaluateChart(chart);

        // Display results
        for (Map.Entry<String, List<RuleResult>> entry : results.entrySet()) {
            System.out.println("\n=== " + entry.getKey().toUpperCase() + " ===");
            for (RuleResult result : entry.getValue()) {
                System.out.printf("- %s (Confidence: %.0f%%)%n", 
                    result.getDescription(), result.getConfidence() * 100);
            }
        }
    }
}
