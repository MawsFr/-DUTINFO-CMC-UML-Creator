package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;

import javax.naming.OperationNotSupportedException;

import vue.CMCUmlView;
import modele.entites.EntiteModele;
import modele.entites.EntiteSelectionnable;
import modele.relation.AggregationModele;
import modele.relation.AssociationModele;
import modele.relation.CompositionModele;
import modele.relation.DependanceModele;
import modele.relation.DirectAssociationModele;
import modele.relation.GeneralisationModele;
import modele.relation.RealisationModele;
import modele.relation.RelationModele;

public class FeuilleDessinModele extends Observable implements Serializable{
	
	
	private static int nbFeuilles;
	// TODO utiliser pile pour les fichier a ajouter

	private String nom;
	private FeuilleMode mode;

	private List<EntiteModele> entites;
	
	private Queue<EntiteModele> pileEntiteAjoutees;
	private Queue<EntiteModele> pileEntiteSupprimees;
	
	private Queue<RelationModele> pileRelationAjoutees;
	private Queue<RelationModele> pileRelationSupprimees;
	
//	private SelectionnableSet entitesSelectionnee;
	private EntiteSelectionnable entiteSelectionnee;

	private List<RelationModele> relations;
	private RelationModele relationACreer;

	public FeuilleDessinModele(String nom) {

		nbFeuilles++;
		this.nom = nom;
		this.entites = new ArrayList<EntiteModele>();
		this.relations = new ArrayList<RelationModele>();
		
		this.pileEntiteAjoutees = new LinkedList<EntiteModele>();
		this.pileEntiteSupprimees = new LinkedList<EntiteModele>();
		
		pileRelationAjoutees = new LinkedList<RelationModele>();
		pileRelationSupprimees = new LinkedList<RelationModele>();
		
		setMode(FeuilleMode.SELECTION);
//		notifier();
	}

	public void ajouterEntite(EntiteModele entite) throws IllegalArgumentException, UnsupportedOperationException, OperationNotSupportedException {
		if (entite == null) {
			throw new IllegalArgumentException("L'entite est null");
		}

		if (this.entites.contains(entite)) {
			throw new IllegalArgumentException(
					"L'entite est deja presente dans cette feuille");
		}

		if (!this.entites.add(entite) || !this.pileEntiteAjoutees.add(entite)) {
			throw new UnsupportedOperationException(
					"L'entite n'as pas pu etre ajoutée");
		}

		notifier();
	}

	public void setEntiteSelectionnee(EntiteModele entite) throws OperationNotSupportedException, IllegalArgumentException {
		if(entiteSelectionnee != null) {
			entiteSelectionnee.setSelectionnee(false);
		}
		
		this.entiteSelectionnee = entite;
		this.entiteSelectionnee.setSelectionnee(true);
		
		notifier();
	}

	public EntiteSelectionnable getEntiteSelectionnee() {
		return entiteSelectionnee;
	}

	public void supprimerEntite(EntiteModele entite) throws IllegalArgumentException, UnsupportedOperationException, OperationNotSupportedException{
		if (entite == null) {
			throw new IllegalArgumentException("L'entite est null");
		}

		if(this.entiteSelectionnee == entite) {
			entiteSelectionnee = null;
		}
		if (!this.entites.contains(entite)) {
			throw new IllegalArgumentException(
					"L'entite n'est pas presente dans cette feuille");
		}

		List<RelationModele> relationsASupprimer = new ArrayList<RelationModele>();

		for(Iterator<RelationModele> it = relations.iterator(); it.hasNext();) {
			RelationModele relation = it.next();
			if(relation.contient(entite)) {
				relationsASupprimer.add(relation);
			}
		}
		
		for(Iterator<RelationModele> it = relationsASupprimer.iterator(); it.hasNext();) {
			supprimerRelation(it.next());
		}
		
		
		
		
		if (!this.entites.remove(entite) || !this.pileEntiteSupprimees.add(entite)) {
			throw new UnsupportedOperationException(
					"L'entite n'as pas pu etre retiré");
		}

		notifier();
	}
	
	public void viderPileAjoutEntite() {
		this.pileEntiteAjoutees.clear();
	}
	
	public void viderPileSuppressionEntite() {
		this.pileEntiteSupprimees.clear();
	}
	
	public void ajouterRelation(RelationModele relation) throws IllegalArgumentException, UnsupportedOperationException {
		if(relation == null) {
			throw new IllegalArgumentException("La relation est null");
		}
		
		if(this.relations.contains(relation)) {
			throw new IllegalArgumentException("La relations existe déjà !");
		}
		
		if (!this.relations.add(relation) || !pileRelationAjoutees.add(relation)) {
			throw new UnsupportedOperationException(
					"La relation n'as pas pu etre ajoutée");
		}

		//this.relationACreer = null;
		
		CMCUmlView.getInstance().getBarreStatut().afficherStatut("RELATION CREEEEE" + relation);
		
		notifier();
		setMode(FeuilleMode.SELECTION);
	}

	public void supprimerRelation(RelationModele relation) throws IllegalArgumentException, UnsupportedOperationException {
		if(relation == null) {
			throw new IllegalArgumentException("La relation est null");
		}
		
		if(!this.relations.contains(relation)) {
			throw new IllegalArgumentException("La relations n'existe pas !");
		}
		
		if (!this.relations.remove(relation) || !pileRelationSupprimees.add(relation)) {
			throw new UnsupportedOperationException(
					"La relation n'as pas pu etre supprimée");
		}
		
		notifier();
	}
	
	public void viderFeuille() throws IllegalArgumentException, UnsupportedOperationException, OperationNotSupportedException {
		ArrayList<EntiteModele> entiteASupprimer = new ArrayList<>(entites);
		for (Iterator<EntiteModele> it = entiteASupprimer.iterator(); it
				.hasNext();) {
			supprimerEntite(it.next());
			
		}
		
		ArrayList<RelationModele> relationASupprimer = new ArrayList<>(relations);
		for (Iterator<RelationModele> it = relationASupprimer.iterator(); it
				.hasNext();) {
			supprimerRelation(it.next());
			
		}
		
		
		
		this.entiteSelectionnee = null;
		viderPileAjoutEntite();
		viderPileAjoutRelation();
		relationACreer = null;
		notifier();
		
	}
	
	public List<RelationModele> getRelationsDe(EntiteModele entite) {
		List<RelationModele> listeRelations = new ArrayList<RelationModele>();
		for(Iterator<RelationModele> it = this.relations.iterator(); it.hasNext();) {
			RelationModele relation = it.next();
			if(relation.getEntiteDepart() == entite) {
				listeRelations.add(relation);
			}
		}
		
		return listeRelations;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
		notifier();
	}

	@Override
	public String toString() {
		return nom;
	}

	public void notifier() {
		notifier(null);
	}

	public void notifier(Object o) {
		setChanged();
		notifyObservers(o);
	}

	public void setMode(FeuilleMode mode) throws IllegalArgumentException {
		this.mode = mode;
		
		switch(this.mode) {
		case GENERALISATION :
			this.relationACreer = new GeneralisationModele(this);
			break;
			
		case ASSOCIATION :
			this.relationACreer = new AssociationModele(this);
			break;
		
		case AGGREGATION :
			this.relationACreer = new AggregationModele(this);
			break;
			
		case COMPOSITION :
			this.relationACreer = new CompositionModele(this);
			break;
		case CLASSE:
			this.relationACreer = null;
			break;
		case DEPENDANCE:
			this.relationACreer = new DependanceModele(this);
			break;
		case DIRECTASSOCIATION:
			this.relationACreer = new DirectAssociationModele(this);
			break;
		case ENUMERATION:
			this.relationACreer = null;
			break;
		case INTERFACE:
			this.relationACreer = null;
			break;
		case PAQUETAGE:
			this.relationACreer = null;
			break;
		case REALISATION:
			this.relationACreer = new RealisationModele(this);
			break;
		case SELECTION:
			this.relationACreer = null;
			break;
		default:
			break;
			
		}
		
		notifier();
	}
	
	public boolean enModeRelation() {
		return (this.mode == FeuilleMode.AGGREGATION
				|| mode == FeuilleMode.ASSOCIATION
				|| mode == FeuilleMode.COMPOSITION
				|| mode == FeuilleMode.DEPENDANCE
				|| mode == FeuilleMode.DIRECTASSOCIATION
				|| mode == FeuilleMode.GENERALISATION
				|| mode == FeuilleMode.REALISATION) && this.relationACreer != null;
	}

	public RelationModele getRelationACreer() {
		return relationACreer;
	}

	public FeuilleMode getMode() {
		return mode;
	}

	public List<EntiteModele> getEntites() {
		return entites;
	}

	public Queue<EntiteModele> getPileEntiteAjoutees() {
		return pileEntiteAjoutees;
	}

	public Queue<EntiteModele> getPileEntiteSupprimees() {
		return pileEntiteSupprimees;
	}

	public List<RelationModele> getRelations() {
		return relations;
	}

	public Queue<RelationModele> getPileRelationAjoutes() {
		return this.pileRelationAjoutees;
	}
	
	public Queue<RelationModele> getPileRelationSupprimees(){
		return this.pileRelationSupprimees;
	}

	public void viderPileAjoutRelation() {
		this.pileRelationAjoutees.clear();
	}
	
	public void viderPileSuppressionRelation() {
		this.pileRelationSupprimees.clear();
	}

	/**
	 * @return the nbFeuilles
	 */
	public static int getNbFeuilles() {
		return nbFeuilles;
	}
	
	
	
	

}
