package modele.entites;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modele.FeuilleDessinModele;
import modele.entites.caracteristiques.Visibilite;
import modele.entites.caracteristiques.attribut.AttributEntite;
import modele.entites.caracteristiques.methode.MethodeEntite;
import modele.exceptions.AlreadyExistException;

public class InterfaceModele extends EntiteModele{


	//La plus utile
	public InterfaceModele(FeuilleDessinModele feuille, String nom, Visibilite visibilite, Point position) {
		this(feuille, nom, visibilite, new ArrayList<AttributEntite>(), new HashMap<String, List<MethodeEntite>>(), position);
	}

	public InterfaceModele(FeuilleDessinModele feuille, String nom, Visibilite visibilite, List<AttributEntite> attributs,
			HashMap<String, List<MethodeEntite>> methodes, Point position) {
		super(feuille, nom, visibilite, attributs, methodes, position);
		

	}
	
	@Override
	public void ajouterAttribut(AttributEntite attribut) throws AlreadyExistException {
		super.ajouterAttribut(attribut);
		attribut.setFinal(true);
		attribut.setStatique(true);
		attribut.setVisibilite(Visibilite.PUBLIC);
		
		
	}

	@Override
	public boolean isClasse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInterface() {
		return true;
	}

	@Override
	public boolean isEnum() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toStringJava() {
		// TODO Auto-generated method stub
		return null;
	}



}
