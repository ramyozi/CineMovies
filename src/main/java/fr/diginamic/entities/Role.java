package fr.diginamic.entities;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_role;

    @Column(name = "personnage")
    private String personnage;

    @ManyToOne
    @JoinColumn(name = "id_acteur")
    private Acteur acteur;

    @ManyToOne
    @JoinColumn(name = "id_film")
    private Film film;
	
	/** Constructeur
	 * 
	 */
	public Role() {
		super();
	}

	
}
