package modele.relation;

/**
 * 
 * @author Maws
 * 
 * Cette classe représente une relation entre deux entités.
 * De cette classe dérive les différentes relation existante en UML.
 * Une relation est représenté par une série de noeud (2 par défaut : Depart et Arrive). On peut en rajouter pour rendre le rendre plus compréhensible à la lecture.
 * 
 * 
 * 
 * @see GeneralisationModele
 * @see RealisationModele
 * @see AggregationModele
 * @see AssociationModele
 * @see CompositionModele
 * @see DirectAssociationModele
 * @see DependanceModele
 * @see NoeudModele
 * 
 * 
 */


import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Observable;

import vue.CMCUmlView;
import modele.FeuilleDessinModele;
import modele.entites.EntiteModele;



//Classe meres des relations

public abstract class RelationModele extends Observable implements Comparable<RelationModele> , Serializable{

	//Informations

	/**
	 * La feuille de dessin sur laquelle a été créée cette relation
	 */
	protected FeuilleDessinModele feuille;

	/**
	 * Le nom de la relation
	 */
	protected String nom;


	/**
	 * L'entité de départ
	 */
	protected EntiteModele entiteDepart;

	/**
	 * L'entité d'arrivée
	 */
	protected EntiteModele entiteArrive;

	/**
	 * La cardinalité de l'entité de départ
	 */
	protected CardinaliteModele cardinaliteDepart;

	/**
	 * La cardinalité de l'entité d'arrivé
	 */
	protected CardinaliteModele cardinaliteArrive;

	/**
	 * Contient true si le depart à été selectionné, sinon false.
	 * Sert à savoir si on est en cours d'édition de cette relation.
	 */
	protected boolean departExiste;


	/**
	 * La liste contenant les noeud créé.
	 */
	protected LinkedList<NoeudModele> noeuds;

	/**
	 * Le noeud représentant le départ. Graphiquement il correspondrai au centre de l'entité de départ par exemple.
	 */
	private NoeudModele noeudDepart;

	/**
	 * Le noeud représentant l'arrivée. Graphiquement il correspondrai au centre de l'entité d'arrivé par exemple.
	 */
	private NoeudModele noeudArrivee;


	/**
	 * 
	 * <strong>RelationModele(FeuilleDessinModele feuille, String nom)</strong> <br> <br>
	 * Constructeur ne prenant que le Nom et la feuille ou la relation se trouve. C'est celui que l'on utilisera le plus souvent.
	 * 
	 * @param feuille : La feuille sur laquelle cette relation est créée
	 * @param nom : Le nom de la relation
	 */
	public RelationModele(FeuilleDessinModele feuille, String nom) {
		this(feuille, nom, null, null);
	}


	/**
	 * <strong>RelationModele(FeuilleDessinModele feuille, String nom, EntiteModele entiteDepart, EntiteModele entiteArrive)</strong> <br><br>
	 * 
	 * Constructeur permetant de fournir l'entité de départ et d'arriver en meme temps que leur cardinalité, le nom de la relation et la feuille à laquelle elle appartient
	 * 
	 * @param feuille : La feuille sur laquelle cette relation est créée
	 * @param nom : Le nom de la relation
	 * @param entiteDepart : L'entité de départ
	 * @param entiteArrive : L'entité d'arrivée
	 */
	public RelationModele(FeuilleDessinModele feuille, String nom, EntiteModele entiteDepart, EntiteModele entiteArrive) {

		this.feuille = feuille;
		this.nom = nom;
		this.entiteDepart = entiteDepart;
		this.entiteArrive = entiteArrive;
		this.cardinaliteDepart = CardinaliteModele.ZERO_N;
		this.cardinaliteArrive = CardinaliteModele.ZERO_N;
		this.noeuds = new LinkedList<NoeudModele>();


	}

	/**
	 * <strong>RelationModele(FeuilleDessinModele feuille)</strong> <br> <br>
	 * Constructeur de relation avec un nom par défaut.
	 * 
	 * @param feuille : La feuille sur laquelle cette relation est créée
	 */
	public RelationModele(FeuilleDessinModele feuille) {
		this(feuille, "Relation", null, null);
	}

	/**
	 * <strong>ajouterEntite(EntiteModele entite)</strong> <br> <br>
	 * Ajoute dans l'ordre : L'entité de départ puis l'entité d'arrivée. Doit être appelé deux fois pour créer une relation complète
	 * 
	 * @param entite : L'entité à rajouter
	 * @throws IllegalArgumentException
	 */
	public void ajouterEntite(EntiteModele entite) throws IllegalArgumentException{
		if(!departExiste) {
			setEntiteDepart(entite);
		} else {
			setEntiteArrive(entite);
		}
	}

	/**
	 * <strong>contient(EntiteModele entite)</strong> <br> <br>
	 * Renvoie si l'entité en paramètre est contenue dans cette relation.
	 * 
	 * @param entite : L'entité à véirifier
	 * @return : <strong>true</strong> si l'entité correspond a l'entité de départ ou d'arrivé, <strong>false</strong> sinon;
	 */
	public boolean contient(EntiteModele entite) {
		return ((this.entiteDepart == entite) || (this.entiteArrive == entite));
	}


	/**
	 * <strong>ajouterNoeud(NoeudModele noeud)</strong> <br> <br>
	 * Ajoute un noeud à la relation et notifie les observeurs. Ce noeud est compris entre le noeud de départ et le noeud d'arrivé.
	 * 
	 * @param noeud : Le noeud à ajouter
	 */
	public void ajouterNoeud(NoeudModele noeud) {
		//		int[] noeuds = getNoeudsX();
		//		for(int i = 0; i < this.noeuds.size(); i++) {
		//			noeud.getPoint().
		//		}

		this.noeuds.add(noeud);
		notifier();

	}

	/**
	 * <strong>ajouterNoeud(NoeudModele noeud, NoeudModele apres)</strong> <br> <br>
	 * Ajoute un noeud après le noeud demandé et notifie les observeurs.
	 * 
	 * 
	 * @param noeud : Le noeud à rajouter
	 * @param apres : Le noeud après lequel on veut rajouter le noeud a rajouter
	 */
	public void ajouterNoeud(NoeudModele noeud, NoeudModele apres) {
		int indice = this.noeuds.indexOf(apres);
		this.noeuds.add(indice + 1, noeud);
		notifier();
	}


	/**
	 * <strong></strong> <br> <br>
	 * Supprime le noeud passé en paramètre et notifie les observeurs.
	 * 
	 * @param noeud : Le noeud à supprimer. 
	 */
	public void supprimerNoeud(NoeudModele noeud) {
		this.noeuds.remove(noeud);
		notifier();
	}


	/**
	 * <strong>String getNom()</strong> <br> <br>
	 * 
	 * @return : Le nom de la relation
	 */
	public String getNom() { //TODO : Demander le nom de la relation
		return nom;
	}

	/**
	 * @param nom : Change le nom de la relation
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * <strong>EntiteModele getEntiteDepart() </strong> <br> <br>
	 * @return : L'entité de départ
	 */
	public EntiteModele getEntiteDepart() {
		return entiteDepart;
	}

	/**
	 * <strong>EntiteModele getEntiteArrive() </strong> <br> <br>
	 * @return : L'entité d'arrivée
	 */
	public EntiteModele getEntiteArrive() {
		return entiteArrive;
	}

	/**
	 * <strong>void setEntiteDepart(EntiteModele entiteDepart)</strong> <br> <br>
	 * Change l'entité de départ
	 * 
	 * @param entiteDepart : La nouvelle entité de départ
	 */
	public void setEntiteDepart(EntiteModele entiteDepart) throws IllegalArgumentException {

		verifierDepart(entiteDepart);
		this.entiteDepart = entiteDepart;
		departExiste = true;
	}

	/**
	 * <strong>verifierDepart(EntiteModele entiteDepart)</strong> <br> <br>
	 * Verifie si l'entité en paramètre peut etre le depart de cette relation suivant les règle UML.
	 * 
	 * @param entiteDepart : L'entité de départ
	 * @throws IllegalArgumentException
	 */
	protected void verifierDepart(EntiteModele entiteDepart) throws IllegalArgumentException {
		if(entiteDepart == null) {
			throw new IllegalArgumentException("L'entité de départ est null");
		}
	}


	/**
	 * <strong>setEntiteArrive(EntiteModele entiteArrive)</strong> <br> <br>
	 * Change l'entité d'arrivée et recalcul les coordonnée des noeud. Ensuite la relation est ajoutée à la feuille de dessin.
	 * 
	 * @param entiteArrive :  La nouvelle entité d'arrivée
	 * @throws IllegalArgumentException : Lorsque l'arrivé n'est pas compatible avec l'entité de départ
	 */
	public void setEntiteArrive(EntiteModele entiteArrive) throws IllegalArgumentException {
		verifierArrive(entiteArrive);
		this.entiteArrive = entiteArrive;
		this.noeudDepart = new NoeudModele(entiteDepart.getCenterPoint());
		this.noeudArrivee = new NoeudModele(entiteArrive.getCenterPoint());
		calculer();
		feuille.ajouterRelation(this);

		//		ajouterNoeud(new NoeudModele(new Point(100, 100)));
		//		ajouterNoeud(new NoeudModele(new Point(200, 200)));
		notifier();
	}

	/**
	 * <strong>void setEntiteArrive(EntiteModele entiteArrive)</strong> <br> <br>
	 * Verifie si l'entité en paramètre peut etre l'arrivée de cette relation suivant le départ et les règle UML.
	 * 
	 * @param entiteArrive : L'entité d'arrivée
	 * @throws IllegalArgumentException Lorsque l'arrivé n'est pas compatible avec l'entité de départ
	 */
	protected void verifierArrive(EntiteModele entiteArrive) throws IllegalArgumentException {
		if(entiteArrive == null) {
			throw new IllegalArgumentException("L'entité d'arrivée est null");
		}

		if(entiteArrive == entiteDepart) {

		}
	}

	/**
	 * <strong>CardinaliteModele getCardinaliteDepart()</strong> <br> <br>
	 * @return La cardinalité de l'entité de départ
	 */
	public CardinaliteModele getCardinaliteDepart() {
		return cardinaliteDepart;
	}
	/**
	 * <strong>setCardinaliteDepart(CardinaliteModele cardinaliteDepart)</strong> <br> <br>
	 * Change la cardinalité de l'entité de départ
	 * @param cardinaliteDepart : La nouvelle cardinalité de départ
	 */
	public void setCardinaliteDepart(CardinaliteModele cardinaliteDepart) {
		this.cardinaliteDepart = cardinaliteDepart;
	}
	/**
	 * <strong>CardinaliteModele getCardinaliteArrive()</strong> <br> <br>
	 * @return La cardinalité de l'entité de départ
	 */
	public CardinaliteModele getCardinaliteArrive() {
		return cardinaliteArrive;
	}
	/**
	 * <strong>setCardinaliteArrive(CardinaliteModele cardinaliteArrive)</strong> <br> <br>
	 * Change la cardinalité de l'entité d'arrivée
	 * @param cardinaliteArrive : La nouvelle cardinalité d'arrivée
	 */
	public void setCardinaliteArrive(CardinaliteModele cardinaliteArrive) {
		this.cardinaliteArrive = cardinaliteArrive;
	}

	
	/**
	 * <strong>compareTo(RelationModele relation)</strong> <br> <br>
	 * Compare le nom de cette relation avec celui de celle passé en paramètre.
	 * 
	 * @param relation : la relation à comparer
	 * @return : 0 si le nom est le même, -1 si le nom de la relation en paramètre est lexicalement inférieur sinon 1.
	 * 
	 */
	@Override
	public int compareTo(RelationModele relation) {
		return this.nom.compareTo(relation.nom);

	}

	/**
	 * <strong>departExiste()</strong> <br> <br>
	 * @return : true si le départ existe, sinon false;
	 */
	public boolean departExiste() {
		return departExiste;
	}

	/**
	 * <strong>setDepartExiste(boolean departExiste)</strong> <br> <br>
	 * Change la valeur de departExiste
	 * @param departExiste : la valeur de l'existence du depart
	 */
	public void setDepartExiste(boolean departExiste) {
		this.departExiste = departExiste;
	}


	//	public int[] getNoeudsX() {
	//		int[] noeudX = new int[noeuds.size()];
	//		
	//		for(int i = 0; i < noeuds.size(); i++) {
	//			noeudX[i] = noeuds.get(i).getPoint().x;
	//		}
	//		
	//		return noeudX;
	//	}
	//	
	//	public int[] getNoeudsY() {
	//		int[] noeudY = new int[noeuds.size()];
	//		
	//		for(int i = 0; i < noeuds.size(); i++) {
	//			noeudY[i] = noeuds.get(i).getPoint().y;
	//		}
	//		
	//		return noeudY;
	//	}

	/**
	 * <strong>NoeudModele getNoeudDepart()</strong> <br> <br>
	 * @return Le noeud de départ
	 */
	public NoeudModele getNoeudDepart() {
		calculer();
		return this.noeudDepart;
	}

	/**
	 * <strong>void calculer()</strong><br><br>
	 * Calcul les coordonnées du noeud de départ et d'arrivée en tant que point d'encrage sur la fenetre.
	 * Cela permet d'avoir des trait horizontal ou vertical.
	 */
	private void calculer() {


		Point d = entiteDepart.getPosition(); //depart
		Point a = entiteArrive.getPosition(); //arrive

		Point fixe = null;
		Point mobile= null;

//				if(pArr.x > pDep.x) {
//					if(pArr.x < pDep.x + entiteDepart.getWidth()) {
//						if(pArr.y >= pDep.y) {
//							this.noeudArrivee.getPoint().x = entiteArrive.getX();
//							this.noeudArrivee.getPoint().y = entiteArrive.getY();
//							this.noeudDepart.getPoint().x = pArr.x;
//							this.noeudDepart.getPoint().y = entiteDepart.getY() + entiteDepart.getTaille().height;
//							
//						} else {
//							this.noeudDepart.getPoint().x = entiteArrive.getX();
//							this.noeudDepart.getPoint().y = entiteDepart.getY();
//							this.noeudArrivee.getPoint().x = pArr.x;
//							this.noeudArrivee.getPoint().y = entiteArrive.getY() + entiteArrive.getTaille().height;
//						}
//					}
//		
//				}
		if(CMCUmlView.getInstance().getModele().getProprietesEditeur().getProperty("coordspecial").equals("true")){
			if(a.x > d.x + entiteDepart.getWidth()) { //D a gauche A a droite
				if(a.y>d.y+entiteDepart.getHeight()-2) { //1
					this.noeudArrivee = new NoeudModele(new Point(a.x, a.y));
					this.noeudDepart = new NoeudModele(new Point(d.x + entiteDepart.getWidth() - 4, d.y + entiteDepart.getHeight() - 4));
				}

				if(d.y > a.y+entiteArrive.getHeight()) { //10
					this.noeudDepart = new NoeudModele(new Point(d.x + entiteDepart.getWidth(), d.y));
					this.noeudArrivee = new NoeudModele(new Point(a.x, a.y+entiteArrive.getHeight()));
				}

				if(a.y > d.y && a.y < d.y + entiteDepart.getHeight()) { // 12
					this.noeudArrivee = new NoeudModele(new Point(a.x, a.y-3));
					this.noeudDepart = new NoeudModele(new Point(d.x + entiteDepart.getWidth()-3, a.y -3));
				}

				if(a.y + entiteArrive.getHeight() > d.y && a.y + entiteArrive.getHeight() < d.y + entiteDepart.getHeight()) { //11
					this.noeudDepart = new NoeudModele(new Point(d.x + entiteDepart.getWidth()-3, d.y));
					this.noeudArrivee = new NoeudModele(new Point(a.x, d.y));
				}
			}


			if(d.x > a.x + entiteArrive.getWidth()) { //D a droite A a gauche
				if(d.y > a.y + entiteArrive.getHeight()) { //7
					this.noeudArrivee = new NoeudModele(new Point(a.x + entiteArrive.getWidth(), a.y + entiteArrive.getHeight()));
					this.noeudDepart = new NoeudModele(new Point(d.x, d.y));
				}

				if(a.y > d.y+entiteDepart.getHeight()) { //4
					this.noeudArrivee = new NoeudModele(new Point(a.x+entiteArrive.getWidth(), a.y));
					this.noeudDepart = new NoeudModele(new Point(d.x, d.y + entiteDepart.getHeight()));
				}

				if(d.y > a.y && d.y < a.y + entiteArrive.getHeight()) { // 6
					this.noeudArrivee = new NoeudModele(new Point(a.x + entiteArrive.getWidth(), d.y));
					this.noeudDepart = new NoeudModele(new Point(d.x, d.y));
				}

				if(d.y + entiteArrive.getHeight() > a.y && d.y + entiteArrive.getHeight() < a.y + entiteDepart.getHeight()) { //5
					this.noeudDepart = new NoeudModele(new Point(a.x + entiteDepart.getWidth(), a.y));
					this.noeudArrivee = new NoeudModele(new Point(d.x, a.y));
				}
			}
			
			if(a.x>d.x && a.x < d.x + entiteDepart.getWidth()) {
				if(a.y + entiteArrive.getHeight() < d.y) {
					this.noeudArrivee = new NoeudModele(new Point(a.x, a.y + entiteArrive.getHeight()));
					this.noeudDepart = new NoeudModele(new Point(a.x, d.y));
				}
				
				if(a.y > d.y + entiteDepart.getHeight()) {
					this.noeudDepart = new NoeudModele(new Point(a.x, d.y + entiteDepart.getHeight()));
					this.noeudArrivee = new NoeudModele(new Point(a.x, a.y));
				}
			}
			
			if(d.x>a.x && d.x < a.x + entiteArrive.getWidth()) {
				if(d.y + entiteDepart.getHeight() < a.y) {
					this.noeudDepart = new NoeudModele(new Point(d.x, d.y + entiteDepart.getHeight()));
					this.noeudArrivee = new NoeudModele(new Point(d.x, a.y));
				}
				
				if(d.y > a.y + entiteArrive.getHeight()) {
					this.noeudArrivee = new NoeudModele(new Point(d.x, a.y + entiteArrive.getHeight()));
					this.noeudDepart = new NoeudModele(new Point(d.x, d.y));
				}
			}

		} else {
			this.noeudDepart = new NoeudModele(entiteDepart.getCenterPoint());
			this.noeudArrivee = new NoeudModele(entiteArrive.getCenterPoint());
		}



	}


	public NoeudModele getNoeudArrivee() {
		calculer();
		return this.noeudArrivee;
	}

	public LinkedList<NoeudModele> getNoeuds() {
		return this.noeuds;
	}

	public void notifier() {
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		return "Relation entre " + entiteDepart + " et " + entiteArrive;
	}

	public abstract boolean isGeneralisation();
	public abstract boolean isRealisation();
	public abstract boolean isAggregation();
	public abstract boolean isComposition();
	public abstract boolean isAssociation();
	public abstract boolean isDirectAssociation();
	public abstract boolean isDependance();














}
