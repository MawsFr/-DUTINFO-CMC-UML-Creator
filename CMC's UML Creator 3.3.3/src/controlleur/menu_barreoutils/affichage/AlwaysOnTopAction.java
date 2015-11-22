package controlleur.menu_barreoutils.affichage;

import java.awt.event.ActionEvent;

import javax.swing.JCheckBoxMenuItem;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;
import vue.CMCUmlView;

public class AlwaysOnTopAction extends AbstractMenuAction {

	
	public AlwaysOnTopAction(EditeurUmlModele modele) {
		super(modele, "Toujours au dessus", "Garder cette fenêtre toujours au dessus");
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JCheckBoxMenuItem) e.getSource()).isSelected()) {
            CMCUmlView.getInstance().setAlwaysOnTop(true);
        } else {
        	CMCUmlView.getInstance().setAlwaysOnTop(false);
        }
	}
	
}
