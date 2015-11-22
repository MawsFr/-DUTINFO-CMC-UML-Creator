package vue.explorateur;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.DefaultMutableTreeNode;

import modele.FeuilleDessinModele;
import modele.ProjetModele;
import vue.CMCUmlView;
import vue.editeur.Editeur;

public class ProjetTreeNode extends DefaultMutableTreeNode implements Observer{

	private ProjetModele projetModele;
	private ExplorateurProjetTreeModel explorateur;
	private Editeur editeur;
	
	public ProjetTreeNode(Editeur editeur, ProjetModele projet, ExplorateurProjetTreeModel explorateur) {
		this.editeur = editeur;
		this.projetModele = projet;
		projetModele.addObserver(this);
		this.explorateur = explorateur;
		
	}

	public ProjetModele getProjetModele() {
		return projetModele;
	}
	
	@Override
	public String toString() {
		return this.projetModele.getNom();
	}

	@Override
	public void update(Observable o, Object arg) {
		
		WorkspaceTreeNode racine = (WorkspaceTreeNode) getRoot();
		for(Iterator<FeuilleDessinModele> it = this.projetModele.getPileFeuilleAjoutees().iterator(); it.hasNext();) {
			FeuilleDessinModele feuille = it.next();
			FeuilleDessinTreeNode noeud = new FeuilleDessinTreeNode(editeur, feuille, explorateur);
			this.add(noeud);
			
//			int indices[] = new int[] {getIndex(noeud)};
//			FeuilleDessinTreeNode[] noeuds = new FeuilleDessinTreeNode[] {noeud};
			
			explorateur.nodeChanged(this);
			explorateur.reload(this);
			
			
			
			
		}
		
		projetModele.viderPileAjoutFeuille();
		
		for(Iterator<FeuilleDessinModele> it = this.projetModele.getPileFeuilleSupprimees().iterator(); it.hasNext();) {
			FeuilleDessinModele feuille = it.next();
			FeuilleDessinTreeNode noeud = null;
			
			for(Enumeration<FeuilleDessinTreeNode> enumeration = this.children(); enumeration.hasMoreElements();) {
				FeuilleDessinTreeNode f = enumeration.nextElement();
				if(f.getFeuilleModele() == feuille) {
					editeur.getGestionnaireFeuilles().remove(f.getOnglet());
					this.remove(f);
				}
			}
			
			explorateur.nodeChanged(this);
			explorateur.reload(this);
			
			
			
			
		}
		
	}
	
	
}
