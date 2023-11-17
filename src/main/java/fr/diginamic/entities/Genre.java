package fr.diginamic.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "label", unique = true)
	private String label;

	@ManyToMany(mappedBy = "genres")
	private List<Film> films;

	/**
	 * Constructeur
	 * 
	 */
	public Genre() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param label
	 */
	public Genre(String label) {
		super();
		this.label = label;
	}

	public static Genre getGenreByNom(List<Genre> listGenre,
			String nomGenre) {
		for (Genre genres : listGenre) {
			if (genres.getLabel().equals(nomGenre)) {
				return genres;
			}
		}
		return null;
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
	 * @return the films
	 */
	public List<Film> getFilms() {
		return films;
	}

	/**
	 * Setter
	 * 
	 * @param films the films to set
	 */
	public void setFilms(List<Film> films) {
		this.films = films;
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
		return "Genre [label=" + label + ", films=" + films + "]";
	}

}
