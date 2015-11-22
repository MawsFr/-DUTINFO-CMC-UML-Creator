package controlleur.menu_barreoutils.edition;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class CopierAction extends AbstractMenuAction {

	public CopierAction(EditeurUmlModele modele) {
		super(modele, "Copier", "copy_edit.gif", "Copier la selection", KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Copie !");

	}
}
