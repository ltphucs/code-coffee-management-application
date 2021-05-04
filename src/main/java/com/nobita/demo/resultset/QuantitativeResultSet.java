package com.nobita.demo.resultset;

import com.nobita.demo.model.Ingredient;
import com.nobita.demo.model.Product;
import com.nobita.demo.model.Quantitative;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuantitativeResultSet implements ResultSetExtractor<List<Quantitative>> {
    @Override
    public List<Quantitative> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Quantitative> quantitatives=new ArrayList<>();
        while(rs.next()){
            Quantitative quantitative=new Quantitative();
            Product product=new Product();
            product.setId(rs.getLong("id_product"));
            product.setName(rs.getString("name_product"));
            quantitative.setProduct(product);
            Ingredient ingredient=new Ingredient();
            ingredient.setId(rs.getLong("id_ingredient"));
            ingredient.setName(rs.getString("name_ingredient"));
            quantitative.setIngredient(ingredient);
            quantitative.setQuantity(rs.getDouble("quantity"));
            quantitatives.add(quantitative);
        }
        return quantitatives;
    }
}
