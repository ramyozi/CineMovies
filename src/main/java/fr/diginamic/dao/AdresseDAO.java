package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.entities.Adresse;

public class AdresseDAO {
    private final EntityManager entityManager;

    public AdresseDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createAdresse(Adresse adresse) {
        entityManager.getTransaction().begin();
        entityManager.persist(adresse);
        entityManager.getTransaction().commit();
    }

}
