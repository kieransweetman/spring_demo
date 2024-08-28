package com.example.spring_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.spring_demo.entities.Region;
import com.example.spring_demo.repository.RegionRepository;

@Service
public class RegionService extends AbstractService<Region, Integer> {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    protected JpaRepository<Region, Integer> getRepository() {
        return regionRepository;
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Boolean saveRegion(Region region) {
        if (regionRepository.findByName(region.getName()) != null) {
            return false;
        }
        regionRepository.save(region);
        return true;

    }

    public Region getRegionByName(String name) {
        return regionRepository.findByName(name);
    }

}
