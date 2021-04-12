package com.nobita.demo.dao;

import java.util.List;

public interface BaseDAO<T>{
    List<T> findAll();

    T findByID(int id);

    boolean save(T t );

    boolean update(T t);

    boolean delete(int id);
}
