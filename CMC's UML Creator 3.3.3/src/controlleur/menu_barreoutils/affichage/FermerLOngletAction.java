package controlleur.menu_barreoutils.affichage;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class FermerLOngletAction extends AbstractMenuAction {

	public FermerLOngletAction(EditeurUmlModele modele) {
		super(modele, "Fermer l'onglet", "rem_co.gif", "Fermer l'onglet visible", KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Fermer juste cet onglet !");

	}
}
