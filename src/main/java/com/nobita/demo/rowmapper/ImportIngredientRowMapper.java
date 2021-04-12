package com.nobita.demo.rowmapper;

import com.nobita.demo.model.ImportIngredient;
import com.nobita.demo.model.Ingredient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportIngredientRowMapper implements RowMapper<ImportIngredient> {
    @Override
    public ImportIngredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        ImportIngredient importIngredient=new ImportIngredient();
        importIngredient.setId(rs.getLong("id"));
        importIngredient.setDateJoin(rs.getTimestamp("date_join").toLocalDateTime());
        importIngredient.setQuantity(rs.getLong("quantity"));
        importIngredient.setTotalQuantity(rs.getLong("total_quantity"));
        importIngredient.setPrice(rs.getLong("price"));
        importIngredient.setTotalPrice(rs.getLong("total_price"));
        importIngredient.setComment(rs.getString("comment"));
        return importIngredient;
    }
}
