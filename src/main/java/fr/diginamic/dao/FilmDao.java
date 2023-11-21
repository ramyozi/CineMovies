package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import fr.diginamic.entities.Acteur;
import fr.diginamic.entities.Film;

public class FilmDao implements Dao<Film> {

	private final EntityManager entityManager;

	

	/** Constructeur
	 * @param entityManager
	 */
	public FilmDao(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	/**
	 * Récupère tous les films de la base de données.
	 *
	 * @return Une liste contenant tous les films présents en base de données.
	 */
	@Override
	public List<Film> getAll() {
		TypedQuery<Film> query = entityManager
				.createQuery("SELECT f FROM Film f", Film.class);
		return query.getResultList();
	}

	/**
	 * Récupère un film par son ID depuis la base de données.
	 *
	 * @param id L'identifiant du film à rechercher.
	 * @return Le film correspondant à l'identifiant spécifié, s'il existe ; sinon,
	 *         retourne null.
	 */
	@Override
	public Film getById(int id) {
		return entityManager.find(Film.class, id);
	}

	/**
	 * Enregistre un nouveau film dans la base de données.
	 *
	 * @param entity Le film à enregistrer.
	 */
	@Override
	public void save(Film entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	/**
	 * Met à jour un film existant dans la base de données.
	 *
	 * @param entity Le film à mettre à jour.
	 */
	@Override
	public void update(Film entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	/**
	 * Supprime un film de la base de données.
	 *
	 * @param entity Le film à supprimer.
	 */
	@Override
	public void delete(Film entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	/**
	 * Récupère un film par son nom depuis la base de données.
	 *
	 * @param nom Le nom du film à rechercher.
	 * @return Le film correspondant au nom spécifié s'il existe ; sinon, retourne
	 *         null.
	 */
	@Override
	public Film getByName(String nom) {
		String jpql = "SELECT f FROM Film f WHERE f.nom = :filmName";

		try {
			return entityManager.createQuery(jpql, Film.class)
					.setParameter("filmName", nom).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Récupère les films sortis entre deux années spécifiées, dans l'ordre
	 * croissant des années.
	 *
	 * @param debutAnnee L'année de début pour la recherche de films.
	 * @param finAnnee   L'année de fin pour la recherche de films.
	 * @return Une liste de films sortis entre les années spécifiées. Retourne null
	 *         en cas d'erreur.
	 */
	public List<Film> getFilmsBetweenYears(String debutAnnee,
			String finAnnee) {
		try {
			String jpql = "SELECT f FROM Film f WHERE f.annee BETWEEN :debutAnnee AND :finAnnee "
					+ "ORDER BY f.annee ASC";
			TypedQuery<Film> query = entityManager.createQuery(jpql,
					Film.class);
			query.setParameter("debutAnnee", debutAnnee);
			query.setParameter("finAnnee", finAnnee);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Récupère les films dans lesquels deux acteurs spécifiques ont joué.
	 *
	 * @param actor1 Le premier acteur pour la recherche de films.
	 * @param actor2 Le deuxième acteur pour la recherche de films.
	 * @return Une liste de films impliquant les deux acteurs spécifiés. Retourne
	 *         null en cas d'erreur.
	 */
	public List<Film> getFilmsByActors(Acteur actor1, Acteur actor2) {

		try {
			String jpql = "SELECT f FROM Film f "
					+ "JOIN f.acteurs a1 JOIN f.acteurs a2 "
					+ "WHERE a1.id = :actor1Id AND a2.id = :actor2Id";

			TypedQuery<Film> query = entityManager.createQuery(jpql,
					Film.class);
			query.setParameter("actor1Id", actor1.getId());
			query.setParameter("actor2Id", actor2.getId());

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Récupère les films communs à deux acteurs spécifiques.
	 *
	 * @param actor1 Le premier acteur pour la recherche de films communs.
	 * @param actor2 Le deuxième acteur pour la recherche de films communs.
	 * @return Une liste de films dans lesquels les deux acteurs sont présents.
	 *         Retourne null en cas d'erreur.
	 */
	public List<Film> getFilmsCommonToActors(Acteur actor1,
			Acteur actor2) {

		try {
			String jpql = "SELECT f FROM Film f " + "JOIN f.acteurs a "
					+ "WHERE a = :actor1 AND :actor2 MEMBER OF f.acteurs";
			TypedQuery<Film> query = entityManager.createQuery(jpql,
					Film.class);
			query.setParameter("actor1", actor1);
			query.setParameter("actor2", actor2);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Récupère les films où deux acteurs apparaissent ensemble, basé sur leurs
	 * rôles.
	 *
	 * @param acteur1 Le premier acteur pour la recherche de films basée sur les
	 *                rôles.
	 * @param acteur2 Le deuxième acteur pour la recherche de films basée sur les
	 *                rôles.
	 * @return Une liste de films dans lesquels les deux acteurs partagent des
	 *         rôles.
	 */
	public List<Film> getFilmsCommonToActorsByRoles(Acteur acteur1,
			Acteur acteur2) {

		String jpqlQuery = "SELECT r1.film " + "FROM Role r1 "
				+ "JOIN Role r2 ON r1.film = r2.film "
				+ "WHERE r1.acteur = :acteur1 AND r2.acteur = :acteur2";

		return entityManager.createQuery(jpqlQuery, Film.class)
				.setParameter("acteur1", acteur1)
				.setParameter("acteur2", acteur2).getResultList();
	}

	/**
	 * Récupère les acteurs communs à deux films spécifiques.
	 *
	 * @param film1 Le premier film pour la recherche d'acteurs communs.
	 * @param film2 Le deuxième film pour la recherche d'acteurs communs.
	 * @return Une liste d'acteurs qui ont joué dans les deux films spécifiés.
	 */
	public List<Acteur> getActorsCommonToFilms(Film film1, Film film2) {

		String jpqlQuery = "SELECT r1.acteur " + "FROM Role r1 "
				+ "JOIN Role r2 ON r1.acteur = r2.acteur "
				+ "WHERE r1.film = :film1 AND r2.film = :film2";

		return entityManager.createQuery(jpqlQuery, Acteur.class)
				.setParameter("film1", film1).setParameter("film2", film2)
				.getResultList();
	}

	/**
	 * Récupère les films entre deux années spécifiées impliquant un acteur ayant un
	 * ID IMDb donné.
	 *
	 * @param idImdb    L'ID IMDb de l'acteur pour la recherche de films.
	 * @param startYear L'année de début pour la recherche de films.
	 * @param endYear   L'année de fin pour la recherche de films.
	 * @return Une liste de films sortis entre les années spécifiées impliquant
	 *         l'acteur avec l'ID IMDb donné.
	 */
	public List<Film> getFilmsBetweenYearsWithActor(String idImdb,
			int startYear, int endYear) {
		try {
			String jpql = "SELECT DISTINCT f FROM Film f "
					+ "JOIN f.acteurs a "
					+ "WHERE f.annee BETWEEN :startYear AND :endYear "
					+ "AND a.idImdb = :idImdb";

			return entityManager.createQuery(jpql, Film.class)
					.setParameter("startYear", String.valueOf(startYear))
					.setParameter("endYear", String.valueOf(endYear))
					.setParameter("idImdb", idImdb).getResultList();
		} finally {
			entityManager.close();
		}
	}

}
