package controlleur.menu_barreoutils.feuille;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class AjoutInterfaceAction extends AbstractMenuAction {

	public AjoutInterfaceAction(EditeurUmlModele modele) {
		super(modele, "Ajouter Interface", "newint_wiz.gif", "Ajouter une nouvelle entité Interface dans la feuille en cours", KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Ajout d'une interface !");

	}
}
