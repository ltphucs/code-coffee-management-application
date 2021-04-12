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
    AreaDAO areaDAO;

    @Override
    public List<Area> findAll() {
        return areaDAO.findAll();
    }

    @Override
    public Area findByID(long id) {
        return areaDAO.findByID(id);
    }

    @Override
    public boolean save(Area area) {
        return areaDAO.save(area);
    }

    @Override
    public boolean update(Area area) {
        return areaDAO.update(area);
    }

    @Override
    public boolean delete(long id) {
        return areaDAO.delete(id);
    }
}
