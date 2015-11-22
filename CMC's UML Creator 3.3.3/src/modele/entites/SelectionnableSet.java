package modele.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.naming.OperationNotSupportedException;

public class SelectionnableSet implements Serializable{

	private Set<EntiteSelectionnable> selection;

	public SelectionnableSet() {
		this.selection = new HashSet<EntiteSelectionnable>();
	}

	public void ajouter(EntiteSelectionnable s) throws OperationNotSupportedException, IllegalArgumentException {
		if (s == null) {
			throw new IllegalArgumentException("l'entité est null");
		}

		if (selection.contains(s)) {
			throw new IllegalArgumentException(
					"L'entité est deja contenu dans la selection");
		}
		if (s.estSelectionnable() && !s.estSelectionee()) {
			if (!selection.add(s)) {
				throw new OperationNotSupportedException(
						"L'entite n'a pa pu etre ajoutée à la selection");
			} else {
				s.setSelectionnee(true);
			}
		}
	}
	
	public void supprimer(EntiteSelectionnable s) throws OperationNotSupportedException, IllegalArgumentException {
		if (s == null) {
			throw new IllegalArgumentException("l'entité est null");
		}

		if (!selection.contains(s)) {
			throw new IllegalArgumentException("L'entité n'est pas contenu dans la selection");
		}
		
		if(s.estSelectionee() && s.estSelectionee()) {
			if(!selection.remove(s)) {
				throw new OperationNotSupportedException("Impossible de supprimer l'entité de la selection");
			} else {
				s.setSelectionnee(false);
			}
			
		}
	}
	
	public void selectionnerSeulement(EntiteSelectionnable entite) throws OperationNotSupportedException, IllegalArgumentException {
		vider();
		ajouter(entite);
	}
	
	public void vider() {
		for(EntiteSelectionnable entite : selection) {
			entite.setSelectionnee(false);
		}
		
		selection.clear();
	}
	
	public boolean estVide() {
		return this.selection.isEmpty();
	}
	
	public int getNbEntiteSelectionnee() {
		return this.selection.size();
	}

	public boolean contient(EntiteModele entite) {
		return this.selection.contains(entite);
	}
	
	

}
