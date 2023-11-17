package fr.diginamic.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Langue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "label", unique = true)
    private String label;

    @OneToMany(mappedBy = "langue")
    private List<Film> film = new ArrayList<>();

	
	/** Constructeur
	 * 
	 */
	public Langue() {
		super();
	}


	/** Constructeur
	 * @param label
	 */
	public Langue(String label) {
		super();
		this.label = label;
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
	 * @return the films
	 */
	public List<Film> getFilm() {
		return film;
	}

	/** Setter
	 * @param films the films to set
	 */
	public void setFilm(List<Film> film) {
		this.film = film;
	}



	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/** Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Langue [label=" + label + ", film=" + film + "]";
	}
	
	
	

}
