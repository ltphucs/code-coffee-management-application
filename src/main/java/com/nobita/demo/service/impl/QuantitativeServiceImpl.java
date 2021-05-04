package com.nobita.demo.service.impl;

import com.nobita.demo.dao.QuantitativeDAO;
import com.nobita.demo.model.Quantitative;
import com.nobita.demo.service.QuantitativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantitativeServiceImpl implements QuantitativeService {
    @Autowired
    QuantitativeDAO quantitativeDAO;

    @Override
    public List<Quantitative> findAll() {
        return quantitativeDAO.findAll();
    }

    @Override
    public Quantitative findByID(Long id) {
        return quantitativeDAO.findByID(id);
    }

    @Override
    public boolean save(Quantitative quantitative) {
        return quantitativeDAO.save(quantitative);
    }

    @Override
    public boolean update(Quantitative quantitative) {
        return quantitativeDAO.update(quantitative);
    }

    @Override
    public boolean delete(Long id) {
        return quantitativeDAO.delete(id);
    }

    @Override
    public Quantitative findByIdProductAndIdIngredient(Long idProduct, Long idIngredient) {
        return quantitativeDAO.findByIdProductAndIdIngredient(idProduct,idIngredient);
    }

    @Override
    public boolean updateByIdProductAndIdIngredient(Quantitative quantitative, Long idProduct, Long idIngredient) {
        return quantitativeDAO.updateByIdProductAndIdIngredient(quantitative,idProduct,idIngredient);
    }
}
