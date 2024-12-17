package com.astrology.vo;

import java.util.LinkedHashMap;
import java.util.List;

public class Planet {
	String planetName;
	boolean exalted;
	boolean debilitation;
	boolean ownSign;
	List<House> aspectingToHouses; //2,5
	List<Planet> aspectingToPlanet;
	LinkedHashMap<Integer, Integer> defaultAspect;
	List<House> defaultHouseOwner;
	House exaltationHouse;
	House debilitationHouse;
	Sign moolTrikon;
	
	public House getExaltationHouse() {
		return exaltationHouse;
	}
	public void setExaltationHouse(House exaltationHouse) {
		this.exaltationHouse = exaltationHouse;
	}
	public House getDebilitationHouse() {
		return debilitationHouse;
	}
	public void setDebilitationHouse(House debilitationHouse) {
		this.debilitationHouse = debilitationHouse;
	}
	public String getPlanetName() {
		return planetName;
	}
	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}
	public boolean isExalted() {
		return exalted;
	}
	public void setExalted(boolean exalted) {
		this.exalted = exalted;
	}
	public List<House> getAspectingToHouses() {
		return aspectingToHouses;
	}
	public void setAspectingToHouses(List<House> aspectingToHouses) {
		this.aspectingToHouses = aspectingToHouses;
	}
	public List<Planet> getAspectingToPlanet() {
		return aspectingToPlanet;
	}
	public void setAspectingToPlanet(List<Planet> aspectingToPlanet) {
		this.aspectingToPlanet = aspectingToPlanet;
	}
	public LinkedHashMap<Integer, Integer> getDefaultAspect() {
		return defaultAspect;
	}
	public void setDefaultAspect(LinkedHashMap<Integer, Integer> sunAspect) {
		this.defaultAspect = sunAspect;
	}
	public List<House> getDefaultHouseOwner() {
		return defaultHouseOwner;
	}
	public void setDefaultHouseOwner(List<House> defaultHouseOwner) {
		this.defaultHouseOwner = defaultHouseOwner;
	}
	public boolean isDebilitation() {
		return debilitation;
	}
	public void setDebilitation(boolean debilitation) {
		this.debilitation = debilitation;
	}
	public boolean isOwnSign() {
		return ownSign;
	}
	public void setOwnSign(boolean ownSign) {
		this.ownSign = ownSign;
	}
	public Sign getMoolTrikon() {
		return moolTrikon;
	}
	public void setMoolTrikon(Sign moolTrikon) {
		this.moolTrikon = moolTrikon;
	}
}
