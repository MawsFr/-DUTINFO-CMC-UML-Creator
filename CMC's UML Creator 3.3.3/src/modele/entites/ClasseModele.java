package modele.entites;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modele.FeuilleDessinModele;
import modele.entites.caracteristiques.Visibilite;
import modele.entites.caracteristiques.attribut.AttributEntite;
import modele.entites.caracteristiques.methode.MethodeEntite;
import modele.relation.RelationModele;


public class ClasseModele extends EntiteModele {

	private static int nbClasses;
	
	private boolean estAbstraite;
	private boolean estFinale;


	//La plus utile
	public ClasseModele(FeuilleDessinModele feuille, String nom, Visibilite visibilite, boolean estAbstraite, boolean estFinale, Point position) {
		this(feuille, nom, visibilite, estAbstraite, estFinale, new ArrayList<AttributEntite>(), new HashMap<String, List<MethodeEntite>>(), position);
	}
	
	
	public ClasseModele(FeuilleDessinModele feuille, String nom, Visibilite visibilite, boolean estAbstraite, boolean estFinale, List<AttributEntite> attributs,
			HashMap<String, List<MethodeEntite>> methodes, Point position) {
		super(feuille, nom, visibilite, attributs, methodes, position);
		this.estAbstraite = estAbstraite;
		this.estFinale = estFinale;
		nbClasses++;

	}
	
	@Override
	public boolean isClasse() {
		return true;
	}


	@Override
	public boolean isInterface() {
		return false;
	}


	@Override
	public boolean isEnum() {
		return false;
	}

	public boolean estAbstraite() {
		return estAbstraite;
	}
	
	public void setAbstraite(boolean estAbstraite) throws IllegalArgumentException {
		if(this.estFinale) {
			throw new IllegalArgumentException("La classe est deja final elle ne peut pas être aussi abstraite");
		}
		this.estAbstraite = estAbstraite;
	}
	
	public boolean estFinale() {
		return estFinale;
	}
	public void setFinale(boolean estFinale) throws IllegalArgumentException {
		if(this.estFinale) {
			throw new IllegalArgumentException("La classe est deja abstraite elle ne peut pas être aussi finale");
		}
		this.estFinale = estFinale;
	}

	public static int getNbClasses() {
		return nbClasses;
	}


	@Override
	public String toStringJava() {
		List<RelationModele> relations = feuille.getRelationsDe(this);
		String s = "";
		if(visibilite != Visibilite.DEFAULT) {
			s += this.visibilite;
		}
		
		if(estAbstraite) {
			s += " abstract";
		}
		
		if(estFinale) {
			s += " final";
		}
		
		s += " class " + this.nom + " {\n\n";
		
		s += attributsToString();
		s += methodesToString();
		
		s+= "\n}";
		return s;
	}


	






}
