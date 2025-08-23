package com.astrology.MultipDivCharts;

import java.io.File;
import java.io.IOException;
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
        d1.setAscendant(ZodiacSign.AQUARIUS);
        
        d1.addPlanetPosition(Planet.SUN, new PlanetPosition(Planet.SUN, 2, ZodiacSign.PISCES, 15.5, Nakshatra.PURVA_PHALGUNI));
        d1.addPlanetPosition(Planet.MERCURY, new PlanetPosition(Planet.MERCURY, 2, ZodiacSign.PISCES, 12.5, Nakshatra.PURVA_PHALGUNI));
        d1.addPlanetPosition(Planet.VENUS, new PlanetPosition(Planet.VENUS, 3, ZodiacSign.ARIES, 8.0, Nakshatra.KRITTIKA));
        d1.addPlanetPosition(Planet.MARS, new PlanetPosition(Planet.MARS, 3, ZodiacSign.ARIES, 20.0, Nakshatra.ROHINI));
        d1.addPlanetPosition(Planet.RAHU, new PlanetPosition(Planet.RAHU, 5, ZodiacSign.GEMINI, 5.0, Nakshatra.MRIGASHIRSHA));
        d1.addPlanetPosition(Planet.SATURN, new PlanetPosition(Planet.SATURN, 9, ZodiacSign.LIBRA, 10.0, Nakshatra.PUSHYA));
        d1.addPlanetPosition(Planet.JUPITER, new PlanetPosition(Planet.JUPITER, 10, ZodiacSign.SCORPIO, 25.0, Nakshatra.UTTARA_PHALGUNI));
        d1.addPlanetPosition(Planet.KETU, new PlanetPosition(Planet.KETU, 11, ZodiacSign.SAGITTARIUS, 15.0, Nakshatra.SWATI));
        d1.addPlanetPosition(Planet.MOON, new PlanetPosition(Planet.MOON, 11, ZodiacSign.SAGITTARIUS, 25.0, Nakshatra.ASHWINI));
        // Set house lords for D1...
        completeChart.addDivisionalChart(DivisionalChart.D1, d1);
        
        // Create and populate D9 chart
        DivisionalChartData d9 = new DivisionalChartData(DivisionalChart.D9);
        d9.setAscendant(ZodiacSign.GEMINI);
        d9.addPlanetPosition(Planet.MOON, new PlanetPosition(Planet.MOON, 4, ZodiacSign.VIRGO, 15.5, Nakshatra.PURVA_PHALGUNI));
        d9.addPlanetPosition(Planet.VENUS, new PlanetPosition(Planet.VENUS, 6, ZodiacSign.SCORPIO, 8.0, Nakshatra.SWATI));
        d9.addPlanetPosition(Planet.RAHU, new PlanetPosition(Planet.RAHU, 6, ZodiacSign.SCORPIO, 20.0, Nakshatra.ASHWINI));
        d9.addPlanetPosition(Planet.SATURN, new PlanetPosition(Planet.SATURN, 7, ZodiacSign.SAGITTARIUS, 10.0, Nakshatra.PUSHYA));
        d9.addPlanetPosition(Planet.JUPITER, new PlanetPosition(Planet.JUPITER, 7, ZodiacSign.SAGITTARIUS, 25.0, Nakshatra.UTTARA_PHALGUNI));
        d9.addPlanetPosition(Planet.SUN, new PlanetPosition(Planet.SUN, 8, ZodiacSign.CAPRICORN, 15.0, Nakshatra.MRIGASHIRSHA));
        d9.addPlanetPosition(Planet.MERCURY, new PlanetPosition(Planet.MERCURY, 11, ZodiacSign.ARIES, 25.0, Nakshatra.KRITTIKA));
        d9.addPlanetPosition(Planet.MARS, new PlanetPosition(Planet.MARS, 12, ZodiacSign.TAURUS, 5.0, Nakshatra.ROHINI));
        d9.addPlanetPosition(Planet.KETU, new PlanetPosition(Planet.KETU, 12, ZodiacSign.TAURUS, 10.0, Nakshatra.PURVA_PHALGUNI));
        // Set house lords for D9...
        completeChart.addDivisionalChart(DivisionalChart.D9, d9);

        // Generate the chart image
        try {
            String personName = "DharmaRaj Panigrahi";
            String imagePath = personName.replace(" ", "_") + "_chart.png";
            NorthIndianChartImageGenerator.generateChartImage(d1, d9, imagePath, personName, "05-Apr-1983", "04:40 AM", "Bhanjanagar, IND");
            System.out.println("Chart image generated at: " + new File(imagePath).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Evaluate with focus on D1 chart
        AdvancedRuleEngine engine = new AdvancedRuleEngine();
        Map<String, List<RuleResult>> results = engine.evaluateCompleteChart(completeChart, DivisionalChart.D1);
        
        // Display results
        results.forEach((category, ruleResults) -> {
            System.out.println("\n=== " + category.toUpperCase() + " ===");
            ruleResults.forEach(result -> 
                System.out.printf("- %s (Confidence: %.0f%%)\\n", 
                    result.getDescription(), result.getConfidence() * 100)
            );
        });
    }
}