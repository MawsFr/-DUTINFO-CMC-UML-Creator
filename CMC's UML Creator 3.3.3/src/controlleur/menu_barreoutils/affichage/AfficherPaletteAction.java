package controlleur.menu_barreoutils.affichage;

import java.awt.event.ActionEvent;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class AfficherPaletteAction extends AbstractMenuAction {

	
	public AfficherPaletteAction(EditeurUmlModele modele) {
		super(modele, "Palette", "palette.png", "Afficher la palette Entites/Relations");
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Palette sauvage apparait !");
	}
	
}
