package com.nobita.demo.rowmapper;

import com.nobita.demo.model.ImportProduct;
import com.nobita.demo.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportProductRowMapper implements RowMapper<ImportProduct> {
    @Override
    public ImportProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        ImportProduct importProduct =new ImportProduct();
        Product product=new Product();
        product.setId(rs.getLong("id_product"));
        product.setName(rs.getString("name_product"));
        importProduct.setProduct(product);
        importProduct.setDateJoin(rs.getTimestamp("date_join").toLocalDateTime());
        importProduct.setQuantity(rs.getInt("quantity"));
        importProduct.setPrice(rs.getLong("price"));
        importProduct.setTotalPrice(rs.getLong("total_price"));
        importProduct.setComment(rs.getString("comment"));
        return importProduct;
    }
}
