package fr.diginamic.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class Ville {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_ville;

	@Column(name = "label", unique = true, nullable = false)
	private String label;

	@OneToMany(mappedBy = "ville")
	private List<Adresse> adresses;

	/**
	 * Constructeur
	 * 
	 */
	public Ville() {
		super();
	}

	/** Getter
	 * @return the id_ville
	 */
	public int getId_ville() {
		return id_ville;
	}

	/** Setter
	 * @param id_ville the id_ville to set
	 */
	public void setId_ville(int id_ville) {
		this.id_ville = id_ville;
	}

	/** Getter
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/** Setter
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/** Getter
	 * @return the adresses
	 */
	public List<Adresse> getAdresses() {
		return adresses;
	}

	/** Setter
	 * @param adresses the adresses to set
	 */
	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}
}
