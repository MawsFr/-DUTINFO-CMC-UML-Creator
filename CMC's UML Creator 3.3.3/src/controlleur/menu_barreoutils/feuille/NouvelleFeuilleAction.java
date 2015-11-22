package controlleur.menu_barreoutils.feuille;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import vue.CMCUmlView;
import vue.editeur.Editeur;
import modele.EditeurUmlModele;
import modele.FeuilleDessinModele;
import controlleur.AbstractMenuAction;

public class NouvelleFeuilleAction extends AbstractMenuAction{
	
	public NouvelleFeuilleAction(EditeurUmlModele modele) {
		super(modele, "Nouvelle Feuille", "editconfig.gif", "Ajouter une nouvelle feuille au projet courant", KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			modele.getWorkspace().getProjetSelectionne().ajouterFeuille(new FeuilleDessinModele("Feuille "+ FeuilleDessinModele.getNbFeuilles()));
		}catch(Exception ex) {
			CMCUmlView.getInstance().getBarreStatut().afficherErreur(ex.getMessage());
		}
		
		
	}

}
