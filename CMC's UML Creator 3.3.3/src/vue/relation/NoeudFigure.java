package vue.relation;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import modele.relation.NoeudModele;
import vue.CMCUmlView;
import vue.editeur.feuille.FeuilleDessin;
import vue.relation.popup.NoeudPopupMenu;

public class NoeudFigure extends JPanel implements Observer {

	private FeuilleDessin feuille;
	private NoeudModele noeud;
	private RelationFigure relation;

	private Point pointDepart;

	public NoeudFigure(FeuilleDessin feuille, RelationFigure relation,
			NoeudModele noeud) {
		this.relation = relation;
		this.noeud = noeud;
		this.feuille = feuille;
		this.noeud.addObserver(this);

		Border b = new LineBorder(Color.BLACK);
		this.setBorder(b);
		setOpaque(true);
		setBackground(Color.LIGHT_GRAY);
		this.setSize(noeud.getTaille(), noeud.getTaille());
		this.setLocation(noeud.getPoint());
		this.addMouseListener(m);
		this.addMouseMotionListener(m);
		setFocusable(true);

	}

	public NoeudModele getNoeudModele() {
		return noeud;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.setLocation(noeud.getPoint());

	}

	private MouseAdapter m = new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent e) {

			if (SwingUtilities.isLeftMouseButton(e)) {
				noeud.setDragged(true);
				pointDepart = e.getPoint();
			}
			afficherPopupMenu(e);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (noeud.isDragged()) {
				noeud.setPoint(new Point(getX() + e.getX() - pointDepart.x,
						getY() + e.getY() - pointDepart.y));
				feuille.repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (noeud.isDragged()) {
				noeud.setDragged(false);
			}
			afficherPopupMenu(e);

			if (e.getButton() == MouseEvent.BUTTON2) {
				relation.getRelation().supprimerNoeud(noeud);
				feuille.repaint();
			}

		}

		private void afficherPopupMenu(MouseEvent e) {
			if (e.isPopupTrigger()) {
				NoeudPopupMenu menu = new NoeudPopupMenu(
						(NoeudFigure) e.getComponent());
				menu.show(e.getComponent(), e.getX(), e.getY());

			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
			CMCUmlView.getInstance().getBarreStatut().afficherStatut("Déplacez le noeud");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			CMCUmlView.getInstance().getBarreStatut().afficherStatut(" ");
		}
	};

	public RelationFigure getRelation() {
		return relation;
	}

}
