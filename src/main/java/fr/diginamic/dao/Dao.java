package fr.diginamic.dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();
    T getById(int id);
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}