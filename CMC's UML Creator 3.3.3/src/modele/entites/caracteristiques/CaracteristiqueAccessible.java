package modele.entites.caracteristiques;



public abstract class CaracteristiqueAccessible extends CaracteristiqueTypable {

	protected Visibilite visibilite;
	protected boolean estStatique;
	
	public CaracteristiqueAccessible(String nom, String type) {
		this(nom, type, Visibilite.PUBLIC, false, false);
	}
	
	public CaracteristiqueAccessible(String nom, String type, boolean estFinal) {
		this(nom, type, Visibilite.PUBLIC, estFinal,  false);
	}
	
	public CaracteristiqueAccessible(String nom, String type, Visibilite visibilite, boolean estFinal,
			 boolean estStatique) {
		
		super(nom, type, estFinal);
		this.visibilite = visibilite;
		this.estStatique = estStatique;
	}
	
	

	public Visibilite getVisibilite() {
		return visibilite;
	}

	public void setVisibilite(Visibilite visibilite) {
		this.visibilite = visibilite;
		notifier();
	}

	public boolean estStatique() {
		return estStatique;
	}

	public void setStatique(boolean estStatique) {
		this.estStatique = estStatique;
		notifier();
	}
	
	
	
	
	


}