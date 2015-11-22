package vue.entites.propriete.listModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import vue.entites.EntiteFigure;
import modele.entites.EntiteModele;
import modele.entites.caracteristiques.attribut.AttributEntite;
import modele.entites.caracteristiques.methode.MethodeEntite;

public class AttributsListModel extends AbstractListModel<AttributEntite> {
	
	List<AttributEntite> copieListeAttributs;
	
	public AttributsListModel(EntiteFigure entite){
		this.copieListeAttributs = new ArrayList<AttributEntite>(entite.getEntite().getAttributs());
	}

	@Override
	public AttributEntite getElementAt(int i) {
		return this.copieListeAttributs.get(i);
		}
	

	@Override
	public int getSize() {
		return this.copieListeAttributs.size();
	}
	
	public void ajouterAttributTemporaire(AttributEntite attribut){
		this.copieListeAttributs.add(attribut);
		fireContentsChanged(this, 0, getSize());
	}
	//ca peux pas etre le observer non? nope puis la liste est la "copieListe"okk
	public List<AttributEntite> getListeCopie(){
		return copieListeAttributs;
		
	}
	
	public void supprimerAttributTemporaire(AttributEntite attributASupprimer) {
		
		this.copieListeAttributs.remove(attributASupprimer);
		fireContentsChanged(this, 0, getSize());
		
	}
	
}