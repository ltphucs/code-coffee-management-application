package com.nobita.demo.service.impl;

import com.nobita.demo.dao.AreaDAO;
import com.nobita.demo.model.Area;
import com.nobita.demo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDAO areaDAO;

    @Override
    public List<Area> findAll() {
        return areaDAO.findAll();
    }

    @Override
    public Area findByID(long id) {
        return null;
    }

    @Override
    public boolean save(Area area) {
        return false;
    }

    @Override
    public boolean update(Area area) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
