package controlleur.menu_barreoutils.feuille;

import java.awt.event.ActionEvent;

import modele.EditeurUmlModele;
import controlleur.AbstractMenuAction;

public class SupprimerFeuilleAction extends AbstractMenuAction{

	
	public SupprimerFeuilleAction(EditeurUmlModele modele) {
		super(modele, "Supprimer la feuille", "delete.gif", "Supprime la feuille selectionnée");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(modele.getFeuilleSelectionnee() != null) {
			modele.getProjetSelectionne().supprimerFeuille(modele.getFeuilleSelectionnee());
		}
	}

}
