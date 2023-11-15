package fr.diginamic.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class Realisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_realisateur;

    @ManyToMany
    @JoinTable(
        name = "diriger",
        joinColumns = @JoinColumn(name = "id_realisateur"),
        inverseJoinColumns = @JoinColumn(name = "id_film")
    )
    private List<Film> filmsDiriges;

	/**
	 * Constructeur
	 * 
	 */
	public Realisateur() {
		super();
	}

}
