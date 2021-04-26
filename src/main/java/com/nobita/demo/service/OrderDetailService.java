package com.nobita.demo.service;

import com.nobita.demo.model.OrderDetail;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetail> {
    OrderDetail findByIdProduct(Long idProduct);

    List<OrderDetail> findByIdOrder(Long idOrder);

    boolean deleteByIdProduct(Long idProduct);
}
