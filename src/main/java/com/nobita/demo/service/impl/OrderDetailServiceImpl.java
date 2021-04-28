package com.nobita.demo.service.impl;

import com.nobita.demo.dao.OrderDetailsDAO;
import com.nobita.demo.model.OrderDetail;
import com.nobita.demo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailsDAO orderDetailsDAO;

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailsDAO.findAll();
    }

    @Override
    public OrderDetail findByID(Long id) {
        return orderDetailsDAO.findByID(id);
    }


    @Override
    public OrderDetail findByIdProductAndIdOrder(Long idProduct,Long idOrder) {
        return orderDetailsDAO.findByIdProductAndIdOrder(idProduct,idOrder);
    }

    @Override
    public List<OrderDetail> findByIdOrder(Long idOrder) {
        return orderDetailsDAO.findByIdOrder(idOrder);
    }

    @Override
    public boolean save(OrderDetail orderDetail) {
        return orderDetailsDAO.save(orderDetail);
    }

    @Override
    public boolean update(OrderDetail orderDetail) {
        return orderDetailsDAO.update(orderDetail);
    }

    @Override
    public boolean delete(Long id) {
        return orderDetailsDAO.delete(id);
    }


    @Override
    public boolean deleteByIdProductAndIdOrder(Long idProduct,Long idOrder) {
        return orderDetailsDAO.deleteByIdProductAndIdOrder(idProduct,idOrder);
    }

    @Override
    public boolean deleteByIdOrder(Long idOrder) {
        return orderDetailsDAO.deleteByIdOrder(idOrder);
    }
}
