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

	

	/** Constructeur
	 * @param personnage
	 * @param acteur
	 * @param film
	 */
	public Role(String personnage, Acteur acteur, Film film) {
		super();
		this.personnage = personnage;
		this.acteur = acteur;
		this.film = film;
	}

	/**
	 * Constructeur pour la liste des Rôles
	 * 
	 * @param personnage
	 */
	public Role(String personnage) {
		super();
		this.personnage = personnage;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", personnage=" + personnage
				+ ", acteur=" + acteur + ", film=" + film + "]";
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

	/**
	 * Getter
	 * 
	 * @return the acteur
	 */
	public Acteur getActeur() {
		return acteur;
	}

	/**
	 * Setter
	 * 
	 * @param acteur the acteur to set
	 */
	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}

	/**
	 * Getter
	 * 
	 * @return the film
	 */
	public Film getFilm() {
		return film;
	}

	/**
	 * Setter
	 * 
	 * @param film the film to set
	 */
	public void setFilm(Film film) {
		this.film = film;
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

}
