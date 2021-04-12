package com.nobita.demo.dao;

import com.nobita.demo.model.Account;
import com.nobita.demo.resultset.AccountResultSet;
import com.nobita.demo.rowmapper.AccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAO implements BaseDAO<Account> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> findAll() {
        String sql="select ac.*,au.name as name_authorization from account ac left join authorization au on au.id= ac.id_authorization";
        return jdbcTemplate.query(sql,new AccountResultSet());
    }

    @Override
    public Account findByID(long id) {
        String sql="select ac.*,au.name as name_authorization from account ac left join authorization au on au.id= ac.id_authorization where ac.id=?";
        Object [] values={id};
        return jdbcTemplate.queryForObject(sql,new AccountRowMapper(),values);
    }

    @Override
    public boolean save(Account account) {
        String sql ="insert into account(name,password,id_authorization) values (?,?,?)";
        Object [] values={account.getName(),account.getPassword(),account.getAuthorization().getId()};
        return jdbcTemplate.update(sql,values) >0;
    }

    @Override
    public boolean update(Account account) {
        String sql="update account set name=?,password=?,id_authorization=? where id =?";
        Object [] values={account.getName(),account.getPassword(),account.getAuthorization().getId(),account.getId()};
        return jdbcTemplate.update(sql,values) >0;
    }

    @Override
    public boolean delete(long id) {
        String sql ="delete from account where id=?";
        Object [] values ={id};
        return jdbcTemplate.update(sql,values) > 0;
    }
}
