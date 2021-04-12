package com.nobita.demo.rowmapper;

import com.nobita.demo.dao.TableDAO;
import com.nobita.demo.model.Area;
import com.nobita.demo.model.Table;
import com.nobita.demo.model.TableStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableRowMapper implements RowMapper<Table> {
    @Override
    public Table mapRow(ResultSet rs, int rowNum) throws SQLException {
        Table table =new Table();
        table.setId(rs.getLong("id"));
        table.setName(rs.getString("name"));
        Area area=new Area();
        area.setId(rs.getLong("id_area"));
        area.setName(rs.getString("name_area"));
        table.setArea(area);
        table.setTableStatus(TableStatus.valueOf(rs.getString("status")));
        return table;
    }
}
