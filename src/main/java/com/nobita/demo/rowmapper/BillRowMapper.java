package com.nobita.demo.rowmapper;

import com.nobita.demo.model.Bill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillRowMapper implements RowMapper<Bill> {
    @Override
    public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bill bill = new Bill();
        bill.setIdOrder(rs.getLong("id_order"));
        bill.setDateJoinView(rs.getTimestamp("date_join").toLocalDateTime());
        bill.setDateExport(rs.getTimestamp("date_export").toLocalDateTime());
        bill.setNameTable(rs.getString("name_table"));
        bill.setTotalPrice(rs.getLong("total_price"));
        return bill;
    }
}
