package fr.diginamic.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class Langue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_langue;

    @Column(name = "label", unique = true, nullable = false)
    private String label;

    @OneToMany(mappedBy = "langue")
    private List<Film> films;
	
	/** Constructeur
	 * 
	 */
	public Langue() {
		super();
	}
	
	
	

}
