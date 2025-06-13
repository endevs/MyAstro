package com.astrology.MultipDivCharts;

import com.astrology.RuleEngine.RuleResult;

public interface AstrologyRule {
	/**
     * @param completeChart All available divisional charts
     * @param focusChart The primary chart to evaluate (usually D1 or D9)
     * @return RuleResult if rule applies, null otherwise
     */
    RuleResult evaluate(CompleteChart completeChart, DivisionalChart focusChart);
}
