package com.example.spring_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.spring_demo.entities.Department;
import com.example.spring_demo.repository.DepartmentRepository;
import com.opencsv.CSVWriter;
import java.lang.reflect.Field;
import java.io.StringWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentService extends AbstractService<Department, Integer> {

    @Autowired
    private DepartmentRepository DepartmentRepository;

    public List<Department> getAllDepartments() {
        return DepartmentRepository.findAll();
    }

    public String toCsv() {
        List<Department> entities = getRepository().findAll();

        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer);

        // Get field names from the first entity and filter out the "id" field
        Department firstEntity = entities.get(0);
        Field[] allFields = firstEntity.getClass().getDeclaredFields();
        List<Field> fields = Stream.of(allFields)
                .filter(field -> !field.getName().equalsIgnoreCase("cities"))
                .collect(Collectors.toList());

        String[] headers = fields.stream()
                .map(Field::getName)
                .toArray(String[]::new);
        csvWriter.writeNext(headers);

        // Write "code" field values for each Department entity
        for (Department entity : entities) {
            String[] data = { Integer.toString(entity.getId()), Integer.toString(entity.getCode()) };
            csvWriter.writeNext(data);
        }

        try {
            csvWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return writer.toString();
    }

    public Boolean saveDepartment(Department department) {
        if (DepartmentRepository.findByCode(department.getCode()) != null) {
            return false;
        }
        DepartmentRepository.save(department);
        return true;
    }

    public Department getDepartmentByCode(int code) {
        return DepartmentRepository.findByCode(code);
    }

    @Override
    protected JpaRepository<Department, Integer> getRepository() {
        return DepartmentRepository;
    }

}
