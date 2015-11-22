package controlleur.menu_barreoutils.affichage;

import java.awt.event.ActionEvent;

import vue.CMCUmlView;
import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class AfficherProprietes extends AbstractMenuAction {

	
	public AfficherProprietes(EditeurUmlModele modele) {
		super(modele, "Propriétés", "properties_view.gif", "Afficher la fenetre de propriétés");
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Propriétés sauvages apparaissent !");

	}
	
}
