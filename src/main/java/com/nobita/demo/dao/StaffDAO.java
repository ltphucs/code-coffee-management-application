package com.nobita.demo.dao;

import com.nobita.demo.model.Staff;
import com.nobita.demo.resultset.StaffResultSet;
import com.nobita.demo.rowmapper.StaffRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class StaffDAO implements BaseDAO<Staff> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Staff> findAll() {
        String sql = "select s.*,p.name as name_position from staff s left join position p on p.id=s.id_position";
        return jdbcTemplate.query(sql, new StaffResultSet());
    }

    @Override
    public Staff findByID(long id) {
        String sql = "select s.*,p.name as name_position from staff s left join position p on p.id=s.id_position where s.id=?";
        Object[] values = {id};
        return jdbcTemplate.queryForObject(sql,new StaffRowMapper(),values);
    }

    @Override
    public boolean save(Staff staff) {
        String sql = "insert into staff(fullname,gender,id_position,date_of_birth,address,phone,username,password) values (?,?,?,?,?,?,?,?)";
        Object[] values = {staff.getFullname(), staff.getGender(), staff.getPosition().getId(), Date.valueOf(staff.getDateOfBirth()), staff.getAddress(), staff.getPhone(), staff.getUsername(), staff.getPassword()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean update(Staff staff) {
        String sql ="update staff set fullname =?,gender=?,id_position =?,date_of_birth=?,address= ?,phone =?,username =? ,password =? where id =?";
        Object [] values = {staff.getFullname(), staff.getGender(), staff.getPosition().getId(), Date.valueOf(staff.getDateOfBirth()), staff.getAddress(), staff.getPhone(), staff.getUsername(), staff.getPassword(),staff.getId()};
        return jdbcTemplate.update(sql,values) > 0 ;
    }

    @Override
    public boolean delete(long id) {
        String sql ="delete from staff where id =?";
        Object[] values= {id};
        return jdbcTemplate.update(sql,values) > 0;
    }
}
