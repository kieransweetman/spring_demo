package com.example.spring_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public abstract class AbstractService<T, ID> {
    protected abstract JpaRepository<T, ID> getRepository();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkDatabaseConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<T> getAll() {
        return getRepository().findAll();
    }
`
    public T getById(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    public T save(T entity) {
        return getRepository().save(entity);
    }

    public boolean deleteById(ID id) {

        if (getRepository().existsById(id)) {
            getRepository().deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

}
