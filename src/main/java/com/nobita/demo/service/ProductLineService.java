package com.nobita.demo.service;

import com.nobita.demo.model.ProductLine;

import java.util.List;

public interface ProductLineService extends BaseService<ProductLine>{

    void isDeleted(Long id);

    void restore(Long id);

    List<ProductLine> findAllIsDeleted();
}
