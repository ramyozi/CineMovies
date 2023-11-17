package fr.diginamic.entities;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Acteur extends Personne {

	/*
	 * * listeActeur
	 * 
	 */
	@OneToMany(mappedBy = "acteur")
	private List<Role> listeRole;

	@ManyToMany(mappedBy = "acteursPrincipaux")
	private List<Film> films;

	/**
	 * Constructeur
	 * 
	 */
	public Acteur() {
		super();
	}

	public static Acteur rechercheParImdb(List<Acteur> acteurs, String idImdb) {
		Acteur acteur = null;
		for (Acteur a : acteurs) {
			if (a.getIdImdb().equals(idImdb)) {
				acteur = a;
				break;
			}
		}
		return acteur;
	}

	/**
	 * Getter
	 * 
	 * @return the listeRole
	 */
	public List<Role> getListeRole() {
		return listeRole;
	}

	/**
	 * Setter
	 * 
	 * @param listeRole the listeRole to set
	 */
	public void setListeRole(List<Role> listeRole) {
		this.listeRole = listeRole;
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

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String formattedDdn = getDdn() != null
				? dateFormat.format(getDdn())
				: "N/A";

		return "Acteur{" + "id=" + getId() + ", idImdb='" + getIdImdb()
				+ '\'' + ", identite='" + getIdentite() + '\'' + ", ddn="
				+ formattedDdn + ", url='" + getUrl() + '\''
				+ ", lieuNaissance=" + getLieuNaissance() + ", listeRole="
				+ listeRole + ", films=" + films + '}';
	}

}
