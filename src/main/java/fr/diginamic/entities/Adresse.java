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
 * Représente une adresse. Cette classe est une entité JPA utilisée pour mapper
 * les données des adresses dans une base de données.
 */
@Entity
@Table(name = "ADRESSE")
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "CONTENU")
	String contenu;

	@OneToMany(mappedBy = "lieuNaissance")
	List<Realisateur> realisateurs;

	@OneToMany(mappedBy = "lieuNaissance")
	List<Acteur> acteurs;

	/**
	 * Constructeur
	 * 
	 */
	public Adresse() {
		super();
	}

	public Adresse(String nom) {
		this.contenu = nom;
	}

	public static Adresse getLieuNaissanceByNom(
			List<Adresse> listLieuNaissance, String contenuAdresse) {
		for (Adresse lieuNaissances : listLieuNaissance) {
			if (lieuNaissances.getNom().equals(contenuAdresse)) {
				return lieuNaissances;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Adresse [contenu=" + contenu + ", realisateurs="
				+ realisateurs + ", acteurs=" + acteurs + ", getId()="
				+ getId() + ", getNom()=" + getNom()
				+ ", getRealisateurs()=" + getRealisateurs()
				+ ", getActeurs()=" + getActeurs() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
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
		return contenu;
	}

	/**
	 * Setter
	 * 
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.contenu = nom;
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
	 * @return the acteurs
	 */
	public List<Acteur> getActeurs() {
		return acteurs;
	}

	/**
	 * Setter
	 * 
	 * @param acteurs the acteurs to set
	 */
	public void setActeurs(List<Acteur> acteurs) {
		this.acteurs = acteurs;
	}

}
