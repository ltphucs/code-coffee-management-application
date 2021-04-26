package com.nobita.demo.service;

import com.nobita.demo.model.Order;

public interface OrderService extends BaseService<Order>{

    Order findByTable(Long idTable);
}
