package com.nobita.demo.dao;

import com.nobita.demo.model.QuantitativeExport;
import com.nobita.demo.resultset.QuantitativeExportResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuantitativeExportDAO implements BaseDAO<QuantitativeExport> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<QuantitativeExport> findAll() {
        String sql = "select * from quantity_export";
        return jdbcTemplate.query(sql, new QuantitativeExportResultSet());
    }

    @Override
    public QuantitativeExport findByID(Long id) {
        return null;
    }

    @Override
    public boolean save(QuantitativeExport quantitativeExport) {
        String sql = "insert into quantitative_export(date_export,name_product,name_ingredient,quantity) values (?,?,?,?)";
        Object[] values = {quantitativeExport.getDateExport(), quantitativeExport.getNameProduct(), quantitativeExport.getNameIngredient(), quantitativeExport.getQuantity()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean update(QuantitativeExport quantitativeExport) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
