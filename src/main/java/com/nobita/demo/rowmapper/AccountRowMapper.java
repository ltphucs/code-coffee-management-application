package com.nobita.demo.rowmapper;

import com.nobita.demo.model.Account;
import com.nobita.demo.model.Authorization;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account=new Account();
        account.setId(rs.getLong("id"));
        account.setName(rs.getString("name"));
        account.setPassword(rs.getString("password"));
        Authorization authorization=new Authorization();
        authorization.setId(rs.getLong("id_authorization"));
        authorization.setName(rs.getString("name_authorization"));
        account.setAuthorization(authorization);
        return account;
    }
}
