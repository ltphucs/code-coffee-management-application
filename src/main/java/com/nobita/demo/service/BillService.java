package com.nobita.demo.service;

import com.nobita.demo.model.Bill;

public interface BillService extends BaseService<Bill> {
    Bill findByIdOrder(Long idOrder);

    boolean deleteByIdOrder(Long idOrder);
}
