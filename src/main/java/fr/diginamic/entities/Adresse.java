package fr.diginamic.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_pays")
	private String pays;
	@Column(name = "departement")
	private String departement;

	@Column(name = "ville")
	private String ville;

	@Column(name = "quartier")
	private String quartier;

	@Column(name = "batiment")
	private String batiment;

	/**
	 * Constructeur
	 * 
	 */
	public Adresse() {
		super();
		this.pays = null;
		this.departement = null;
		this.ville = null;
		this.quartier = null;
		this.batiment = null;
	}

	/** Constructeur
	 * @param pays
	 * @param departement
	 * @param ville
	 * @param quartier
	 * @param batiment
	 */
	public Adresse(String pays, String departement, String ville,
			String quartier, String batiment) {
		super();
		this.pays = pays;
		this.departement = departement;
		this.ville = ville;
		this.quartier = quartier;
		this.batiment = batiment;
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
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * Setter
	 * 
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * Getter
	 * 
	 * @return the departement
	 */
	public String getDepartement() {
		return departement;
	}

	/**
	 * Setter
	 * 
	 * @param departement the departement to set
	 */
	public void setDepartement(String departement) {
		this.departement = departement;
	}

	/**
	 * Getter
	 * 
	 * @return the quartier
	 */
	public String getQuartier() {
		return quartier;
	}

	/**
	 * Setter
	 * 
	 * @param quartier the quartier to set
	 */
	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}

	/**
	 * Getter
	 * 
	 * @return the batiment
	 */
	public String getBatiment() {
		return batiment;
	}

	/**
	 * Setter
	 * 
	 * @param batiment the batiment to set
	 */
	public void setBatiment(String batiment) {
		this.batiment = batiment;
	}

	/**
	 * Getter
	 * 
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}

	/**
	 * Setter
	 * 
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}

	@Override
	public String toString() {
		return "Adresse [pays=" + this.pays + ", departement=" + departement
				+ ", ville=" + ville + ", quartier=" + quartier
				+ ", batiment=" + batiment + "]";
	}

	

}
