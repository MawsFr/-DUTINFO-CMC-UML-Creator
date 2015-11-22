package controlleur.menu_barreoutils.affichage;

import java.awt.event.ActionEvent;

import javax.swing.JCheckBoxMenuItem;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;
import vue.CMCUmlView;

public class AfficherBarreOutilsAction extends AbstractMenuAction {
	
	public AfficherBarreOutilsAction(EditeurUmlModele modele) {
		super(modele, "Barre d'outils", "Afficher/Cacher la barre d'outils");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JCheckBoxMenuItem)e.getSource()).isSelected()) {
            CMCUmlView.getInstance().getBarreOutils().setVisible(true);
        } else {
        	CMCUmlView.getInstance().getBarreOutils().setVisible(false);
        }
	}

}
