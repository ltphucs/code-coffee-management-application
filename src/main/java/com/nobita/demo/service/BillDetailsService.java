package com.nobita.demo.service;

import com.nobita.demo.model.BillDetail;

import java.util.List;

public interface BillDetailsService extends BaseService<BillDetail> {
    List<BillDetail> findByIdOrder(Long idOrder);

    boolean deleteByIdOrder(Long idOrder);
}
