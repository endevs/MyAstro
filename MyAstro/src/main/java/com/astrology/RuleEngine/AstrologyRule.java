package com.astrology.RuleEngine;

public interface AstrologyRule {
	RuleResult evaluate(BirthChart chart);
}
