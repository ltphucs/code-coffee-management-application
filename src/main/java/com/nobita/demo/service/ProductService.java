package com.nobita.demo.service;

import com.nobita.demo.model.Product;

import java.util.List;

public interface ProductService extends BaseService<Product> {

    public List<Product> findByProductLine(Long id);

    public List<Product> findAllNotIngredient();
}
