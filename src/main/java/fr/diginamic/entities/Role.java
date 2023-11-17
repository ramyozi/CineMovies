package fr.diginamic.entities;

import javax.persistence.*;
@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "personnage")
	private String personnage;

	@ManyToOne
	@JoinColumn(name = "id_acteur")
	private Acteur acteur;

	@ManyToOne
	@JoinColumn(name = "id_film")
	private Film film;

	/**
	 * Constructeur
	 * 
	 */
	public Role() {
		super();
	}

	
	/**
	 * Getter
	 * 
	 * @return the personnage
	 */
	public String getPersonnage() {
		return personnage;
	}

	/**
	 * Setter
	 * 
	 * @param personnage the personnage to set
	 */
	public void setPersonnage(String personnage) {
		this.personnage = personnage;
	}

	/** Getter
	 * @return the acteur
	 */
	public Acteur getActeur() {
		return acteur;
	}

	/** Setter
	 * @param acteur the acteur to set
	 */
	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}

	/** Getter
	 * @return the film
	 */
	public Film getFilm() {
		return film;
	}

	/** Setter
	 * @param film the film to set
	 */
	public void setFilm(Film film) {
		this.film = film;
	}


	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/** Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
