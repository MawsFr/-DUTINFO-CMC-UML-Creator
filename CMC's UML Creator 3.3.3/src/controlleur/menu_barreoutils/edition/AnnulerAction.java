package controlleur.menu_barreoutils.edition;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class AnnulerAction extends AbstractMenuAction {

	public AnnulerAction(EditeurUmlModele modele) {
		super(modele, "Annuler", "undo_edit.gif", "Annuler la dernière action", KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Annulation !");

	}
}
