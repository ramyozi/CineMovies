package fr.diginamic.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_adresse;

    @ManyToOne
    @JoinColumn(name = "id_pays")
    private Pays pays;

    @ManyToOne
    @JoinColumn(name = "id_ville")
    private Ville ville;

    @ManyToOne
    @JoinColumn(name = "id_dep")
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "id_quartier")
    private Quartier quartier;

    @ManyToOne
    @JoinColumn(name = "id_batiment")
    private Batiment batiment;
    
    @OneToMany(mappedBy = "adresseDeTournage")
    private List<Film> films;


	/** Constructeur
	 * 
	 */
	public Adresse() {
		super();
	}

	/** Getter
	 * @return the id_adresse
	 */
	public int getId_adresse() {
		return id_adresse;
	}

	/** Setter
	 * @param id_adresse the id_adresse to set
	 */
	public void setId_adresse(int id_adresse) {
		this.id_adresse = id_adresse;
	}

	/** Getter
	 * @return the pays
	 */
	public Pays getPays() {
		return pays;
	}

	/** Setter
	 * @param pays the pays to set
	 */
	public void setPays(Pays pays) {
		this.pays = pays;
	}

	/** Getter
	 * @return the ville
	 */
	public Ville getVille() {
		return ville;
	}

	/** Setter
	 * @param ville the ville to set
	 */
	public void setVille(Ville ville) {
		this.ville = ville;
	}

	/** Getter
	 * @return the departement
	 */
	public Departement getDepartement() {
		return departement;
	}

	/** Setter
	 * @param departement the departement to set
	 */
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	/** Getter
	 * @return the quartier
	 */
	public Quartier getQuartier() {
		return quartier;
	}

	/** Setter
	 * @param quartier the quartier to set
	 */
	public void setQuartier(Quartier quartier) {
		this.quartier = quartier;
	}

	/** Getter
	 * @return the batiment
	 */
	public Batiment getBatiment() {
		return batiment;
	}

	/** Setter
	 * @param batiment the batiment to set
	 */
	public void setBatiment(Batiment batiment) {
		this.batiment = batiment;
	}
    
	
}
