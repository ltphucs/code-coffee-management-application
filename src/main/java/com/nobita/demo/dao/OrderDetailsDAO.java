package com.nobita.demo.dao;

import com.nobita.demo.model.OrderDetail;
import com.nobita.demo.resultset.OrderDetailResultSet;
import com.nobita.demo.rowmapper.OrderDetailRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDetailsDAO implements BaseDAO<OrderDetail> {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<OrderDetail> findAll() {
        String sql = "select od.*,p.name as name_product from orderdetail od left join product p on p.id =od.id_product";
        return jdbcTemplate.query(sql, new OrderDetailResultSet());
    }

    @Override
    public OrderDetail findByID(Long id) {
        return null;
    }

    public OrderDetail findByIdProductAndIdOrder(Long idProduct,Long idOrder) {
        String sql = "select od.*,p.name as name_product from orderdetail od left join product p on p.id =od.id_product where od.id_product =? and od.id_order=?";
        Object[] values = {idProduct,idOrder};
        return jdbcTemplate.queryForObject(sql, new OrderDetailRowMapper(), values);
    }

    public List<OrderDetail> findByIdOrder(Long idOrder) {
        String sql = "select od.*,p.name as name_product from orderdetail od left join product p on p.id =od.id_product where od.id_order =?";
        Object[] values = {idOrder};
        return jdbcTemplate.query(sql, new OrderDetailResultSet(), values);
    }

    @Override
    public boolean save(OrderDetail orderDetail) {
        String sql = "insert into orderdetail (id_order,id_product,quantity,price_each,total_price) values(?,?,?,?,?) on duplicate key update quantity=?,total_price=?";
        Object[] values = {orderDetail.getOrder().getId(),orderDetail.getProduct().getId(), orderDetail.getQuantity(), orderDetail.getPriceEach(), orderDetail.getTotalPrice(),orderDetail.getQuantity(),orderDetail.getTotalPrice()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean update(OrderDetail orderDetail) {
        String sql = "update orderdetail set quantity=?,total_price=? where id_product=?";
        Object[] values = {orderDetail.getQuantity(), orderDetail.getTotalPrice()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    public boolean deleteByIdProductAndIdOrder(Long idProduct,Long idOrder) {
        String sql = "delete from orderdetail where id_product =? and id_order=?";
        Object[] values = {idProduct,idOrder};
        return jdbcTemplate.update(sql, values) > 0;
    }

    public boolean deleteByIdOrder(Long idOrder) {
        String sql = "delete from orderdetail where id_order =?";
        Object[] values ={idOrder};
        return jdbcTemplate.update(sql, values) > 0;
    }
}
