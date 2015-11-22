package controlleur.entites;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import vue.entites.EntiteFigure;
import vue.entites.propriete.ProprieteEntite;
import modele.EditeurUmlModele;
import modele.entites.EntiteModele;
import controlleur.AbstractMenuAction;

public class AjouterAttributAction extends AbstractAction {

	private EntiteFigure entite;
	
	public AjouterAttributAction(EntiteFigure entite2) {
		super("Ajouter Attribut");
		this.entite = entite2;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		entite.getFeuille().ouvrirFenetreProprietes(entite).selectionnerOngletAttributs();
		
	}

}
