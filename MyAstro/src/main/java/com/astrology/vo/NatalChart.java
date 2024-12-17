package com.astrology.vo;

import java.util.LinkedHashMap;

public class NatalChart {
	LinkedHashMap<Integer, House> houses; 
	String natalChartName;
	String divisionalChart;
	String divisionSymbol;
	
	
	public String getDivisionalChart() {
		return divisionalChart;
	}
	public void setDivisionalChart(String divisionalChart) {
		this.divisionalChart = divisionalChart;
	}
	public String getDivisionSymbol() {
		return divisionSymbol;
	}
	public void setDivisionSymbol(String divisionSymbol) {
		this.divisionSymbol = divisionSymbol;
	}
	public LinkedHashMap<Integer, House> getHouses() {
		return houses;
	}
	public void setHouses(LinkedHashMap<Integer, House> houses) {
		this.houses = houses;
	}
	public String getNatalChartName() {
		return natalChartName;
	}
	public void setNatalChartName(String natalChartName) {
		this.natalChartName = natalChartName;
	}
	
}
