package com.nobita.demo.service;

import java.util.List;

public interface BaseService<T>{
    List<T> findAll();

    T findByID(Long id);

    boolean save(T t );

    boolean update(T t);

    boolean delete(Long id);
}
