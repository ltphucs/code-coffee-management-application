package com.nobita.demo.service;

import com.nobita.demo.model.Product;
import com.nobita.demo.model.ProductLine;

import java.io.IOException;
import java.util.List;

public interface ProductService extends BaseService<Product> {

    public List<Product> findByProductLine(Long id);

    public List<Product> findAllNotIngredient();

    public List<Product> findByProductLineAndProductName(Long idProductLine, String nameProduct);

    public void uploadAndSaveProductImage(Product product) throws IOException;

    void isDeleted(Long id);

    void restore(Long id);

    List<Product> findAllIsDeleted();


}
