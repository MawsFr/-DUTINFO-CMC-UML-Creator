package controlleur.menu_barreoutils.edition;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class RefaireAction extends AbstractMenuAction {

	public RefaireAction(EditeurUmlModele modele) {
		super(modele, "Refaire", "redo_edit.gif", "Refaire la dernière action", KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Refairation ?!");

	}
}
