package com.nobita.demo.service;

import com.nobita.demo.model.Table;

import java.util.List;

public interface TableService extends BaseService<Table>{

    public List<Table> findByArea(Long id);

    public boolean updateStatus(Table table);


}
