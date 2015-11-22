package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;

public class WorkspaceModele extends Observable implements Serializable {

	private List<ProjetModele> projets;
	
	private ProjetModele projetSelectionne;
	private Queue<ProjetModele> pileProjetAjoutes;
	private Queue<ProjetModele> pileProjetSupprimes;
	
	
	public WorkspaceModele() {
		this(new ArrayList<ProjetModele>());
		this.pileProjetAjoutes = new LinkedList<ProjetModele>();
		this.pileProjetSupprimes = new LinkedList<ProjetModele>();
	}
	
	public WorkspaceModele(List<ProjetModele> projets) {
		this.projets = projets;
	}
	
	public void ajouterProjet(ProjetModele projet) {
		if(projet == null) {
			throw new IllegalArgumentException("Vous n'ajoutez aucun projet ! (projet == null)");
		}
		
		if(this.projets.contains(projet)) {
			throw new IllegalArgumentException("Ce projet est deja dans le workspace !");
		}
		
		if(!this.projets.add(projet) || !this.pileProjetAjoutes.add(projet)){
			throw new IllegalArgumentException("Ce projet ne peut pas etre ajouté !");
		}
		
		setProjetSelectionne(projet);
		notifier();
	}
	
	public void supprimerProjet(ProjetModele projet) {
		if(projet == null) {
			throw new IllegalArgumentException("Vous ne supprimez aucun projet ! (projet == null)");
		}
		
		if(this.projets.contains(projet)) {
			throw new IllegalArgumentException("Ce projet n'est pas dans le workspace !");
		}
		if(projetSelectionne == projet) {
			setProjetSelectionne(null);
		}
			
		if(!this.projets.add(projet) || this.pileProjetSupprimes.add(projet)){
			throw new IllegalArgumentException("Ce projet ne peut pas etre supprimé !");
		}
		
		
		notifier();
	}

	public ProjetModele getProjetSelectionne() {
		return projetSelectionne;
	}

	public void setProjetSelectionne(ProjetModele projetSelectionne) {
		this.projetSelectionne = projetSelectionne;
//		notifier();
	}
	
	private void notifier() {
		notifier(null);
	}
	
	private void notifier(Object o) {
		setChanged();
		notifyObservers();
	}

	public Queue<ProjetModele> getPileProjetAjoutes() {
		return pileProjetAjoutes;
	}

	public Queue<ProjetModele> getPileProjetSupprimes() {
		return pileProjetSupprimes;
	}
	
	public void viderPileAjoutProjets() {
		this.pileProjetAjoutes.clear();
	}
	
	public void viderPileSuppressionProjet() {
		this.pileProjetSupprimes.clear();
	}
}

