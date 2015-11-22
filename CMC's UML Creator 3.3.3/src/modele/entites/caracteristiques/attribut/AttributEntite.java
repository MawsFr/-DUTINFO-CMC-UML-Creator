package modele.entites.caracteristiques.attribut;

import modele.entites.caracteristiques.CaracteristiqueAccessible;


public class AttributEntite extends CaracteristiqueAccessible implements Comparable<AttributEntite>{

	protected String valeur;
	
	public AttributEntite(String type, String nom) {
		this(type, nom, false);
		
	}
	
	public AttributEntite(String type, String nom, boolean estFinal) {
		this(type, nom, estFinal, null);
		
	}
	
	public AttributEntite() {
		this(null, null);
	}
	
	public AttributEntite(String type, String nom, boolean estFinal, String valeur) {
		super(type, nom, estFinal);
		
	}
	
	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
	public String toStringJava() {
		String s = this.visibilite + " ";
		
		if(estStatique) {
			s += "static ";
		}
		
		if(estFinal) {
			s += "final ";
		}
		
		s += type + " " + nom;
		
		if(valeur != null) {
			s += " = " + valeur;
		}
		
		return s;
	}

	@Override
	public String toStringUML() {
		return visibilite.toStringUML() + " " + nom + " : " + type;
	}
	
	@Override
	public int compareTo(AttributEntite attribut) {
		return this.nom.compareTo(attribut.nom);
	}
	
	

	

}