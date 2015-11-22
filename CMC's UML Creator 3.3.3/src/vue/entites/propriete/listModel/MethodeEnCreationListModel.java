package vue.entites.propriete.listModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import modele.entites.caracteristiques.methode.MethodeEntite;
import modele.entites.caracteristiques.methode.ParametreMethode;

public class MethodeEnCreationListModel extends AbstractListModel<ParametreMethode> implements Observer{
	private MethodeEntite methodeACreer;
	
	public MethodeEnCreationListModel(MethodeEntite methodeEnCreation) {
		
		this.methodeACreer = methodeEnCreation;
		this.methodeACreer.addObserver(this);
		
	}

	@Override
	public ParametreMethode getElementAt(int i) {
		return methodeACreer.getParametres().get(i);
	}

	@Override
	public int getSize() {
		return methodeACreer.getParametres().size();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		fireContentsChanged(this, 0, getSize());
	}

	public void setListeParametre(MethodeEntite methode) {
		this.methodeACreer = methode;
		this.methodeACreer.addObserver(this);
		fireContentsChanged(this, 0, getSize());
	}

}
