package com.nobita.demo.service;

import com.nobita.demo.model.OrderDetail;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetail> {
    OrderDetail findByIdProductAndIdOrder(Long idProduct,Long idOrder);

    List<OrderDetail> findByIdOrder(Long idOrder);

    boolean deleteByIdProductAndIdOrder(Long idProduct,Long idOrder);

    boolean deleteByIdOrder(Long idOrder);
}
