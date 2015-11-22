package controlleur.feuille;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import modele.EditeurUmlModele;
import modele.FeuilleMode;
import modele.entites.ClasseModele;
import modele.entites.EntiteModele;
import modele.entites.caracteristiques.attribut.AttributEntite;
import modele.exceptions.AlreadyExistException;
import vue.CMCUmlView;
import controlleur.AbstractMenuAction;

public class ChangerModeAction extends AbstractMenuAction {

	public ChangerModeAction(EditeurUmlModele modele, String texte, String icone, String tooltip, KeyStroke raccourci ) {
		super(modele, texte, icone, tooltip, raccourci);

	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if(modele.getFeuilleSelectionnee() != null) {
			if(a.getActionCommand().equals("SELECTION")) {
				modele.getFeuilleSelectionnee().setMode(FeuilleMode.SELECTION);
			}

			if(a.getActionCommand().equals("CLASSE")) {
				modele.getFeuilleSelectionnee().setMode(FeuilleMode.CLASSE);
			}

			if(a.getActionCommand().equals("INTERFACE")) {
				modele.getFeuilleSelectionnee().setMode(FeuilleMode.INTERFACE);
			}

			if(a.getActionCommand().equals("ENUMERATION")) {
				modele.getFeuilleSelectionnee().setMode(FeuilleMode.ENUMERATION);
			}

			if(a.getActionCommand().equals("PAQUETAGE")) {
				modele.getFeuilleSelectionnee().setMode(FeuilleMode.PAQUETAGE);
			}

			if(a.getActionCommand().equals("ASSOCIATION")) {
				modele.getFeuilleSelectionnee().setMode(FeuilleMode.ASSOCIATION);
			}

			if(a.getActionCommand().equals("DIRECTASSOCIATION")) {
				modele.getFeuilleSelectionnee().setMode(FeuilleMode.DIRECTASSOCIATION);
			}
			if(a.getActionCommand().equals("GENERALISATION")) {
				modele.getFeuilleSelectionnee().setMode(FeuilleMode.GENERALISATION);
			}
			if(a.getActionCommand().equals("REALISATION")) {
				modele.getFeuilleSelectionnee().setMode(FeuilleMode.REALISATION);
			}

			if(a.getActionCommand().equals("AGGREGATION")) {
				modele.getFeuilleSelectionnee().setMode(FeuilleMode.AGGREGATION);
			}
			if(a.getActionCommand().equals("COMPOSITION")) {
				modele.getFeuilleSelectionnee().setMode(FeuilleMode.COMPOSITION);
			}

			if(a.getActionCommand().equals("DEPENDANCE")) {
				modele.getFeuilleSelectionnee().setMode(FeuilleMode.DEPENDANCE);
			}

		}


	}

}
