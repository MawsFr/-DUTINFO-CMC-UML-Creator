package vue;

/**
 * 
 * @author Maws
 * 
 * Fenetre Principale
 * 
 * 
 * 
 * 
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import modele.EditeurUmlModele;
import modele.FeuilleDessinModele;
import modele.ProjetModele;
import modele.fonctions.BoiteFonctionsModele;
import vue.editeur.Editeur;
import vue.explorateur.ExplorateurProjet;
import vue.explorateur.WorkspaceTreeNode;
import vue.fonctions.BoiteFonctionsVue;

public class CMCUmlView extends JFrame implements WindowListener, WindowStateListener {

	private static CMCUmlView instance;
	
	private EditeurUmlModele modele;
	private Editeur editeur;
	private Container c;
	private MenuPrincipal menuPrincipale;
	private BarreOutils barreOutils;
	private BarreStatut barreStatut;
	private JSplitPane panelGauche;
	private JPanel panelPropriete;
	
	private ExplorateurProjet explorateurProjet;
	

	public CMCUmlView() {
		super();
		this.modele = new EditeurUmlModele();
		this.menuPrincipale = new MenuPrincipal(modele);
		this.barreOutils = new BarreOutils(modele);
		TitledBorder t = new TitledBorder("Explorateur de Projets");
		this.editeur = new Editeur(modele);
		
		this.panelGauche = new JSplitPane();
		
		this.panelGauche.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.panelGauche.setOneTouchExpandable(true);
		
		this.explorateurProjet = new ExplorateurProjet(editeur, modele.getWorkspace());
		this.explorateurProjet.setBorder(t);
		this.panelPropriete = new JPanel();
		panelPropriete.setPreferredSize(new Dimension(200,200));
		
		panelGauche.setLeftComponent(explorateurProjet);
		panelGauche.setRightComponent(panelPropriete);
		
		this.setJMenuBar(menuPrincipale);
		
		
		
		
		
//		editeur.setPreferredSize(new Dimension(500, 500));
//		palette.setPreferredSize(new Dimension(100, 100));
		
		c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		this.barreStatut = new BarreStatut();
		
		c.add(barreStatut, BorderLayout.SOUTH);
		barreStatut.afficherStatut("Bienvenue...");
		c.add(barreOutils, BorderLayout.NORTH);
		c.add(editeur, BorderLayout.CENTER);
		c.add(panelGauche, BorderLayout.WEST);
		
		initProprietes();
		
		addWindowListener(this);
		addWindowStateListener(this);
		
		setMinimumSize(new Dimension(400,400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
	}
	
	private void init() {
		explorateurProjet.getWorkspace().ajouterProjet(new ProjetModele("Projet"));
		explorateurProjet.getWorkspace().getProjetSelectionne().ajouterFeuille(new FeuilleDessinModele("Feuille "+ FeuilleDessinModele.getNbFeuilles()));
	}


	private void initProprietes() {
		
		Properties proprietes = modele.getProprietesLogiciel();
		
		setTitle(proprietes.getProperty("nom"));
		setBounds(Integer.parseInt(proprietes.getProperty("coordx")), Integer.parseInt(proprietes.getProperty("coordy")), Integer.parseInt(proprietes.getProperty("width")), Integer.parseInt(proprietes.getProperty("height")));
		setExtendedState(Integer.parseInt(proprietes.getProperty("state")));
		
	}



	public static CMCUmlView getInstance() {
		if(instance == null) {
//			synchronized (EditeurUmlView.class) {
//				if(instance == null) {
					instance = new CMCUmlView();
//				}

//			}
		}

		return instance;
	}
	
	
	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				CMCUmlView.getInstance().setVisible(true);				
			}
		});
		
	}
	
	public Editeur getEditeur() {
		return this.editeur;
	}

	public BarreOutils getBarreOutils() {
		return this.barreOutils;
	}



	public BarreStatut getBarreStatut() {
		return barreStatut;
	}

	

	
	/**
	 * @return the modele
	 */
	public EditeurUmlModele getModele() {
		return modele;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		Properties proprietes  = modele.getProprietesLogiciel();
		
		proprietes.setProperty("nom", getTitle());
		proprietes.setProperty("coordx", "" + getX());
		proprietes.setProperty("coordy", "" + getY());
		proprietes.setProperty("width", "" + getWidth());
		proprietes.setProperty("height", "" + getHeight());
		try {
			BoiteFonctionsModele.sauvegarderProprietes("logiciel", proprietes);
			BoiteFonctionsModele.sauvegarderProprietes("editeur", modele.getProprietesEditeur());
		} catch (IOException e1) {
		} catch (URISyntaxException e1) {
		}
		
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}



	@Override
	public void windowStateChanged(WindowEvent e) {
		modele.getProprietesLogiciel().setProperty("state", "" +  e.getNewState());
		
	}
}