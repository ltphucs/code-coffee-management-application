package com.nobita.demo.service;

import com.nobita.demo.model.Quantitative;

public interface QuantitativeService extends BaseService<Quantitative> {
    Quantitative findByIdProductAndIdIngredient(Long idProduct, Long idIngredient);

    boolean updateByIdProductAndIdIngredient(Quantitative quantitative, Long idProduct, Long idIngredient);
}
