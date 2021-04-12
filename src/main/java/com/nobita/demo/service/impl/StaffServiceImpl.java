package com.nobita.demo.service.impl;

import com.nobita.demo.dao.StaffDAO;
import com.nobita.demo.model.Staff;
import com.nobita.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffDAO staffDAO;

    @Override
    public List<Staff> findAll() {
        return staffDAO.findAll();
    }

    @Override
    public Staff findByID(long id) {
        return staffDAO.findByID(id);
    }

    @Override
    public boolean save(Staff staff) {
        return staffDAO.save(staff);
    }

    @Override
    public boolean update(Staff staff) {
        return staffDAO.update(staff);
    }

    @Override
    public boolean delete(long id) {
        return staffDAO.delete(id);
    }
}
