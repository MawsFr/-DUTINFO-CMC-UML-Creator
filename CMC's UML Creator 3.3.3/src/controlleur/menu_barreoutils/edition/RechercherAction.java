package controlleur.menu_barreoutils.edition;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class RechercherAction extends AbstractMenuAction {

	public RechercherAction(EditeurUmlModele modele) {
		super(modele, "Rechercher", "Find16.gif", "Rechercher une entité ou une relation", KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Recherchons !");

	}
}
