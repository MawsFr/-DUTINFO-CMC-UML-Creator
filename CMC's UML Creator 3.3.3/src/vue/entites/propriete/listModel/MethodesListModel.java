package vue.entites.propriete.listModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import modele.entites.caracteristiques.methode.MethodeEntite;
import vue.entites.EntiteFigure;

public class MethodesListModel extends AbstractListModel<MethodeEntite>{
	
	List<MethodeEntite> copieListeMethodes;
	
	public MethodesListModel(EntiteFigure entite) {
		this.copieListeMethodes = new ArrayList<MethodeEntite>(entite.getEntite().getMethodes());
	}

	@Override
	public MethodeEntite getElementAt(int i) {
		return this.copieListeMethodes.get(i);
	}
	
	@Override
	public int getSize() {
		return this.copieListeMethodes.size();
	}
	
	public void ajouterMethodeTemporaire(MethodeEntite methode){
		this.copieListeMethodes.add(methode);
		fireContentsChanged(this, 0, getSize());
	}
	
	public List<MethodeEntite> getListeCopie(){
		return copieListeMethodes;
		
	}

	public void supprimerMethodeTemporaire(MethodeEntite methodeASupprimer) {
		
		methodeASupprimer.getParametres().clear();
		this.copieListeMethodes.remove(methodeASupprimer);
		fireContentsChanged(this, 0, getSize());
		
	}
	

}
