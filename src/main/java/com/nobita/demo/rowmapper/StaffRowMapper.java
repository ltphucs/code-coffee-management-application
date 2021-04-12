package com.nobita.demo.rowmapper;

import com.nobita.demo.model.Position;
import com.nobita.demo.model.Staff;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StaffRowMapper implements RowMapper<Staff> {
    @Override
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        Staff staff=new Staff();
        staff.setDateJoin(rs.getDate("date_join").toLocalDate());
        staff.setFullname(rs.getString("fullname"));
        staff.setGender(rs.getString("gender"));
        Position position =new Position();
        position.setId(rs.getLong("id_position"));
        position.setName(rs.getString("name_position"));
        staff.setPosition(position);
        staff.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        staff.setAddress(rs.getString("address"));
        staff.setPhone(rs.getString("phone"));
        staff.setPassword(rs.getString("password"));
        return staff;
    }
}
