package controlleur.menu_barreoutils.fichier;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;
import modele.fonctions.BoiteFonctionsModele;

public class EnregistrerProjetAction extends AbstractMenuAction {

	public EnregistrerProjetAction(EditeurUmlModele modele) {
		super(modele, "Enregistrer", "save_edit.gif", "Enregistrer le projet selectionné", KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			BoiteFonctionsModele.sauvegarderProjet(modele.getProjetSelectionne());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
