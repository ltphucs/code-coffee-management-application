package com.nobita.demo.service.impl;

import com.nobita.demo.dao.UnitDAO;
import com.nobita.demo.model.Unit;
import com.nobita.demo.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    UnitDAO unitDAO;

    @Override
    public List<Unit> findAll() {
        return unitDAO.findAll();
    }

    @Override
    public Unit findByID(long id) {
        return unitDAO.findByID(id);
    }

    @Override
    public boolean save(Unit unit) {
        return unitDAO.save(unit);
    }

    @Override
    public boolean update(Unit unit) {
        return unitDAO.update(unit);
    }

    @Override
    public boolean delete(long id) {
        return unitDAO.delete(id);
    }
}
