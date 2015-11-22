package vue.entites;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.naming.OperationNotSupportedException;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;

import modele.FeuilleMode;
import modele.entites.EntiteModele;
import modele.entites.caracteristiques.attribut.AttributEntite;
import modele.entites.caracteristiques.methode.MethodeEntite;
import modele.entites.caracteristiques.methode.ParametreMethode;
import modele.exceptions.AlreadyExistException;
import vue.CMCUmlView;
import vue.editeur.feuille.FeuilleDessin;
import vue.entites.propriete.PopUpEntiteFigure;
import vue.entites.propriete.ProprieteEntite;
import controlleur.entites.EntiteFocusListener;

public abstract class EntiteFigure extends JPanel implements Observer {

	protected FeuilleDessin feuille;
	protected EntiteModele entite;
	protected ProprieteEntite fenetreProprietes;

	// apparence
	protected Color couleurDegrade1;
	protected Color couleurDegrade2;
	protected int espaceLigneOblique;
	protected int largeurBordure;
	protected int espaceBordure;
	protected int espaceEnPlus;
	protected Graphics2D g2d;
	protected Stroke s;

	protected Color couleurPolice;
	protected Font font;
	protected FontMetrics fm;
	protected Rectangle r;
	protected boolean minimisee;

	protected int hauteurFont;
	protected int longueur;
	protected int largeur;
	protected int paintPosition;

	protected int espaceCaracteristique;

	// Gestion deplacement
	protected Point depart;
	//	protected Rectangle rectDeplacement;

	public EntiteFigure(FeuilleDessin feuilleDessin, EntiteModele entite) {
		this.feuille = feuilleDessin;


		this.entite = entite;
		this.entite.addObserver(this);
		//		this.setDoubleBuffered(true);

		this.couleurDegrade1 = new Color(253, 253, 250);
		this.couleurDegrade2 = new Color(241, 231, 196);
		this.espaceLigneOblique = 20;
		this.largeurBordure = 1;
		this.espaceBordure = 10;
		espaceEnPlus = 3;

		this.couleurPolice = Color.BLACK;
		this.espaceCaracteristique = 4;
		this.setLocation(entite.getPosition());
		this.setSize(entite.getTaille());
		this.setFocusable(true);




		this.addMouseListener(this.deplacementListner);
		this.addMouseMotionListener(this.deplacementListner);
//		this.addKeyListener(keyEvents);
		this.addFocusListener(new EntiteFocusListener(feuille, this));

	}

	protected void init() {
		try {
			this.entite.ajouterAttribut(new AttributEntite("String", "nom"));
			this.entite.ajouterAttribut(new AttributEntite("sdf", "erez"));
			this.entite.ajouterAttribut(new AttributEntite("hgk",
					"zeahhhhhhddddd"));
			this.entite.ajouterAttribut(new AttributEntite("cvn",
					"zaerazezraze"));
			this.entite.ajouterAttribut(new AttributEntite("wxcb", "zzz"));
			this.entite.ajouterAttribut(new AttributEntite("fdshgsd", "zer"));
			this.entite.ajouterAttribut(new AttributEntite("dfhdsfh", "z"));
			this.entite.ajouterAttribut(new AttributEntite("sdfhdh", "zerzewwwwwr"));
			this.entite.ajouterAttribut(new AttributEntite("sdfhsd",
					"zerzerzer"));
			this.entite
			.ajouterAttribut(new AttributEntite("sdfh", "zerzerzerz"));

			MethodeEntite m = new MethodeEntite("void", "manger");
			m.ajouterParametre(new ParametreMethode("int", "nombre"));
			m.ajouterParametre(new ParametreMethode("String", "fruit"));
			this.entite.ajouterMethode(m);

			MethodeEntite m2 = new MethodeEntite("void", "boire");
			m2.ajouterParametre(new ParametreMethode("int", "litre"));
			m2.ajouterParametre(new ParametreMethode("String", "jmblc"));
			this.entite.ajouterMethode(m2);

		} catch (AlreadyExistException e) {
			// TODO JDialog ici
			System.out.println(e.getMessage());
		}

		// actualiserInfos();
	}

	// protected Dimension calculerTaille() {
	//
	// Dimension dimAttributs = attributsPanel.getTaille();
	// Dimension dimMethodes = methodesPanel.getTaille();
	// System.out.println(dimAttributs);
	// int longueur = (int) ((dimAttributs.getWidth() >= dimMethodes.getWidth())
	// ? dimAttributs.getWidth() : dimMethodes.getWidth());
	// int longueurNom =
	// getGraphics().getFontMetrics().stringWidth(this.nom.getText());
	//
	// if(longueurNom > longueur) {
	// longueur = longueurNom;
	// }
	//
	// int largeur = (int) (this.nom.getHeight() + dimAttributs.getHeight() +
	// dimMethodes.getHeight());
	//
	// System.out.println(longueur + " qdsfqsd " + largeur);
	//
	// return new Dimension(longueur, largeur);
	//
	// }

	@Override
	public void update(Observable o, Object arg) {


		this.setLocation(this.entite.getPosition());

		repaint();
		//		revalidate();


	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g2d = (Graphics2D) g;
		s = g2d.getStroke();
		font = g.getFont();
		calculerTaille(g);
		this.setSize(entite.getTaille());
		// PAINT DU FOND

		GradientPaint gp = new GradientPaint(getWidth() / 4, 0.0F,
				couleurDegrade1, getWidth() * 3 / 4, getHeight(),
				couleurDegrade2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		g2d.setPaint(Color.LIGHT_GRAY);

		Stroke s = g2d.getStroke();
		g2d.setStroke(new BasicStroke(2f));
		for (int i = espaceLigneOblique; i <= (getHeight() * 2)
				+ espaceLigneOblique * 2; i += espaceLigneOblique) {
			g2d.draw(new Line2D.Double(0, i, i, 0));
		}

		g2d.setStroke(s);

		// PAINT DU NOM DE L'ENTITE
		paintNom(g2d);

		if(!minimisee) {
			// PAINT DES ATTRIBUTS
			paintAttributs(g2d);

			// PAINT DES METHODES
			paintMethodes(g2d);
		}

	}



	protected void calculerTaille(Graphics g) {
		Font f = g.getFont().deriveFont(Font.BOLD);
		g.setFont(f);
		this.fm = g.getFontMetrics();
		this.hauteurFont = fm.getHeight();
		largeur = 4;
		longueur = fm.stringWidth(entite.toStringUml());
		if(!minimisee) {

			for(Iterator<AttributEntite> it = entite.getAttributs().iterator(); it.hasNext();) {
				longueur = Math.max(fm.stringWidth(it.next().toStringUML()), longueur);
				largeur++;

			}

			for(Iterator<MethodeEntite> it = entite.getMethodes().iterator(); it.hasNext();) {
				longueur = Math.max(fm.stringWidth(it.next().toStringUML()), longueur);
				largeur++;
			}

		}
		if(!minimisee) {
			this.entite.setTaille(new Dimension(longueur  + (espaceEnPlus*2), (largeur * hauteurFont)));
		} else {
			this.entite.setTaille(new Dimension(longueur + (espaceEnPlus*2), ((largeur - 1) * hauteurFont)));
		}

	}

	@Override
	protected void paintBorder(Graphics g) {
		
		g2d.setStroke(new BasicStroke(2f));
		g2d.setColor(Color.GRAY);
		largeurBordure = 1;

		if (entite.estSelectionee()) {
			largeurBordure = 2;
			g2d.setColor(Color.BLACK);
		}

		if(largeurBordure == 1) {
			g2d.setStroke(new BasicStroke(1f));
			g2d.drawRect(0, 0, entite.getWidth() - 1, entite.getHeight() - 1);
		}

		if(largeurBordure == 2) {
			g2d.setStroke(new BasicStroke(3f));
			g2d.drawRect(0, 0, entite.getWidth() - 1, entite.getHeight() - 1);
		}



	}
	protected void paintNom(Graphics2D g) {
		g.setColor(couleurDegrade2);
		paintPosition = hauteurFont * 3 ;
		r = new Rectangle(0, 0, getWidth(), paintPosition);

		g.fill(r);

		g.setColor(Color.GRAY);
		g2d.setStroke(new BasicStroke(3f));
		g.drawLine(0, r.height, r.width, r.height);
		g2d.setStroke(s);
		//		g.drawLine(0, r.height - 1, r.width, r.height - 1);
		//		g.drawLine(0, r.height - 2, r.width, r.height - 2);


		g.setColor(Color.BLACK);

		int tailleNom = fm.stringWidth(entite.toStringUml());

		Font f = g.getFont().deriveFont(Font.BOLD);
		g.setFont(f);

		g.drawString(entite.toStringUml(),
				((entite.getWidth()/2) - (tailleNom/2)), (r.height / 2) + (hauteurFont /2));

	}

	protected void paintAttributs(Graphics2D g) {
		paintPosition += hauteurFont;
		g.setFont(font);

		for (Iterator<AttributEntite> it = entite.getAttributs().iterator(); it
				.hasNext();) {
			g.drawString(it.next().toStringUML(), espaceBordure/2, paintPosition);
			paintPosition += hauteurFont;
		}
		paintPosition -= hauteurFont / 2;
		g.drawLine(0, paintPosition, getWidth(), paintPosition);
		g.drawLine(0, paintPosition + 1, getWidth(), paintPosition + 1);
	}

	protected void paintMethodes(Graphics g) {
		paintPosition += hauteurFont;

		g.setFont(font);

		for (Iterator<MethodeEntite> it = entite.getMethodes().iterator(); it
				.hasNext();) {
			g.drawString(it.next().toStringUML(), espaceBordure/2, paintPosition);
			paintPosition += hauteurFont;
		}

	}

	protected MouseAdapter deplacementListner = new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent e) {

			if(SwingUtilities.isLeftMouseButton(e) && !feuille.getFeuilleModele().enModeRelation()) {

				// if(modele.getMode() == EditeurUmlModele.SELECTION) { //TODO
				// on
				// enleve ca si l'on veux autoriser le deplacement pendant
				// l'édition

				requestFocus();
				// if(getCursor(). == Cursor.SE_RESIZE_CURSOR) {
				//
				// }
				entite.setEnDeplacement(true);
				depart = e.getPoint();
			}

		}

		@Override
		public void mouseDragged(MouseEvent e) {

			if (SwingUtilities.isLeftMouseButton(e) && hasFocus() && entite.estEnDeplacement()) {
				Point p = e.getPoint();
				JPanel source = (JPanel) e.getSource();

				int x = (int) ((p.getX() + (source.getX())) - depart.getX());
				int y = (int) ((p.getY() + (source.getY())) - depart.getY());

				if (x <= 0) {
					x = 0;
				}

				if (x >= feuille.getWidth() - getWidth()) {
					x = feuille.getWidth() - getWidth();
				}

				if (y <= 0) {
					y = 0;
				}

				if (y >= feuille.getHeight() - getHeight()) {
					y = feuille.getHeight() - getHeight();
				}

				entite.setPosition(new Point(x, y));

				if (getParent() != null) {

					getParent().revalidate();
					getParent().repaint();
				}

			}



		}

		@Override
		public void mouseReleased(MouseEvent e) {

			if (SwingUtilities.isLeftMouseButton(e)) {


				if(e.getClickCount() == 1) {
					if(entite.estEnDeplacement()) {
						entite.setEnDeplacement(false);
					}

					if(feuille.getFeuilleModele().enModeRelation()) {
						try {
							feuille.getFeuilleModele().getRelationACreer().ajouterEntite(entite);
							CMCUmlView.getInstance().getBarreStatut().afficherStatut("Selectionner la deuxième entité");
						} catch (IllegalArgumentException e2) {
							CMCUmlView.getInstance().getBarreStatut().afficherErreur(e2.getMessage());

						}

					}
				}
			} 

			if (SwingUtilities.isRightMouseButton(e) || e.isPopupTrigger()) {
				if(feuille.getFeuilleModele().enModeRelation()) {
					feuille.getFeuilleModele().setMode(FeuilleMode.SELECTION);
				}
				requestFocus();
				EntiteFigure ent = (EntiteFigure) e.getComponent();
				PopUpEntiteFigure p = new PopUpEntiteFigure(ent);
				p.show(ent, e.getX(), e.getY());
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				if(r.contains(e.getPoint())) {
					minimisee = !minimisee;
					repaint();
				} else {
					feuille.ouvrirFenetreProprietes((EntiteFigure) e.getComponent());
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(!feuille.getFeuilleModele().enModeRelation()) {
				CMCUmlView.getInstance().getBarreStatut().afficherStatut("Double clic ou clic droit pour éditer les propriétés");
			} else {
				CMCUmlView.getInstance().getBarreStatut().afficherStatut("Sélectionnez l'entité pour créer une relation");
			}
		}
	};

	protected KeyAdapter keyEvents = new KeyAdapter() {

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_DELETE:
				if (!entite.estEnDeplacement()) {
					try {
						feuille.getFeuilleModele().supprimerEntite(entite);
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (UnsupportedOperationException e1) {
						e1.printStackTrace();
					} catch (OperationNotSupportedException e1) {
						e1.printStackTrace();
					}
				}
				break;

			default:
				break;
			}
		}

	};

	public String toString() {
		return this.entite.toString();
	}

	public EntiteModele getEntite() {
		return entite;
	}

	public FeuilleDessin getFeuille() {
		return feuille;
	}


}
