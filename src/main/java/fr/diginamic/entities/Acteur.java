package fr.diginamic.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class Acteur extends Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_acteur;

    @OneToMany(mappedBy = "acteur")
    private List<Role> roles;
    
    @ManyToMany
    @JoinTable(
        name = "acteurs_films_principaux",
        joinColumns = @JoinColumn(name = "id_acteur"),
        inverseJoinColumns = @JoinColumn(name = "id_film")
    )
    private List<Film> films;

	/** Constructeur
	 * 
	 */
	public Acteur() {
		super();
	}

    
}
