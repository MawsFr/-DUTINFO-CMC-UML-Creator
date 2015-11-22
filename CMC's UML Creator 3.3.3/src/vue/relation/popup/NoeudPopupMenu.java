package vue.relation.popup;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import vue.relation.NoeudFigure;
import controlleur.relation.SupprimerNoeudAction;

public class NoeudPopupMenu extends JPopupMenu {
	
	private JMenuItem menuSupprimer;
	
	public NoeudPopupMenu(NoeudFigure noeud) {
		this.menuSupprimer = new JMenuItem(new SupprimerNoeudAction(noeud));
		add(menuSupprimer);
	}

}
