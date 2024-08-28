package com.example.spring_demo.dto;

public class DepartmentDto {
    private String code;

    public DepartmentDto(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}