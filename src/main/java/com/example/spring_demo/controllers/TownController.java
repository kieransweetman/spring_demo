package com.example.spring_demo.controllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.spring_demo.entities.City;
import com.example.spring_demo.service.CityService;

@Controller
public class TownController {

    @Autowired
    private CityService cityService;

    @GetMapping("/town")
    public String town(Model model) {
        List<City> towns = cityService.getAll();
        System.out.println(towns);
        model.addAttribute("towns", towns);

        boolean isConnected = cityService.checkDatabaseConnection();
        model.addAttribute("dbConnection", isConnected ? "Connected" : "Not Connected");

        return "town";
    }

    @GetMapping("/town/{id}")
    public ResponseEntity<String> townById(@PathVariable String id) {
        try {
            int cityId = Integer.parseInt(id);
            City city = cityService.getById(cityId);
            if (city != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("cityName", city.getName());
                response.put("regionName", city.getRegion().getName());
                response.put("departmentCode", city.getDepartment().getCode());

                return ResponseEntity.ok(response.toString());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not found");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid city ID");
        }
    }

}
