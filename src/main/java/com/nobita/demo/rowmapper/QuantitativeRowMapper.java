package com.nobita.demo.rowmapper;

import com.nobita.demo.model.Ingredient;
import com.nobita.demo.model.Product;
import com.nobita.demo.model.Quantitative;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuantitativeRowMapper implements RowMapper<Quantitative> {
    @Override
    public Quantitative mapRow(ResultSet rs, int rowNum) throws SQLException {
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
        return quantitative;
    }
}
