package com.nobita.demo.rowmapper;

import com.nobita.demo.model.QuantitativeExport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuantitativeExportRowMapper implements RowMapper<QuantitativeExport> {
    @Override
    public QuantitativeExport mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuantitativeExport quantitativeExport=new QuantitativeExport();
        quantitativeExport.setId(rs.getLong("id"));
        quantitativeExport.setDateExport(rs.getTimestamp("date_export").toLocalDateTime());
        quantitativeExport.setNameProduct(rs.getString("name_product"));
        quantitativeExport.setNameIngredient(rs.getString("name_ingredient"));
        quantitativeExport.setQuantity(rs.getDouble("quantity"));
        return quantitativeExport;
    }
}
