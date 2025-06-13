package com.astrology.MultipDivCharts;

import java.util.Arrays;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.PlanetPosition;
import com.astrology.RuleEngine.RuleResult;

//Rule 2: Checking 7th Lord in D1 and D9
public class SeventhLordInD1D9Rule implements AstrologyRule {
    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d1 = completeChart.getChart(DivisionalChart.D1);
        DivisionalChartData d9 = completeChart.getChart(DivisionalChart.D9);
        
        if (d1 == null || d9 == null) return null;
        
        Planet seventhLordD1 = d1.getHouseLords().get(7);
        Planet seventhLordD9 = d9.getHouseLords().get(7);
        
        if (seventhLordD1 != null && seventhLordD1.equals(seventhLordD9)) {
            PlanetPosition d9Pos = d9.getPlanetPosition(seventhLordD1);
            
            return new RuleResult(
                String.format("7th lord %s is same in D1 and D9. Position in D9: %s %s - indicates %s", 
                    seventhLordD1, 
                    d9Pos.getHouse(), 
                    d9Pos.getSign(),
                    interpretSeventhLord(d9Pos)),
                0.75,
                Arrays.asList("marriage", "relationships")
            );
        }
        return null;
    }
    
    private String interpretSeventhLord(PlanetPosition position) {
        // Interpretation logic based on position
        if (position.getHouse() == 1 || position.getHouse() == 7) {
            return "strong marital prospects";
        } else if (position.getHouse() == 6 || position.getHouse() == 8 || position.getHouse() == 12) {
            return "possible challenges in marriage";
        }
        return "moderate marital results";
    }
}
