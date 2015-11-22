package modele.entites;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Map.Entry;

import modele.FeuilleDessinModele;
import modele.entites.caracteristiques.Visibilite;
import modele.entites.caracteristiques.attribut.AttributEntite;
import modele.entites.caracteristiques.methode.MethodeEntite;
import modele.exceptions.AlreadyExistException;

public abstract class EntiteModele extends Observable implements
EntiteRedimensionnable , Serializable{


	protected FeuilleDessinModele feuille;
	protected Visibilite visibilite;
	protected String nom;
	protected List<AttributEntite> listeAttributs;
	protected Map<String, List<MethodeEntite>> methodes;

	protected Point position;
	protected Dimension taille;
	protected Dimension tailleMinimum;
	protected boolean estSelectionnable;
	protected boolean estRedimensionnable;
	protected boolean estDeplacable;

	protected boolean estSelectionnee;
	protected boolean estEnDeplacement;
	protected boolean estEnRedimension;

	public EntiteModele(FeuilleDessinModele feuille, String nom, Visibilite visibilite,
			List<AttributEntite> attributs,
			HashMap<String, List<MethodeEntite>> methodes, Point position) throws IllegalArgumentException {

		

		setNom(nom);
		this.feuille = feuille;
		this.visibilite = visibilite;
		this.listeAttributs = attributs;
		this.methodes = methodes;
		this.position = position;
		this.taille = new Dimension(60, 30);
		this.taille = new Dimension(60, 30);
		this.estSelectionnable = true;
		this.estDeplacable = true;
		this.estRedimensionnable = true;

	}

	public void ajouterAttribut(AttributEntite attribut)
			throws AlreadyExistException {
		if (this.listeAttributs.contains(listeAttributs) || contient(attribut)) {
			throw new AlreadyExistException("Cet attribut existe deja !");
		}

		this.listeAttributs.add(attribut);
		notifier();
	}

	public void ajouterMethode(MethodeEntite methode)
			throws AlreadyExistException {
		List<MethodeEntite> listeMethodes = this.methodes.get(methode.getNom());

		// if(!contient(methode)) {
		if (listeMethodes == null) {
			listeMethodes = new ArrayList<MethodeEntite>();
			this.methodes.put(methode.getNom(), listeMethodes);
		} else {
			if (contient(methode) || listeMethodes.contains(methode)) {
				throw new AlreadyExistException("Cette methode existe deja !");
			}
		}

		listeMethodes.add(methode);
		notifier(methode);

	}
	
	public void supprimer(AttributEntite attribut) {
		if(this.listeAttributs.contains(attribut)) {
			this.listeAttributs.remove(attribut);
			notifier();
		}
	}
	
	public void supprimer(MethodeEntite methode) throws IllegalArgumentException{
		List<MethodeEntite> liste = this.methodes.get(methode.getNom());
		if(liste == null) {
			throw new IllegalArgumentException("Aucune methode de ce nom n'existe !");
		} else {
			if(!liste.contains(methode)) {
				throw new IllegalArgumentException("La methode n'existe pas/plus !");
			} else {
				liste.remove(methode);
				if(liste.isEmpty()) {
					this.methodes.remove(methode.getNom());
				}
				notifier();
			}
		}
		
	}

	public boolean contient(MethodeEntite methode) throws AlreadyExistException {
		List<MethodeEntite> listeMethodes = this.methodes.get(methode.getNom());

		if (listeMethodes != null) {
			for (Iterator<MethodeEntite> it = listeMethodes.iterator(); it
					.hasNext();) {
				MethodeEntite methode2 = it.next();
				int comp = methode.compareTo(methode2);
				if (comp == 0) {
					return true;
				}
				
				if(comp == 3) {
					throw new AlreadyExistException("Methode de meme nom mais pas de meme type !");
				}
			}
		}

		return false;

	}

	public boolean contient(AttributEntite attribut) {
		for (Iterator<AttributEntite> it = listeAttributs.iterator(); it
				.hasNext();) {
			AttributEntite attribut2 = it.next();
			if (attribut.compareTo(attribut2) == 0) {
				return true;
			}
		}

		return false;
	}

	// public void ajouterMethode(MethodeEntite methode) {
	// if(!this.methodes.contains(methode)) {
	// this.methodes.add(methode);
	// notifier();
	// }
	// }

	public abstract boolean isClasse();

	public abstract boolean isInterface();

	public abstract boolean isEnum();

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) throws IllegalArgumentException {
		nom.trim();

		if(nom.isEmpty()) {
			throw new IllegalArgumentException("Le nom ne peut pas être vide !");
		}

		if(!nom.matches("^[a-zA-Z0-9]*$")) {
			throw new IllegalArgumentException("Le nom ne peut contenir que des lettre et des chiffre");
		}

		if(Character.isDigit(nom.charAt(0))) {
			throw new IllegalArgumentException("Le nom doit commencer par une lettre !!");
		}

		if(Character.isLowerCase(nom.charAt(0))) {
			nom = Character.toUpperCase(nom.charAt(0)) + nom.substring(1);

		}
		
		this.nom = nom;
		notifier();
	}

	public List<AttributEntite> getAttributs() {
		return listeAttributs;
	}

	public void setAttributs(List<AttributEntite> attributs) throws AlreadyExistException {
		this.listeAttributs.clear();
		for(Iterator<AttributEntite> it = attributs.iterator(); it.hasNext();) {
			ajouterAttribut(it.next());
		}
		notifier();
	}

	public List<MethodeEntite> getMethodes() {
		List<MethodeEntite> listeMethodes = new ArrayList<MethodeEntite>();
		for (Map.Entry<String, List<MethodeEntite>> entry : this.methodes
				.entrySet()) {
			listeMethodes.addAll(entry.getValue());
		}

		return listeMethodes;

	}

	public void setMethodes(List<MethodeEntite> listeMethode) throws AlreadyExistException {
		this.methodes.clear();
		for(Iterator<MethodeEntite> it = listeMethode.iterator(); it.hasNext();) {
			ajouterMethode(it.next());
		}
		notifier();
	}

	protected void notifier() {
		notifier(null);
	}

	protected void notifier(Object o) {
		setChanged();
		notifyObservers(o);
	}

	public String toStringUml() {
		return visibilite.toStringUML() + " " + nom;
	}

	public abstract String toStringJava();


	@Override
	public String toString() {
		return this.nom;
	}

	// /////////////////////////////////////////

	@Override
	public boolean estRedimensionnable() {
		return this.estRedimensionnable;
	}

	@Override
	public boolean estEnRedimension() {
		return this.estEnRedimension;
	}

	@Override
	public void setEnRedimension(boolean b) {
		if (estRedimensionnable) {
			this.estEnRedimension = b;
			notifier();
		}
	}

	@Override
	public boolean estDeplacable() {
		return this.estDeplacable;
	}

	@Override
	public boolean estEnDeplacement() {
		return this.estEnDeplacement;
	}

	@Override
	public void setEnDeplacement(boolean b) {
		if (estDeplacable) {
			this.estEnDeplacement = b;
			notifier();
		}
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void setPosition(Point position) {
		if (estDeplacable) {
			this.position = position;
			notifier();
		}
	}

	@Override
	public Dimension getTaille() {
		return taille;
	}

	@Override
	public void setTaille(Dimension taille) {
		if (estRedimensionnable) {
			this.taille = taille;
		}
	}

	@Override
	public int getX() {
		return position.x;
	}

	@Override
	public int getY() {
		return this.position.y;
	}

	public int getCenterX() {
		return position.x + taille.width/2;
	}

	public int getCenterY() {
		return position.y + taille.height/2;
	}
	@Override
	public void setX(int x) {
		if (estDeplacable) {
			this.position.x = x;
			notifier();
		}
	}

	@Override
	public void setY(int y) {
		if (estDeplacable) {
			this.position.y = y;
			notifier();
		}
	}

	@Override
	public int getWidth() {
		return this.taille.width;
	}

	@Override
	public int getHeight() {
		return this.taille.height;
	}

	@Override
	public void setWidth(int width) {
		if (estRedimensionnable) {
			this.taille.width = width;
			notifier();
		}

	}

	@Override
	public void setHeight(int height) {
		if (estRedimensionnable) {
			this.taille.height = height;
			notifier();
		}
	}

	@Override
	public boolean estSelectionnable() {
		return this.estSelectionnable;
	}

	@Override
	public boolean estSelectionee() {
		return this.estSelectionnee;

	}

	@Override
	public void setSelectionnee(boolean selectionnee) {
		if (estSelectionnable) {
			this.estSelectionnee = selectionnee;
			notifier();
		}
	}

	@Override
	public void setRedimensionnable(boolean b) {
		this.estRedimensionnable = b;
		notifier();
	}

	@Override
	public void setDeplacable(boolean b) {
		this.estDeplacable = b;
		notifier();
	}

	@Override
	public void setSelectionnable(boolean b) {
		this.estSelectionnable = b;
		notifier();
	}

	public Point getCenterPoint() {
		return new Point(getCenterX(), getCenterY());
	}

	public Dimension getTailleMinimum() {
		return tailleMinimum;
	}

	public void setTailleMinimum(Dimension tailleMinimum) {
		this.tailleMinimum = tailleMinimum;
	}

	/**
	 * @return the feuille
	 */
	public FeuilleDessinModele getFeuille() {
		return feuille;
	}

	///////

	public String attributsToString() {
		String s = "\n";

		for(Iterator<AttributEntite> it = this.listeAttributs.iterator(); it.hasNext();) {
			s += "\t" + it.next().toStringJava() + ";\n";
		}

		return s;

	}

	public String methodesToString() {
		String s = "\n";

		for(Entry<String, List<MethodeEntite>> entry : methodes.entrySet()) {
			for(Iterator<MethodeEntite> it = entry.getValue().iterator(); it.hasNext();) {
				MethodeEntite methode = it.next();
				if(methode.getNom().equals(this.nom)) {
					s += methode.toStringJava(true) + "\n";
				} else {
					s += methode.toStringJava(false) + "\n";
				}
			}

		}
		s += "\n";


		return s;

	}

	/**
	 * @param visibilite the visibilite to set
	 */
	public void setVisibilite(Visibilite visibilite) {
		this.visibilite = visibilite;
	}

	/**
	 * @return the visibilite
	 */
	public Visibilite getVisibilite() {
		return visibilite;
	}
	
	





}
