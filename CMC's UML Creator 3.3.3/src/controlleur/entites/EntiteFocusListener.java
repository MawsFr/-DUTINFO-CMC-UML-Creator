package controlleur.entites;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.naming.OperationNotSupportedException;

import modele.EditeurUmlModele;
import modele.FeuilleDessinModele;
import vue.editeur.feuille.FeuilleDessin;
import vue.entites.EntiteFigure;

public class EntiteFocusListener implements FocusListener{

	private EntiteFigure entite;
	private FeuilleDessin feuille;
	
	public EntiteFocusListener(FeuilleDessin feuille, EntiteFigure entiteFigure) {
		this.feuille = feuille;
		this.entite = entiteFigure;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		try {
			feuille.getFeuilleModele().setEntiteSelectionnee(entite.getEntite());
			feuille.moveToFront(entite);
			
		} catch (OperationNotSupportedException e) {
			// TODO CATCHER SELECTION
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {
//		System.out.println(arg0.getSource() + " a perdu le focus !");
		if(feuille.getFeuilleModele().getEntiteSelectionnee() == this.entite) {
			try {
				feuille.getFeuilleModele().setEntiteSelectionnee(null);
			} catch (OperationNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
