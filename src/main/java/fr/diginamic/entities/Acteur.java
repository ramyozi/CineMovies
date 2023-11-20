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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ACTEUR")
public class Acteur {
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
	@JoinTable(name = "CASTING_PRINCIPAL", joinColumns = @JoinColumn(name = "ID_ACTEUR"), inverseJoinColumns = @JoinColumn(name = "ID_FILM"))
	List<Film> films = new ArrayList<>();

	@OneToMany(mappedBy = "acteur")
	List<Role> roles;

	@ManyToOne
	@JoinColumn(name = "ID_LIEU_NAISSANCE")
	Adresse lieuNaissance;

	public Acteur(String idImdb, String identite, LocalDate dateNaissance,
			String url) {
		this.idImdb = idImdb;
		this.identite = identite;
		this.dateNaissance = dateNaissance;
		this.url = url;
	}

	public Acteur(String idImdb, String identite) {
		this.idImdb = idImdb;
		this.identite = identite;
	}

	public static Acteur getActeurByIdbm(List<Acteur> listActeur,
			String nomActeur) {
		for (Acteur acteurs : listActeur) {
			if (acteurs.getIdImdb().equals(nomActeur)) {
				return acteurs;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Acteur [id=" + id + ", idImdb=" + idImdb + ", identite="
				+ identite + ", dateNaissance=" + dateNaissance + ", url="
				+ url + ", films=" + films + ", roles=" + roles
				+ ", lieuNaissance=" + lieuNaissance + "]";
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
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * Setter
	 * 
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
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