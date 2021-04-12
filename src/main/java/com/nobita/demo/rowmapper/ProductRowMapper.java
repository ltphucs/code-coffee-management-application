package com.nobita.demo.rowmapper;

import com.nobita.demo.model.Product;
import com.nobita.demo.model.ProductLine;
import com.nobita.demo.model.ProductStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product=new Product();
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setInventory(rs.getLong("inventory"));
        product.setPrice(rs.getLong("price"));
        ProductLine productLine=new ProductLine();
        productLine.setId(rs.getLong("id_productline"));
        productLine.setName(rs.getString("name_productline"));
        product.setProductLine(productLine);
        product.setImage(rs.getString("image"));
        product.setProductStatus(ProductStatus.valueOf(rs.getString("status")));
        return product;
    }
}
