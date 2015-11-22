package controlleur.menu_barreoutils.feuille;

import java.awt.event.ActionEvent;

import javax.naming.OperationNotSupportedException;

import modele.EditeurUmlModele;
import controlleur.AbstractMenuAction;


public class ViderFeuilleAction extends AbstractMenuAction {
	
	
	public ViderFeuilleAction(EditeurUmlModele modele) {
		super(modele, "Vider la Feuille", "Delete16.gif", "Supprime toute les entités et relations");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.modele.getFeuilleSelectionnee().viderFeuille();
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
