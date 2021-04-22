package com.nobita.demo.rowmapper;

import com.nobita.demo.model.ImportProduct;
import com.nobita.demo.model.Product;
import com.nobita.demo.model.ProductLine;
import com.nobita.demo.model.en.ProductStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportProductRowMapper implements RowMapper<ImportProduct> {
    @Override
    public ImportProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        ImportProduct importProduct =new ImportProduct();
        Product product=new Product();
        product.setId(rs.getLong("id_product"));
        product.setName(rs.getString("name"));
        product.setInventory(rs.getLong("inventory"));
        product.setPrice(rs.getLong("price"));
        ProductLine productLine = new ProductLine();
        productLine.setId(rs.getLong("id_productline"));
        productLine.setName(rs.getString("productline_name"));
        product.setProductLine(productLine);
        product.setImage(rs.getString("image"));
        product.setProductStatus(ProductStatus.valueOf(rs.getString("status")));
        importProduct.setProduct(product);
        importProduct.setId(rs.getLong("id"));
        importProduct.setDateJoin(rs.getTimestamp("date_join").toLocalDateTime());
        importProduct.setQuantity(rs.getInt("quantity"));
        importProduct.setPrice(rs.getLong("price"));
        importProduct.setTotalPrice(rs.getLong("total_price"));
        importProduct.setComment(rs.getString("comment"));
        return importProduct;
    }
}
