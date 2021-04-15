package com.nobita.demo.resultset;

import com.nobita.demo.model.en.Position;
import com.nobita.demo.model.Staff;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffResultSet implements ResultSetExtractor<List<Staff>> {
    @Override
    public List<Staff> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Staff> staffs=new ArrayList<>();
        while(rs.next()){
            Staff staff=new Staff();
            staff.setDateJoin(rs.getDate("date_join").toLocalDate());
            staff.setFullName(rs.getString("fullname"));
            staff.setGender(rs.getString("gender"));
            staff.setPosition(Position.valueOf(rs.getString("position")));
            staff.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
            staff.setAddress(rs.getString("address"));
            staff.setPhone(rs.getString("phone"));
            staff.setPassword(rs.getString("password"));
            staffs.add(staff);
        }
        return staffs;
    }
}
