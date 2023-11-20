package fr.diginamic.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.diginamic.entities.Acteur;
import fr.diginamic.entities.Adresse;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Genre;
import fr.diginamic.entities.Langue;
import fr.diginamic.entities.Pays;
import fr.diginamic.entities.Realisateur;
import fr.diginamic.entities.Role;

public class LecteurData {

	List<Adresse> arrayLieuNaissanceActeur = new ArrayList<>();
	List<Adresse> arrayLieuNaissanceRealisateur = new ArrayList<>();
	List<Genre> arrayGenre = new ArrayList<>();
	List<Langue> arrayLangue = new ArrayList<>();
	List<Pays> arrayPays = new ArrayList<>();
	List<Film> arrayFilm = new ArrayList<>();
	List<Realisateur> arrayRealisateur = new ArrayList<>();
	List<Acteur> arrayActeur = new ArrayList<>();

	// ***************************************************************************************

	/**
	 * Analyse une représentation sous forme de chaîne d'une adresse en objet
	 * Adresse.
	 *
	 * @param adresseString La chaîne d'adresse à analyser
	 * @return L'objet Adresse analysé
	 */
	public List<String> parseListeAdresse(String filename) {

		List<String> stringLieuNaissance = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filename);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filename);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String lieuNaissance = tokens[3];
				if (!lieuNaissance.isEmpty()) {
					if (!stringLieuNaissance.contains(lieuNaissance)) {
						stringLieuNaissance.add(lieuNaissance);
					}
				}
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return stringLieuNaissance;
	}

	/**
	 * Parse le fichier des réalisateurs pour obtenir une liste d'adresses de lieux
	 * de naissance.
	 * 
	 * @param filename1 Le chemin vers le fichier des réalisateurs
	 * @param filename2 Le chemin vers le fichier des acteurs pour obtenir les
	 *                  adresses de lieux de naissance
	 * @return Une liste d'adresses de lieux de naissance des réalisateurs et
	 *         acteurs
	 * @throws RuntimeException Si le fichier .csv des réalisateurs n'est pas trouvé
	 *                          ou une erreur survient lors de la lecture
	 */
	public List<Adresse> parseListeAdresse(String filename1,
			String filename2) {

		List<Adresse> arrayLieuNaissance = new ArrayList<>();
		List<String> arrayStringLieuNaissance = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filename1);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filename1);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String lieuNaissance = tokens[3];

				LecteurData lectureCsvPays = new LecteurData();
				arrayStringLieuNaissance = lectureCsvPays
						.parseListeAdresse(filename2);

				if (!lieuNaissance.isEmpty()) {
					if (!arrayStringLieuNaissance
							.contains(lieuNaissance)) {
						arrayStringLieuNaissance.add(lieuNaissance);
					}
				}
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		for (String lieuNaissances : arrayStringLieuNaissance) {
			Adresse newLieuNaissance = new Adresse(lieuNaissances);
			arrayLieuNaissance.add(newLieuNaissance);
		}
		return arrayLieuNaissance;
	}

	/**
	 * Analyse une liste de pays à partir du fichier spécifié.
	 *
	 * @param filename Le nom du fichier contenant les données des pays
	 * @return Une liste de pays analysée
	 */
	public List<Pays> parsePays(String filename) {

		List<Pays> arrayPays = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filename);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filename);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String nom = tokens[0];
				String url = tokens[1];

				Pays actuelPays = new Pays(nom, url);

				arrayPays.add(actuelPays);
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayPays;
	}

	/**
	 * Analyse une liste de genres à partir du fichier spécifié.
	 *
	 * @param nomFichier Le nom du fichier contenant les données des genres
	 * @return Une liste de genres analysée
	 */
	public List<Genre> parseGenres(String filename) {

		List<Genre> arrayGenre = new ArrayList<>();
		List<String> stringGenre = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filename);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filename);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String lineGenre = tokens[6];
				String[] arrayLineGenre = lineGenre.split(",");
				for (String lineGenres : arrayLineGenre) {
					if (!lineGenres.isEmpty()) {
						if (!stringGenre.contains(lineGenres)) {
							stringGenre.add(lineGenres);
							Genre newGenre = new Genre(lineGenres);
							arrayGenre.add(newGenre);
						}
					}
				}
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayGenre;
	}

	/**
	 * Analyse une liste de langues à partir du fichier spécifié.
	 *
	 * @param nomFichier Le nom du fichier contenant les données des langues
	 * @return Une liste de langues analysée
	 */
	public List<Langue> parseLangues(String filename) {

		List<Langue> arrayLangue = new ArrayList<>();
		List<String> stringLangue = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filename);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filename);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String lineLangue = tokens[7];
				if (!lineLangue.isEmpty()) {
					if (!stringLangue.contains(lineLangue)) {
						stringLangue.add(lineLangue);
						Langue newLangue = new Langue(lineLangue);
						arrayLangue.add(newLangue);
					}
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayLangue;
	}

	/**
	 * Parse le fichier des acteurs pour créer une liste d'acteurs avec leurs
	 * informations.
	 * 
	 * @param filename1           Le chemin vers le fichier des acteurs à parser
	 * @param filename2           Le chemin vers le fichier des réalisateurs pour
	 *                            obtenir les lieux de naissance des acteurs
	 * @param ListeLieuNaissances La liste des adresses de lieux de naissance
	 * @return Une liste d'acteurs avec leurs détails
	 * @throws RuntimeException Si le fichier .csv des acteurs n'est pas trouvé ou
	 *                          une erreur survient lors de la lecture
	 */
	public List<Acteur> parseActeur(String filename1, String filename2,
			List<Adresse> ListeLieuNaissances) {

		List<Acteur> arrayActeur = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filename1);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filename1);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";", -1);
				String idImdb = tokens[0];
				String identite = tokens[1];
				LocalDate dateNaissance = null;
				if (!tokens[2].isEmpty()) {
					if (!tokens[2].contains("-")) {
						DateTimeFormatter formatter = DateTimeFormatter
								.ofPattern("dd/MM/yyyy");
						dateNaissance = LocalDate.parse(tokens[2],
								formatter);
					}
				}
				String lieuNaissance = tokens[3].trim();
				String url = tokens[4];

				Adresse actuelLieuNaissance = Adresse
						.getLieuNaissanceByNom(ListeLieuNaissances,
								lieuNaissance);

				Acteur actuelActeur = new Acteur(idImdb, identite,
						dateNaissance, url);
				actuelActeur.setLieuNaissance(actuelLieuNaissance);

				arrayActeur.add(actuelActeur);
			}
		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayActeur;
	}

	/**
	 * Parse le fichier des réalisateurs pour créer une liste de réalisateurs avec
	 * leurs informations.
	 * 
	 * @param filename1           Le chemin vers le fichier des réalisateurs à
	 *                            parser
	 * @param filename2           Le chemin vers le fichier des acteurs pour obtenir
	 *                            les lieux de naissance des réalisateurs
	 * @param ListeLieuNaissances La liste des adresses de lieux de naissance
	 * @return Une liste de réalisateurs avec leurs détails
	 * @throws RuntimeException Si le fichier .csv des réalisateurs n'est pas trouvé
	 *                          ou une erreur survient lors de la lecture
	 */
	public List<Realisateur> parseRealisateur(String filename1,
			String filename2, List<Adresse> ListeLieuNaissances) {

		List<Realisateur> arrayRealisateur = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filename1);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filename1);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";", -1);
				String idImdb = tokens[0];
				String identite = tokens[1];
				LocalDate dateNaissance = null;
				if (!tokens[2].isEmpty()) {
					try {
						DateTimeFormatter formatter = DateTimeFormatter
								.ofPattern("MMMM d yyyy ", Locale.ENGLISH);
						dateNaissance = LocalDate.parse(tokens[2],
								formatter);
					} catch (Exception e) {
					}
				}
				String lieuNaissance = tokens[3].trim();
				String url = tokens[4];

				Adresse actuelLieuNaissance = Adresse
						.getLieuNaissanceByNom(ListeLieuNaissances,
								lieuNaissance);

				Realisateur actuelRealisateur = new Realisateur(idImdb,
						identite, dateNaissance, url);
				actuelRealisateur.setLieuNaissance(actuelLieuNaissance);

				arrayRealisateur.add(actuelRealisateur);
			}
		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayRealisateur;
	}

	/**
	 * Parse le fichier des films pour créer une liste de films avec leurs détails.
	 * 
	 * @param filename1 Le chemin vers le fichier des films à parser
	 * @param ListePays     La liste des pays pour attribuer les pays aux films
	 * @param ListeLangues   La liste des langues pour attribuer les langues aux films
	 * @param ListeGenres    La liste des genres pour attribuer les genres aux films
	 * @return Une liste de films avec leurs détails
	 * @throws RuntimeException Si le fichier .csv des films n'est pas trouvé ou une
	 *                          erreur survient lors de la lecture
	 */
	public List<Film> parseFilm(String filename1, List<Pays> ListePays,
			List<Langue> ListeLangues, List<Genre> ListeGenres) {

		List<Film> arrayFilms = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filename1);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filename1);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				if (tokens.length > 10) {
					tokens[8] = tokens[8] + tokens[9];
					tokens[9] = tokens[10];
				}

				String pays = null;

				if (tokens.length == 9) {

					pays = "Pas de pays";

				} else {

					pays = tokens[9];
				}

				String idImdb = tokens[0];
				String nom = tokens[1];
				String annee = tokens[2];
				String rating = tokens[3];
				String url = tokens[4];
				String lieuTournage = tokens[5];
				String ligneGenre = tokens[6];
				String langue = tokens[7];
				String resume = tokens[8];

				Pays actuelPays = Pays.getPaysByNom(ListePays, pays);
				Langue actuelLangue = Langue.getLangueByNom(ListeLangues,
						langue);

				List<Genre> arrayActuelGenre = new ArrayList<>();
				String[] listGenre = ligneGenre.split(",");
				for (String genres : listGenre) {
					Genre actuelGenre = Genre.getGenreByNom(ListeGenres,
							genres);
					arrayActuelGenre.add(actuelGenre);
				}

				Film actuelFilm = new Film(idImdb, nom, annee, rating, url,
						lieuTournage, resume);
				actuelFilm.setLangue(actuelLangue);
				actuelFilm.setGenres(arrayActuelGenre);
				actuelFilm.setPays(actuelPays);

				arrayFilms.add(actuelFilm);
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayFilms;
	}

	/**
	 * Associe les acteurs aux films en fonction des informations du fichier.
	 * 
	 * @param filename   Le chemin vers le fichier pour associer les acteurs aux
	 *                   films
	 * @param ListeFilms   La liste des films à compléter avec les acteurs
	 * @param ListeActeurs La liste des acteurs à associer aux films
	 * @throws RuntimeException Si le fichier .csv n'est pas trouvé ou une erreur
	 *                          survient lors de la lecture
	 */
	public void parseFilmActeur(String filename, List<Film> ListeFilms,
			List<Acteur> ListeActeurs) {

		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filename);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filename);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String idIdbmFilm = tokens[0];
				String idIdbmActeur = tokens[1];

				Film actuelFilm = Film.getFilmByIdbm(ListeFilms, idIdbmFilm);

				Acteur actuelActeur = Acteur.getActeurByIdbm(ListeActeurs,
						idIdbmActeur);

				if (actuelFilm != null && actuelActeur != null) {
					actuelFilm.getActeurs().add(actuelActeur);
					actuelActeur.getFilms().add(actuelFilm);
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur est survenue lors de la lecture du ficher .csv.");
		}
	}

	/**
	 * Associe les réalisateurs aux films en fonction des informations du fichier.
	 * 
	 * @param filename        Le chemin vers le fichier pour associer les
	 *                        réalisateurs aux films
	 * @param ListeFilm        La liste des films à compléter avec les réalisateurs
	 * @param ListeRealisateur La liste des réalisateurs à associer aux films
	 * @throws RuntimeException Si le fichier .csv n'est pas trouvé ou une erreur
	 *                          survient lors de la lecture
	 */
	public void parseFilmRealisateur(String filename, List<Film> ListeFilm,
			List<Realisateur> ListeRealisateur) {

		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filename);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filename);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";");
				String idIdbmFilm = tokens[0];
				String idIdbmRealisateur = tokens[1];

				Film actuelFilm = Film.getFilmByIdbm(ListeFilm, idIdbmFilm);

				Realisateur actuelRealisateur = Realisateur
						.getRealisateurByIdbm(ListeRealisateur,
								idIdbmRealisateur);

				if (actuelFilm != null && actuelRealisateur != null) {
					actuelFilm.getRealisateurs().add(actuelRealisateur);
					actuelRealisateur.getFilms().add(actuelFilm);
				}

			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur est survenue lors de la lecture du ficher .csv.");
		}
	}

	/**
	 * Parse le fichier pour créer une liste de rôles et les associe aux acteurs et
	 * aux films correspondants.
	 * 
	 * @param filename   Le chemin vers le fichier pour obtenir les rôles des
	 *                   acteurs dans les films
	 * @param ListeFilms   La liste des films pour associer les rôles
	 * @param ListeActeurs La liste des acteurs pour associer les rôles
	 * @return Une liste de rôles associant des acteurs à des films avec leur
	 *         personnage
	 * @throws RuntimeException Si le fichier .csv des rôles n'est pas trouvé ou une
	 *                          erreur survient lors de la lecture
	 */

	public List<Role> parseRole(String filename, List<Film> ListeFilms,
			List<Acteur> ListeActeurs) {

		List<Role> arrayRole = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filename);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filename);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] tokens = line.split(";", -1);

				String idIdmbFilm = tokens[0];
				String idIdmbActeur = tokens[1];
				String personnage = tokens[2];

				Film actuelFilm = Film.getFilmByIdbm(ListeFilms, idIdmbFilm);
				Acteur actuelActeur = Acteur.getActeurByIdbm(ListeActeurs,
						idIdmbActeur);

				Role actuelRole = new Role(personnage);

				if (actuelFilm != null && actuelActeur != null) {
					actuelRole.setFilm(actuelFilm);
					actuelRole.setActeur(actuelActeur);

					arrayRole.add(actuelRole);
				}

			}

		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return arrayRole;
	}
}
