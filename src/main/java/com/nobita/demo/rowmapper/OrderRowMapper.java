package com.nobita.demo.rowmapper;

import com.nobita.demo.model.Account;
import com.nobita.demo.model.Order;
import com.nobita.demo.model.Table;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order=new Order();
        order.setId(rs.getLong("id"));
        order.setDateJoin(rs.getTimestamp("date_join").toLocalDateTime());
        if(rs.getTimestamp("date_export") != null) {
            order.setDateExport(rs.getTimestamp("date_export").toLocalDateTime());
        }
        Table table =new Table();
        table.setId(rs.getLong("id_table"));
        table.setName(rs.getString("name_table"));
        order.setTable(table);
        order.setTotalAllPrice(rs.getLong("total_all_price"));
        Account account =new Account();
        account.setId(rs.getLong("id_account"));
        order.setAccount(account);
        return order;
    }
}
