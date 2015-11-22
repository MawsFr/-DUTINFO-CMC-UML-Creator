package controlleur.menu_barreoutils.feuille;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class AjoutEnumerationAction extends AbstractMenuAction {

	public AjoutEnumerationAction(EditeurUmlModele modele) {
		super(modele, "Ajouter Énumération", "newenum_wiz.gif", "Ajouter une nouvelle entité Énumération dans la feuille en cours", KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Ajout d'une énumération !");

	}
}
