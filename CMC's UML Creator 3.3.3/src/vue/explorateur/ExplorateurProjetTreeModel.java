package vue.explorateur;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import modele.FeuilleDessinModele;
import modele.ProjetModele;
import modele.WorkspaceModele;
import vue.editeur.Editeur;
import vue.fonctions.BoiteFonctionsVue;

public class ExplorateurProjetTreeModel extends DefaultTreeModel implements Observer{

	private Editeur editeur;


	public ExplorateurProjetTreeModel(Editeur editeur, WorkspaceModele workspace) {
		
		super(new WorkspaceTreeNode(workspace));
		this.editeur = editeur;
		workspace.addObserver(this);
		WorkspaceTreeNode racine = (WorkspaceTreeNode) getRoot();
		racine.setExplorateur(this);
		
		

	}

	
	@Override
	public void update(Observable o, Object arg) {

		WorkspaceTreeNode racine = (WorkspaceTreeNode) getRoot();

		for(Iterator<ProjetModele> it = racine.getWorkspaceModele().getPileProjetAjoutes().iterator(); it.hasNext();) {
			ProjetModele projet = it.next();
			projet.addObserver(editeur.getGestionnaireFeuilles());
			ProjetTreeNode noeud = new ProjetTreeNode(editeur, projet, this);
			racine.add(noeud);

			//			int indices[] = new int[] {getIndexOfChild(racine, noeud)};
			//			ProjetTreeNode[] noeuds = new ProjetTreeNode[] {noeud};
			nodeChanged(racine);
			reload(racine);



		}

		racine.getWorkspaceModele().viderPileAjoutProjets();
		
		
		

	}




}
