package controlleur.menu_barreoutils.fichier;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class RafraichirAction extends AbstractMenuAction {

	public RafraichirAction(EditeurUmlModele modele) {
		super(modele, "Rafraîchir", "refresh_nav.gif", "Rafraichir le projet pour afficher d'éventuelles fichiers rajouté récemment", KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Rafraichssement du projet !");
	}

}
