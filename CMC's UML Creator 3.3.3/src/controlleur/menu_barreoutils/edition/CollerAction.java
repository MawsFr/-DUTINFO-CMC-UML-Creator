package controlleur.menu_barreoutils.edition;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class CollerAction extends AbstractMenuAction {

	public CollerAction(EditeurUmlModele modele) {
		super(modele, "Coller", "paste_edit.gif", "Coller ce qu'il y a dans le presse-papier", KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Collage !");

	}
}
