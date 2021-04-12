package com.nobita.demo.resultset;

import com.nobita.demo.model.ImportIngredient;
import com.nobita.demo.model.Ingredient;
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
            importIngredient.setId(rs.getLong("id"));
            importIngredient.setDateJoin(rs.getTimestamp("date_join").toLocalDateTime());
            importIngredient.setQuantity(rs.getLong("quantity"));
            importIngredient.setTotalQuantity(rs.getLong("total_quantity"));
            importIngredient.setPrice(rs.getLong("price"));
            importIngredient.setTotalPrice(rs.getLong("total_price"));
            importIngredient.setComment(rs.getString("comment"));
            importIngredientList.add(importIngredient);
        }
        return importIngredientList;
    }
}
