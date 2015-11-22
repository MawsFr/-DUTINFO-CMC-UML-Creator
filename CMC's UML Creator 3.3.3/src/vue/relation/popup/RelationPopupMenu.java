package vue.relation.popup;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import vue.relation.RelationFigure;
import controlleur.relation.SupprimerRelationAction;

public class RelationPopupMenu extends JPopupMenu{

	private JMenuItem menuSupprimer;
	
	public RelationPopupMenu(RelationFigure relation) {
		this.menuSupprimer = new JMenuItem(new SupprimerRelationAction(relation));
		add(menuSupprimer);
	}
}
