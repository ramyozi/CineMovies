package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.entities.Role;

public class RoleDAO {

    private final EntityManager entityManager;

    public RoleDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createRole(Role role) {
        entityManager.persist(role);
    }
}