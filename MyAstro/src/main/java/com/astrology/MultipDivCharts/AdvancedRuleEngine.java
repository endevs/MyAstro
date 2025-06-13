package com.astrology.MultipDivCharts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.RuleResult;
import com.astrology.RuleEngine.ZodiacSign;

public class AdvancedRuleEngine {
    private List<AstrologyRule> rules;
    
    public AdvancedRuleEngine() {
        this.rules = new ArrayList<>();
        // Register basic rules
        //this.rules.add(new SunInTenthHouseRule());
        // Register divisional chart rules
        this.rules.add(new PlanetInRasiAndNavamsaRule(Planet.VENUS, ZodiacSign.TAURUS, ZodiacSign.LIBRA));
        this.rules.add(new SeventhLordInD1D9Rule());
        // Add more rules here
    }
    
    public Map<String, List<RuleResult>> evaluateCompleteChart(CompleteChart completeChart, DivisionalChart focusChart) {
        Map<String, List<RuleResult>> resultsByCategory = new HashMap<>();
        
        for (AstrologyRule rule : rules) {
            try {
                RuleResult result = rule.evaluate(completeChart, focusChart);
                if (result != null) {
                    for (String category : result.getCategories()) {
                        resultsByCategory.computeIfAbsent(category, k -> new ArrayList<>()).add(result);
                    }
                }
            } catch (Exception e) {
                // Log error but continue with other rules
                System.err.println("Error evaluating rule: " + rule.getClass().getSimpleName());
                e.printStackTrace();
            }
        }
        
        return resultsByCategory;
    }
}