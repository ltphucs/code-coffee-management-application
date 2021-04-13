package com.nobita.demo.dao;

import com.nobita.demo.model.Area;
import com.nobita.demo.resultset.AreaResultSet;
import com.nobita.demo.rowmapper.AreaRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AreaDAO implements BaseDAO<Area> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Area> findAll() {
        String sql = "select * from area";
        return jdbcTemplate.query(sql, new AreaResultSet());
    }

    @Override
    public Area findByID(long id) {
        String sql = "select * from area where id=?";
        Object[] values = {id};
        return jdbcTemplate.queryForObject(sql, new AreaRowMapper(), values);
    }

    @Override
    public boolean save(Area area) {
        String sql = "insert into area(name) values(?)";
        Object[] values = {area.getName()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean update(Area area) {
        String sql="update area set name =? where id=?";
        Object[] values = {area.getName(),area.getId()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean delete(long id) {
        String sql="delete from area where id=?";
        Object[] values ={id};
        return jdbcTemplate.update(sql, values) > 0;
    }
}
