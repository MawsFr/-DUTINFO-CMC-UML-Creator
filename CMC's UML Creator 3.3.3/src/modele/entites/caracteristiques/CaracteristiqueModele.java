package modele.entites.caracteristiques;

import java.io.Serializable;
import java.util.Observable;

public abstract class CaracteristiqueModele extends Observable implements Serializable{
	
	
	
	protected String nom;



	public CaracteristiqueModele(String nom) {
		this.nom = nom;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
		notifier();
	}
	
	protected void notifier() {
		setChanged();
		notifyObservers();
	}
	
	
	public abstract String toStringUML();
	
	public String toString() {
		return this.nom;
	}
	
	


}
