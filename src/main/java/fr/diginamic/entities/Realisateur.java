package fr.diginamic.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Entity
public class Realisateur extends Personne {

	@ManyToMany(mappedBy = "realisateurs")
	private List<Film> filmsDiriges = new ArrayList<>();
	@Column(name = "idLieuNaissance")
	private int idLieuNaissance;

	/**
	 * Constructeur
	 * 
	 */
	public Realisateur() {
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

	public static Realisateur rechercheParImdb(
			List<Realisateur> realisateurs, String idImdb) {
		Realisateur rea = null;
		for (Realisateur a : realisateurs) {
			if (a.getIdImdb().equals(idImdb)) {
				rea = a;
				break;
			}
		}
		return rea;
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

	/**
	 * Getter
	 * 
	 * @return the idLieuNaissance
	 */
	public int getIdLieuNaissance() {
		return idLieuNaissance;
	}

	/**
	 * Setter
	 * 
	 * @param idLieuNaissance the idLieuNaissance to set
	 */
	public void setIdLieuNaissance(int idLieuNaissance) {
		this.idLieuNaissance = idLieuNaissance;
	}

}
