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
 * Représente un pays de production de film.
 * Cette classe est une entité JPA utilisée pour mapper les pays de production dans une base de données.
 */
@Entity
@Table(name = "PAYS")
public class Pays {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "NOM")
	String nom;

	@Column(name = "URL")
	String url;

	@OneToMany(mappedBy = "pays")
	List<Film> films;
	 /**
     * Constructeur de Pays prenant en paramètre le nom et l'URL du pays.
     *
     * @param nom Le nom du pays.
     * @param url L'URL du pays.
     */
	public Pays(String nom, String url) {
		this.nom = nom;
		this.url = url;
	}
	/**
     * Constructeur par défaut de Pays.
     */
	public Pays() {
	}
	/**
     * Méthode toString pour obtenir une représentation textuelle de l'objet Pays.
     *
     * @return Une chaîne de caractères représentant l'objet Pays.
     */
	@Override
	public String toString() {
		return "Pays [id=" + id + ", nom=" + nom + ", url=" + url
				+ ", films=" + films + "]";
	}
	/**
     * Récupère un objet Pays à partir de sa liste et de son nom.
     *
     * @param listPays La liste des pays parmi lesquels rechercher.
     * @param nomPays  Le nom du pays à trouver.
     * @return         L'objet Pays correspondant au nom spécifié, ou null si non trouvé.
     */
	public static Pays getPaysByNom(List<Pays> listPays, String nomPays) {
		for (Pays pays : listPays) {
			if (pays.getNom().equals(nomPays)) {
				return pays;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Setter
	 * 
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
