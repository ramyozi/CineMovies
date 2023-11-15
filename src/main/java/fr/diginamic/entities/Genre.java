package fr.diginamic.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_genre;

    @Column(name = "label", unique = true, nullable = false)
    private String label;

    @ManyToMany(mappedBy = "genres")
    private List<Film> films;
    
	/** Constructeur
	 * 
	 */
	public Genre() {
		super();
	}
	
	
	

}
