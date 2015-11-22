package controlleur.menu_barreoutils.feuille;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class AjoutPaquetageAction extends AbstractMenuAction {

	public AjoutPaquetageAction(EditeurUmlModele modele) {
		super(modele, "Ajouter Paquetage", "newpack_wiz.gif", "Ajouter un nouveau paquetage dans le projet en cours", KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Ajout d'un paquetage !");

	}
}
