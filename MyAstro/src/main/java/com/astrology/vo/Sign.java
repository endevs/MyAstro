package com.astrology.vo;

public class Sign {
	String signName;
	Integer signNumber;
	Planet signOwner;
	String signMovableFixedDuel;
	String signElementsFieryEarthyAiryWatery;
	

	public String getSignMovableFixedDuel() {
		return signMovableFixedDuel;
	}

	public void setSignMovableFixedDuel(String signMovableFixedDuel) {
		this.signMovableFixedDuel = signMovableFixedDuel;
	}

	public String getSignElementsFieryEarthyAiryWatery() {
		return signElementsFieryEarthyAiryWatery;
	}

	public void setSignElementsFieryEarthyAiryWatery(String signElementsFieryEarthyAiryWatery) {
		this.signElementsFieryEarthyAiryWatery = signElementsFieryEarthyAiryWatery;
	}

	public Planet getSignOwner() {
		return signOwner;
	}

	public void setSignOwner(Planet signOwner) {
		this.signOwner = signOwner;
	}

	public Integer getSignNumber() {
		return signNumber;
	}

	public void setSignNumber(Integer signNumber) {
		this.signNumber = signNumber;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}
	
	
}
