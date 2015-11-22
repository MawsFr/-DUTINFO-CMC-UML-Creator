package modele.entites.caracteristiques;


public abstract class CaracteristiqueTypable extends CaracteristiqueModele  {

	protected String type;
	protected boolean estFinal;
	
	
	public CaracteristiqueTypable(String type, String nom) {
		this(type, nom, false);
	}
	
	public CaracteristiqueTypable(String type, String nom, boolean estFinal) {
		
		super(nom);
		this.type = type;
		this.estFinal = estFinal;
	}



	


	public boolean estFinal() {
		return estFinal;
	}



	public void setFinal(boolean estFinal) {
		this.estFinal = estFinal;
		notifier();
	}


	
	public String getType() {
		return type;
	}
	
	

	public void setType(String type) {
		this.type = type;
		notifier();
	}
	

}