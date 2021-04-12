package com.nobita.demo.resultset;

import com.nobita.demo.model.Account;
import com.nobita.demo.model.Authorization;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountResultSet implements ResultSetExtractor<List<Account>> {
    @Override
    public List<Account> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Account> accounts=new ArrayList<>();
        while(rs.next()){
            Account account=new Account();
            account.setId(rs.getLong("id"));
            account.setName(rs.getString("name"));
            account.setPassword(rs.getString("password"));
            Authorization authorization=new Authorization();
            authorization.setId(rs.getLong("id_authorization"));
            authorization.setName(rs.getString("name_authorization"));
            account.setAuthorization(authorization);
            accounts.add(account);
        }
        return accounts;
    }
}
