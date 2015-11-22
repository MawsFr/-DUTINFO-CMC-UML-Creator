package controlleur.menu_barreoutils.affichage;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class FermerToutOngletsAction extends AbstractMenuAction {

	public FermerToutOngletsAction(EditeurUmlModele modele) {
		super(modele, "Fermer tous les onglets", "rem_all_co.gif", "Fermer tous les onglets ouverts", KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Fermer tout les onglets !");

	}
}
