package com.nobita.demo.dao;

import com.nobita.demo.model.Table;
import com.nobita.demo.resultset.TableResultSet;
import com.nobita.demo.rowmapper.TableRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TableDAO implements BaseDAO<Table>{

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override

    public List<Table> findAll() {
        String sql= "select t.*,a.name as name_area from `table` t left join area a on a.id=t.id_area";
        return jdbcTemplate.query(sql,new TableResultSet());
    }

    public List<Table> findByArea(Long area){
        String sql= "select t.*,a.name as name_area from `table` t left join area a on a.id=t.id_area where a.id=?";
        Object [] values ={area};
        return jdbcTemplate.query(sql,new TableResultSet(),values);
    }

    @Override
    public Table findByID(Long id) {
        String sql= "select t.*,a.name as name_area from `table` t left join area a on a.id=t.id_area where t.id = ?";
        Object [] values ={id};
        return jdbcTemplate.queryForObject(sql,new TableRowMapper(),values);
    }

    @Override
    public boolean save(Table table) {
        String sql ="insert into `table`(name,id_area,comment) values (?,?,?) on duplicate key";
        Object [] values ={table.getName(),table.getArea().getId(),table.getComment()};
        return jdbcTemplate.update(sql,values) > 0;
    }

    @Override
    public boolean update(Table table) {
        String sql = "update `table` set name =?,comment=? where id= ?";
        Object[] values ={table.getName(),table.getComment(),table.getId()};
        return jdbcTemplate.update(sql,values) > 0;
    }

    public boolean updateStatus(Table table){
        String sql="update `table` set status =? where id=?";
        Object [] values={table.getTableStatus().toString(),table.getId()};
        return jdbcTemplate.update(sql,values) >0 ;
    }

    @Override
    public boolean delete(Long id) {
        String sql="delete from `table` where id=?";
        Object[] values ={id};
        return jdbcTemplate.update(sql,values) > 0;
    }
}
