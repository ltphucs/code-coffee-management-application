package com.nobita.demo.dao;

import com.nobita.demo.model.ImportProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImportProductDAO implements BaseDAO<ImportProduct> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ImportProduct> findAll() {
        return null;
    }

    @Override
    public ImportProduct findByID(int id) {
        return null;
    }

    @Override
    public boolean save(ImportProduct importProduct) {
        return false;
    }

    @Override
    public boolean update(ImportProduct importProduct) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
