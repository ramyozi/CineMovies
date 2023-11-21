package fr.diginamic.menu;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.dao.ActeurDao;
import fr.diginamic.dao.FilmDao;
import fr.diginamic.entities.Acteur;
import fr.diginamic.entities.Film;

/**
 * Classe permettant d'afficher un menu interactif pour effectuer différentes
 * opérations liées aux acteurs et aux films dans une base de données.
 */
public class Menu {
	/**
	 * Constructeur par défaut de la classe Menu.
	 */
	public Menu() {

	}

	/**
	 * Affiche le menu interactif et gère les différentes fonctionnalités proposées.
	 */
	public void afficherMenu() {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("CineMovies");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		ActeurDao acteurDao = new ActeurDao(entityManager);
		FilmDao filmDao = new FilmDao(entityManager);

		Scanner scanner = new Scanner(System.in);
		int choix = 0;

		do {
			System.out.println("===== MENU =====");
			System.out.println(
					"1. Afficher la filmographie d’un acteur donné");
			System.out.println("2. Afficher le casting d’un film donné");
			System.out.println(
					"3. Afficher les films sortis entre 2 années données");
			System.out.println(
					"4. Afficher les films communs à 2 acteurs/actrices donnés");
			System.out.println(
					"5. Afficher les acteurs communs à 2 films donnés");
			System.out.println(
					"6. Afficher les films entre 2 années avec un acteur donné au casting");
			System.out.println("7. Quitter");
			System.out.println("================");

			System.out.print("Faites votre choix : ");
			while (!scanner.hasNextInt()) {
				System.out.println("Veuillez entrer un nombre.");
				scanner.next();
			}
			choix = scanner.nextInt();
			scanner.nextLine();

			switch (choix) {
			case 1:
				System.out.print("Entrez le nom de l'acteur : ");
				String nomActeur = scanner.nextLine();
				Acteur actor = acteurDao.getByName(nomActeur);

				if (actor != null) {
					System.out.println("Actor: " + actor.getIdentite());
					Set<Film> filmography = new HashSet<>(
							actor.getFilms());

					if (!filmography.isEmpty()) {
						System.out.println("Filmography:");
						for (Film film : filmography) {
							System.out.println("- " + film.getNom());
						}
					} else {
						System.out.println(
								"N3o films found for this actor.");
					}
				} else {
					System.out.println("Actor not found.");
				}
				break;
			case 2:
				System.out.print("Entrez le nom du film : ");
				String nomFilm = scanner.nextLine();
				Film film = filmDao.getByName(nomFilm);

				if (film != null) {
					System.out.println("Film: " + film.getNom());
					List<Acteur> casting = film.getActeurs();

					if (casting != null && !casting.isEmpty()) {
						System.out.println("Casting:");
						for (Acteur acteur : casting) {
							System.out
									.println("- " + acteur.getIdentite());
						}
					} else {
						System.out.println(
								"No casting found for this film.");
					}
				} else {
					System.out.println("Film not found.");
				}
				break;
			case 3:
				System.out.print("Entrez l'année de début : ");
				String debutAnnee = scanner.nextLine();
				System.out.print("Entrez l'année de fin : ");
				String finAnnee = scanner.nextLine();

				List<Film> filmsBetweenYears = filmDao
						.getFilmsBetweenYears(debutAnnee, finAnnee);

				if (filmsBetweenYears != null
						&& !filmsBetweenYears.isEmpty()) {
					System.out.println("Films sortis entre " + debutAnnee
							+ " et " + finAnnee + ":");
					for (Film film1 : filmsBetweenYears) {
						System.out.println("- " + film1.getNom() + " ("
								+ film1.getAnnee() + ")");
					}
				} else {
					System.out.println("Aucun film trouvé entre "
							+ debutAnnee + " et " + finAnnee);
				}
				break;
			case 4:
				System.out.println(
						"Entrez le nom du premier acteur/actrice:");
				String nomActeur1 = scanner.nextLine();

				Acteur acteur1 = acteurDao.getByName(nomActeur1);
				if (acteur1 == null) {
					System.out.println("Acteur/Actrice non trouvé(e).");
					break;
				}

				System.out.println(
						"Entrez le nom du deuxième acteur/actrice:");
				String nomActeur2 = scanner.nextLine();

				Acteur acteur2 = acteurDao.getByName(nomActeur2);
				if (acteur2 == null) {
					System.out.println("Acteur/Actrice non trouvé(e).");
					break;
				}

				List<Film> filmsCommuns = filmDao
						.getFilmsCommonToActorsByRoles(acteur1, acteur2);
				if (filmsCommuns.isEmpty()) {
					System.out.println(
							"Aucun film en commun trouvé entre ces acteurs/actrices.");
				} else {
					System.out.println("Les films en commun entre "
							+ acteur1.getIdentite() + " et "
							+ acteur2.getIdentite() + ":");
					for (Film f : filmsCommuns) {
						System.out.println("- " + f.getNom());
					}
				}
				break;
			case 5:
				System.out.println("Entrez le nom du premier film:");
				String nomFilm1 = scanner.nextLine();
				Film film1 = filmDao.getByName(nomFilm1);

				System.out.println("Entrez le nom du deuxième film:");
				String nomFilm2 = scanner.nextLine();
				Film film2 = filmDao.getByName(nomFilm2);

				if (film1 != null && film2 != null) {
					List<Acteur> commonActors = filmDao
							.getActorsCommonToFilms(film1, film2);
					if (!commonActors.isEmpty()) {
						System.out.println("Acteurs communs aux films "
								+ nomFilm1 + " et " + nomFilm2 + ":");
						for (Acteur a : commonActors) {
							System.out.println("- " + a.getIdentite());
						}
					} else {
						System.out.println(
								"Aucun acteur commun trouvé pour les films donnés.");
					}
				} else {
					System.out.println(
							"Au moins l'un des films n'a pas été trouvé.");
				}
				break;
			case 6:
				System.out.println("Entrez l'année de début :");
				int startYear = Integer.parseInt(scanner.nextLine());

				System.out.println("Entrez l'année de fin :");
				int endYear = Integer.parseInt(scanner.nextLine());

				System.out
						.println("Entrez l'ID IMDB de l'acteur/actrice :");
				String actorIdImdb = scanner.nextLine();

				List<Film> filmsBetweenYearsWithActor = filmDao
						.getFilmsBetweenYearsWithActor(actorIdImdb,
								startYear, endYear);

				if (!filmsBetweenYearsWithActor.isEmpty()) {
					System.out.println("Films sortis entre " + startYear
							+ " et " + endYear + " avec l'acteur/actrice "
							+ actorIdImdb + " :");
					for (Film f : filmsBetweenYearsWithActor) {
						System.out.println("- " + f.getNom());
					}
				} else {
					System.out.println(
							"Aucun film trouvé pour les critères donnés.");
				}
				break;

			case 7:
				System.out.println("Fin de l'application.");
				break;

			default:
				System.out.println("Choix invalide.");
				break;
			}
		} while (choix != 7);

		scanner.close();
		entityManager.close();
		entityManagerFactory.close();
	}

}
