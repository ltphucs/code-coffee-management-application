package com.nobita.demo.service.impl;

import com.nobita.demo.dao.ImportProductDAO;
import com.nobita.demo.model.ImportProduct;
import com.nobita.demo.service.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportProductServiceImpl implements ImportProductService {
    @Autowired
    ImportProductDAO importProductDAO;

    @Override
    public List<ImportProduct> findAll() {
        return importProductDAO.findAll();
    }

    @Override
    public ImportProduct findByID(long id) {
        return importProductDAO.findByID(id);
    }

    @Override
    public boolean save(ImportProduct importProduct) {
        return importProductDAO.save(importProduct);
    }

    @Override
    public boolean update(ImportProduct importProduct) {
        return importProductDAO.update(importProduct);
    }

    @Override
    public boolean delete(long id) {
        return importProductDAO.delete(id);
    }
}
