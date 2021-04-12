package com.nobita.demo.rowmapper;

import com.nobita.demo.model.ProductLine;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductLineRowMapper implements RowMapper<ProductLine> {
    @Override
    public ProductLine mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductLine productLine=new ProductLine();
        productLine.setId(rs.getLong("id"));
        productLine.setName(rs.getString("name"));
        return productLine;
    }
}
