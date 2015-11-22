/**
 * 
 * Maws
 * 
 * 
 */

package vue.editeur.feuille;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.Set;

import javax.swing.JLayeredPane;

import modele.EditeurUmlModele;
import modele.FeuilleDessinModele;
import modele.entites.EntiteModele;
import modele.relation.NoeudModele;
import modele.relation.RelationModele;
import vue.CMCUmlView;
import vue.entites.ClasseFigure;
import vue.entites.EntiteFigure;
import vue.entites.EnumerationFigure;
import vue.entites.InterfaceFigure;
import vue.entites.propriete.ProprieteEntite;
import vue.relation.AggregationFigure;
import vue.relation.AssociationFigure;
import vue.relation.CompositionFigure;
import vue.relation.DependanceFigure;
import vue.relation.DirectAssociationFigure;
import vue.relation.GeneralisationFigure;
import vue.relation.NoeudFigure;
import vue.relation.RealisationFigure;
import vue.relation.RelationFigure;
import controlleur.feuille.FeuilleDessinListener;

public class FeuilleDessin extends JLayeredPane implements Observer {

	private EditeurUmlModele modele;
	private FeuilleDessinModele feuilleModele;
	private Set<EntiteFigure> entiteFigures;
	private Set<RelationFigure> relationFigures;
	private Map<EntiteFigure, ProprieteEntite> fenetresProprietes; //seulement fenetres ouvertees

	private Color couleurDegrade1;
	private Color couleurDegrade2;

	public FeuilleDessin(EditeurUmlModele modele,
			FeuilleDessinModele feuilleModele) {
		this.modele = modele;
		this.feuilleModele = feuilleModele;
		this.feuilleModele.addObserver(this);
		this.entiteFigures = new HashSet<EntiteFigure>();
		this.relationFigures = new HashSet<RelationFigure>();
		this.fenetresProprietes = new HashMap<EntiteFigure, ProprieteEntite>();
		couleurDegrade1 = Color.WHITE;
		couleurDegrade2 = new Color(255, 255, 150);
		setLayout(null);

		setDoubleBuffered(true);
		setOpaque(true);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(1000, 1000));
		addMouseListener(new FeuilleDessinListener(this));
		addMouseMotionListener(new FeuilleDessinListener(this));

	}

	private void ajouterEntite(EntiteModele entite) {
		if (entite == null) {
			return;
		}

		EntiteFigure e = null;

		switch (this.feuilleModele.getMode()) {
		case CLASSE:
			e = new ClasseFigure(this, entite);
			break;
		case INTERFACE:
			e = new InterfaceFigure(this, entite);
			break;
		case ENUMERATION:
			e = new EnumerationFigure(this, entite);
			break;

		default:
			break;
		}
		if (e != null) {
			add(e);
			this.entiteFigures.add(e);
			e.requestFocus();

			CMCUmlView.getInstance().getBarreStatut().afficherStatut("Entite " + e.getEntite() + " ajoutée");
		}
	}

	private void ajouterRelation(RelationModele relation) {
		if (relation == null) {
			return;
		}

		RelationFigure rel = null;

		if (relation.isGeneralisation()) {
			rel = new GeneralisationFigure(this, relation);
		}

		if (relation.isRealisation()) {
			rel = new RealisationFigure(this, relation);
		}

		if (relation.isAggregation()) {
			rel = new AggregationFigure(this, relation);
		}

		if (relation.isComposition()) {
			rel = new CompositionFigure(this, relation);
		}

		if (relation.isAssociation()) {
			rel = new AssociationFigure(this, relation);
		}

		if (relation.isDirectAssociation()) {
			rel = new DirectAssociationFigure(this, relation);
		}

		if (relation.isDependance()) {
			rel = new DependanceFigure(this, relation);
		}

		if (rel != null) {
			this.relationFigures.add(rel);
			//			rel.getRelationModele().ajouterNoeud(
			//					new NoeudModele(new Point(100, 100)));
			// for (Iterator<NoeudFigure> it = rel.getNoeuds().iterator(); it
			// .hasNext();) {
			// NoeudFigure noeud = it.next();
			// add(noeud);
			// this.moveToFront(noeud);
			// }
			CMCUmlView.getInstance().getBarreStatut().afficherStatut("Relation " + rel.getRelation() + " ajoutée");
			revalidate();
			repaint();
		}
	}

	private EntiteFigure getEntiteFigure(EntiteModele entite) {
		EntiteFigure e = null;
		for (Iterator<EntiteFigure> it = this.entiteFigures.iterator(); it
				.hasNext();) {
			EntiteFigure entit = it.next();
			if (entit.getEntite() == entite) {
				e = entit;
				break;
			}

		}
		return e;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		if(CMCUmlView.getInstance().getEditeur().antialiashingActive()) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		Stroke stroke = g2d.getStroke();
		Paint paint = g2d.getPaint();

		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		int w = getWidth();
		int h = getHeight();
		GradientPaint gp = new GradientPaint(0, 0, couleurDegrade1, 0, h,
				couleurDegrade2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);

		g2d.setPaint(paint);

		for (Iterator<RelationFigure> it = relationFigures.iterator(); it
				.hasNext();) {
			RelationFigure rel = it.next();
			g2d.setStroke(rel.getStroke());
			NoeudModele noeudPrecedant = rel.getRelation()
					.getNoeudDepart();
			for (Iterator<NoeudFigure> it2 = rel.getNoeuds().iterator(); it2
					.hasNext();) {
				NoeudModele noeudSuivant = it2.next().getNoeudModele();
				g2d.drawLine(noeudPrecedant.getCenterX(),
						noeudPrecedant.getCenterY(), noeudSuivant.getCenterX(),
						noeudSuivant.getCenterY());
				// g.setColor(Color.WHITE);
				// g2d.fillOval(noeudPrecedant.getPoint().x,
				// noeudPrecedant.getPoint().y, 10, 10);
				// g.setColor(Color.BLACK);
				noeudPrecedant = noeudSuivant;
			}
			g2d.drawLine(noeudPrecedant.getCenterX(),
					noeudPrecedant.getCenterY(), rel.getRelation()
					.getNoeudArrivee().getCenterX(), rel
					.getRelation().getNoeudArrivee().getCenterY());
		}

		g2d.setStroke(stroke);
		if (feuilleModele.enModeRelation()
				&& feuilleModele.getRelationACreer().departExiste()) {
			g2d.drawLine(feuilleModele.getRelationACreer().getEntiteDepart()
					.getCenterX(), feuilleModele.getRelationACreer()
					.getEntiteDepart().getCenterY(), getMousePosition().x,
					getMousePosition().y);
		}

	}

	// public void selectionnerEntite(EntiteFigure entite) {
	// if (entite != null) {
	// if (this.entiteFigures.contains(entite)) {
	// moveToFront(entite);
	// entite.requestFocus();
	// revalidate();
	// repaint();
	// }
	// }
	// }

	public FeuilleDessinModele getFeuilleModele() {
		return feuilleModele;
	}

	@Override
	public void update(Observable observable, Object o) {

		CMCUmlView.getInstance().getBarreStatut().afficherMode(feuilleModele.getMode());

		// ajout des entités
		Queue<EntiteModele> pile = this.feuilleModele.getPileEntiteAjoutees();

		for (Iterator<EntiteModele> it = pile.iterator(); it.hasNext();) {
			ajouterEntite(it.next());


		}

		feuilleModele.viderPileAjoutEntite();

		// suppression des entités
		pile = feuilleModele.getPileEntiteSupprimees();

		for (Iterator<EntiteModele> it = pile.iterator(); it.hasNext();) {
			supprimerEntite(it.next());

		}

		feuilleModele.viderPileSuppressionEntite();

		Queue<RelationModele> pile2 = feuilleModele.getPileRelationAjoutes();

		for (Iterator<RelationModele> it = pile2.iterator(); it.hasNext();) {
			ajouterRelation(it.next());
		}

		feuilleModele.viderPileAjoutRelation();

		pile2 = feuilleModele.getPileRelationSupprimees();

		for (Iterator<RelationModele> it = pile2.iterator(); it.hasNext();) {
			supprimerRelation(it.next());
		}

		feuilleModele.viderPileSuppressionRelation();


		revalidate();
		repaint();

	}

	private void supprimerEntite(EntiteModele next) {
		remove(getEntiteFigure(next));
	}

	private void supprimerRelation(RelationModele next) {

		for (Iterator<RelationFigure> it = this.relationFigures.iterator(); it
				.hasNext();) {
			RelationFigure rel = it.next();
			if (rel.getRelation() == next) {
				for (Iterator<NoeudFigure> it2 = rel.getNoeuds().iterator(); it2
						.hasNext();) {
					this.remove(it2.next());
				}
				relationFigures.remove(rel);
				revalidate();
				repaint();
				break;
			}

		}
	}

	// public RelationModele relationTouchee(Point point) {
	//
	// System.out.println(point);
	//
	// for (Iterator<RelationFigure> it = this.relationFigures.iterator(); it
	// .hasNext();) {
	// RelationFigure rel = it.next();
	//
	// NoeudModele noeudPrecedant = rel.getRelationModele()
	// .getNoeudDepart();
	// for (Iterator<NoeudFigure> it2 = rel.getNoeuds().iterator(); it2
	// .hasNext();) {
	// NoeudModele noeudSuivant = it2.next().getNoeudModele();
	// if ((new Line2D.Double(noeudPrecedant.getCenterX(),
	// noeudPrecedant.getCenterY(), noeudSuivant.getCenterX(),
	// noeudSuivant.getCenterY()).contains(point.x, point.y, 8,8))) {
	// System.out.println("TOUCHEEEEEEE");
	// return rel.getRelationModele();
	// }
	// noeudPrecedant = noeudSuivant;
	// }
	// }
	//
	// return null;
	// }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List relationTouchee(Point point) {
		for (Iterator<RelationFigure> it = this.relationFigures.iterator(); it
				.hasNext();) {
			RelationFigure relation = it.next();
			NoeudModele noeudPrecedant = relation.getRelation().getNoeudDepart();
			for (Iterator<NoeudModele> it2 = relation.getRelation().getNoeuds().iterator(); it2
					.hasNext();) {
				NoeudModele noeudSuivant = it2.next();
				Line2D ligne = new Line2D.Double(noeudPrecedant.getCenterX(),
						noeudPrecedant.getCenterY(), noeudSuivant.getCenterX(),
						noeudSuivant.getCenterY());
				if (ligne.ptSegDistSq(point.x, point.y) >= 0
						&& ligne.ptSegDistSq(point.x, point.y) <= 4) {
					List l = new ArrayList();
					l.add(relation);
					l.add(noeudPrecedant);
					return l;
				}
				noeudPrecedant = noeudSuivant;
			}

			NoeudModele dernierNoeud = relation.getRelation().getNoeudArrivee();
			Line2D ligne = new Line2D.Double(noeudPrecedant.getCenterX(),
					noeudPrecedant.getCenterY(), dernierNoeud.getCenterX(),
					dernierNoeud.getCenterY());
			if (ligne.ptSegDistSq(point.x, point.y) >= 0
					&& ligne.ptSegDistSq(point.x, point.y) <= 4) {
				List l = new ArrayList();
				l.add(relation);
				l.add(noeudPrecedant);
				return l;
			}

		}
		return null;
	}

	public ProprieteEntite ouvrirFenetreProprietes(EntiteFigure entite) {
		ProprieteEntite fenetre = null;
		if(this.fenetresProprietes.containsKey(entite)) {
			fenetre = this.fenetresProprietes.get(entite);
		} else {
			fenetre = new ProprieteEntite(entite);
			this.fenetresProprietes.put(entite, fenetre);

		}

		if(fenetre != null) {
			fenetre.setVisible(true);
			fenetre.requestFocus();
		}
		return fenetre;

	}

	public void fermerFenetre(EntiteFigure entite) {
		if(this.fenetresProprietes.containsKey(entite)) {
			this.fenetresProprietes.remove(entite);
		}
	}

	public ProprieteEntite getFenetre(EntiteFigure entite) {
		if(this.fenetresProprietes.containsKey(entite)) {
			return this.fenetresProprietes.get(entite);
		}

		return null;

	}

	/**
	 * @return the modele
	 */
	public EditeurUmlModele getModele() {
		return modele;
	}

	public BufferedImage getImage() {
		int w = this.getWidth();
		int h = this.getHeight();
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		this.paint(g);
		return bi;
		
	}


}
