package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.entities.Acteur;

public class ActeurDAO {
    private final EntityManager entityManager;

    public ActeurDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createActeur(Acteur acteur) {
        entityManager.getTransaction().begin();
        entityManager.persist(acteur);
        entityManager.getTransaction().commit();
    }

    public Acteur findById(Long id) {
        return entityManager.find(Acteur.class, id);
    }

    // Autres méthodes CRUD (update, delete, etc.) si nécessaire
}