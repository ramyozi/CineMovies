package fr.diginamic.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import fr.diginamic.entities.Acteur;
import fr.diginamic.entities.Adresse;
import fr.diginamic.entities.Genre;
import fr.diginamic.entities.Langue;
import fr.diginamic.entities.Pays;
import fr.diginamic.entities.Realisateur;

public class LecteurData {
	public <T> List<T> parseData(String filePath, Function<String[], T> createEntity) {
	    ClassLoader cl = getClass().getClassLoader();
	    InputStream is = cl.getResourceAsStream(filePath);

	    if (is == null) {
	        throw new RuntimeException("Le fichier .csv n'a pas été trouvé: " + filePath);
	    }

	    List<T> entities = new ArrayList<>();
	    String line;

	    try (BufferedReader lecteur = new BufferedReader(new InputStreamReader(is))) {
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

	public List<Acteur> parseActeurs(String filePath) {
	    return parseData(filePath, this::createActeurFromData);
	}

	public List<Realisateur> parseRealisateurs(String filePath) {
	    return parseData(filePath, this::createRealisateurFromData);
	}

	private Acteur createActeurFromData(String[] data) {
	    Acteur acteur = new Acteur();
	    try {
	        acteur.setIdImdb(data[0]);
	        acteur.setIdentite(data[1]);
	        acteur.setDdn(parseDate(data[2]));
	        acteur.setUrl(data[4]);
	        acteur.setLieuNaissance(parseUneAdresse(data[3]));
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
	        realisateur.setDdn(parseDate(data[2]));
	        realisateur.setUrl(data[4]);
	        realisateur.setLieuNaissance(parseUneAdresse(data[3]));
	    } catch (Exception e) {
	        e.printStackTrace();
	        realisateur = null;
	    }
	    return realisateur;
	}

	private Date parseDate(String dateString) {
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = null;

		if (dateString.isEmpty()) {
			return null;
		}

		try {
			parsedDate = format1.parse(dateString);
		} catch (ParseException e1) {
			try {
				parsedDate = format2.parse(dateString);
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
		}

		return parsedDate;
	}

	public Adresse parseUneAdresse(String adresseString) {
		Adresse adresse = new Adresse();
		String[] locations = adresseString.split(",");
		int len = locations.length;

		switch (len) {
		case 0:
			break;
		case 1:
			adresse.setPays(locations[0].trim());
			break;
		case 2:
			adresse.setDepartement(locations[0].trim());
			adresse.setPays(locations[1].trim());
			break;
		case 3:
			adresse.setVille(locations[0].trim());
			adresse.setDepartement(locations[1].trim());
			adresse.setPays(locations[2].trim());
			break;
		case 4:
			adresse.setQuartier(locations[0].trim());
			adresse.setVille(locations[1].trim());
			adresse.setDepartement(locations[2].trim());
			adresse.setPays(locations[3].trim());
			break;
		case 5:
			adresse.setBatiment(locations[0].trim());
			adresse.setQuartier(locations[1].trim());
			adresse.setVille(locations[2].trim());
			adresse.setDepartement(locations[3].trim());
			adresse.setPays(locations[4].trim());
			break;
		default:
			break;
		}

		return adresse;
	}

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

		try (BufferedReader lecteur = new BufferedReader(
				new InputStreamReader(is))) {
			String ligne;
			lecteur.readLine(); // Skipping header
			while ((ligne = lecteur.readLine()) != null) {
				String[] colonnes = ligne.split(";");
				if (colonnes.length >= 4) {
					Adresse adresse = parseUneAdresse(colonnes[3]);
					adresses.add(adresse);
				} else {
					System.err.println(
							"Ligne incorrecte dans le fichier CSV: "
									+ ligne);
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(
					"Une erreur RuntimeException est survenue (lors de la lecture du fichier .csv.)");
		}
		return adresses;
	}

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

	public List<Langue> parseLangue(String nomFichier) {

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

	public List<Genre> parseGenre(String nomFichier) {

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
}
