package vue.explorateur;

import javax.swing.tree.DefaultMutableTreeNode;

import modele.WorkspaceModele;

public class WorkspaceTreeNode extends DefaultMutableTreeNode {
	
	private WorkspaceModele workspaceModele;
	private ExplorateurProjetTreeModel explorateur;
	
	public WorkspaceTreeNode(WorkspaceModele modele) {
		this.workspaceModele = modele;
		this.allowsChildren = true;
		
	}

	public WorkspaceModele getWorkspaceModele() {
		return workspaceModele;
		
	}
	
	
	
	public ExplorateurProjetTreeModel getExplorateur() {
		return explorateur;
	}

	public void setExplorateur(ExplorateurProjetTreeModel explorateur) {
		this.explorateur = explorateur;
	}

	@Override
	public String toString() {
		return "Workspace";
	}

	
}
