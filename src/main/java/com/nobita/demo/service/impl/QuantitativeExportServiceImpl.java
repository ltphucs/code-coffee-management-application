package com.nobita.demo.service.impl;

import com.nobita.demo.dao.QuantitativeExportDAO;
import com.nobita.demo.model.QuantitativeExport;
import com.nobita.demo.service.QuantitativeExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantitativeExportServiceImpl implements QuantitativeExportService {
    @Autowired
    QuantitativeExportDAO quantitativeExportDAO;

    @Override
    public List<QuantitativeExport> findAll() {
        return quantitativeExportDAO.findAll();
    }

    @Override
    public QuantitativeExport findByID(Long id) {
        return quantitativeExportDAO.findByID(id);
    }

    @Override
    public boolean save(QuantitativeExport quantitativeExport) {
        return quantitativeExportDAO.save(quantitativeExport);
    }

    @Override
    public boolean update(QuantitativeExport quantitativeExport) {
        return quantitativeExportDAO.update(quantitativeExport);
    }

    @Override
    public boolean delete(Long id) {
        return quantitativeExportDAO.delete(id);
    }
}
