package beans;

public class Position {
	
	private long idPosition;
	private String ville;
	private String lieuDit;
	private String piece;
	private int hauteur; // centimetre
	
	
	
	public Position() {
		super();
	}
	public Position(long idPosition, String ville, String lieuDit, String piece, int hauteur) {
		super();
		this.idPosition = idPosition;
		this.ville = ville;
		this.lieuDit = lieuDit;
		this.piece = piece;
		this.hauteur = hauteur;
	}
	public long getIdPosition() {
		return idPosition;
	}
	public void setIdPosition(long idPosition) {
		this.idPosition = idPosition;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getLieuDit() {
		return lieuDit;
	}
	public void setLieuDit(String lieuDit) {
		this.lieuDit = lieuDit;
	}
	public String getPiece() {
		return piece;
	}
	public void setPiece(String piece) {
		this.piece = piece;
	}
	public int getHauteur() {
		return hauteur;
	}
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	
	
}
