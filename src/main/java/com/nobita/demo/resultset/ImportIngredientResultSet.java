package com.nobita.demo.resultset;

import com.nobita.demo.model.ImportIngredient;
import com.nobita.demo.model.Ingredient;
import com.nobita.demo.model.Unit;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportIngredientResultSet implements ResultSetExtractor<List<ImportIngredient>> {
    @Override
    public List<ImportIngredient> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<ImportIngredient> importIngredientList=new ArrayList<>();
        while(rs.next()){
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
            importIngredientList.add(importIngredient);
        }
        return importIngredientList;
    }
}
