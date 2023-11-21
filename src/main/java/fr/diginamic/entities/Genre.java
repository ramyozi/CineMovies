package fr.diginamic.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Représente un genre de film. Cette classe est une entité JPA utilisée pour
 * mapper les genres de film dans une base de données.
 */
@Entity
@Table(name = "GENRE")
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "NOM")
	String nom;

	@ManyToMany(mappedBy = "genres")
	List<Film> films;

	/**
	 * Constructeur par défaut de Genre.
	 */
	public Genre() {
	}

	/**
	 * Constructeur de Genre prenant en paramètre le nom du genre.
	 *
	 * @param nom Le nom du genre.
	 */
	public Genre(String nom) {
		this.nom = nom;
	}

	/**
	 * Méthode toString pour obtenir une représentation textuelle de l'objet Genre.
	 *
	 * @return Une chaîne de caractères représentant l'objet Genre.
	 */
	@Override
	public String toString() {
		return "Genre [id=" + id + ", nom=" + nom + ", films=" + films
				+ "]";
	}

	/**
	 * Récupère un objet Genre à partir de sa liste et de son nom.
	 *
	 * @param listGenre La liste des genres parmi lesquels rechercher.
	 * @param nomGenre  Le nom du genre à trouver.
	 * @return L'objet Genre correspondant au nom spécifié, ou null si non trouvé.
	 */
	public static Genre getGenreByNom(List<Genre> listGenre,
			String nomGenre) {
		for (Genre genres : listGenre) {
			if (genres.getNom().equals(nomGenre)) {
				return genres;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

}
