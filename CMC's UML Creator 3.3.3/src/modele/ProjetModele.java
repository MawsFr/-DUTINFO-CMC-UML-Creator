package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;

import vue.CMCUmlView;

public class ProjetModele extends Observable implements Serializable{

	private String nom;
	
	private List<FeuilleDessinModele> feuilles;
	private FeuilleDessinModele feuilleSelectionnee;
	
	private Queue<FeuilleDessinModele> pileFeuilleAjoutees;
	private Queue<FeuilleDessinModele> pileFeuilleSupprimees;
	
	
	public ProjetModele() {
		this("Projet Sans nom");
	}
	
	public ProjetModele(String nom) {
		this.nom = nom;
		this.feuilles = new ArrayList<FeuilleDessinModele>();
		this.pileFeuilleAjoutees = new LinkedList<FeuilleDessinModele>();
		this.pileFeuilleSupprimees = new LinkedList<FeuilleDessinModele>();
		
		ajouterFeuille(new FeuilleDessinModele("Feuille " + + FeuilleDessinModele.getNbFeuilles()));
	}
	
	public void ajouterFeuille(FeuilleDessinModele feuille) throws IllegalArgumentException {
		if(feuille == null) {
			throw new IllegalArgumentException("Votre feuille est null !");
		}
		
		if(this.feuilles.contains(feuille)) {
			throw new IllegalArgumentException("Cette feuille existe deja dans ce projet");
		}
		
		if(!this.feuilles.add(feuille) || !this.pileFeuilleAjoutees.add(feuille)) {
			throw new IllegalArgumentException("La feuille n'a pas pu etre ajoutee");
		}
		
		setFeuilleSelectionnee(feuille);
		notifier();
		

	}
	
	public void supprimerFeuille(FeuilleDessinModele feuille) throws IllegalArgumentException, UnsupportedOperationException {
		if(feuille == null) {
			throw new IllegalArgumentException("Votre feuille est null !");
		}
		
		if(!this.feuilles.contains(feuille)) {
			throw new IllegalArgumentException("Cette feuille n'existe pas dans ce projet");
		}
		
		if(feuilleSelectionnee == feuille) {
			setFeuilleSelectionnee(null);
		}
		
		if(!this.feuilles.remove(feuille) || !pileFeuilleSupprimees.add(feuille)) {
			throw new UnsupportedOperationException("Cette feuille n'a pas pu etre retirée !");
		}
		
		notifier();
	}
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
		notifier();
	}

	public FeuilleDessinModele getFeuilleSelectionnee() {
		return feuilleSelectionnee;
	}

	public void setFeuilleSelectionnee(FeuilleDessinModele feuilleSelectionnee) {
		this.feuilleSelectionnee = feuilleSelectionnee;
		System.out.println("Feuille selectionnee " + feuilleSelectionnee);
	}

	private void notifier() {
		notifier(null);
	}

	private void notifier(Object o) {
		setChanged();
		notifyObservers(o);
	}

	public Queue<FeuilleDessinModele> getPileFeuilleAjoutees() {
		return pileFeuilleAjoutees;
	}

	public Queue<FeuilleDessinModele> getPileFeuilleSupprimees() {
		return pileFeuilleSupprimees;
	}
	
	public void viderPileAjoutFeuille() {
		this.pileFeuilleAjoutees.clear();
		
	}
	
	public void viderPileSuppression() {
		this.pileFeuilleSupprimees.clear();
	}

	public List<FeuilleDessinModele> getFeuilles() {
		return feuilles;
	}
	
	
	
}
