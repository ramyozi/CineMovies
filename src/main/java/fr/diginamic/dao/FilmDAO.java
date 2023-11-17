package fr.diginamic.dao;

import javax.persistence.EntityManager;

import fr.diginamic.entities.Film;

public class FilmDAO {

	private final EntityManager entityManager;

	public FilmDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void createFilm(Film film) {
		entityManager.persist(film);
	}
}
