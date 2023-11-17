package fr.diginamic;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import fr.diginamic.entities.Acteur;
import fr.diginamic.entities.Adresse;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Genre;
import fr.diginamic.entities.Langue;
import fr.diginamic.entities.Pays;
import fr.diginamic.entities.Realisateur;
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
		List<Acteur> acteurs = ld.parseActeurs(filePathActeurs);
		List<Realisateur> realisateur = ld.parseRealisateurs(filePathRealisateurs);
		
		List<Film> arrayFilm = ld.parseFilms(filePathfilms);
		

		for(Film film : arrayFilm) {
			System.out.println(film.toString());
		}
	}
}
