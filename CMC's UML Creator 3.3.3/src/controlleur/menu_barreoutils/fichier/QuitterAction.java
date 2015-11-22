package controlleur.menu_barreoutils.fichier;

import java.awt.event.ActionEvent;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class QuitterAction extends AbstractMenuAction{
	
	public QuitterAction(EditeurUmlModele modele) {
		super(modele, "Quitter", "stop.gif", "Quitter le programme");
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
