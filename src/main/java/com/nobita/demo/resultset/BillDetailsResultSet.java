package com.nobita.demo.resultset;

import com.nobita.demo.model.BillDetail;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDetailsResultSet implements ResultSetExtractor<List<BillDetail>> {
    @Override
    public List<BillDetail> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<BillDetail> billDetails=new ArrayList<>();
        while (rs.next()){
            BillDetail billDetail=new BillDetail();
            billDetail.setIdOrder(rs.getLong("id_order"));
            billDetail.setNameProduct(rs.getString("name_product"));
            billDetail.setQuantity(rs.getLong("quantity"));
            billDetail.setPriceEach(rs.getLong("price_each"));
            billDetails.add(billDetail);
        }
        return billDetails;
    }
}
