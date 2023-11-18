package fr.diginamic.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.dao.AdresseDAO;
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

	EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("CineMovies");
	EntityManager entityManager = entityManagerFactory
			.createEntityManager();

	private final Set<String> uniqueAddresses = new HashSet<>();

	/**
	 * Analyse une liste de genres à partir du fichier spécifié.
	 *
	 * @param nomFichier Le nom du fichier contenant les données des genres
	 * @return Une liste de genres analysée
	 */
	public List<Genre> parseGenres(String nomFichier) {

		List<Genre> genres = new ArrayList<>();
		List<String> genreUnicite = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(nomFichier);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + nomFichier);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String line;
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				String[] colonnes = line.split(";");
				String nomGenre = colonnes[6];
				String[] arrayGenre = nomGenre.split(",");
				for (String nomGenres : arrayGenre) {
					if (!nomGenres.isEmpty()) {
						if (!genreUnicite.contains(nomGenres)) {
							genreUnicite.add(nomGenres);
							Genre genre = new Genre(nomGenres);
							genres.add(genre);
						}
					}
				}
			}

		} catch (

		IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return genres;
	}

	// ***************************************************************************************
	/**
	 * Analyse une liste de langues à partir du fichier spécifié.
	 *
	 * @param nomFichier Le nom du fichier contenant les données des langues
	 * @return Une liste de langues analysée
	 */
	public List<Langue> parseLangues(String nomFichier) {

		List<Langue> langues = new ArrayList<>();
		List<String> donneesLangue = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(nomFichier);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + nomFichier);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String ligne;
			lecteur.readLine();
			while ((ligne = lecteur.readLine()) != null) {
				String[] colonnes = ligne.split(";");
				String nom = colonnes[7];
				if (!nom.isEmpty()) {
					if (!donneesLangue.contains(nom)) {
						donneesLangue.add(nom);
						Langue langue = new Langue(nom);
						langues.add(langue);
					}
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur est survenue lors de la lecture du ficher .csv.");
		}
		return langues;
	}

	// ***************************************************************************************
	/**
	 * Analyse une liste de pays à partir du fichier spécifié.
	 *
	 * @param filename Le nom du fichier contenant les données des pays
	 * @return Une liste de pays analysée
	 */
	public List<Pays> parsePays(String filename) {

		List<Pays> data = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filename);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filename);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String ligne;
			lecteur.readLine();
			while ((ligne = lecteur.readLine()) != null) {
				String[] colonnes = ligne.split(";");
				if (colonnes.length == 2) {
					String nom = colonnes[0].trim();
					String url = colonnes[1].trim();
					Pays pays = new Pays();
					pays.setLabel(nom);
					pays.setUrl(url);
					data.add(pays);
				} else {
					System.err.println(
							"Ligne incorrecte dans le fichier CSV: "
									+ ligne);
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur RuntimeException est survenue (lors de la lecture du ficher .csv.)");
		}
		return data;
	}

	// ***************************************************************************************

	/**
	 * Analyse une représentation sous forme de chaîne d'une adresse en objet
	 * Adresse.
	 *
	 * @param adresseString La chaîne d'adresse à analyser
	 * @return L'objet Adresse analysé
	 */
	public Adresse parseUneAdresse(String adresseString) {
		Adresse adresse = new Adresse();
		String[] locations = adresseString.split(",");
		int len = locations.length;

		switch (len) {
		case 0:
			return null;
		case 1:
			adresse.setPays(locations[0]);
			break;
		case 2:
			adresse.setDepartement(locations[0]);
			adresse.setPays(locations[1]);
			break;
		case 3:
			adresse.setVille(locations[0]);
			adresse.setDepartement(locations[1]);
			adresse.setPays(locations[2]);
			break;
		case 4:
			adresse.setQuartier(locations[0]);
			adresse.setVille(locations[1]);
			adresse.setDepartement(locations[2]);
			adresse.setPays(locations[3]);
			break;
		case 5:
			adresse.setBatiment(locations[0]);
			adresse.setQuartier(locations[1]);
			adresse.setVille(locations[2]);
			adresse.setDepartement(locations[3]);
			adresse.setPays(locations[4]);
			break;
		default:
			break;
		}
		System.out.println(adresse);

		return adresse;
	}

	/**
	 * Analyse une représentation sous forme de chaîne d'une adresse de tournage en
	 * objet Adresse.
	 *
	 * @param adresseString La chaîne d'adresse de tournage à analyser
	 * @return L'objet Adresse de tournage analysé
	 */
	public Adresse parseUneAdresseTournage(String adresseString) {
		Adresse adresse = new Adresse();
		String[] locations = adresseString.split(",");
		int len = locations.length;

		switch (len) {
		case 0:
			break;
		case 1:
			adresse.setPays(locations[0]);
			break;
		case 2:
			adresse.setPays(locations[0]);
			adresse.setDepartement(locations[1]);
			break;
		case 3:
			adresse.setPays(locations[0]);
			adresse.setDepartement(locations[1]);
			adresse.setVille(locations[2]);

			break;
		case 4:
			adresse.setPays(locations[0]);
			adresse.setDepartement(locations[1]);
			adresse.setVille(locations[2]);
			adresse.setQuartier(locations[3]);
			break;
		case 5:
			adresse.setPays(locations[0]);
			adresse.setDepartement(locations[1]);
			adresse.setVille(locations[2]);
			adresse.setQuartier(locations[3]);
			adresse.setBatiment(locations[4]);
			break;
		default:
			break;
		}

		return adresse;
	}

	/**
	 * Analyse une liste d'adresses à partir du fichier spécifié.
	 *
	 * @param adresseString Le nom du fichier contenant les adresses
	 * @return Une liste d'adresses analysées
	 * @throws IOException Si une erreur se produit lors de la lecture du fichier
	 */
	public List<Adresse> parseAdressesListes(String adresseString)
			throws IOException {
		LecteurData ld = new LecteurData();
		List<Adresse> adresses = new ArrayList<>();

		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(adresseString);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: "
							+ adresseString);
		}

		String line;
		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			lecteur.readLine();
			while ((line = lecteur.readLine()) != null) {
				try {
					String[] data = line.split(";");
					if (data.length >= 4) {
						Adresse adresse = parseUneAdresse(data[3]);
						if (adresse != null) {
							adresses.add(adresse);
						} else {
							System.err.println(
									"Invalid address data: " + line);
						}
					} else {
						System.err.println("Invalid data length: " + line);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adresses;
	}

	/**
	 * Analyse une liste d'adresses de lieu de tournage à partir du fichier
	 * spécifié.
	 *
	 * @param adresseString Le nom du fichier contenant les adresses
	 * @return Une liste d'adresses analysées
	 * @throws IOException Si une erreur se produit lors de la lecture du fichier
	 */
	public List<Adresse> parseListesLieuTournage(String adresseString)
			throws IOException {
		List<Adresse> adresses = new ArrayList<>();

		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(adresseString);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: "
							+ adresseString);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String ligne;
			lecteur.readLine();
			while ((ligne = lecteur.readLine()) != null) {
				String[] colonnes = ligne.split(";");
				Adresse adresse = parseUneAdresseTournage(colonnes[5]);
				adresses.add(adresse);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur RuntimeException est survenue (lors de la lecture du fichier .csv.)");
		}
		return adresses;
	}

	// ***************************************************************************************

	public <T> List<T> parseDataPersonne(String filePath,
			Function<String[], T> createEntity) {
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filePath);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filePath);
		}

		List<T> entities = new ArrayList<>();
		String line;

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			lecteur.readLine(); // Skip header
			while ((line = lecteur.readLine()) != null) {
				try {
					String[] data = line.split(";");
					if (data.length == 5) {
						T entity = createEntity.apply(data);
						if (entity != null) {
							entities.add(entity);
						} else {
							System.err.println("Invalid data: " + line);
						}
					} else {
						System.err.println("Invalid data length: " + line);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entities;
	}

	/**
	 * Analyse une liste d'acteurs à partir du fichier spécifié.
	 *
	 * @param filePath Le chemin du fichier contenant les données des acteurs
	 * @return Une liste d'acteurs analysée
	 */
	public List<Acteur> parseActeurs(String filePath) {
		return parseDataPersonne(filePath, this::createActeurFromData);
	}

	/**
	 * Analyse une liste de réalisateurs à partir du fichier spécifié.
	 *
	 * @param filePath Le chemin du fichier contenant les données des réalisateurs
	 * @return Une liste de réalisateurs analysée
	 */
	public List<Realisateur> parseRealisateurs(String filePath) {
		return parseDataPersonne(filePath,
				this::createRealisateurFromData);
	}

	public Acteur createActeurFromData(String[] data) {
		Acteur acteur = new Acteur();
		try {
			acteur.setIdImdb(data[0]);
			acteur.setIdentite(data[1]);
			acteur.setDdn(parseDateActeur(data[2]));
			acteur.setUrl(data[4]);

			Adresse adresse = parseUneAdresse(data[3]);

			if (adresse != null) {
				int idAdresse = acteur
						.findIdAdresseByAdresse(entityManager, adresse);
				if (idAdresse == -1) {
					AdresseDAO adresseDAO = new AdresseDAO(entityManager);
					idAdresse = adresseDAO.create(adresse);
				}
				acteur.setIdLieuNaissance(idAdresse);
			} else {
				System.err.println("Invalid address data: " + data[3]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			acteur = null;
		}
		return acteur;
	}

	private Realisateur createRealisateurFromData(String[] data) {
		Realisateur realisateur = new Realisateur();
		try {
			realisateur.setIdImdb(data[0]);
			realisateur.setIdentite(data[1]);
			realisateur.setDdn(parseDateRealisateur(data[2]));
			realisateur.setUrl(data[4]);
			Adresse adresse = parseUneAdresse(data[3]);

			if (adresse != null) {
				int idAdresse = realisateur
						.findIdAdresseByAdresse(entityManager, adresse);
				if (idAdresse == -1) {
					AdresseDAO adresseDAO = new AdresseDAO(entityManager);
					idAdresse = adresseDAO.create(adresse);
				}
				realisateur.setIdLieuNaissance(idAdresse);
			} else {
				System.err.println("Invalid address data: " + data[3]);
			}		} catch (Exception e) {
			e.printStackTrace();
			realisateur = null;
		}
		return realisateur;
	}

	public int fetchAdressFromDB(String data) {
		int idAssocie = 0;

		return idAssocie;
	}

	/**
	 * Analyse une chaîne représentant une date en objet Date.
	 *
	 * @param dateString La chaîne de date à analyser
	 * @return L'objet Date analysé
	 */
	public Date parseDateActeur(String dateString) {
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-dd-mm");

		if (dateString.isEmpty()) {
			return null;
		}

		try {
			return format1.parse(dateString);
		} catch (ParseException e1) {
			try {
				return format2.parse(dateString);
			} catch (ParseException e2) {
				return null;
			}
		}
	}

	/**
	 * Analyse une chaîne représentant une date en objet Date.
	 *
	 * @param dateString La chaîne de date à analyser
	 * @return L'objet Date analysé
	 */
	public Date parseDateRealisateur(String dateString) {
		SimpleDateFormat format1 = new SimpleDateFormat("MMMM d yyyy",
				Locale.ENGLISH);
		SimpleDateFormat format2 = new SimpleDateFormat("MMMM dd yyyy",
				Locale.ENGLISH);

		try {
			return format1.parse(dateString);
		} catch (ParseException e1) {
			try {
				return format2.parse(dateString);
			} catch (ParseException e2) {
				return null;
			}
		}
	}

	// ***************************************************************************************
//A terminer
	// ***************************************************************************************

	public List<Film> parseFilms(String nomFichier) throws ParseException {
		List<Film> films = new ArrayList<>();
		LecteurData ld = new LecteurData();
		List<Pays> arrayPays = ld.parsePays("pays.csv");
		List<Langue> arrayLangues = ld.parseLangues("films.csv");
		List<Genre> arrayGenres = ld.parseGenres("films.csv");
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(nomFichier);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + nomFichier);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String ligne;
			lecteur.readLine();
			while ((ligne = lecteur.readLine()) != null) {
				String[] colonnes = ligne.split(";", -1);
				if (colonnes.length > 10) {
					colonnes[8] = colonnes[8] + colonnes[9];
					colonnes[9] = colonnes[10];
				}

				Film film = new Film();
				try {
					film.setIdImdb(colonnes[0]);
					film.setNom(colonnes[1]);
					film.setAnnee(colonnes[2]);

					String ratingValue = colonnes[3];
					double rating = 0.0;
					if (!ratingValue.isEmpty()) {
						rating = Double.parseDouble(ratingValue);
					} else {
						rating = 0.0;
					}
					film.setNote(rating);

					film.setUrl(colonnes[4]);

					Adresse adresse = ld
							.parseUneAdresseTournage(colonnes[5]);
					film.setLieuDeTournage(adresse);

					film.setResume(colonnes[8]);

					Pays pays = Pays.rechercheParNom(arrayPays,
							colonnes[9]);
					film.setPaysDeSortie(pays);

					Langue langue = Langue.getLangueByNom(arrayLangues,
							colonnes[7]);
					film.setLangue(langue);

					String ligneGenres = colonnes[6];
					String[] genres = ligneGenres.split(",");
					List<Genre> filmGenres = new ArrayList<>();
					for (String genre : genres) {
						Genre tmpGenre = Genre.getGenreByNom(arrayGenres,
								genre.trim());
						if (tmpGenre != null) {
							filmGenres.add(tmpGenre);
						}
					}
					film.setGenres(filmGenres);

				} catch (NumberFormatException e) {
					e.printStackTrace();
					continue;
				}

				films.add(film);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur RuntimeException est survenue lors de la lecture du fichier .csv.");
		}
		return films;
	}

	public List<Role> parseRoles(String filename) throws ParseException {

		List<Role> data = new ArrayList<>();
		ClassLoader cl = getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream(filename);

		if (is == null) {
			throw new RuntimeException(
					"Le fichier .csv n'a pas été trouvé: " + filename);
		}

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String ligne;
			lecteur.readLine();
			while ((ligne = lecteur.readLine()) != null) {
				String[] colonnes = ligne.split(";", -1);
				if (colonnes.length == 3) {
					String personnage = colonnes[2].trim();
					Role role = new Role(personnage);
					data.add(role);
				} else {
					Role role = new Role("Unknown");
					data.add(role);
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur RuntimeException est survenue (lors de la lecture du ficher .csv.)");
		}
		return data;
	}

	/**
	 * Traite les données à partir du fichier spécifié.
	 *
	 * @param filePath Le chemin du fichier contenant les données à traiter
	 */
	public void processData(String filePath) {
		try {
			List<Acteur> acteurs = parseActeurs(filePath);
			System.out.println(
					"Successfully parsed actors: " + acteurs.size());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error processing data: " + e.getMessage());
		}
	}

	/**
	 * Lit un fichier CSV et retourne son contenu sous forme de liste de chaînes.
	 *
	 * @param fileName Le nom du fichier CSV à lire
	 * @return Le contenu du fichier CSV sous forme de liste de chaînes
	 */
	public List<String> readCSV(String fileName) {
		List<String> dataList = new ArrayList<>();

		try (InputStream is = getClass().getClassLoader()
				.getResourceAsStream(fileName);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(";");
				String formattedLine = String.join(", ", data);
				dataList.add(formattedLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataList;
	}

//	/**
//	 * Analyse une liste de rôles à partir du fichier spécifié.
//	 *
//	 * @param nomFichier Le nom du fichier contenant les rôles
//	 * @return Une liste de rôles analysée
//	 * @throws IOException Si une erreur se produit lors de la lecture du fichier
//	 */
//	public List<Role> parseRole(String fileRole) {
//
//		List<Role> arrayRole = new ArrayList<>();
//		ClassLoader cl = getClass().getClassLoader();
//		InputStream is = cl.getResourceAsStream(fileRole);
//
//		if (is == null) {
//			throw new RuntimeException(
//					"Le fichier .csv n'a pas été trouvé: " + fileRole);
//		}
//
//		try (BufferedReader lecteur = new BufferedReader(
//				new InputStreamReader(is))) {
//			String line;
//			lecteur.readLine();
//			while ((line = lecteur.readLine()) != null) {
//				String[] tokens = line.split(";", -1);
//
//				String idIdmbFilm = tokens[0];
//				String idIdmbActeur = tokens[1];
//				String personnage = tokens[2];
//
//				if (arrayActeur.isEmpty()) {
//
//					arrayActeur = this.parseActeurs("acteurs.csv");
//				}
//				if (arrayFilm.isEmpty()) {
//					arrayFilm = this.parseFilms("films.csv");
//				}
//
//				Film actuelFilm = Film.rechercheParImdb(arrayFilm,
//						idIdmbFilm);
//				Acteur actuelActeur = Acteur.rechercheParImdb(arrayActeur,
//						idIdmbActeur);
//
//				Role actuelRole = new Role(personnage);
//				actuelRole.setFilm(actuelFilm);
//				actuelRole.setActeur(actuelActeur);
//
//				arrayRole.add(actuelRole);
//			}
//
//		} catch (
//
//		IOException e) {
//			System.err.println(e.getMessage());
//			throw new RuntimeException(
//					"Une erreur est survenue lors de la lecture du ficher .csv.");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return arrayRole;
//	}
}
