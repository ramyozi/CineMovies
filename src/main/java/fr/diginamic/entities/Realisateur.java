package fr.diginamic.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Realisateur extends Personne {

	@ManyToMany(mappedBy = "realisateurs")
	private List<Film> filmsDiriges = new ArrayList<>();

	/**
	 * Constructeur
	 * 
	 */
	public Realisateur() {
		super();
	}



	/**
	 * Getter
	 * 
	 * @return the filmsDiriges
	 */
	public List<Film> getFilmsDiriges() {
		return filmsDiriges;
	}

	/**
	 * Setter
	 * 
	 * @param filmsDiriges the filmsDiriges to set
	 */
	public void setFilmsDiriges(List<Film> filmsDiriges) {
		this.filmsDiriges = filmsDiriges;
	}

}
