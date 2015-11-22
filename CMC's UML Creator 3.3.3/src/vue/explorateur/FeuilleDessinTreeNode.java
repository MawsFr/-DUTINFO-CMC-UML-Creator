package vue.explorateur;

import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.DefaultMutableTreeNode;

import vue.editeur.Editeur;
import vue.editeur.feuille.OngletFeuilleDessin;
import modele.FeuilleDessinModele;

public class FeuilleDessinTreeNode extends DefaultMutableTreeNode implements Observer {
	
	private OngletFeuilleDessin onglet;
	private FeuilleDessinModele feuilleModele;
	private ExplorateurProjetTreeModel explorateur;
	
	public FeuilleDessinTreeNode(Editeur editeur, FeuilleDessinModele modele, ExplorateurProjetTreeModel explorateur) {
		this.feuilleModele = modele;
		this.feuilleModele.addObserver(this);
		this.explorateur = explorateur;
		
		this.onglet = editeur.getGestionnaireFeuilles().ajouterFeuille(modele);
		
	}

	
	public FeuilleDessinModele getFeuilleModele() {
		return feuilleModele;
	}
	
	@Override
	public String toString() {
		return this.feuilleModele.getNom();
	}


	@Override
	public void update(Observable o, Object arg) {
		
	}


	/**
	 * @return the onglet
	 */
	public OngletFeuilleDessin getOnglet() {
		return onglet;
	}
	

}
