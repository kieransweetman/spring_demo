package com.example.spring_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_demo.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByCode(int code);
}
