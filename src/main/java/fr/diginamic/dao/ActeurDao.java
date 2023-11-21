package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import fr.diginamic.entities.Acteur;

public class ActeurDao implements Dao<Acteur> {

	private final EntityManager entityManager;

	/**
	 * Constructeur de la classe ActeurDao.
	 *
	 * @param entityManager L'EntityManager utilisé pour interagir avec la base de
	 *                      données.
	 */
	public ActeurDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Récupère tous les acteurs présents en base de données.
	 *
	 * @return Une liste contenant tous les acteurs présents en base de données.
	 */
	@Override
	public List<Acteur> getAll() {
		TypedQuery<Acteur> query = entityManager
				.createQuery("SELECT a FROM Acteur a", Acteur.class);
		return query.getResultList();
	}

	/**
	 * Récupère un acteur par son ID depuis la base de données.
	 *
	 * @param id L'identifiant de l'acteur à rechercher.
	 * @return L'acteur correspondant à l'identifiant spécifié, s'il existe ; sinon,
	 *         retourne null.
	 */
	@Override
	public Acteur getById(int id) {
		return entityManager.find(Acteur.class, id);
	}

	/**
	 * Enregistre un nouvel acteur dans la base de données.
	 *
	 * @param entity L'acteur à enregistrer.
	 */
	@Override
	public void save(Acteur entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	/**
	 * Met à jour un acteur existant dans la base de données.
	 *
	 * @param entity L'acteur à mettre à jour.
	 */
	@Override
	public void update(Acteur entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	/**
	 * Supprime un acteur de la base de données.
	 *
	 * @param entity L'acteur à supprimer.
	 */
	@Override
	public void delete(Acteur entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	/**
	 * Récupère un acteur par son nom depuis la base de données.
	 *
	 * @param nomActeur Le nom de l'acteur à rechercher.
	 * @return L'acteur correspondant au nom spécifié s'il existe ; sinon, retourne
	 *         null.
	 */
	@Override
	public Acteur getByName(String nomActeur) {

		TypedQuery<Acteur> query = entityManager.createQuery(
				"SELECT a FROM Acteur a WHERE a.identite = :nomActeur",
				Acteur.class);
		query.setParameter("nomActeur", nomActeur);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
}