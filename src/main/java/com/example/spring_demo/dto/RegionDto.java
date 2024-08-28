package com.example.spring_demo.dto;

public class RegionDto {
    private String name;
    private String departmentCode;

    public RegionDto(String name, String departmentCode) {
        this.name = name;
        this.departmentCode = departmentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
