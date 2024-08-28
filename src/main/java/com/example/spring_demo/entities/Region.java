package com.example.spring_demo.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "region")
public class Region {

    @jakarta.persistence.Id
    private int id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "region")
    private List<City> cities;

    public Region() {
        super();
    }

    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    @Override
    public String toString() {
        return "Region [id=" + id + ", name=" + name + "]";
    }

}
