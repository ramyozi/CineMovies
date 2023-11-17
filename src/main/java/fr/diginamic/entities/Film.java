package fr.diginamic.entities;

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

@Entity
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "idImdb", unique = true)
	private String idImdb;

	@Column(name = "nom")
	private String nom;

	@Column(name = "annee")
	private String annee;

	@Column(name = "note")
	private double note;

	@Column(name = "url", unique = true)
	private String url;

	@Column(name = "resume", length = 500)
	private String resume;

	@ManyToOne
	@JoinColumn(name = "id_pays")
	private Pays paysDeSortie;

	@ManyToOne
	@JoinColumn(name = "id_langue")
	private Langue langue;

	@ManyToMany
	@JoinTable(name = "FILM_GENRE", joinColumns = @JoinColumn(name = "ID_FILM", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_GENRE", referencedColumnName = "ID"))
	private List<Genre> genres;

	@OneToMany(mappedBy = "film")
	private List<Role> roles;

	@ManyToMany
	@JoinTable(name = "films_realisateurs", joinColumns = @JoinColumn(name = "id_film", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "id_realisateur", referencedColumnName = "ID"))
	private List<Realisateur> realisateurs;

	@ManyToMany
	@JoinTable(name = "films_acteurs_principaux", joinColumns = @JoinColumn(name = "id_film", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "id_acteur", referencedColumnName = "ID"))
	private List<Acteur> acteursPrincipaux;

	/**
	 * Constructeur
	 * 
	 */
	public Film() {
		super();
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
	 * @return the annee
	 */
	public String getAnnee() {
		return annee;
	}

	/**
	 * Setter
	 * 
	 * @param annee the annee to set
	 */
	public void setAnnee(String annee) {
		this.annee = annee;
	}

	/**
	 * Getter
	 * 
	 * @return the note
	 */
	public double getNote() {
		return note;
	}

	/**
	 * Setter
	 * 
	 * @param note the note to set
	 */
	public void setNote(double note) {
		this.note = note;
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
	 * @return the resume
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * Setter
	 * 
	 * @param resume the resume to set
	 */
	public void setResume(String resume) {
		this.resume = resume;
	}

	/**
	 * Getter
	 * 
	 * @return the langue
	 */
	public Langue getLangue() {
		return langue;
	}

	/**
	 * Setter
	 * 
	 * @param langue the langue to set
	 */
	public void setLangue(Langue langue) {
		this.langue = langue;
	}

	/**
	 * Getter
	 * 
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/**
	 * Setter
	 * 
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
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
	 * @return the realisateurs
	 */
	public List<Realisateur> getRealisateurs() {
		return realisateurs;
	}

	/**
	 * Setter
	 * 
	 * @param realisateurs the realisateurs to set
	 */
	public void setRealisateurs(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

	/**
	 * Getter
	 * 
	 * @return the acteursPrincipaux
	 */
	public List<Acteur> getActeursPrincipaux() {
		return acteursPrincipaux;
	}

	/**
	 * Setter
	 * 
	 * @param acteursPrincipaux the acteursPrincipaux to set
	 */
	public void setActeursPrincipaux(List<Acteur> acteursPrincipaux) {
		this.acteursPrincipaux = acteursPrincipaux;
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
	 * @return the paysDeSortie
	 */
	public Pays getPaysDeSortie() {
		return paysDeSortie;
	}

	/**
	 * Setter
	 * 
	 * @param paysDeSortie the paysDeSortie to set
	 */
	public void setPaysDeSortie(Pays paysDeSortie) {
		this.paysDeSortie = paysDeSortie;
	}

}
