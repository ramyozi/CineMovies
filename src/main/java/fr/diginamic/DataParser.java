package fr.diginamic;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.entities.Acteur;
import fr.diginamic.entities.Adresse;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Genre;
import fr.diginamic.entities.Langue;
import fr.diginamic.entities.Pays;
import fr.diginamic.entities.Realisateur;
import fr.diginamic.entities.Role;
import fr.diginamic.parsers.LecteurData;

public class DataParser {

	public static void main(String[] args) throws IOException {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("CineMovies");
		EntityManager em = emf.createEntityManager();

		// Chemin vers le fichier csv
		String pathFileFilm = "films.csv";
		String pathFilePays = "Pays.csv";
		String pathFileRealisateur = "realisateurs.csv";
		String pathFileActeur = "acteurs.csv";
		String pathFileFilmRealisateur = "film_realisateurs.csv";
		String pathFileFilmActeur = "castingPrincipal.csv";
		String pathRole = "roles.csv";

		em.getTransaction().begin();

		LecteurData lectureCsvPays = new LecteurData();
		List<Pays> arrayPays = lectureCsvPays.parsePays(pathFilePays);
		for (Pays pays : arrayPays) {
			em.persist(pays);
		}

		LecteurData lectureCsvLangue = new LecteurData();
		List<Langue> arrayLangue = lectureCsvLangue
				.parseLangues(pathFileFilm);
		for (Langue langues : arrayLangue) {
			em.persist(langues);
		}

		LecteurData lectureCsvGenre = new LecteurData();
		List<Genre> arrayGenre = lectureCsvGenre.parseGenres(pathFileFilm);
		for (Genre genres : arrayGenre) {
			em.persist(genres);
		}

		LecteurData lectureCsvLieuNaissance = new LecteurData();
		List<Adresse> arrayLieuNaissance = lectureCsvLieuNaissance
				.parseListeAdresse(pathFileRealisateur, pathFileActeur);
		for (Adresse lieuNaissances : arrayLieuNaissance) {
			em.persist(lieuNaissances);
		}

		LecteurData lectureCsvFilm = new LecteurData();
		List<Film> arrayFilm = lectureCsvFilm.parseFilm(pathFileFilm,
				arrayPays, arrayLangue, arrayGenre);
		for (Film films : arrayFilm) {
			em.persist(films);
		}

		LecteurData lectureCsvActeur = new LecteurData();
		List<Acteur> arrayActeur = lectureCsvActeur.parseActeur(
				pathFileActeur, pathFileRealisateur, arrayLieuNaissance);
		for (Acteur acteurs : arrayActeur) {
			em.persist(acteurs);
		}

		LecteurData lectureCsvRealisateur = new LecteurData();
		List<Realisateur> arrayRealisateur = lectureCsvRealisateur
				.parseRealisateur(pathFileRealisateur, pathFileActeur,
						arrayLieuNaissance);
		for (Realisateur realisateurs : arrayRealisateur) {
			em.persist(realisateurs);
		}

		LecteurData lectureCsvFilmActeur = new LecteurData();
		lectureCsvFilmActeur.parseFilmActeur(pathFileFilmActeur, arrayFilm,
				arrayActeur);

		LecteurData lectureCsvFilmRealisateur = new LecteurData();
		lectureCsvFilmRealisateur.parseFilmRealisateur(
				pathFileFilmRealisateur, arrayFilm, arrayRealisateur);

		LecteurData lectureCsvRole = new LecteurData();
		List<Role> arrayRole = lectureCsvRole.parseRole(pathRole,
				arrayFilm, arrayActeur);
		for (Role roles : arrayRole) {
			em.persist(roles);
		}
		em.getTransaction().commit();

		// Fermeture de la connection a la BDD
		em.close();
		emf.close();
	}
}
