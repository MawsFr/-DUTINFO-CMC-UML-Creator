package controlleur.relation;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import vue.relation.RelationFigure;

public class SupprimerRelationAction extends AbstractAction{

	private RelationFigure relation;
	
	public SupprimerRelationAction(RelationFigure relation) {
		super("Supprimer la relation");
		this.relation = relation;
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		relation.getFeuille().getFeuilleModele().supprimerRelation(relation.getRelation());
	}

}
