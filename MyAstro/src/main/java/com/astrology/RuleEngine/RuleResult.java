package com.astrology.RuleEngine;

import java.util.List;

//Rule result container
public class RuleResult {
	private String description;
	private double confidence;
	private List<String> categories;
	private String ruleName;
	private String reference;

	public RuleResult(String description, double confidence, List<String> categories) {
		this.description = description;
		this.confidence = confidence;
		this.categories = categories;
	}

	public RuleResult(String description, double confidence, List<String> categories, String ruleName, String reference) {
		this.description = description;
		this.confidence = confidence;
		this.categories = categories;
		this.ruleName = ruleName;
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
}
