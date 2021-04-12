package com.nobita.demo.resultset;

import com.nobita.demo.model.Area;
import com.nobita.demo.model.Table;
import com.nobita.demo.model.TableStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableResultSet implements ResultSetExtractor<List<Table>> {
    @Override
    public List<Table> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Table> tables=new ArrayList<>();
        while(rs.next()){
            Table table =new Table();
            table.setId(rs.getLong("id"));
            table.setName(rs.getString("name"));
            Area area=new Area();
            area.setId(rs.getLong("id_area"));
            area.setName(rs.getString("name_area"));
            table.setArea(area);
            table.setTableStatus(TableStatus.valueOf(rs.getString("status")));
            tables.add(table);
        }
        return tables;
    }
}
