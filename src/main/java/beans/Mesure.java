package beans;

public class Mesure {
	
	private long idMesure;
	private String nomMesure;
	private String Unite;
	
	
	
	public Mesure() {
		super();
	}


	public Mesure(long idMesure, String nomMesure, String unite) {
		super();
		this.idMesure = idMesure;
		this.nomMesure = nomMesure;
		Unite = unite;
	}



	public long getIdMesure() {
		return idMesure;
	}



	public void setIdMesure(long idMesure) {
		this.idMesure = idMesure;
	}



	public String getNomMesure() {
		return nomMesure;
	}



	public void setNomMesure(String nomMesure) {
		this.nomMesure = nomMesure;
	}



	public String getUnite() {
		return Unite;
	}



	public void setUnite(String unite) {
		Unite = unite;
	}
	
	

}
