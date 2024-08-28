package com.example.spring_demo.controllers;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_demo.service.DepartmentService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DepartmentRestController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/dep/exportToCsv")
    public ResponseEntity<String> toCsv() {

        String csvData = departmentService.toCsv();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=departments.csv");
        headers.add("Content-Type", "text/csv");

        return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
    }
}
