package fr.diginamic.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class Batiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_batiment;

    @OneToMany(mappedBy = "batiment")
    private List<Adresse> adresses;
	
	/** Constructeur
	 * 
	 */
	public Batiment () {
		super();
	}

	/** Getter
	 * @return the id_batiment
	 */
	public int getId_batiment() {
		return id_batiment;
	}

	/** Setter
	 * @param id_batiment the id_batiment to set
	 */
	public void setId_batiment(int id_batiment) {
		this.id_batiment = id_batiment;
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
