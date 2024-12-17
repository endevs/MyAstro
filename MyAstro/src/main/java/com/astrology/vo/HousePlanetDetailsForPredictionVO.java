package com.astrology.vo;

public class HousePlanetDetailsForPredictionVO {
	String houseSignName;
	Integer houseNumber;
	String houseOwner;
	Integer houseOwnerPlacedAtHouse;
	
	public String getHouseSignName() {
		return houseSignName;
	}
	public void setHouseSignName(String houseSignName) {
		this.houseSignName = houseSignName;
	}
	public Integer getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getHouseOwner() {
		return houseOwner;
	}
	public void setHouseOwner(String houseOwner) {
		this.houseOwner = houseOwner;
	}
	public Integer getHouseOwnerPlacedAtHouse() {
		return houseOwnerPlacedAtHouse;
	}
	public void setHouseOwnerPlacedAtHouse(Integer houseOwnerPlacedAtHouse) {
		this.houseOwnerPlacedAtHouse = houseOwnerPlacedAtHouse;
	}
	public Integer getHouseToHouseOwnerDistance() {
		return houseToHouseOwnerDistance;
	}
	public void setHouseToHouseOwnerDistance(Integer houseToHouseOwnerDistance) {
		this.houseToHouseOwnerDistance = houseToHouseOwnerDistance;
	}
	public Integer getHouseOwnerToHouseDistance() {
		return houseOwnerToHouseDistance;
	}
	public void setHouseOwnerToHouseDistance(Integer houseOwnerToHouseDistance) {
		this.houseOwnerToHouseDistance = houseOwnerToHouseDistance;
	}
	Integer houseToHouseOwnerDistance;
	Integer houseOwnerToHouseDistance;
	
}
