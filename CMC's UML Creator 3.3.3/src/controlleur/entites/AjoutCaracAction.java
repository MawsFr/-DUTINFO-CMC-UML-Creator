package controlleur.entites;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import vue.entites.EntiteFigure;
import vue.entites.propriete.AjoutModifMethodes;
import vue.entites.propriete.AjouterModifAttribut;
import vue.fonctions.BoiteFonctionsVue;

public class AjoutCaracAction extends AbstractAction {

	private EntiteFigure entite;
	//TODO Peut etre ajouter un champs feuilleModele pour lui ajouter les carac

	public AjoutCaracAction(EntiteFigure entite2,String actionCommand) {
		super("+", BoiteFonctionsVue.getIcone("correction_add.gif", "Ajouter..."));
		this.entite = entite2;
		putValue(NAME, BoiteFonctionsVue.verifierIcone((ImageIcon) getValue(SMALL_ICON), "+"));
		putValue(ACTION_COMMAND_KEY, actionCommand);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getActionCommand().equals("ATTRIBUT")) {
			AjouterModifAttribut fenetre = new AjouterModifAttribut(entite);
			fenetre.setVisible(true);
		}

		if(arg0.getActionCommand().equals("METHODE")) {
			AjoutModifMethodes fenetre = new AjoutModifMethodes(entite);
			fenetre.setVisible(true);
		}

		if(arg0.getActionCommand().equals("LITTERAL")) {

		}
	}



}