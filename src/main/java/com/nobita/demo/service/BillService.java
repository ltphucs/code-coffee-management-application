package com.nobita.demo.service;

import com.nobita.demo.model.Bill;

import java.time.LocalDateTime;
import java.util.List;

public interface BillService extends BaseService<Bill> {
    Bill findByIdOrder(Long idOrder);

    boolean deleteByIdOrder(Long idOrder);

    public List<Bill> findByDateExport(String dateExportIn, String dateExportOut);
}
