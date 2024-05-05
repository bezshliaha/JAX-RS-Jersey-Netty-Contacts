package org.example.app.service;

import java.util.List;

public interface AppService<T> {
    T create(T obj);

    List<T> fetchAll();

    T fetchById(Long id);

    T update(Long id, T obj);

    boolean delete(Long id);
}