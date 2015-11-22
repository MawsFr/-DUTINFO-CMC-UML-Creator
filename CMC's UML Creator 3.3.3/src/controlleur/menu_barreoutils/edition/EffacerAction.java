package controlleur.menu_barreoutils.edition;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.naming.OperationNotSupportedException;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import vue.CMCUmlView;
import vue.entites.EntiteFigure;
import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;
import modele.entites.EntiteModele;

public class EffacerAction extends AbstractMenuAction {

	public EffacerAction(EditeurUmlModele modele) {
		super(modele, "Effacer", "delete.gif", "Effacer la sélection", KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			EntiteModele ent = (EntiteModele) modele.getFeuilleSelectionnee().getEntiteSelectionnee();
			if(ent != null) {
				modele.getFeuilleSelectionnee().supprimerEntite(ent);
			}
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedOperationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (OperationNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
