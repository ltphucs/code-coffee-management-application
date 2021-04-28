package com.nobita.demo.dao;

import com.nobita.demo.model.Product;
import com.nobita.demo.resultset.ProductResultSet;
import com.nobita.demo.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO implements BaseDAO<Product> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAll() {
        String sql = "select p.* ,pl.name as name_productline from product p left join productline pl on pl.id =p.id_productline";
        return jdbcTemplate.query(sql, new ProductResultSet());
    }

    public List<Product> findAllNotIngredient(){
        String sql = "select p.* ,pl.name as name_productline from product p left join productline pl on pl.id =p.id_productline where p.is_ingredient=0";
        return jdbcTemplate.query(sql,new ProductResultSet());
    }

    @Override
    public Product findByID(Long id) {
        String sql = "select p.* ,pl.name as name_productline from product p left join productline pl on pl.id =p.id_productline where p.id=?";
        Object[] values = {id};
        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), values);
    }


    public List<Product> findByProductLine(Long id){
        String sql = "select p.* ,pl.name as name_productline from product p left join productline pl on pl.id =p.id_productline where p.id_productline=? and p.status ='STOCKING'";
        Object [] values={id};
        return jdbcTemplate.query(sql,new ProductResultSet(),values);
    }

    @Override
    public boolean save(Product product) {
        String sql = "insert into product(name,price,id_productline,image) values(?,?,?,?)";
        Object[] values = {product.getName(), product.getPrice(), product.getProductLine().getId(), product.getImage()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean update(Product product) {
        String sql = "update product set name=?,price =?,id_productline =?,image=? where id=?";
        Object[] values = {product.getName(), product.getPrice(), product.getProductLine().getId(), product.getImage(), product.getId()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "delete from product where id =?";
        Object[] values = {id};
        return jdbcTemplate.update(sql, values) > 0;
    }
}
