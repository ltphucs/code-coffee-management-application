package com.nobita.demo.service;

import com.nobita.demo.model.ImportProduct;

import java.util.List;

public interface ImportProductService extends BaseService<ImportProduct> {

    public List<ImportProduct> findByProduct(Long product);
}
