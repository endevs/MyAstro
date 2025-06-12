package com.astrology.RuleEngine.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.astrology.RuleEngine.AstrologyRule;
import com.astrology.RuleEngine.BirthChart;
import com.astrology.RuleEngine.RuleResult;
import com.astrology.RuleEngine.Rules.GajakesariYogaRule;
import com.astrology.RuleEngine.Rules.MarsAspectSeventhHouseRule;
import com.astrology.RuleEngine.Rules.SunInTenthHouseRule;

public class RuleEngine {
    private List<AstrologyRule> rules;
    
    public RuleEngine() {
        this.rules = new ArrayList<>();
        // Register all rules
        this.rules.add(new SunInTenthHouseRule());
        this.rules.add(new MarsAspectSeventhHouseRule());
        this.rules.add(new GajakesariYogaRule());
        // Add more rules here
    }
    
    public Map<String, List<RuleResult>> evaluateChart(BirthChart chart) {
        Map<String, List<RuleResult>> resultsByCategory = new HashMap<>();
        
        for (AstrologyRule rule : rules) {
            RuleResult result = rule.evaluate(chart);
            if (result != null) {
                for (String category : result.getCategories()) {
                    resultsByCategory.computeIfAbsent(category, k -> new ArrayList<>()).add(result);
                }
            }
        }
        
        return resultsByCategory;
    }
    
    // Method to add rules dynamically
    public void registerRule(AstrologyRule rule) {
        rules.add(rule);
    }
}
