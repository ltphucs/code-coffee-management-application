package com.nobita.demo.resultset;

import com.nobita.demo.model.ProductExport;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductExportResultSet implements ResultSetExtractor<List<ProductExport>> {
    @Override
    public List<ProductExport> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<ProductExport> productExports=new ArrayList<>();
        while(rs.next()){
            ProductExport productExport=new ProductExport();
            productExport.setDateExport(rs.getTimestamp("date_export").toLocalDateTime());
            productExport.setNameProduct(rs.getString("name_product"));
            productExport.setQuantity(rs.getLong("quantity"));
            productExport.setIdProduct(rs.getLong("id_product"));
            productExports.add(productExport);
        }
        return productExports;
    }
}
