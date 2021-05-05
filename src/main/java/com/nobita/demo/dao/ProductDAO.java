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
        String sql = "select p.* ,pl.name as name_productline from product p left join productline pl on pl.id =p.id_productline where p.deleted=0";
        return jdbcTemplate.query(sql, new ProductResultSet());
    }

    public List<Product> findAllNotIngredient() {
        String sql = "select p.* ,pl.name as name_productline from product p left join productline pl on pl.id =p.id_productline where p.is_ingredient=0 and p.deleted=0";
        return jdbcTemplate.query(sql, new ProductResultSet());
    }

    @Override
    public Product findByID(Long id) {
        String sql = "select p.* ,pl.name as name_productline from product p left join productline pl on pl.id =p.id_productline where p.id=? and p.deleted=0";
        Object[] values = {id};
        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), values);
    }


    public List<Product> findByProductLine(Long id) {
        String sql = "select p.* ,pl.name as name_productline from product p left join productline pl on pl.id =p.id_productline where p.id_productline=? and p.status <>'STOP_SELLING' and p.deleted=0";
        Object[] values = {id};
        return jdbcTemplate.query(sql, new ProductResultSet(), values);
    }

    @Override
    public boolean save(Product product) {
        String sql = "insert into product(name,inventory, price,id_productline,image,status, is_ingredient) values(?,?,?,?,?,?,?)";
        Object[] values = {product.getName(), product.getInventory(), product.getPrice(), product.getProductLine().getId(), product.getImage(), product.getProductStatus().toString(), product.isIngredient()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean update(Product product) {
        String sql = "update product set name=?,inventory = ?, price =?,id_productline =?,image=?,status=?, is_ingredient=? where id=?";
        Object[] values = {product.getName(), product.getInventory(), product.getPrice(), product.getProductLine().getId(), product.getImage(), product.getProductStatus().toString(), product.isIngredient(), product.getId()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    public boolean updateInventory(Product product){
        String sql ="update product set inventory=? where id =?";
        Object[] values={product.getInventory(),product.getId()};
        return jdbcTemplate.update(sql,values) >0 ;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "update product set deleted=1 where id =?";
        Object[] values = {id};
        return jdbcTemplate.update(sql, values) > 0;
    }

    public List<Product> findByProductLineAndProductName(Long idProductLine, String nameProduct) {
        String sql = "select p.* ,pl.name as name_productline from product p left join productline pl on pl.id =p.id_productline where p.id_productline=? and p.status <>'STOP_SELLING' and p.deleted=0 and p.name like ?";
        nameProduct = "%" + nameProduct + "%";
        Object[] values = {idProductLine, nameProduct};
        return jdbcTemplate.query(sql, new ProductResultSet(), values);
    }
}
