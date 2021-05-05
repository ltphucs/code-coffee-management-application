package com.nobita.demo.dao;

import com.nobita.demo.model.ProductLine;

import java.util.List;

public interface BaseDAO<T>{
    List<T> findAll();

    T findByID(Long id);

    boolean save(T t );

    boolean update(T t);

    boolean delete(Long id);
}
