package com.nobita.demo.rowmapper;

import com.nobita.demo.model.ImportIngredient;
import com.nobita.demo.model.Ingredient;
import com.nobita.demo.model.Unit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportIngredientRowMapper implements RowMapper<ImportIngredient> {
    @Override
    public ImportIngredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        ImportIngredient importIngredient=new ImportIngredient();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rs.getLong("id_ingredient"));
        ingredient.setName(rs.getString("name_ingredient"));
        Unit unit =new Unit();
        unit.setName(rs.getString("name_unit"));
        ingredient.setUnit(unit);
        importIngredient.setId(rs.getLong("id"));
        importIngredient.setDateJoin(rs.getTimestamp("date_join").toLocalDateTime());
        importIngredient.setQuantity(rs.getLong("import_quatity"));
        importIngredient.setTotalQuantity(rs.getLong("total_quantitative_prediction"));
        importIngredient.setPrice(rs.getLong("price"));
        importIngredient.setTotalPrice(rs.getLong("total_price"));
        importIngredient.setComment(rs.getString("comment"));
        importIngredient.setIngredient(ingredient);
        return importIngredient;
    }
}
