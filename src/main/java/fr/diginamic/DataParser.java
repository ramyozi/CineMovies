package fr.diginamic;

import java.io.IOException;
import java.util.List;

import fr.diginamic.entities.*;
import fr.diginamic.parsers.LecteurData;

public class DataParser {

	public static void main(String[] args) throws IOException {
		String filePathPays = "pays.csv";
		String filePathRoles = "roles.csv";
		String filePathfilms = "films.csv";
		String filePathCastingPrincipale = "castingPrincipal.csv";
		String filePathFilmRealisateur = "film_realisateurs.csv";
		String filePathRealisateurs = "realisateurs.csv";
		String filePathActeurs = "acteurs.csv";

		LecteurData ld = new LecteurData();
		List<Pays> arrayPays = ld.parsePays(filePathPays);
		List<Langue> arrayLangues = ld.parseLangue(filePathfilms);
		List<Genre> arrayGenre = ld.parseGenre(filePathfilms);
		List<Adresse> arrayLieuNaissanceRea = ld.parseAdressesListes(filePathRealisateurs);
		List<Adresse> arrayLieuNaissanceAct = ld.parseAdressesListes(filePathActeurs);
		List<Adresse> arrayDateNaissanceAct = ld.parseAdressesListes(filePathActeurs);
		List<Acteur> acteurs = ld.parseActeurs(filePathActeurs);
		List<Realisateur> realisateur = ld.parseRealisateurs(filePathRealisateurs);
		
		

		
		
	}
}
