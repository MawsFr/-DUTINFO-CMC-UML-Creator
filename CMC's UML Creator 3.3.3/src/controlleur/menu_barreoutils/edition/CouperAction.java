package controlleur.menu_barreoutils.edition;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class CouperAction extends AbstractMenuAction {

	public CouperAction(EditeurUmlModele modele) {
		super(modele, "Couper", "cut_edit_enabled.gif", "Couper la selection", KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Coupage !");

	}
}
