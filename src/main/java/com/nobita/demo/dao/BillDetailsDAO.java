package com.nobita.demo.dao;

import com.nobita.demo.model.BillDetail;
import com.nobita.demo.model.ProductExport;
import com.nobita.demo.model.QuantitativeExport;
import com.nobita.demo.resultset.BillDetailsResultSet;
import com.nobita.demo.resultset.ProductExportResultSet;
import com.nobita.demo.resultset.QuantitativeExportResultSet;
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
    
    public List<QuantitativeExport> getQuantitativeExport(Long idOrder){
        String sql ="select bd.id_order as id_order,b.date_export as date_export,bd.name_product as name_product,i.name as name_ingredient,q.quantity*bd.quantity as quantity from billdetail bd left join bill b on b.id_order=bd.id_order left join quantitative q on q.id_product=bd.id_product left join ingredient i on q.id_ingredient=i.id left join product p on p.id=bd.id_product where bd.id_order=? and p.is_ingredient=1";
        Object[] values={idOrder};
        return jdbcTemplate.query(sql,new QuantitativeExportResultSet(),values);
    }

    public List<ProductExport> getProductExport(Long idOrder){
        String sql="select b.date_export as date_export,bd.name_product as name_product,bd.quantity as quantity from billdetail bd left join bill b on b.id_order=bd.id_order left join product p on p.id=bd.id_product where bd.id_order=? and p.is_ingredient = 0";
        Object[] values={idOrder};
        return jdbcTemplate.query(sql,new ProductExportResultSet(),values);
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
