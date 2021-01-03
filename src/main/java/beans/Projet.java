package beans;

public class Projet {
	
	private long idProjet;
	private String nom;

	
	public Projet(String nom) {
		super();
		this.nom = nom;
	}
	public Projet() {
		super();
	}
	public Projet(long idProjet, String nom) {
		super();
		this.idProjet = idProjet;
		this.nom = nom;
	}
	public long getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(long idProjet) {
		this.idProjet = idProjet;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
