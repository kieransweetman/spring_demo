package com.example.spring_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.spring_demo.entities.City;
import com.example.spring_demo.entities.Department;
import com.example.spring_demo.entities.Region;
import com.example.spring_demo.exception.CustomException;
import com.example.spring_demo.repository.CityRepository;
import com.opencsv.CSVWriter;
import java.lang.reflect.Field;
import java.io.StringWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CityService extends AbstractService<City, Integer> {

    @Autowired
    private CityRepository cityRepository;

    @Override
    protected JpaRepository<City, Integer> getRepository() {
        return cityRepository;
    }

    public City addCity(City city) throws CustomException {
        if (cityRepository.existsByNameAndDepartmentCode(city.getName(), city.getDepartment().getCode())) {
            throw new CustomException("City with the same name and department code already exists.");
        }
        return cityRepository.save(city);
    }

    public Boolean saveCity(City city) {

        if (cityRepository.findByName(city.getName()) != null) {
            return false;
        }

        cityRepository.save(city);
        return true;
    }

    public City getCityByNameDepartmentRegion(String name, Department d, Region r) {
        return cityRepository.findByNameAndDepartmentIdAndRegionId(name, d.getId(), r.getId());
    }

    public List<City> getTopNCitiesByDepartment(int departmentId, int n) {
        PageRequest pageable = PageRequest.of(0, n);
        return cityRepository.findTopNCitiesByDepartment(departmentId, pageable);
    }

    public List<City> getCitiesByPopulationRangeAndDepartment(int departmentId, int minPopulation, int maxPopulation) {
        return cityRepository.findCitiesByPopulationRangeAndDepartment(departmentId, minPopulation, maxPopulation);
    }

    public List<City> findByString(String characters) {
        return cityRepository.findByString(characters);
    }

    public Boolean isCityNameDuplicate(String name, Integer departmentCode) {
        return cityRepository.existsByNameAndDepartmentCode(name, departmentCode);
    }

    public String toCsv() {
        {
            List<City> entities = getRepository().findAll();
            StringWriter writer = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(writer);

            // Get field names from the first entity and filter out the "id" field
            City firstEntity = entities.get(0);
            Field[] allFields = firstEntity.getClass().getDeclaredFields();
            List<Field> fields = Stream.of(allFields)
                    .filter(field -> !field.getName().equalsIgnoreCase("id"))
                    .collect(Collectors.toList());

            String[] headers = fields.stream()
                    .map(Field::getName)
                    .toArray(String[]::new);
            csvWriter.writeNext(headers);

            // Write entity data
            for (City entity : entities) {
                System.out.println(entity);
                String[] data = new String[fields.size()];
                for (int i = 0; i < fields.size(); i++) {
                    fields.get(i).setAccessible(true);
                    try {
                        Object value = fields.get(i).get(entity);
                        if (value.getClass().getName().contains("com.example.spring_demo.entities")) {

                            List<Field> classFields = Stream.of(value.getClass().getDeclaredFields())
                                    .filter(field -> !field.getName().equalsIgnoreCase("id"))
                                    .collect(Collectors.toList());

                            classFields.get(0).setAccessible(true);
                            data[i] = classFields.get(0).get(value).toString();
                            System.out.println(data[i]);

                        } else {
                            data[i] = value != null ? value.toString() : "";
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                csvWriter.writeNext(data);
            }

            try {
                csvWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return writer.toString();
        }
    }
}