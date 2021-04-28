package com.nobita.demo.service.impl;

import com.nobita.demo.dao.TableDAO;
import com.nobita.demo.model.Table;
import com.nobita.demo.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableDAO tableDAO;

    @Override
    public List<Table> findAll() {
        return tableDAO.findAll();
    }

    @Override
    public Table findByID(Long id) {
        return tableDAO.findByID(id);
    }

    @Override
    public boolean save(Table table) {
        return tableDAO.save(table);
    }

    @Override
    public boolean update(Table table) {
        return tableDAO.update(table);
    }

    public boolean updateStatus(Table table) {
        return tableDAO.updateStatus(table);
    }

    @Override
    public boolean delete(Long id) {
        return tableDAO.delete(id);
    }

    @Override
    public List<Table> findByArea(Long id) {
        return tableDAO.findByArea(id);
    }
}
