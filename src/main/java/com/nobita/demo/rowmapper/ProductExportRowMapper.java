package com.nobita.demo.rowmapper;

import com.nobita.demo.model.ProductExport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductExportRowMapper implements RowMapper<ProductExport> {
    @Override
    public ProductExport mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductExport productExport=new ProductExport();
        productExport.setDateExport(rs.getTimestamp("date_export").toLocalDateTime());
        productExport.setNameProduct(rs.getString("name_product"));
        productExport.setQuantity(rs.getLong("quantity"));
        return productExport;
    }
}
