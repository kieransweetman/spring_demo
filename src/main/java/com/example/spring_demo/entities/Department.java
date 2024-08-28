package com.example.spring_demo.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int code;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<City> cities;

    public Department() {
        super();
    }

    public Department(int id, int code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<City> getCities() {
        return cities;
    }

    @Override
    public String toString() {
        return "Department [id=" + id + ", code=" + code + "]";
    }

}
