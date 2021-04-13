package com.nobita.demo.dao;

import com.nobita.demo.resultset.IngredientResultSet;
import com.nobita.demo.rowmapper.IngredientRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredientDAO implements BaseDAO<com.nobita.demo.model.Ingredient>{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<com.nobita.demo.model.Ingredient> findAll() {
        String sql="select i.* ,u.name as name_unit from ingredient i lef join unit u on u.id=i.id_ingredient";
        return jdbcTemplate.query(sql,new IngredientResultSet());
    }

    @Override
    public com.nobita.demo.model.Ingredient findByID(long id) {
        String sql="select i.* ,u.name as name_unit from ingredient i lef join unit u on u.id=i.id_ingredient";
        Object[]values ={id};
        return jdbcTemplate.queryForObject(sql,new IngredientRowMapper(),values);
    }

    @Override
    public boolean save(com.nobita.demo.model.Ingredient ingredient) {
        String sql="insert into ingredient(name,id_unit,comment) values(?,?,?)";
        Object[] values ={ingredient.getName(),ingredient.getUnit().getId(),ingredient.getComment()};
        return jdbcTemplate.update(sql,values) >0;
    }

    @Override
    public boolean update(com.nobita.demo.model.Ingredient ingredient) {
        String sql ="update ingredient set name=?,id_unit=?,comment=? where id=?";
        Object[] values ={ingredient.getName(),ingredient.getUnit().getId(),ingredient.getComment(), ingredient.getId()};
        return jdbcTemplate.update(sql,values) >0;
    }

    @Override
    public boolean delete(long id) {
        String sql="delete from ingredient where id=?";
        Object[]values ={id};
        return jdbcTemplate.update(sql,values) >0;
    }
}
