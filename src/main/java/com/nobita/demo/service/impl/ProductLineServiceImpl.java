package com.nobita.demo.service.impl;

import com.nobita.demo.dao.ProductDAO;
import com.nobita.demo.dao.ProductLineDAO;
import com.nobita.demo.model.ProductLine;
import com.nobita.demo.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductLineServiceImpl implements ProductLineService {
    @Autowired
    ProductLineDAO productLineDAO;

    @Override
    public List<ProductLine> findAll() {
        return productLineDAO.findAll();
    }

    @Override
    public ProductLine findByID(long id) {
        return productLineDAO.findByID(id);
    }

    @Override
    public boolean save(ProductLine productLine) {
        return productLineDAO.save(productLine);
    }

    @Override
    public boolean update(ProductLine productLine) {
        return productLineDAO.update(productLine);
    }

    @Override
    public boolean delete(long id) {
        return productLineDAO.delete(id);
    }
}
