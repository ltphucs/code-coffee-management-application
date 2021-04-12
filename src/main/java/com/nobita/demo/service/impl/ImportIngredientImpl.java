package com.nobita.demo.service.impl;

import com.nobita.demo.dao.ImportIngredientDAO;
import com.nobita.demo.model.ImportIngredient;
import com.nobita.demo.service.ImportIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportIngredientImpl implements ImportIngredientService {
    @Autowired
    ImportIngredientDAO importIngredientDAO;

    @Override
    public List<ImportIngredient> findAll() {
        return importIngredientDAO.findAll();
    }

    @Override
    public ImportIngredient findByID(long id) {
        return importIngredientDAO.findByID(id);
    }

    @Override
    public boolean save(ImportIngredient importIngredient) {
        return importIngredientDAO.save(importIngredient);
    }

    @Override
    public boolean update(ImportIngredient importIngredient) {
        return importIngredientDAO.update(importIngredient);
    }

    @Override
    public boolean delete(long id) {
        return importIngredientDAO.delete(id);
    }
}
