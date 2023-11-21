package fr.diginamic.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * Représente une langue de film.
 * Cette classe est une entité JPA utilisée pour mapper les langues de film dans une base de données.
 */
@Entity
@Table(name = "LANGUE")
public class Langue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "NOM")
	String nom;

	@OneToMany(mappedBy = "langue")
	List<Film> films;

	/** Constructeur
	 * 
	 */
	public Langue() {
		super();
	}
	 /**
     * Constructeur de Langue prenant en paramètre le nom de la langue.
     *
     * @param nom Le nom de la langue.
     */
	public Langue(String nom) {
		this.nom = nom;
	}
	/**
     * Méthode toString pour obtenir une représentation textuelle de l'objet Langue.
     *
     * @return Une chaîne de caractères représentant l'objet Langue.
     */
	@Override
	public String toString() {
		return "Langue [id=" + id + ", nom=" + nom + ", films=" + films
				+ "]";
	}
	 /**
     * Récupère un objet Langue à partir de sa liste et de son nom.
     *
     * @param listLangue La liste des langues parmi lesquelles rechercher.
     * @param nomLangue  Le nom de la langue à trouver.
     * @return           L'objet Langue correspondant au nom spécifié, ou null si non trouvé.
     */
	public static Langue getLangueByNom(List<Langue> listLangue,
			String nomLangue) {
		for (Langue langues : listLangue) {
			if (langues.getNom().equals(nomLangue)) {
				return langues;
			}
		}
		return null;
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter
	 * 
	 * @return the films
	 */
	public List<Film> getFilms() {
		return films;
	}

	/**
	 * Setter
	 * 
	 * @param films the films to set
	 */
	public void setFilms(List<Film> films) {
		this.films = films;
	}

}
