package controlleur.feuille;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.SwingUtilities;

import modele.FeuilleMode;
import modele.entites.ClasseModele;
import modele.entites.EntiteModele;
import modele.entites.EnumerationModele;
import modele.entites.InterfaceModele;
import modele.entites.caracteristiques.Visibilite;
import modele.relation.NoeudModele;
import modele.relation.RelationModele;
import vue.CMCUmlView;
import vue.editeur.feuille.FeuilleDessin;
import vue.editeur.feuille.NouvelleFigureFenetre;
import vue.relation.RelationFigure;
import vue.relation.popup.RelationPopupMenu;

public class FeuilleDessinListener extends MouseAdapter {

	private FeuilleDessin feuilleModele;

	public FeuilleDessinListener(FeuilleDessin feuilleModele) {
		this.feuilleModele = feuilleModele;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void mouseReleased(MouseEvent e) {

		if(SwingUtilities.isLeftMouseButton(e)) {
			if (((feuilleModele.getFeuilleModele().enModeRelation() && !feuilleModele.getFeuilleModele().getRelationACreer().departExiste()) || !feuilleModele.getFeuilleModele().enModeRelation()) && e.getClickCount() == 2) {
				List l = feuilleModele.relationTouchee(e.getPoint());
				RelationFigure relation = null;
				NoeudModele noeud = null;
				if (l != null && l.size() == 2) {
					relation = (RelationFigure) l.get(0);
					noeud = (NoeudModele) l.get(1);
				}

				if (relation != null && noeud != null) {
					Point p = new Point(e.getPoint().x - (noeud.getTaille() /2), e.getPoint().y - (noeud.getTaille() /2));
					relation.getRelation().ajouterNoeud(new NoeudModele(p), noeud);
					feuilleModele.repaint();
				}

			} else {
				feuilleModele.getFeuilleModele().setMode(
						feuilleModele.getFeuilleModele().getMode());
			}
		}

		if(SwingUtilities.isRightMouseButton(e)) {
			if((feuilleModele.getFeuilleModele().enModeRelation()
					&& !feuilleModele.getFeuilleModele().getRelationACreer()
					.departExiste()) || !feuilleModele.getFeuilleModele().enModeRelation() && e.getClickCount() == 1) {
				List l = feuilleModele.relationTouchee(e.getPoint());
				RelationFigure relation = null;
				if (l != null && l.size() == 2) {
					relation = (RelationFigure) l.get(0);
				}

				if (relation != null) {
					RelationPopupMenu popup = new RelationPopupMenu(relation);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}

			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		List l = feuilleModele.relationTouchee(e.getPoint());
		RelationFigure relation = null;
		if (l != null && l.size() == 2) {
			relation = (RelationFigure) l.get(0);
		}

		if (feuilleModele.getFeuilleModele().enModeRelation()
				&& feuilleModele.getFeuilleModele().getRelationACreer()
				.departExiste()) {
			feuilleModele.repaint();
			CMCUmlView.getInstance().getBarreStatut().afficherStatut("Selectionner la deuxième entité");
		} else if (relation != null) {
			e.getComponent().setCursor(
					Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			CMCUmlView.getInstance().getBarreStatut().afficherStatut("Double cliquez pour créer un noeud");
		} else {
			e.getComponent().setCursor(Cursor.getDefaultCursor());
			CMCUmlView.getInstance().getBarreStatut().afficherStatut(" ");
		}

	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e) && !feuilleModele.getFeuilleModele().enModeRelation() && feuilleModele.getFeuilleModele().getMode() != FeuilleMode.SELECTION
				&& feuilleModele.relationTouchee(e.getPoint()) == null) {
			new NouvelleFigureFenetre(feuilleModele, e.getPoint());

		} else {
			System.out.println("Rien ajouté");
		}
	}

}
