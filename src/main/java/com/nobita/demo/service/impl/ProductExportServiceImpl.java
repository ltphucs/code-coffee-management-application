package com.nobita.demo.service.impl;

import com.nobita.demo.dao.ProductExportDAO;
import com.nobita.demo.model.ProductExport;
import com.nobita.demo.service.ProductExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductExportServiceImpl implements ProductExportService {
    @Autowired
    ProductExportDAO productExportDAO;

    @Override
    public List<ProductExport> findAll() {
        return productExportDAO.findAll();
    }

    @Override
    public ProductExport findByID(Long id) {
        return productExportDAO.findByID(id);
    }

    @Override
    public boolean save(ProductExport productExport) {
        return productExportDAO.save(productExport);
    }

    @Override
    public boolean update(ProductExport productExport) {
        return productExportDAO.update(productExport);
    }

    @Override
    public boolean delete(Long id) {
        return productExportDAO.delete(id);
    }

    @Override
    public List<ProductExport> findAllByDateExport(String dateIn, String dateOut) {
        return productExportDAO.findAllByDateExport(dateIn,dateOut);
    }
}
