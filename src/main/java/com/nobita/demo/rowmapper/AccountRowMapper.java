package com.nobita.demo.rowmapper;

import com.nobita.demo.model.Account;
import com.nobita.demo.model.en.Authorization;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account=new Account();
        account.setId(rs.getLong("id"));
        account.setUsername(rs.getString("name"));
        account.setPassword(rs.getString("password"));
        account.setAuthorization(Authorization.valueOf(rs.getString("authorization")));
        return account;
    }
}
