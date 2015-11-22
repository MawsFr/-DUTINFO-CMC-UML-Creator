package modele.entites;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modele.FeuilleDessinModele;
import modele.entites.caracteristiques.Visibilite;
import modele.entites.caracteristiques.attribut.AttributEntite;
import modele.entites.caracteristiques.litteral.LitteralModele;
import modele.entites.caracteristiques.methode.MethodeEntite;
import modele.exceptions.AlreadyExistException;


public class EnumerationModele extends EntiteModele {
	
	private List<LitteralModele> litteraux;

	//La plus utile
	public EnumerationModele(FeuilleDessinModele feuille, String nom, Visibilite visibilite, Point position) {
		this(feuille, nom, visibilite, new ArrayList<AttributEntite>(), new HashMap<String, List<MethodeEntite>>(), position);
	}
	
	
	public EnumerationModele(FeuilleDessinModele feuille, String nom, Visibilite visibilite, List<AttributEntite> attributs,
			HashMap<String, List<MethodeEntite>> methodes, Point position) {
		super(feuille, nom, visibilite, attributs, methodes, position);
		
		this.litteraux = new ArrayList<LitteralModele>();

	}

	public EnumerationModele(FeuilleDessinModele feuille, String nom, Visibilite visibilite, List<AttributEntite> attributs, HashMap<String, List<MethodeEntite>> methodes) {
		this(feuille, nom, visibilite, attributs, methodes, new Point(10, 10));
	}

	public void ajouterLitteral(LitteralModele litteral) throws AlreadyExistException {
		if(this.litteraux.contains(litteral)) {
			throw new AlreadyExistException("Ce litteral existe deja !");
		}
		
		this.litteraux.add(litteral);
		notifier(litteral);
	}

	public void supprimerLitteral(LitteralModele litteral) {
		if(!this.litteraux.contains(litteral)) {
			throw new IllegalArgumentException("Ce litteral n'existe pas !");
		}
		
		this.litteraux.remove(litteral);
	}


	public List<LitteralModele> getLitteraux() {
		return this.litteraux;
	}

	@Override
	public boolean isClasse() {
		return false;
	}


	@Override
	public boolean isInterface() {
		return false;
	}


	@Override
	public boolean isEnum() {
		return true;
	}


	@Override
	public String toStringJava() {
		return null;
	}
	

	






}
