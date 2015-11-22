package vue.explorateur;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import modele.ProjetModele;
import modele.WorkspaceModele;
import vue.editeur.Editeur;
import vue.fonctions.BoiteFonctionsVue;

public class ExplorateurProjet extends JPanel{
	
	private WorkspaceModele workspace;
	
	private JScrollPane scrollpane;
	private JTree explorateur;

	private Editeur editeur;
	
	public Editeur getEditeur() {
		return editeur;
	}

	public ExplorateurProjet(final Editeur editeur, final WorkspaceModele workspace) {
		this.editeur = editeur;
		this.workspace = workspace;
		this.explorateur = new JTree(new ExplorateurProjetTreeModel(editeur, workspace));
		explorateur.setVisibleRowCount(20);
		this.scrollpane = new JScrollPane(explorateur);
		
		explorateur.setPreferredSize(new Dimension(200, 200));
		
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setOpenIcon(BoiteFonctionsVue.getIcone("prj_obj.gif", "Projet"));
		renderer.setClosedIcon(BoiteFonctionsVue.getIcone("prj_obj.gif", "Projet"));
		renderer.setLeafIcon(BoiteFonctionsVue.getIcone("editconfig.gif", "Feuille"));
		explorateur.setCellRenderer(renderer);
		
		
		explorateur.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode d = (DefaultMutableTreeNode) explorateur.getLastSelectedPathComponent();
				if(d == null) {
					return;
				}
				
				if(d instanceof ProjetTreeNode) {
					workspace.setProjetSelectionne((((ProjetTreeNode) d).getProjetModele()));
				}
				
				if(d instanceof FeuilleDessinTreeNode) {
					ProjetTreeNode p = (ProjetTreeNode) d.getParent();
					workspace.setProjetSelectionne(p.getProjetModele());
					FeuilleDessinTreeNode f = (FeuilleDessinTreeNode) d;
					workspace.getProjetSelectionne().setFeuilleSelectionnee(f.getFeuilleModele());
					editeur.getGestionnaireFeuilles().setSelectedComponent(f.getOnglet());
				}
				
			}
		});
		
//		explorateur.addMouseListener(new MouseAdapter() {
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
//					DefaultMutableTreeNode d = (DefaultMutableTreeNode) explorateur.getLastSelectedPathComponent();
//					if(d != null && d instanceof FeuilleDessinTreeNode) {
//						FeuilleDessinTreeNode f = (FeuilleDessinTreeNode) d;
//						if(editeur.getGestionnaireFeuilles().indexOfTabComponent(f.getOnglet()) == -1) {
//							editeur.getGestionnaireFeuilles().addTab(f.getOnglet().toString(), f.getOnglet());
//						} else {
//							editeur.getGestionnaireFeuilles().setSelectedComponent(f.getOnglet());
//						}
//					}
//				}
//			}
//			
//		});
		
		add(scrollpane);
		
	}

	public WorkspaceModele getWorkspace() {
		return workspace;
	}
	
	

}
