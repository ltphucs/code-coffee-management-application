package com.nobita.demo.dao;

import com.nobita.demo.model.ProductExport;
import com.nobita.demo.resultset.ProductExportResultSet;
import com.nobita.demo.rowmapper.ProductExportRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProductExportDAO implements BaseDAO<ProductExport> {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<ProductExport> findAll() {
        String sql="select * from product_export";
        return jdbcTemplate.query(sql,new ProductExportResultSet());
    }

    public List<ProductExport> findAllByDateExport(LocalDateTime dateIn,LocalDateTime dateOut) {
        String sql = "select * from product_export where date_export > ? and date_export < ?";
        Object[] values = {dateIn, dateOut};
        return jdbcTemplate.query(sql,new ProductExportResultSet(),values);
    }

    @Override
    public ProductExport findByID(Long id) {
        String sql="select * from product_export where id =?";
        Object[] values={};
        return jdbcTemplate.queryForObject(sql,new ProductExportRowMapper(),values);
    }

    @Override
    public boolean save(ProductExport productExport) {
        String sql="insert into product_export (date_export,name_product,quantity) values (?,?,?)";
        Object[] values={productExport.getDateExport(),productExport.getNameProduct(),productExport.getQuantity()};
        return jdbcTemplate.update(sql,values) >0;
    }

    @Override
    public boolean update(ProductExport productExport) {
        String sql="update product_export set date_export=?,name_product=?,quantity=? where id=?";
        Object[] values={productExport.getDateExport(),productExport.getNameProduct(),productExport.getQuantity(),productExport.getId()};
        return jdbcTemplate.update(sql,values) >0;
    }

    @Override
    public boolean delete(Long id) {
        String sql="delete from product_export where id=?";
        Object[] values={id};
        return jdbcTemplate.update(sql,values) >0 ;
    }
}
