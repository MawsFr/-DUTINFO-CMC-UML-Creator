package controlleur.entites;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import vue.entites.EntiteFigure;
import vue.entites.propriete.ProprieteEntite;
import modele.entites.EntiteModele;

public class AjouterProprieteAction extends AbstractAction{

	private EntiteFigure entite;
	
	public AjouterProprieteAction(EntiteFigure entite2) {
		super("Propriétés");
		this.entite = entite2;
	}

	
	public void actionPerformed(ActionEvent e) {
		entite.getFeuille().ouvrirFenetreProprietes(entite).selectionnerOngletGeneral();
	}

}


