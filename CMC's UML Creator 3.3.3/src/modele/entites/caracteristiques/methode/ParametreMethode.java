package modele.entites.caracteristiques.methode;

import modele.entites.caracteristiques.CaracteristiqueTypable;

public class ParametreMethode extends CaracteristiqueTypable {

	public ParametreMethode(String type, String nom, boolean estFinal) {
		super(type, nom, estFinal);
		
	}

	public ParametreMethode(String type, String nom) {
		this(type, nom, false);
	}
	
	@Override
    public String toString() {
        return type + " " + nom;
    }

	@Override
	public String toStringUML() {
		return this.type;
	}
	
	

}