package controlleur.relation;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import vue.relation.NoeudFigure;

public class SupprimerNoeudAction extends AbstractAction {
	private NoeudFigure noeud;
	
	public SupprimerNoeudAction(NoeudFigure noeud) {
		super("Supprimer le noeud");
		this.noeud = noeud;
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		noeud.getRelation().getRelation().supprimerNoeud(noeud.getNoeudModele());
		
	}

}
