package fr.diginamic.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import fr.diginamic.entities.Adresse;
import fr.diginamic.parsers.LecteurData;

public class AdresseDAO {
	private final EntityManager entityManager;

	public AdresseDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Adresse findAdresse(EntityManager entityManager, String pays,
			String departement, String ville, String quartier,
			String batiment) {
		try {
			return entityManager.createQuery(
					"SELECT a FROM Adresse a WHERE a.pays = :pays AND a.departement = :departement AND a.ville = :ville AND a.quartier = :quartier AND a.batiment = :batiment",
					Adresse.class).setParameter("pays", pays)
					.setParameter("departement", departement)
					.setParameter("ville", ville)
					.setParameter("quartier", quartier)
					.setParameter("batiment", batiment).getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}
	}

	public void createAdresse(Adresse adresse, List<Adresse> addressList)
			throws IOException {
		if (adresse.getDepartement() == null && adresse.getVille() == null
				&& adresse.getQuartier() == null
				&& adresse.getBatiment() == null) {

			return;
		}

		Adresse existingAdresse = findAdresse(entityManager,
				adresse.getPays(), adresse.getDepartement(),
				adresse.getVille(), adresse.getQuartier(),
				adresse.getBatiment());

		if (existingAdresse == null) {
			entityManager.getTransaction().begin();
			entityManager.persist(adresse);
			entityManager.getTransaction().commit();
		}
	}

	public int create(Adresse adresse) {
	    entityManager.getTransaction().begin();
	    entityManager.persist(adresse);
	    entityManager.getTransaction().commit();
	    return adresse.getId();
	}
}
