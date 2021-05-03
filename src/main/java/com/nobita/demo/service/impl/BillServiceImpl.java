package com.nobita.demo.service.impl;

import com.nobita.demo.dao.BillDAO;
import com.nobita.demo.model.Bill;
import com.nobita.demo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillDAO billDAO;

    @Override
    public List<Bill> findAll() {
        return billDAO.findAll();
    }

    @Override
    public Bill findByID(Long id) {
        return billDAO.findByID(id);
    }

    @Override
    public List<Bill> findByDateExport(String dateExportIn, String dateExportOut){
        return billDAO.findByDateExport(dateExportIn,dateExportOut);
    }

    @Override
    public boolean save(Bill bill) {
        return billDAO.save(bill);
    }

    @Override
    public boolean update(Bill bill) {
        return billDAO.update(bill);
    }

    @Override
    public boolean delete(Long id) {
        return billDAO.delete(id);
    }

    @Override
    public Bill findByIdOrder(Long idOrder) {
        return billDAO.findByIdOder(idOrder);
    }

    @Override
    public boolean deleteByIdOrder(Long idOrder) {
        return billDAO.deleteByIdOrder(idOrder);
    }
}
