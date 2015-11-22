package controlleur.entites;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import vue.entites.EntiteFigure;
import vue.entites.propriete.ProprieteEntite;
import modele.EditeurUmlModele;
import modele.entites.EntiteModele;
import controlleur.AbstractMenuAction;




public class AjouterMethodeAction extends AbstractAction {
	

	private EntiteFigure entite;
	
	public AjouterMethodeAction(EntiteFigure entite2) {
		super("Ajouter methodes");
		this.entite=entite2;
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		entite.getFeuille().ouvrirFenetreProprietes(entite).selectionnerOngletMethodes();
		
		
	}

}

