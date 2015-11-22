package controlleur.menu_barreoutils.feuille;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import vue.CMCUmlView;
import vue.editeur.Editeur;
import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;
import modele.FeuilleDessinModele;

public class AjoutClasseAction extends AbstractMenuAction {

	public AjoutClasseAction(EditeurUmlModele modele) {
		super(modele, "Ajouter Classe", "newclass_wiz.gif", "Ajouter une nouvelle entité Classe dans la feuille en cours", KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
