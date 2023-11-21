package fr.diginamic.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe représentant un réalisateur de films.
 */

@Entity
@Table(name = "REALISATEUR")
public class Realisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "ID_IMDB")
	String idImdb;

	@Column(name = "IDENTITE")
	String identite;

	@Column(name = "DATE_NAISSANCE")
	LocalDate dateNaissance;

	@Column(name = "URL")
	String url;

	@ManyToMany
	@JoinTable(name = "FILMS_REALISATEURS", joinColumns = @JoinColumn(name = "ID_REALISATEUR"), inverseJoinColumns = @JoinColumn(name = "ID_FILM"))
	List<Film> films = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "ID_LIEU_NAISSANCE")
	Adresse lieuNaissance;

	/**
	 * Constructeur
	 * 
	 */
	public Realisateur() {
		super();
	}

	/**
	 * Constructeur de Realisateur avec ID IMDB et identité.
	 * 
	 * @param idImdb   L'ID IMDB du réalisateur.
	 * @param identite L'identité du réalisateur.
	 */
	public Realisateur(String idImdb, String identite) {
		this.idImdb = idImdb;
		this.identite = identite;
	}

	/**
	 * Constructeur de Realisateur avec toutes les informations.
	 * 
	 * @param idImdb        L'ID IMDB du réalisateur.
	 * @param identite      L'identité du réalisateur.
	 * @param dateNaissance La date de naissance du réalisateur.
	 * @param url           L'URL associée au réalisateur.
	 */
	public Realisateur(String idImdb, String identite,
			LocalDate dateNaissance, String url) {
		this.idImdb = idImdb;
		this.identite = identite;
		this.dateNaissance = dateNaissance;
		this.url = url;
	}

	/**
	 * Renvoie une représentation textuelle de l'objet Realisateur.
	 * 
	 * @return Une chaîne de caractères représentant l'objet Realisateur.
	 */
	@Override
	public String toString() {
		return "Realisateur [id=" + id + ", idImdb=" + idImdb
				+ ", identite=" + identite + ", dateNaissance="
				+ dateNaissance + ", url=" + url + ", films=" + films
				+ ", lieuNaissance=" + lieuNaissance + "]";
	}

	/**
	 * Recherche un réalisateur dans une liste en fonction de son ID IMDB.
	 * 
	 * @param listRealisateur La liste des réalisateurs.
	 * @param idImdb          L'ID IMDB du réalisateur recherché.
	 * @return Le réalisateur correspondant à l'ID IMDB spécifié, ou null s'il n'est
	 *         pas trouvé.
	 */
	public static Realisateur getRealisateurByIdbm(
			List<Realisateur> listRealisateur, String nomRealisateur) {
		for (Realisateur realisateurs : listRealisateur) {
			if (realisateurs.getIdImdb().equals(nomRealisateur)) {
				return realisateurs;
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
	 * @return the idImdb
	 */
	public String getIdImdb() {
		return idImdb;
	}

	/**
	 * Setter
	 * 
	 * @param idImdb the idImdb to set
	 */
	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
	}

	/**
	 * Getter
	 * 
	 * @return the identite
	 */
	public String getIdentite() {
		return identite;
	}

	/**
	 * Setter
	 * 
	 * @param identite the identite to set
	 */
	public void setIdentite(String identite) {
		this.identite = identite;
	}

	/**
	 * Getter
	 * 
	 * @return the dateNaissance
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * Setter
	 * 
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
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

	/**
	 * Getter
	 * 
	 * @return the lieuNaissance
	 */
	public Adresse getLieuNaissance() {
		return lieuNaissance;
	}

	/**
	 * Setter
	 * 
	 * @param lieuNaissance the lieuNaissance to set
	 */
	public void setLieuNaissance(Adresse lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

}