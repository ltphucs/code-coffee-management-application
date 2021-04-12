package com.nobita.demo.dao;

import com.nobita.demo.model.ProductLine;
import com.nobita.demo.resultset.ProductLineResultSet;
import com.nobita.demo.rowmapper.ProductLineRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductLineDAO implements BaseDAO<ProductLine> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductLine> findAll() {
        String sql = "select * from productline";
        return jdbcTemplate.query(sql, new ProductLineResultSet());
    }

    @Override
    public ProductLine findByID(long id) {
        String sql = "select * from productline where id=?";
        Object[] values = {id};
        return jdbcTemplate.queryForObject(sql, new ProductLineRowMapper(), values);
    }

    @Override
    public boolean save(ProductLine productLine) {
        String sql = "insert into productline(name) values (?)";
        Object[] values = {productLine.getName()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean update(ProductLine productLine) {
        String sql = "update productline set name=? where id =?";
        Object[] values = {productLine.getName(), productLine.getId()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean delete(long id) {
        String sql = "delete from productline where id=?";
        Object[] values = {id};
        return jdbcTemplate.update(sql, values) > 0;
    }
}
