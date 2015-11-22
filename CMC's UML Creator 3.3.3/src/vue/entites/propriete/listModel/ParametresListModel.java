package vue.entites.propriete.listModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.AbstractListModel;

import modele.entites.caracteristiques.methode.MethodeEntite;
import modele.entites.caracteristiques.methode.ParametreMethode;

public class ParametresListModel extends AbstractListModel<ParametreMethode> {

	private MethodeEntite entiteMethode;
	private List<ParametreMethode> copieListeParametres;

	public ParametresListModel(MethodeEntite entite){
		this.entiteMethode = entite;
		copieListeParametres = new ArrayList<ParametreMethode>(entite.getParametres());
		
	}
	@Override
	public ParametreMethode getElementAt(int i) {
		return copieListeParametres.get(i);
	}

	@Override
	public int getSize() {
		return copieListeParametres.size();
	}
	
	public void ajouterParametreTemporaire(ParametreMethode parametre){
		this.copieListeParametres.add(parametre);
		fireContentsChanged(this, 0, getSize());
	}
	
	public List<ParametreMethode> getListeCopie(){
		return copieListeParametres;
	}
	
	public void supprimerTousLesParametres(){
		this.copieListeParametres.clear();
		fireContentsChanged(this, 0, getSize());
	}

	public void supprimerParametreTemporaire(ParametreMethode parametre) {
		this.copieListeParametres.remove(parametre);
		fireContentsChanged(this, 0, getSize());
	}

}