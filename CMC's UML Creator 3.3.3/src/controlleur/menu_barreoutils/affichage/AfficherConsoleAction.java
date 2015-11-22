package controlleur.menu_barreoutils.affichage;

import java.awt.event.ActionEvent;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class AfficherConsoleAction extends AbstractMenuAction {

	
	public AfficherConsoleAction(EditeurUmlModele modele) {
		super(modele, "Console", "console_view.gif", "Afficher la console ");
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Console sauvage apparait !");
	}
	
}
