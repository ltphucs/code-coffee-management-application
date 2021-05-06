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

    public List<ProductExport> findAllByDateExport(String dateIn,String dateOut) {
        String sql = "select max(px.id) as id,max(px.date_export) as date_export,name_product,sum(px.quantity) as quantity,px.id_product as id_product from product_export px where px.date_export > ? and px.date_export < ? group by px.name_product";
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
        String sql="insert into product_export (date_export,name_product,quantity,id_product) values (?,?,?,?)";
        Object[] values={productExport.getDateExport(),productExport.getNameProduct(),productExport.getQuantity(),productExport.getIdProduct()};
        return jdbcTemplate.update(sql,values) > 0;
    }

    @Override
    public boolean update(ProductExport productExport) {
        String sql="update product_export set name_product=? where id_product=?";
        Object[] values={productExport.getNameProduct(),productExport.getIdProduct()};
        return jdbcTemplate.update(sql,values) >0;
    }

    @Override
    public boolean delete(Long id) {
        String sql="delete from product_export where id=?";
        Object[] values={id};
        return jdbcTemplate.update(sql,values) >0 ;
    }
}
