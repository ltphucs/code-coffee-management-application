package com.nobita.demo.dao;

import com.nobita.demo.model.BillDetail;
import com.nobita.demo.resultset.BillDetailsResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillDetailsDAO implements BaseDAO<BillDetail> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<BillDetail> findAll() {
        String sql="select * from billdetail";
        return jdbcTemplate.query(sql,new BillDetailsResultSet());
    }

    @Override
    public BillDetail findByID(Long id) {
        return null;
    }

    public List<BillDetail> findByIdOrder(Long idOrder){
        String sql="select * from billdetail where id_order=?";
        Object[] values ={idOrder};
        return jdbcTemplate.query(sql,new BillDetailsResultSet(),values);
    }

    @Override
    public boolean save(BillDetail billDetail) {
        String sql="insert into billdetail(id_order,name_product,quantity,price_each,id_product) values (?,?,?,?,?)";
        Object[] values={billDetail.getIdOrder(),billDetail.getNameProduct(),billDetail.getQuantity(),billDetail.getPriceEach(),billDetail.getIdProduct()};
        return jdbcTemplate.update(sql,values)>0;
    }

    @Override
    public boolean update(BillDetail billDetail) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    public boolean deleteByIdOrder(Long idOrder){
        String sql="delete from billdetail where id_order=?";
        Object[] values ={idOrder};
        return jdbcTemplate.update(sql,values) >0;
    }
}
