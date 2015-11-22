package controlleur.menu_barreoutils.fichier;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;
import modele.fonctions.BoiteFonctionsModele;

public class OuvrirProjetAction extends AbstractMenuAction {

	public OuvrirProjetAction(EditeurUmlModele modele) {
		super(modele, "Ouvrir Projet...", "prj_obj.gif", "Ouvrir un projet existant sur l'ordinateur", KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		//modele.setProjetSelectionne(BoiteFonctionsModele.ouvrirProjet(modele, new File("Projet Sans nom.cmcproject")));

	}

}
