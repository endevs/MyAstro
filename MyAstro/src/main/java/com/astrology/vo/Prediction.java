package com.astrology.vo;

import java.util.LinkedHashMap;

public class Prediction {
	String ruleName;
	StringBuffer description;
	String reference;
	StringBuffer singleLinePrediction;
	LinkedHashMap<Integer, StringBuffer> predictionResult;
	
	public StringBuffer getSingleLinePrediction() {
		return singleLinePrediction;
	}
	public void setSingleLinePrediction(StringBuffer sbString) {
		this.singleLinePrediction = sbString;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public StringBuffer getDescription() {
		return description;
	}
	public void setDescription(StringBuffer sbResult) {
		this.description = sbResult;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public LinkedHashMap<Integer, StringBuffer> getPredictionResult() {
		return predictionResult;
	}
	public void setPredictionResult(LinkedHashMap<Integer, StringBuffer> predictionResult2) {
		this.predictionResult = predictionResult2;
	}
	
}
