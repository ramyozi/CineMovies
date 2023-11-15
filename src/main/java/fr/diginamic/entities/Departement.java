package fr.diginamic.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_dep;

    @OneToMany(mappedBy = "departement")
    private List<Adresse> adresses;
	
	/** Constructeur
	 * 
	 */
	public Departement() {
		super();
	}

	/** Getter
	 * @return the id_dep
	 */
	public int getId_dep() {
		return id_dep;
	}

	/** Setter
	 * @param id_dep the id_dep to set
	 */
	public void setId_dep(int id_dep) {
		this.id_dep = id_dep;
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
