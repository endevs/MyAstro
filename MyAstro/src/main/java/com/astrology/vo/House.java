package com.astrology.vo;

import java.util.List;

import com.astrology.utils.Utils;

public class House {
	Integer houseNumber; //Change to Bhabha
	List<Planet> planetsContainHouse;
	Planet houseOwner;
	Sign houseSign;
	boolean ascendant = false;
	
	public Integer getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}
	public List<Planet> getPlanetsContainHouse() {
		return planetsContainHouse;
	}
	public void setPlanetsContainHouse(List<Planet> planetsContainHouse) {
		this.planetsContainHouse = planetsContainHouse;
	}
	public Planet getHouseOwner() {
		return houseOwner;
	}
	public void setHouseOwner(Planet houseOwner) {
		this.houseOwner = houseOwner;
	}
	
	public Sign getHouseSign() {
		return houseSign;
	}
	public void setHouseSign(Sign houseSign) {
		this.houseSign = houseSign;
		this.houseOwner = Utils.getHouseOwner(houseSign);
	}
	public boolean isAscendant() {
		return ascendant;
	}
	public void setAscendant(boolean ascendant) {
		this.ascendant = ascendant;
	}
	
	
}
