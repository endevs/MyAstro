package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.Planet;
import com.astrology.RuleEngine.RuleResult;

import java.util.ArrayList;
import java.util.List;

public class NewRule6_2 implements AstrologyRule {

    @Override
    public RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart) {
        DivisionalChartData d1 = completeChart.getChart(DivisionalChart.D1);
        if (d1 == null) {
            return null;
        }

        String ruleName = "NAVAMSHA (D9 CHART) - Rahu/Ketu in 7th House";
        String reference = "https://docs.google.com/document/d/1FrCfwBEu_0pIOWAk8BvS7lwRsuzUWJYIzJME4wN8v7s/edit?usp=sharing";

        boolean rahuInD1_7th = d1.isPlanetInHouse(Planet.RAHU, 7);
        boolean ketuInD1_7th = d1.isPlanetInHouse(Planet.KETU, 7);

        if (rahuInD1_7th || ketuInD1_7th) {
            String description = "The person will not be committed to any relationship until a son is born (Ketu represents Kulasya Unnati - family lineage).\n" +
                    "This can create problems in marriage as the person may seek another spouse.\n" +
                    "Is there any Yoga between D9 - 1st House and 8th House - Divorce.\n" +
                    "Is there any yoga between D9 - 7th and 8th House - Spouse is asking for divorce.\n" +
                    "Is there any yoga between D9 - 8th and 2nd House - Leaving the Spouse because of a second spouse (2nd is 8th from 7th).\n" +
                    "If 6th house is involved then separation from physical partner.\n" +
                    "If only D1 7th House contains Rahu or Ketu and no above combination is present in D9, there could be a chance of having a boyfriend or girlfriend before marriage.";
            List<String> categories = new ArrayList<>();
            categories.add("NAVAMSHA (D9 CHART)");
            return new RuleResult(description, 1.0, categories, ruleName, reference);
        }

        return null;
    }
}
