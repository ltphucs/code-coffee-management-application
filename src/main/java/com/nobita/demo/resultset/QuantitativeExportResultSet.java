package com.nobita.demo.resultset;

import com.nobita.demo.model.Quantitative;
import com.nobita.demo.model.QuantitativeExport;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuantitativeExportResultSet implements ResultSetExtractor<List<QuantitativeExport>> {
    @Override
    public List<QuantitativeExport> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<QuantitativeExport> quantitativeExports=new ArrayList<>();
        while(rs.next()){
            QuantitativeExport quantitativeExport=new QuantitativeExport();
            quantitativeExport.setId(rs.getLong("id"));
            quantitativeExport.setDateExport(rs.getTimestamp("date_export").toLocalDateTime());
            quantitativeExport.setNameIngredient(rs.getString("name_ingredient"));
            quantitativeExport.setQuantity(rs.getDouble("quantity"));
            quantitativeExports.add(quantitativeExport);
        }
        return quantitativeExports;
    }
}
