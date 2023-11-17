package fr.diginamic;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.dao.ActeurDAO;
import fr.diginamic.dao.AdresseDAO;
import fr.diginamic.dao.FilmDAO;
import fr.diginamic.dao.GenreDAO;
import fr.diginamic.dao.LangueDAO;
import fr.diginamic.dao.PaysDAO;
import fr.diginamic.dao.RealisateurDAO;
import fr.diginamic.dao.RoleDAO;
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

    public static void main(String[] args) throws IOException, ParseException {
        String filePathPays = "pays.csv";
        String filePathRoles = "roles.csv";
        String filePathfilms = "films.csv";
        String filePathCastingPrincipale = "castingPrincipal.csv";
        String filePathFilmRealisateur = "film_realisateurs.csv";
        String filePathRealisateurs = "realisateurs.csv";
        String filePathActeurs = "acteurs.csv";

        LecteurData ld = new LecteurData();

        List<Pays> arrayPays = ld.parsePays(filePathPays);
        List<Langue> arrayLangues = ld.parseLangues(filePathfilms);
        List<Genre> arrayGenre = ld.parseGenres(filePathfilms);
        List<Adresse> arrayLieuNaissanceRea = ld.parseAdressesListes(filePathRealisateurs);
        List<Adresse> arrayLieuNaissanceAct = ld.parseAdressesListes(filePathActeurs);
        List<Adresse> arrayDateNaissanceAct = ld.parseAdressesListes(filePathActeurs);
        List<Role> arrayRoles = ld.parseRoles(filePathRoles);
        List<Film> arrayFilm = ld.parseFilms(filePathfilms);

        List<Acteur> arrayActeurs = ld.parseActeurs(filePathActeurs);
        List<Realisateur> arrayRealisateurs = ld.parseRealisateurs(filePathRealisateurs);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CineMovies");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PaysDAO paysDAO = new PaysDAO(entityManager);
        LangueDAO langueDAO = new LangueDAO(entityManager);
        GenreDAO genreDAO = new GenreDAO(entityManager);
        AdresseDAO adresseDAO = new AdresseDAO(entityManager);
        RoleDAO roleDAO = new RoleDAO(entityManager);
        FilmDAO filmDAO = new FilmDAO(entityManager);
        RealisateurDAO realisateurDAO = new RealisateurDAO(entityManager);
        ActeurDAO acteurDAO = new ActeurDAO(entityManager);

        try {

            // Persist Pays entities
            for (Pays pays : arrayPays) {
                paysDAO.createPays(pays);
            }

            // Persist Langue entities
            for (Langue langue : arrayLangues) {
                langueDAO.createLangue(langue);
            }

            // Persist Genre entities
            for (Genre genre : arrayGenre) {
                genreDAO.createGenre(genre);
            }

            // Persist Adresse entities (assuming it's parsed from some file)
            List<Adresse> adresses = ld.parseAdressesListes(filePathActeurs);
            for (Adresse adresse : adresses) {
                adresseDAO.createAdresse(adresse);
            }
//            // Persist Acteurs
//            for (Acteur acteur : arrayActeurs) {
//                acteurDAO.createActeur(acteur);
//            }
//
//            // Persist Realisateurs
//            for (Realisateur realisateur : arrayRealisateurs) {
//                realisateurDAO.createRealisateur(realisateur);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
