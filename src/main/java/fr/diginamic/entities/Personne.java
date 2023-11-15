package fr.diginamic.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_personne;

    @Column(name = "id_imdb", unique = true, nullable = false)
    private String id_imdb;

    @Column(name = "identite", nullable = false)
    private String identite;

    @Column(name = "ddn")
    private Date ddn;

    @ManyToOne
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;
    
	/** Constructeur
	 * 
	 */
	public Personne() {
		super();
	}


}
