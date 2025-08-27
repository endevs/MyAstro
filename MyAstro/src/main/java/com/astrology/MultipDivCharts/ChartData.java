package com.astrology.MultipDivCharts;

public class ChartData {
    private String name;
    private String dob;
    private String birthTime;
    private String birthPlace;
    private String[] housePositions;
    private String[] d9HousePositions;

    public ChartData(String name, String dob, String birthTime, String birthPlace, String[] housePositions, String[] d9HousePositions) {
        this.name = name;
        this.dob = dob;
        this.birthTime = birthTime;
        this.birthPlace = birthPlace;
        this.housePositions = housePositions;
        this.d9HousePositions = d9HousePositions;
    }

    // Getters
    public String getName() { return name; }
    public String getDob() { return dob; }
    public String getBirthTime() { return birthTime; }
    public String getBirthPlace() { return birthPlace; }
    public String[] getHousePositions() { return housePositions; }
    public String[] getD9HousePositions() { return d9HousePositions; }
}
