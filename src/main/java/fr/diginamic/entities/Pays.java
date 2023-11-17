package fr.diginamic.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pays {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(unique = true)
	String label;

	@Column(unique = true)
	String url;

	/**
	 * Constructeur
	 * 
	 */
	public Pays() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param label
	 * @param url
	 */
	public Pays(String label, String url) {
		super();
		this.label = label;
		this.url = url;
	}

	public static Pays rechercheParNom(List<Pays> listPays, String nom) {
		for (Pays p : listPays) {
			if (p.getLabel().equals(nom)) {
				return p;
			}
		}
		return null;
	}

	public static boolean doesPaysAlreadyExist(List<Pays> listPays,
			String nom) {
		return listPays.stream().anyMatch(p -> p.getLabel().equals(nom));
	}

	/**
	 * Getter
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Setter
	 * 
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
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

	@Override
	public String toString() {
		return "Pays: nom=" + label + ", url=" + url;
	}

}
