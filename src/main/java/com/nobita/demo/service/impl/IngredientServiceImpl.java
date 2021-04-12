package com.nobita.demo.service.impl;

import com.nobita.demo.dao.IngredientDAO;
import com.nobita.demo.model.Ingredient;
import com.nobita.demo.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    IngredientDAO ingredientDAO;

    @Override
    public List<Ingredient> findAll() {
        return ingredientDAO.findAll();
    }

    @Override
    public Ingredient findByID(long id) {
        return ingredientDAO.findByID(id);
    }

    @Override
    public boolean save(Ingredient ingredient) {
        return ingredientDAO.save(ingredient);
    }

    @Override
    public boolean update(Ingredient ingredient) {
        return ingredientDAO.update(ingredient);
    }

    @Override
    public boolean delete(long id) {
        return ingredientDAO.delete(id);
    }
}
