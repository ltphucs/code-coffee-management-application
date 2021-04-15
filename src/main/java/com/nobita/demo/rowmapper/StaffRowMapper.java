package com.nobita.demo.rowmapper;

import com.nobita.demo.model.en.Position;
import com.nobita.demo.model.Staff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRowMapper implements RowMapper<Staff> {
    @Override
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        Staff staff=new Staff();
        staff.setDateJoin(rs.getDate("date_join").toLocalDate());
        staff.setFullName(rs.getString("fullname"));
        staff.setGender(rs.getString("gender"));
        staff.setPosition(Position.valueOf(rs.getString("position")));
        staff.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        staff.setAddress(rs.getString("address"));
        staff.setPhone(rs.getString("phone"));
        staff.setPassword(rs.getString("password"));
        return staff;
    }
}
