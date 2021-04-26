package com.nobita.demo.resultset;

import com.nobita.demo.model.Order;
import com.nobita.demo.model.OrderDetail;
import com.nobita.demo.model.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailResultSet implements ResultSetExtractor<List<OrderDetail>> {
    @Override
    public List<OrderDetail> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<OrderDetail> orderDetails=new ArrayList<>();
        while(rs.next()){
            OrderDetail orderDetail=new OrderDetail();
            Order order=new Order();
            order.setId(rs.getLong("id_order"));
            orderDetail.setOrder(order);
            Product product=new Product();
            product.setId(rs.getLong("id_product"));
            product.setName(rs.getString("name_product"));
            orderDetail.setProduct(product);
            orderDetail.setQuantity(rs.getLong("quantity"));
            orderDetail.setPriceEach(rs.getLong("price_each"));
            orderDetail.setTotalPrice(rs.getLong("total_price"));
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }
}
