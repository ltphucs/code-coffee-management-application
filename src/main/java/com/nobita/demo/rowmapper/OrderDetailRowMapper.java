package com.nobita.demo.rowmapper;

import com.nobita.demo.model.Order;
import com.nobita.demo.model.OrderDetail;
import com.nobita.demo.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailRowMapper implements RowMapper<OrderDetail> {
    @Override
    public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
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
        return orderDetail;
    }
}
