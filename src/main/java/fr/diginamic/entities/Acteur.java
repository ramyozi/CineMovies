package fr.diginamic.entities;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

@Entity
public class Acteur extends Personne {

	@OneToMany(mappedBy = "acteur")
	private List<Role> listeRole;

	@ManyToMany(mappedBy = "acteursPrincipaux")
	private List<Film> films;
	@Column(name = "idLieuNaissance")
	private int idLieuNaissance;

	/**
	 * Constructeur
	 * 
	 */
	public Acteur() {
		super();
	}

	public int findIdAdresseByAdresse(EntityManager entityManager,
			Adresse adresse) {
		try {
			TypedQuery<Adresse> query = entityManager.createQuery(
					"SELECT a FROM Adresse a WHERE " + "a.pays = :pays "
							+ "AND a.departement = :departement "
							+ "AND a.ville = :ville "
							+ "AND a.quartier = :quartier "
							+ "AND a.batiment = :batiment",
					Adresse.class);

			// Set parameters
			query.setParameter("pays", adresse.getPays());
			query.setParameter("departement", adresse.getDepartement());
			query.setParameter("ville", adresse.getVille());
			query.setParameter("quartier", adresse.getQuartier());
			query.setParameter("batiment", adresse.getBatiment());

			List<Adresse> resultList = query.getResultList();

			for (Adresse result : resultList) {
				if (result.getPays().equals(adresse.getPays())
						&& result.getDepartement()
								.equals(adresse.getDepartement())
						&& result.getVille().equals(adresse.getVille())
						&& result.getQuartier()
								.equals(adresse.getQuartier())
						&& result.getBatiment()
								.equals(adresse.getBatiment())) {
					return result.getId(); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}

	public static Acteur rechercheParImdb(List<Acteur> acteurs,
			String idImdb) {
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
				+ ", listeRole=" + listeRole + ", films=" + films + '}';
	}

	/** Getter
	 * @return the idLieuNaissance
	 */
	public int getIdLieuNaissance() {
		return idLieuNaissance;
	}

	/** Setter
	 * @param idLieuNaissance the idLieuNaissance to set
	 */
	public void setIdLieuNaissance(int idLieuNaissance) {
		this.idLieuNaissance = idLieuNaissance;
	}

	

}
