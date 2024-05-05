package org.example.app.repository;

import java.util.List;
import java.util.Optional;

public interface AppRepository<T> {
    void create(T obj);

    Optional<List<T>> fetchAll();

    Optional<T> fetchById(Long id);

    void update(Long id, T obj);

    void delete(Long id);
}
