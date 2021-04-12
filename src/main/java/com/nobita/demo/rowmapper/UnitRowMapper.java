package com.nobita.demo.rowmapper;

import com.nobita.demo.model.Unit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnitRowMapper implements RowMapper<Unit> {
    @Override
    public Unit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Unit unit=new Unit();
        unit.setId(rs.getLong("id"));
        unit.setName(rs.getString("name"));
        unit.setComment(rs.getString("comment"));
        return unit;
    }
}
