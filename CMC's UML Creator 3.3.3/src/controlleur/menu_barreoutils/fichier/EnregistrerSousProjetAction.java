package controlleur.menu_barreoutils.fichier;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class EnregistrerSousProjetAction extends AbstractMenuAction {

	public EnregistrerSousProjetAction(EditeurUmlModele modele) {
		super(modele, "Enregistrer sous...", "saveas_edit.gif", "Enregistrer le projet selectionné sous un autre nom", KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Sauvegarde du projet sous !");

	}

}
