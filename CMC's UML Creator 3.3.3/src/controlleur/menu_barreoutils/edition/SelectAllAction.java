package controlleur.menu_barreoutils.edition;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class SelectAllAction extends AbstractMenuAction {

	public SelectAllAction(EditeurUmlModele modele) {
		super(modele, "Sélectionner tout", "Selectionner toutes les entités et relations", KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Sélection de TOUT !");

	}
}
