package com.example.spring_demo.dto;

public class CityDto {
    private String name;
    private Integer departmentCode;
    private String regionName;
    private Integer population;

    public CityDto(String name, Integer departmentCode, String regionName) {
        this.name = name;
        this.departmentCode = departmentCode;
        this.regionName = regionName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Integer departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

}