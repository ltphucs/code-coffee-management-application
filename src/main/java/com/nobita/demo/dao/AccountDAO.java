package com.nobita.demo.dao;

import com.nobita.demo.model.Account;
import com.nobita.demo.resultset.AccountResultSet;
import com.nobita.demo.rowmapper.AccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountDAO implements BaseDAO<Account> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> findAll() {
        String sql="select * from account";
        return jdbcTemplate.query(sql,new AccountResultSet());
    }

    @Override
    public Account findByID(Long id) {
        String sql="select * from account where id=?";
        Object [] values={id};
        return jdbcTemplate.queryForObject(sql,new AccountRowMapper(),values);
    }

    @Override
    public boolean save(Account account) {
        String sql ="insert into account(username,password,authorization) values (?,?,?)";
        Object [] values={account.getUsername(),account.getPassword(),account.getAuthorization()};
        return jdbcTemplate.update(sql,values) >0;
    }

    @Override
    public boolean update(Account account) {
        String sql="update account set username=?,password=?,authorization=? where id =?";
        Object [] values={account.getUsername(),account.getPassword(),account.getAuthorization(),account.getId()};
        return jdbcTemplate.update(sql,values) >0;
    }

    @Override
    public boolean delete(Long id) {
        String sql ="delete from account where id=?";
        Object [] values ={id};
        return jdbcTemplate.update(sql,values) > 0;
    }

    public Account findByUsername(String username){
        String sql="select * from account where username=?";
        Object [] values={username};
        return jdbcTemplate.queryForObject(sql,new AccountRowMapper(),values);
    }
}
