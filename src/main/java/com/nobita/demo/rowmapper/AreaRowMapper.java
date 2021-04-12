package com.nobita.demo.rowmapper;

import com.nobita.demo.model.Area;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaRowMapper implements RowMapper<Area> {
    @Override
    public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
        Area area=new Area();
        area.setId(rs.getLong("id"));
        area.setName(rs.getString("name"));
        return area;
    }
}
