package controlleur.menu_barreoutils.affichage;

import java.awt.event.ActionEvent;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class AfficherHistoriqueAction extends AbstractMenuAction {

	
	public AfficherHistoriqueAction(EditeurUmlModele modele) {
		super(modele, "Historique", "history_list.gif", "Afficher l'historique des actions ");
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Historique apparait !");
	}
	
}
