package fr.diginamic.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Quartier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_quartier;

    @OneToMany(mappedBy = "quartier")
    private List<Adresse> adresses;
	
	/** Constructeur
	 * 
	 */
	public Quartier() {
		super();
	}

	/** Getter
	 * @return the id_quartier
	 */
	public int getId_quartier() {
		return id_quartier;
	}

	/** Setter
	 * @param id_quartier the id_quartier to set
	 */
	public void setId_quartier(int id_quartier) {
		this.id_quartier = id_quartier;
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
