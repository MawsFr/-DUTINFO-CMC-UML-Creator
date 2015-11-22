package controlleur.feuille;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import modele.FeuilleMode;
import modele.entites.ClasseModele;
import modele.entites.EntiteModele;
import modele.entites.EnumerationModele;
import modele.entites.InterfaceModele;
import modele.entites.caracteristiques.Visibilite;
import vue.CMCUmlView;
import vue.editeur.feuille.FeuilleDessin;
import vue.editeur.feuille.NouvelleFigureFenetre;
import vue.entites.propriete.ErreurDialogue;

public class CreerFigureAction extends AbstractAction{

	private FeuilleDessin feuilleModele;
	private Point mouse;
	private NouvelleFigureFenetre fenetre;

	public CreerFigureAction(NouvelleFigureFenetre fenetre, FeuilleDessin feuille, Point mouse2) {
		this.feuilleModele = feuille;
		this.mouse = mouse2;
		this.fenetre = fenetre;

		putValue(NAME, "Créer");
		putValue(ACTION_COMMAND_KEY, "CREER");
		putValue(SHORT_DESCRIPTION, "Créér");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String nom = fenetre.getTxtNom().getText();

		if (!feuilleModele.getFeuilleModele().enModeRelation()) {
			EntiteModele entite = null;
			try {
				if (feuilleModele.getFeuilleModele().getMode() == FeuilleMode.CLASSE) {
					entite = new ClasseModele(feuilleModele.getFeuilleModele(), nom,
							Visibilite.PUBLIC, false, false, mouse);

				}

				if (feuilleModele.getFeuilleModele().getMode() == FeuilleMode.INTERFACE) {
					entite = new InterfaceModele(feuilleModele.getFeuilleModele(), nom,
							Visibilite.PUBLIC, mouse);

				}

				if (feuilleModele.getFeuilleModele().getMode() == FeuilleMode.ENUMERATION) {
					entite = new EnumerationModele(feuilleModele.getFeuilleModele(), nom,
							Visibilite.PUBLIC, mouse);

				}

				if (entite != null) {
					try {
						feuilleModele.getFeuilleModele().ajouterEntite(
								entite);

						CMCUmlView.getInstance().getBarreStatut().afficherStatut(entite + " ajouté");
					} catch (Exception ex) {
						CMCUmlView.getInstance().getBarreStatut().afficherErreur(ex.getMessage());
					}

				}

				fenetre.dispose();
				// TODO Code pour Énumération

			} catch (IllegalArgumentException ex) {
				CMCUmlView.getInstance().getBarreStatut().afficherErreur(ex.getMessage());
			}
		}




	}



}
