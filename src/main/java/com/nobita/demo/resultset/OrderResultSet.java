package com.nobita.demo.resultset;

import com.nobita.demo.model.Account;
import com.nobita.demo.model.Order;
import com.nobita.demo.model.Table;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderResultSet implements ResultSetExtractor<List<Order>> {
    @Override
    public List<Order> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Order> orders=new ArrayList<>();
        while(rs.next()){
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
            orders.add(order);
        }
        return orders;
    }
}
