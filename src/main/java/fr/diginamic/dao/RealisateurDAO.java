package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.entities.Realisateur;

public class RealisateurDAO {
    private final EntityManager entityManager;

    public RealisateurDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createRealisateur(Realisateur realisateur) {
        entityManager.getTransaction().begin();
        entityManager.persist(realisateur);
        entityManager.getTransaction().commit();
    }

    public Realisateur findById(Long id) {
        return entityManager.find(Realisateur.class, id);
    }

    // Autres méthodes CRUD (update, delete, etc.) si nécessaire
}