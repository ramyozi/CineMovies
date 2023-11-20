package fr.diginamic.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GENRE")
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "NOM")
	String nom;

	@ManyToMany(mappedBy = "genres")
	List<Film> films;

	public Genre() {
	}

	public Genre(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", nom=" + nom + ", films=" + films + "]";
	}

	public static Genre getGenreByNom(List<Genre> listGenre, String nomGenre) {
		for (Genre genres : listGenre) {
			if (genres.getNom().equals(nomGenre)) {
				return genres;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

}
