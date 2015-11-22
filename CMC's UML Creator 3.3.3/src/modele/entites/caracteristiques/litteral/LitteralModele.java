package modele.entites.caracteristiques.litteral;

import modele.entites.caracteristiques.CaracteristiqueModele;

public class LitteralModele extends CaracteristiqueModele {

	public LitteralModele(String nom) {
		super(nom);
	}
	
	@Override
	public String toStringUML() {
		return toString();
	}
	

}
