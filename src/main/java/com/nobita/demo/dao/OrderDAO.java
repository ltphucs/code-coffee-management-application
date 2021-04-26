package com.nobita.demo.dao;

import com.nobita.demo.model.Order;
import com.nobita.demo.resultset.OrderResultSet;
import com.nobita.demo.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAO implements BaseDAO<Order>{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> findAll() {
        String sql="select o.*,t.name as name_table from `order` o left join `table` t on t.id=o.id_table";
        return jdbcTemplate.query(sql,new OrderResultSet());
    }

    @Override
    public Order findByID(Long id) {
        String sql="select o.*,t.name as name_table from `order` o left join `table` t on t.id=o.id_table where o.id=?";
        Object[] values={id};
        return jdbcTemplate.queryForObject(sql,new OrderRowMapper(),values);
    }

    public Order findByTable(Long idTable){
        String sql="select o.*,t.name as name_table from `order` o left join `table` t on t.id=o.id_table where o.id_table=?";
        Object [] values ={idTable};
        return jdbcTemplate.queryForObject(sql,new OrderRowMapper(),values);
    }

    @Override
    public boolean save(Order order) {
        String sql = "insert into `order`(id_table,total_all_price,id_account) values(?,?,?)";
        Object[] values={order.getTable().getId(),order.getTotalAllPrice(),order.getAccount().getId()};
        return jdbcTemplate.update(sql,values) > 0;
    }

    @Override
    public boolean update(Order order) {
        String sql ="update `order` set id_table=?,total_all_price=? values (?,?)";
        Object[] values={order.getTable().getId(),order.getTotalAllPrice()};
        return jdbcTemplate.update(sql,values) > 0;
    }

    @Override
    public boolean delete(Long id) {
        String sql ="delete from `order` where id =?";
        Object[] values={id};
        return jdbcTemplate.update(sql,values) >0 ;
    }
}
