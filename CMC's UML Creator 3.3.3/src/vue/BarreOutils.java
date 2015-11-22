package vue;
/**
 * @author Maws
 * 
 * 
 * Barre d'outil en dessous du menu
 * 
 * 
 * 
 * 
 */
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import modele.EditeurUmlModele;
import vue.fonctions.BoiteFonctionsVue;
import controlleur.DescriptionBoutonAdapter;
import controlleur.menu_barreoutils.edition.AnnulerAction;
import controlleur.menu_barreoutils.edition.CollerAction;
import controlleur.menu_barreoutils.edition.CopierAction;
import controlleur.menu_barreoutils.edition.CouperAction;
import controlleur.menu_barreoutils.edition.RechercherAction;
import controlleur.menu_barreoutils.edition.RefaireAction;
import controlleur.menu_barreoutils.feuille.AjoutClasseAction;
import controlleur.menu_barreoutils.feuille.AjoutEnumerationAction;
import controlleur.menu_barreoutils.feuille.AjoutInterfaceAction;
import controlleur.menu_barreoutils.feuille.AjoutPaquetageAction;
import controlleur.menu_barreoutils.feuille.AjouterEntiteMenuDragAction;
import controlleur.menu_barreoutils.feuille.AssistantEntiteAction;
import controlleur.menu_barreoutils.feuille.NouvelleFeuilleAction;
import controlleur.menu_barreoutils.fichier.EnregistrerProjetAction;
import controlleur.menu_barreoutils.fichier.EnregistrerSousProjetAction;
import controlleur.menu_barreoutils.fichier.NouveauProjetAction;
import controlleur.menu_barreoutils.fichier.OuvrirProjetAction;

public class BarreOutils extends JToolBar {

	private EditeurUmlModele modele;

	private boolean btnClasseDragged;
	private JButton toolBtnNouvelleFeuille;
	private JButton toolBtnNouveauProjet;
	private JButton toolBtnOuvrirProjet;
	private JButton toolBtnEnregistrerProjet;
	private JButton toolBtnEnregistrerSousProjet;
	private JButton toolBtnAnnuler;
	private JButton toolBtnRefaire;
	private JButton toolBtnCouper;
	private JButton toolBtnCopier;
	private JButton toolBtnColler;
	private JButton toolBtnRechercher;
	private JButton toolBtnAjoutPaquetage;
	private JButton toolBtnAjoutClasse;
	private JPopupMenu menuEntite;
	private JMenuItem toolMenAjoutClasse;
	private JMenuItem toolMenAjoutInterface;
	private JMenuItem toolMenAjoutEnumeration;
	private JMenuItem toolMenAssistantEntite;

	public BarreOutils(EditeurUmlModele modele) {
		this.modele = modele;
		setFloatable(true);
		//		setOpaque(true);
		//		setBackground(Color.WHITE);

		toolBtnNouvelleFeuille = new JButton();
		toolBtnNouvelleFeuille.setAction(new NouvelleFeuilleAction(modele));
		toolBtnNouvelleFeuille.addMouseListener(new DescriptionBoutonAdapter());
		add(toolBtnNouvelleFeuille);

		toolBtnNouveauProjet = new JButton();
		toolBtnNouveauProjet.setAction(new NouveauProjetAction(modele));
		toolBtnNouveauProjet.addMouseListener(new DescriptionBoutonAdapter());
		add(toolBtnNouveauProjet);

		toolBtnOuvrirProjet = new JButton();
		toolBtnOuvrirProjet.setAction(new OuvrirProjetAction(modele));
		toolBtnOuvrirProjet.addMouseListener(new DescriptionBoutonAdapter());
		add(toolBtnOuvrirProjet);

		addSeparator();

		toolBtnEnregistrerProjet = new JButton();
		toolBtnEnregistrerProjet.setAction(new EnregistrerProjetAction(modele));
		toolBtnEnregistrerProjet.addMouseListener(new DescriptionBoutonAdapter());
		add(toolBtnEnregistrerProjet);

		toolBtnEnregistrerSousProjet = new JButton();
		toolBtnEnregistrerSousProjet.setAction(new EnregistrerSousProjetAction(modele));
		toolBtnEnregistrerSousProjet.addMouseListener(new DescriptionBoutonAdapter());
		add(toolBtnEnregistrerSousProjet);
		addSeparator();

		toolBtnAnnuler = new JButton();
		toolBtnAnnuler.setAction(new AnnulerAction(modele));
		toolBtnAnnuler.addMouseListener(new DescriptionBoutonAdapter());
		add(toolBtnAnnuler);

		toolBtnRefaire = new JButton();
		toolBtnRefaire.setAction(new RefaireAction(modele));
		toolBtnRefaire.addMouseListener(new DescriptionBoutonAdapter());
		add(toolBtnRefaire);

		addSeparator();

		toolBtnCouper = new JButton();
		toolBtnCouper.setAction(new CouperAction(modele));
		toolBtnCouper.addMouseListener(new DescriptionBoutonAdapter());
		add(toolBtnCouper);

		toolBtnCopier = new JButton();
		toolBtnCopier.setAction(new CopierAction(modele));
		toolBtnCopier.addMouseListener(new DescriptionBoutonAdapter());
		add(toolBtnCopier);

		toolBtnColler = new JButton();
		toolBtnColler.setAction(new CollerAction(modele));
		toolBtnColler.addMouseListener(new DescriptionBoutonAdapter());
		add(toolBtnColler);

		toolBtnRechercher = new JButton();
		toolBtnRechercher.setAction(new RechercherAction(modele));
		toolBtnRechercher.addMouseListener(new DescriptionBoutonAdapter());
		add(toolBtnRechercher);

		addSeparator();

		toolBtnAjoutPaquetage = new JButton();
		toolBtnAjoutPaquetage.setAction(new AjoutPaquetageAction(modele));
		toolBtnAjoutPaquetage.addMouseListener(new DescriptionBoutonAdapter());
		add(toolBtnAjoutPaquetage);


		menuEntite = new JPopupMenu();

		toolBtnAjoutClasse = new JButton();
		toolBtnAjoutClasse.setIcon(BoiteFonctionsVue.getIcone("newclass_wiz.gif", "Ajouter une entité"));
		toolBtnAjoutClasse.addMouseMotionListener(new AjouterEntiteMenuDragAction(modele, menuEntite, this));
		toolBtnAjoutClasse.addMouseListener(new AjouterEntiteMenuDragAction(modele, menuEntite, this));


		toolMenAjoutClasse = new JMenuItem(new AjoutClasseAction(modele));
		toolMenAjoutClasse.addMouseListener(new DescriptionBoutonAdapter());
		toolMenAjoutInterface = new JMenuItem(new AjoutInterfaceAction(modele));
		toolMenAjoutInterface.addMouseListener(new DescriptionBoutonAdapter());
		toolMenAjoutEnumeration = new JMenuItem(new AjoutEnumerationAction(modele));
		toolMenAjoutEnumeration.addMouseListener(new DescriptionBoutonAdapter());
		toolMenAssistantEntite = new JMenuItem(new AssistantEntiteAction(modele));
		toolMenAssistantEntite.addMouseListener(new DescriptionBoutonAdapter());

		menuEntite.add(toolMenAjoutClasse);
		menuEntite.add(toolMenAjoutInterface);
		menuEntite.add(toolMenAjoutEnumeration);
		menuEntite.addSeparator();
		menuEntite.add(toolMenAssistantEntite);

		add(toolBtnAjoutClasse);

		//		addHierarchyListener(new FloatingToolbarListener());





		//		toolChkEntite = new JComboBox<JButton>(entiteItems);

		//		add(toolChkEntite);




		for(Component c : getComponents()) {
			if(c instanceof JButton) {
				if(((AbstractButton) c).getIcon() != null) {
					((AbstractButton) c).setText("");
				}
			}
		}
	}

	public boolean isBtnClasseDragged() {
		return btnClasseDragged;
	}

	public void setBtnClasseDragged(boolean btnClasseDragged) {
		this.btnClasseDragged = btnClasseDragged;
	}


}
