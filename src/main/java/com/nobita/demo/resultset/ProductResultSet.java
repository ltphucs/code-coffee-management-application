package com.nobita.demo.resultset;

import com.nobita.demo.model.Product;
import com.nobita.demo.model.ProductLine;
import com.nobita.demo.model.ProductStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductResultSet implements ResultSetExtractor<List<Product>> {
    @Override
    public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Product> products=new ArrayList<>();
        while(rs.next()){
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
            products.add(product);
        }
        return products;
    }
}
