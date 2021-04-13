package com.nobita.demo.dao;

import com.nobita.demo.model.ImportIngredient;
import com.nobita.demo.resultset.ImportIngredientResultSet;
import com.nobita.demo.rowmapper.ImportIngredientRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImportIngredientDAO implements BaseDAO<ImportIngredient> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ImportIngredient> findAll() {
        String sql="select ip.*,i.name as name_ingredient from import_ingredient ip left join ingredient i on i.id=ip.id_ingredient";
        return jdbcTemplate.query(sql,new ImportIngredientResultSet());
    }

    @Override
    public ImportIngredient findByID(long id) {
        String sql="select ip.*,i.name as name_ingredient from import_ingredient ip left join ingredient i on i.id=ip.id_ingredient where ip.id=?";
        Object [] values ={id};
        ImportIngredient ingredient=jdbcTemplate.queryForObject(sql,new ImportIngredientRowMapper(),values);
        return ingredient;
    }

    @Override
    public boolean save(ImportIngredient importIngredient) {
        String sql="insert into import_ingredient (id_ingredient,quantity,total_quantity,price,total_price,comment) values (?,?,?,?,?,?)";
        Object[] values ={importIngredient.getIngredient().getId(),importIngredient.getQuantity(),importIngredient.getTotalQuantity(),importIngredient.getPrice(),importIngredient.getTotalPrice(),importIngredient.getComment()};
        return jdbcTemplate.update(sql,values) > 0;
    }

    @Override
    public boolean update(ImportIngredient importIngredient) {
        String sql="update import_ingredient set id_ingredient=?,quantity=?,total_quantity=?,price=?,total_price=?,comment=? where id=?";
        Object[] values ={importIngredient.getIngredient().getId(),importIngredient.getQuantity(),importIngredient.getTotalQuantity(),importIngredient.getPrice(),importIngredient.getTotalPrice(),importIngredient.getComment(),importIngredient.getId()};
        return jdbcTemplate.update(sql,values) > 0;
    }

    @Override
    public boolean delete(long id) {
        String sql="delete from import_ingredient where id=?";
        Object[] values ={id};
        return jdbcTemplate.update(sql,values) > 0;
    }
}
