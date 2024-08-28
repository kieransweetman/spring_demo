package com.example.spring_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.spring_demo.entities.Region;

@EnableJpaRepositories
public interface RegionRepository extends JpaRepository<Region, Integer> {
    Region findByName(String name);
}
