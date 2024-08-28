package com.example.spring_demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.spring_demo.dto.CityDto;
import com.example.spring_demo.entities.City;
import com.example.spring_demo.entities.Department;
import com.example.spring_demo.entities.Region;
import com.example.spring_demo.exception.CustomException;
import com.example.spring_demo.service.CityService;
import com.example.spring_demo.service.DepartmentService;
import com.example.spring_demo.service.RegionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CityRestController {
    @Autowired
    private RegionService regionService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CityService cityService;

    // @GetMapping("/town/{id}")
    // public ResponseEntity<String> townById(@PathVariable String id) {
    // try {
    // int cityId = Integer.parseInt(id);
    // City city = cityService.getById(cityId);
    // if (city != null) {
    // Map<String, Object> response = new HashMap<>();
    // response.put("cityName", city.getName());
    // response.put("regionName", city.getRegion().getName());
    // response.put("departmentCode", city.getDepartment().getCode());

    // return ResponseEntity.ok(response.toString());
    // } else {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not found");
    // }
    // } catch (NumberFormatException e) {
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid city ID");
    // }
    // }

    @GetMapping("/townJson")
    public ResponseEntity<List<City>> townJson() {
        List<City> towns = cityService.getAll();

        if (towns.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(towns);

    }

    @PostMapping("/town/add")
    public ResponseEntity<String> addTown(
            @RequestBody CityDto cityDto) throws CustomException {

        validateCity(cityDto);

        Region region = regionService.getRegionByName(cityDto.getRegionName());
        if (region == null) {
            region = new Region();
            region.setName(cityDto.getRegionName());
            regionService.saveRegion(region);
        }

        Department department = departmentService.getDepartmentByCode(cityDto.getDepartmentCode());
        if (department == null) {
            department = new Department();
            department.setCode(cityDto.getDepartmentCode());
            departmentService.saveDepartment(department);
        }

        City city = new City();
        city.setName(cityDto.getName());
        city.setPopulation(cityDto.getPopulation());
        city.setDepartment(department);
        city.setRegion(region);
        cityService.addCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body("City added!");

    }

    @PutMapping("/town/update/{id}")
    public ResponseEntity<String> updateTown(
            @PathVariable String id,
            @RequestParam String regionName,
            @RequestParam Integer departmentCode,
            @RequestParam String cityName,
            @RequestParam Integer cityPopulation) {

        try {
            int cityId = Integer.parseInt(id);
            City city = cityService.getById(cityId);
            if (city == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not found");
            }

            if (cityName != null) {
                city.setName(cityName);
            }

            if (regionName != null) {
                Region region = regionService.getById(city.getRegion().getId());
                city.setRegion(region);
            }

            if (departmentCode != null) {
                Department department = departmentService.getById(city.getDepartment().getId());
                city.setDepartment(department);
            }

            if (cityPopulation != null) {
                city.setPopulation(cityPopulation);
            }

            cityService.saveCity(city);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("City Edited");

        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City not found");

        }
    }

    @DeleteMapping("/town/delete/{id}")
    public ResponseEntity<String> deleteTown(@PathVariable int id) {
        boolean isDeleted = cityService.deleteById(id);

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not found");
        }

        return ResponseEntity.ok("City deleted successfully!");
    }

    @GetMapping("/town/ByString")
    public ResponseEntity<List<City>> searchBy(@RequestParam String searchTextString) {
        List<City> query = cityService.findByString(searchTextString);

        if (query.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(query);
    }

    @GetMapping("/town/byDepartmentPopulationRange")
    public ResponseEntity<List<City>> searchBy(@RequestParam int departmentId, @RequestParam int minPopulation,
            @RequestParam int maxPopulation) {
        List<City> query = cityService.getCitiesByPopulationRangeAndDepartment(departmentId, minPopulation,
                maxPopulation);

        if (query.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(query);
    }

    @GetMapping("town/exportToCsv")
    public ResponseEntity<String> toCsv() {
        String csvData = cityService.toCsv();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=cities.csv");
        headers.add("Content-Type", "text/csv");

        return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
    }

    private void validateCity(CityDto cityDto) throws CustomException {
        StringBuilder errorMessages = new StringBuilder();

        if (cityDto.getName() == null) {
            errorMessages.append("City name is required");
        }
        if (cityDto.getRegionName() == null) {
            errorMessages.append("Region name is required");
        }
        if (cityDto.getDepartmentCode() == null) {
            errorMessages.append("Department code is required");
        }
        if (cityDto.getPopulation() < 10) {
            errorMessages.append("City must have at least 10 inhabitants. ");
        }
        if (cityDto.getName().length() < 2) {
            errorMessages.append("City name must contain at least 2 letters. ");
        }
        if (cityDto.getDepartmentCode().toString().length() != 2) {
            errorMessages.append("Department code must be exactly 2 characters. ");
        }
        if (cityService.isCityNameDuplicate(cityDto.getName(), cityDto.getDepartmentCode())) {
            errorMessages.append("City name must be unique within a department. ");
        }

        if (errorMessages.length() > 0) {
            throw new CustomException(errorMessages.toString().trim());
        }
    }

}
