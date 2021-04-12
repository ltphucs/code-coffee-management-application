package com.nobita.demo.resultset;

import com.nobita.demo.model.ProductLine;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductLineResultSet implements ResultSetExtractor<List<ProductLine>> {
    @Override
    public List<ProductLine> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<ProductLine> productLines=new ArrayList<>();
        while(rs.next()){
            ProductLine productLine=new ProductLine();
            productLine.setId(rs.getLong("id"));
            productLine.setName(rs.getString("name"));
            productLines.add(productLine);
        }
        return productLines;
    }
}
