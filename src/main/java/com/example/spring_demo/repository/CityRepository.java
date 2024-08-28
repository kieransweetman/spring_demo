package com.example.spring_demo.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.spring_demo.entities.City;

public interface CityRepository extends JpaRepository<City, Integer> {
    City findByName(String name);

    City findByNameAndDepartmentIdAndRegionId(String name, Integer departmentId, Integer regionId);

    @Query("SELECT c FROM City c WHERE c.department.id = :departmentId ORDER BY c.population DESC")
    List<City> findTopNCitiesByDepartment(@Param("departmentId") int departmentId, PageRequest pageable);

    @Query("SELECT c FROM City c WHERE c.department.id = :departmentId AND c.population BETWEEN :minPopulation AND :maxPopulation")
    List<City> findCitiesByPopulationRangeAndDepartment(@Param("departmentId") int departmentId,
            @Param("minPopulation") int minPopulation, @Param("maxPopulation") int maxPopulation);

    @Query("Select c from City c where c.name Like %:characters% ")
    List<City> findByString(@Param("characters") String characters);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
            "FROM City c JOIN c.department d " +
            "WHERE c.name = :name AND d.code = :departmentCode")
    Boolean existsByNameAndDepartmentCode(String name, Integer departmentCode);

}
