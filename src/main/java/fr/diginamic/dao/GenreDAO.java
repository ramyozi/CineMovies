package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.entities.Genre;

public class GenreDAO {

    private final EntityManager entityManager;

    public GenreDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createGenre(Genre genre) {
        entityManager.persist(genre);
    }
}
