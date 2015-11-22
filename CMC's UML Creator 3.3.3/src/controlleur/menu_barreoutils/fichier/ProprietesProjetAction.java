package controlleur.menu_barreoutils.fichier;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class ProprietesProjetAction extends AbstractMenuAction {

	public ProprietesProjetAction(EditeurUmlModele modele) {
		super(modele, "Propriétés", "properties_view.gif", "Afficher les propriétés du projet", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_MASK));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Propriétés du projet !");
	}

}
