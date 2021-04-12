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

    @Override
    public Product findByID(long id) {
        String sql = "select p.* ,pl.name as name_productline from product p left join productline pl on pl.id =p.id_productline where p.id=?";
        Object [] values ={id};
        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(),values);
    }

    @Override
    public boolean save(Product product) {
        String sql ="insert into product(name,inventory,price,id_productline,image,status) values(?,?,?,?,?,?)";
        Object [] values={product.getName(),product.getInventory(),product.getPrice(),product.getProductLine().getId(),product.getImage(),product.getProductStatus()};
        return jdbcTemplate.update(sql,values) > 0;
    }

    @Override
    public boolean update(Product product) {
        String sql = "update product set name=?,inventory=?,price =?,id_productline =?,image=?,status=? where id=?";
        Object [] values={product.getName(),product.getInventory(),product.getPrice(),product.getProductLine().getId(),product.getImage(),product.getProductStatus(),product.getId()};
        return jdbcTemplate.update(sql,values) >0 ;
    }

    @Override
    public boolean delete(long id) {
        String sql= "delete from product where id =?";
        Object[] values={id};
        return jdbcTemplate.update(sql,values) >0;
    }
}
