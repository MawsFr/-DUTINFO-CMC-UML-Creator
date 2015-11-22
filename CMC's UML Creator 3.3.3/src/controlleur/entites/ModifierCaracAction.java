package controlleur.entites;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import modele.entites.caracteristiques.CaracteristiqueAccessible;
import modele.entites.caracteristiques.attribut.AttributEntite;
import modele.entites.caracteristiques.methode.MethodeEntite;
import vue.entites.EntiteFigure;
import vue.entites.propriete.AjoutModifMethodes;
import vue.entites.propriete.AjouterModifAttribut;
import vue.entites.propriete.ProprieteEntite;
import vue.fonctions.BoiteFonctionsVue;

public class ModifierCaracAction extends AbstractAction {

	private CaracteristiqueAccessible carac;
	private ProprieteEntite prop;
	
	public ModifierCaracAction(ProprieteEntite proprieteEntite, String actionCommand) {
		super("+", BoiteFonctionsVue.getIcone("correction_add.gif", "Ajouter..."));
		this.prop = proprieteEntite;
		putValue(NAME, BoiteFonctionsVue.verifierIcone((ImageIcon) getValue(SMALL_ICON), "+"));
		putValue(ACTION_COMMAND_KEY, actionCommand);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getActionCommand().equals("ATTRIBUT")) {
			AttributEntite attribut = this.prop.getOngletAttributs().getListAttribut().getElementAt(prop.getOngletAttributs().getListeAttributs().getSelectedIndex());
			if(attribut != null) {
				AjouterModifAttribut fenetre = new AjouterModifAttribut(prop.getOngletAttributs().getEntite(), attribut);
				fenetre.setVisible(true);
			}
			
		}

		if(arg0.getActionCommand().equals("METHODE")) {
			MethodeEntite methode = this.prop.getOngletMethodes().getListModel().getElementAt(prop.getOngletMethodes().getListeMethodes().getSelectedIndex());
			if(methode != null) {
				AjoutModifMethodes fenetre = new AjoutModifMethodes(prop.getOngletAttributs().getEntite(), methode);
				fenetre.setVisible(true);
			}
		}

		if(arg0.getActionCommand().equals("LITTERAL")) {

		}
	}
}