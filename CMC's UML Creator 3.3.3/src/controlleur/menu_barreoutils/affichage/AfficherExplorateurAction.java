package controlleur.menu_barreoutils.affichage;

import java.awt.event.ActionEvent;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class AfficherExplorateurAction extends AbstractMenuAction {

	
	public AfficherExplorateurAction(EditeurUmlModele modele) {
		super(modele, "Explorateur de projets", "package.gif", "Afficher l'explorateur de projets");
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Explorateur de projets sauvage apparait !");
	}
	
}
