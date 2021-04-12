package com.nobita.demo.resultset;

import com.nobita.demo.model.Unit;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitResultSet implements ResultSetExtractor<List<Unit>> {
    @Override
    public List<Unit> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Unit> units=new ArrayList<>();
        while(rs.next()){
            Unit unit=new Unit();
            unit.setId(rs.getLong("id"));
            unit.setName(rs.getString("name"));
            unit.setComment(rs.getString("comment"));
            units.add(unit);
        }
        return units;
    }
}
