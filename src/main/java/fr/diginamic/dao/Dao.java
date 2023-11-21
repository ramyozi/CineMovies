package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.entities.Film;

public interface Dao<T> {
    List<T> getAll();
    T getById(int id);
    T getByName(String nom);
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}