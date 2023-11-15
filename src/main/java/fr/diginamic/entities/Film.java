package fr.diginamic.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_film;

    @Column(name = "id_imdb", unique = true, nullable = false)
    private String id_imdb;

    @Column(name = "nom")
    private String nom;

    @Column(name = "annee")
    private String annee;

    @Column(name = "note")
    private double note;

    @Column(name = "url", unique = true, nullable = false)
    private String url;

    @Column(name = "resume", length = 500)
    private String resume;

    @ManyToOne
    @JoinColumn(name = "id_adresse_tournage")
    private Adresse lieuDeTournage;

    @ManyToOne
    @JoinColumn(name = "id_pays")
    private Pays pays;

    @ManyToOne
    @JoinColumn(name = "id_langue")
    private Langue langue;

    @ManyToMany
    @JoinTable(
        name = "appartenir",
        joinColumns = @JoinColumn(name = "id_film"),
        inverseJoinColumns = @JoinColumn(name = "id_genre")
    )
    private List<Genre> genres;

    @OneToMany(mappedBy = "film")
    private List<Role> roles;

    @ManyToMany(mappedBy = "filmsDiriges")
    private List<Realisateur> realisateurs;
    
    @ManyToMany(mappedBy = "films")
    private List<Acteur> acteursPrincipaux;
	/** Constructeur
	 * 
	 */
	public Film() {
		super();
	}

	/** Getter
	 * @return the id_film
	 */
	public int getId_film() {
		return id_film;
	}

	/** Setter
	 * @param id_film the id_film to set
	 */
	public void setId_film(int id_film) {
		this.id_film = id_film;
	}

	/** Getter
	 * @return the id_imdb
	 */
	public String getId_imdb() {
		return id_imdb;
	}

	/** Setter
	 * @param id_imdb the id_imdb to set
	 */
	public void setId_imdb(String id_imdb) {
		this.id_imdb = id_imdb;
	}

	/** Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Getter
	 * @return the annee
	 */
	public String getAnnee() {
		return annee;
	}

	/** Setter
	 * @param annee the annee to set
	 */
	public void setAnnee(String annee) {
		this.annee = annee;
	}

	/** Getter
	 * @return the note
	 */
	public double getNote() {
		return note;
	}

	/** Setter
	 * @param note the note to set
	 */
	public void setNote(double note) {
		this.note = note;
	}

	/** Getter
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/** Setter
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/** Getter
	 * @return the resume
	 */
	public String getResume() {
		return resume;
	}

	/** Setter
	 * @param resume the resume to set
	 */
	public void setResume(String resume) {
		this.resume = resume;
	}

	/** Getter
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return lieuDeTournage;
	}

	/** Setter
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.lieuDeTournage = adresse;
	}

	/** Getter
	 * @return the pays
	 */
	public Pays getPays() {
		return pays;
	}

	/** Setter
	 * @param pays the pays to set
	 */
	public void setPays(Pays pays) {
		this.pays = pays;
	}

	/** Getter
	 * @return the langue
	 */
	public Langue getLangue() {
		return langue;
	}

	/** Setter
	 * @param langue the langue to set
	 */
	public void setLangue(Langue langue) {
		this.langue = langue;
	}
    
    
}
