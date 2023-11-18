package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.entities.Pays;

public class PaysDAO {
	 private EntityManagerFactory entityManagerFactory;
	    private EntityManager entityManager;

	    public PaysDAO(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }
	    public PaysDAO() {
	        entityManagerFactory = Persistence.createEntityManagerFactory("CineMovies");
	        entityManager = entityManagerFactory.createEntityManager();
	    }

	    public void close() {
	        entityManager.close();
	        entityManagerFactory.close();
	    }

	    public void createPays(Pays pays) {
	        try {
	            entityManager.getTransaction().begin();
	            entityManager.persist(pays);
	            entityManager.getTransaction().commit();
	        } catch (Exception e) {
	            if (entityManager.getTransaction().isActive()) {
	                entityManager.getTransaction().rollback();
	            }
	            e.printStackTrace();
	        }
	    }

	    public List<Pays> getAllPays() {
	        return entityManager.createQuery("SELECT p FROM Pays p", Pays.class).getResultList();
	    }
}
