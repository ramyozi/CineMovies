package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.entities.Langue;

public class LangueDAO {
    private final EntityManager entityManager;

    public LangueDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createLangue(Langue langue) {
        entityManager.getTransaction().begin();
        entityManager.persist(langue);
        entityManager.getTransaction().commit();
    }

    public Langue findById(Long id) {
        return entityManager.find(Langue.class, id);
    }

}