package com.nobita.demo.service.impl;

import com.nobita.demo.dao.OrderDAO;
import com.nobita.demo.model.Order;
import com.nobita.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDAO orderDAO;

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public Order findByID(Long id) {
        return orderDAO.findByID(id);
    }

    @Override
    public Order findByTable(Long idTable) {
        return orderDAO.findByTable(idTable);
    }

    @Override
    public boolean save(Order order) {
        return orderDAO.save(order);
    }

    @Override
    public boolean update(Order order) {
        return orderDAO.update(order);
    }

    @Override
    public boolean delete(Long id) {
        return orderDAO.delete(id);
    }
}
