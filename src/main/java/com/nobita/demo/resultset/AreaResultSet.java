package com.nobita.demo.resultset;

import com.nobita.demo.model.Area;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaResultSet implements ResultSetExtractor<List<Area>> {
    @Override
    public List<Area> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Area> areas=new ArrayList<>();
        while (rs.next()){
            Area area=new Area();
            area.setId(rs.getLong("id"));
            area.setName(rs.getString("name"));
            areas.add(area);
        }
        return areas;
    }
}
