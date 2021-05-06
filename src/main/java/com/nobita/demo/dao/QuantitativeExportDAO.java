package com.nobita.demo.dao;

import com.nobita.demo.model.QuantitativeExport;
import com.nobita.demo.resultset.QuantitativeExportResultSet;
import com.nobita.demo.resultset.QuantitativeReportResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuantitativeExportDAO implements BaseDAO<QuantitativeExport> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<QuantitativeExport> findAll() {
        String sql = "select * from quantity_export";
        return jdbcTemplate.query(sql, new QuantitativeExportResultSet());
    }

    public List<QuantitativeExport> findAllByDateExport(String dateIn,String dateOut){
        String sql="select qx.name_ingredient as name_ingredient,sum(qx.quantity) as quantity from quantitative_export qx where qx.date_export > ? and qx.date_export < ? group by qx.name_ingredient";
        Object[] values={dateIn,dateOut};
        return jdbcTemplate.query(sql,new QuantitativeReportResultSet(),values);
    }

    @Override
    public QuantitativeExport findByID(Long id) {
        return null;
    }

    @Override
    public boolean save(QuantitativeExport quantitativeExport) {
        String sql = "insert into quantitative_export(date_export,name_product,name_ingredient,quantity) values (?,?,?,?)";
        Object[] values = {quantitativeExport.getDateExport(), quantitativeExport.getNameProduct(), quantitativeExport.getNameIngredient(), quantitativeExport.getQuantity()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean update(QuantitativeExport quantitativeExport) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
