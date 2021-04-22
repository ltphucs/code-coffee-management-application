package com.nobita.demo.dao;

import com.nobita.demo.model.ImportProduct;
import com.nobita.demo.model.Table;
import com.nobita.demo.resultset.ImportProductResultSet;
import com.nobita.demo.resultset.TableResultSet;
import com.nobita.demo.rowmapper.ImportProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImportProductDAO implements BaseDAO<ImportProduct> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ImportProduct> findAll() {
        String sql = "select ip.*,p.*, pl.name as productline_name from import_product ip left join product p on p.id=ip.id_product join productline pl on p.id_productline = pl.id;";
        return jdbcTemplate.query(sql, new ImportProductResultSet());
    }

    @Override
    public ImportProduct findByID(Long id) {
        String sql = "select ip.*,p.*, pl.name as productline_name from import_product ip left join product p on p.id=ip.id_product join productline pl on p.id_productline = pl.id where ip.id=?";
        Object[] values = {id};
        return jdbcTemplate.queryForObject(sql, new ImportProductRowMapper(), values);
    }

    @Override
    public boolean save(ImportProduct importProduct) {
        String sql = "insert into import_product(id_product,quantity,price,total_price,comment) values (?,?,?,?,?)";
        Object[] values = {importProduct.getProduct().getId(), importProduct.getQuantity(), importProduct.getPrice(), importProduct.getTotalPrice(), importProduct.getComment()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean update(ImportProduct importProduct) {
        String sql ="update import_product set id_product=?,quantity=?,price =?,total_price=?,comment=? where id =?";
        Object[] values = {importProduct.getProduct().getId(), importProduct.getQuantity(), importProduct.getPrice(), importProduct.getTotalPrice(), importProduct.getComment(),importProduct.getId()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean delete(Long id) {
        String sql="delete from import_product where id=?";
        Object[] values={id};
        return jdbcTemplate.update(sql,values) >0;
    }

    public List<ImportProduct> findByProduct(Long product){
        String sql= "select ip.*,p.name as name_product from `import_product` ip left join product p on p.id=ip.id_product where p.id=?";
        Object [] values ={product};
        return jdbcTemplate.query(sql,new ImportProductResultSet(),values);
    }
}
