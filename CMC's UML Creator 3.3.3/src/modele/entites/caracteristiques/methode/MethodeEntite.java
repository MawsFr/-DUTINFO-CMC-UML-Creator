package modele.entites.caracteristiques.methode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modele.entites.caracteristiques.CaracteristiqueAccessible;

public class MethodeEntite extends CaracteristiqueAccessible implements Comparable<MethodeEntite>{

	protected List<ParametreMethode> parametres;

	private boolean estAbstraite;

	
	public MethodeEntite(String type, String nom) {
		this(type, nom, false, new ArrayList<ParametreMethode>(), false);
	}
	public MethodeEntite(String type, String nom, boolean estFinal) {
		this(type, nom, estFinal, new ArrayList<ParametreMethode>(), false);

	}
	
	public MethodeEntite() {
		this(null, null);
	}

	public MethodeEntite(String type, String nom, boolean estFinal,
			List<ParametreMethode> parametres, boolean estAbstraite) {

		super(type, nom, estFinal);
		this.parametres = parametres;
		this.estAbstraite = estAbstraite;
	}

	public void ajouterParametre(ParametreMethode parametre) throws IllegalArgumentException {
		if(!contient(parametre.getNom())) {
			this.parametres.add(parametre);
			notifier();
		} else {
			throw new IllegalArgumentException("Un parametre de meme nom existe deja !");
		}

	}

	public void ajouterParametre(Set<ParametreMethode> parametres) throws IllegalArgumentException {
		//TODO
		for(Iterator<ParametreMethode> it = this.parametres.iterator(); it.hasNext();) {
			ajouterParametre(it.next());
		}

	}

	public void retirerParametre(String nom) {
		if(!contient(nom)) {
			throw new IllegalArgumentException("Cet argument n'existe pas !");
		} else {
			this.parametres.remove(getParametre(nom));
		}
	}

	public void retirerParametre(ParametreMethode p) throws IllegalArgumentException{
		if(contient(p)) {
			this.parametres.remove(p);
		} else {
			throw new IllegalArgumentException("Cet argument n'existe pas !");
		}

	}

	public boolean estAbstraite() {
		return estAbstraite;
	}

	public void setAbstraite(boolean estAbstraite) {
		this.estAbstraite = estAbstraite;
	}

	public int getNbParametre() {
		return this.parametres.size();
	}

	public ParametreMethode getParametre(String parametre) {
		for(Iterator<ParametreMethode> it = this.parametres.iterator(); it.hasNext();) {
			ParametreMethode p = it.next();
			if(p.getNom().equals(parametre)) {
				return p;
			}
		}
		return null;

	}

	public ParametreMethode getParametreAt(int index) {
		if(index >= 0 && index < getNbParametre()) {
			return this.parametres.get(index);
		}

		return null;
	}

	public boolean contient(String parametre) {
		for(Iterator<ParametreMethode> it = this.parametres.iterator(); it.hasNext();) {
			if(parametre.equals(it.next().getNom())) {
				return true;
			}
		}

		return false;
	}

	public boolean contient(ParametreMethode parametre) {
		//		for(Iterator<ParametreMethode> it = this.parametres.iterator(); it.hasNext();) {
		//			if(parametre.getNom().equals(it.next().getNom())) {
		//				return true;
		//			}
		//		}
		//
		//		return false;

		return this.parametres.contains(parametre);
	}


	/**
	 * 
	 * @author Maws
	 * 
	 * Compare cette methode a celle passee en parametre<br /><br />
	 * 
	 * Verification effectuee : 
	 * 
	 * Est ce que la methode � ajoutee a le meme nom qu'une methode deja presente ?<br />
	 *		.Si oui : est ce qu'elle a le meme type ?<br />
	 *			..Si oui : est ce qu'elle a le meme nombre de parametre?<br />
	 *				...Si oui : Est ce que ces parametre sont dans le meme ordre de type ?<br />
	 *					....Si oui : Erreur la fonction existe DEJA !<br />
	 *					....Si non : On ajoute ;) !<br />
	 *				...Si non : on l'ajoute ;)<br />
	 *			..Si non : Erreur on ne peut pas avoir deux methode de meme nom ET de type DIFFERENT !<br />
	 *		.Si non : On cr�� le tableau car c'est la premiere fois qu'on ajoute cette methode, et on l'ajoute dans ce tableau ;)!<br />
	 * 
	 * @param methode : Methode a comparer a celle ci
	 * 
	 * @return A rediger attention c'est un brainfuck !!
	 * 
	 * 
	 */

	@Override
	public int compareTo(MethodeEntite methode) {
		int nb = 0;


		if(methode.nom.equals(nom)) { //Est ce que la methode � ajoutee a le meme nom qu'une methode deja presente ?
			if(methode.type.equals(type)){ //Oui : est ce qu'elle a le meme type ?
				if(getNbParametre() == methode.getNbParametre()) { //Oui : est ce qu'elle a le meme nombre de parametre?
					//Oui : Est ce que ces parametre sont dans le meme ordre de type ?
					if(getNbParametre() != 0) {
						for(int i = 0; i < getNbParametre(); i++) {
							ParametreMethode p1 = getParametreAt(i);
							ParametreMethode p2 = methode.getParametreAt(i);

							if(!p1.getType().equals(p2.getType())) {
								nb = 4;
								break;
							}
						}
					}

				} else {
					nb = 2; //Non : on l'ajoute ;)
				}
			} else {
				nb = 3; //Non : Erreur on ne peut pas avoir deux methode de meme nom ET de type DIFFERENT !
			}
		} else {
			nb = 1; //Non : On créé le tableau car c'est la premiere fois qu'on ajoute cette methode, et on l'ajoute dans ce tableau ;)!
		}


		return nb;
	}

	private String paramToString() {
		String s = "";
		for(Iterator<ParametreMethode> it = parametres.iterator(); it.hasNext();) {
			s+=it.next();
			if(it.hasNext()) {
				s+=", ";
			}
		}
		return s;
	}
	
	private String paramTypeToString() {
		String s = "";
		for(Iterator<ParametreMethode> it = parametres.iterator(); it.hasNext();) {
			s+=it.next().getType();
			if(it.hasNext()) {
				s+=", ";
			}
		}
		return s;
	}

	//au cas ou on veut afficher les nom au lieu des type plus tard
	private String paramNomToString() {
		String s = "";
		for(Iterator<ParametreMethode> it = parametres.iterator(); it.hasNext();) {
			s+=it.next().getNom();
			if(it.hasNext()) {
				s+=", ";
			}
		}
		return s;
	}
	
	public String toStringJava(boolean constructeur) {
		if(constructeur) {
			return "\n\t" + visibilite + " " + nom +"(" + paramToString() + ") {\n\n\t}";
		}
		return "\n\t" + visibilite + " " +  type +  " " + nom +"(" + paramToString() + ") {\n\n\t}";
	}
	
	@Override
	public String toStringUML() {
		String s = visibilite.toStringUML() + " " + nom + "(" + paramTypeToString() + ") : " + type;
		
		
		return s;
		
	}

	public static void main(String[] args) {
		MethodeEntite m1 = new MethodeEntite("String", "mouss", false);
		MethodeEntite m2 = new MethodeEntite("String", "mouss", false);
		m1.ajouterParametre(new ParametreMethode("String", "Swing"));
		m2.ajouterParametre(new ParametreMethode("String", "fdh"));
		try{
			m2.ajouterParametre(new ParametreMethode("String", "fdh"));
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(m1.compareTo(m2));
		System.out.println(m1);
		System.out.println(m2);
	}
	public List<ParametreMethode> getParametres() {
		return this.parametres;
	}





}