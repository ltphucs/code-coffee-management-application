package com.nobita.demo.resultset;

import com.nobita.demo.model.Ingredient;
import com.nobita.demo.model.Unit;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientResultSet implements ResultSetExtractor<List<Ingredient>> {
    @Override
    public List<Ingredient> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Ingredient> ingredients=new ArrayList<>();
        while(rs.next()){
            Ingredient ingredient=new Ingredient();
            ingredient.setId(rs.getLong("id"));
            ingredient.setName(rs.getString("name"));
            Unit unit=new Unit();
            unit.setId(rs.getLong("id_unit"));
            unit.setName(rs.getString("name_unit"));
            ingredient.setUnit(unit);
            ingredient.setComment(rs.getString("comment"));
            ingredients.add(ingredient);
        }
        return ingredients;
    }
}
