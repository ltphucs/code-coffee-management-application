package com.nobita.demo.resultset;

import com.nobita.demo.model.ImportProduct;
import com.nobita.demo.model.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportProductResultSet implements ResultSetExtractor<List<ImportProduct>> {
    @Override
    public List<ImportProduct> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<ImportProduct> importProducts=new ArrayList<>();
        while(rs.next()){
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
            importProducts.add(importProduct);
        }
        return importProducts;
    }
}
