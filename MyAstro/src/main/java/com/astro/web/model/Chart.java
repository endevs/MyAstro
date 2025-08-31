package com.astro.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "charts")
public class Chart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String dob;
    private String birthTime;
    private String birthPlace;

    @Lob
    @Column(name = "house_positions", columnDefinition = "CLOB")
    private String housePositions;

    @Lob
    @Column(name = "d9_house_positions", columnDefinition = "CLOB")
    private String d9HousePositions;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getHousePositions() {
        return housePositions;
    }

    public void setHousePositions(String housePositions) {
        this.housePositions = housePositions;
    }

    public String getD9HousePositions() {
        return d9HousePositions;
    }

    public void setD9HousePositions(String d9HousePositions) {
        this.d9HousePositions = d9HousePositions;
    }
}
