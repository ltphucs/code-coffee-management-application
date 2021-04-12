package com.nobita.demo.rowmapper;

import com.nobita.demo.model.Ingredient;
import com.nobita.demo.model.Unit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientRowMapper implements RowMapper<Ingredient> {
    @Override
    public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ingredient ingredient=new Ingredient();
        ingredient.setId(rs.getLong("id"));
        ingredient.setName(rs.getString("name"));
        Unit unit=new Unit();
        unit.setId(rs.getLong("id_unit"));
        unit.setName(rs.getString("name_unit"));
        ingredient.setUnit(unit);
        ingredient.setComment(rs.getString("comment"));
        return ingredient;
    }
}
