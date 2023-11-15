package fr.diginamic.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pays;

    @Column(name = "label", unique = true, nullable = false)
    private String label;
    
    @OneToMany(mappedBy = "pays")
    private List<Adresse> adresses;

	
	/** Constructeur
	 * 
	 */
	public Pays() {
		super();
	}


	/** Getter
	 * @return the id_pays
	 */
	public int getId_pays() {
		return id_pays;
	}


	/** Setter
	 * @param id_pays the id_pays to set
	 */
	public void setId_pays(int id_pays) {
		this.id_pays = id_pays;
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
