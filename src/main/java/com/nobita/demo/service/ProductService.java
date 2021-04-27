package com.nobita.demo.service;

import com.nobita.demo.model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService extends BaseService<Product> {

    public List<Product> findByProductLine(Long id);

    public List<Product> findAllNotIngredient();

    public void uploadAndSaveProductImage(Product product) throws IOException;

}
