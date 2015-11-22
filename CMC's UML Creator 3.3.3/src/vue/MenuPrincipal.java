package vue;
/**
 * 
 * @author Maws
 * 
 * Barre de statut
 * 
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

import modele.EditeurUmlModele;
import controlleur.menu_barreoutils.GenererJavaAction;
import controlleur.menu_barreoutils.affichage.AfficherBarreOutilsAction;
import controlleur.menu_barreoutils.affichage.AfficherConsoleAction;
import controlleur.menu_barreoutils.affichage.AfficherExplorateurAction;
import controlleur.menu_barreoutils.affichage.AfficherHistoriqueAction;
import controlleur.menu_barreoutils.affichage.AfficherPaletteAction;
import controlleur.menu_barreoutils.affichage.AfficherProprietes;
import controlleur.menu_barreoutils.affichage.AlwaysOnTopAction;
import controlleur.menu_barreoutils.affichage.FermerLOngletAction;
import controlleur.menu_barreoutils.affichage.FermerToutOngletsAction;
import controlleur.menu_barreoutils.edition.AnnulerAction;
import controlleur.menu_barreoutils.edition.CollerAction;
import controlleur.menu_barreoutils.edition.CopierAction;
import controlleur.menu_barreoutils.edition.CouperAction;
import controlleur.menu_barreoutils.edition.EffacerAction;
import controlleur.menu_barreoutils.edition.RechercherAction;
import controlleur.menu_barreoutils.edition.RefaireAction;
import controlleur.menu_barreoutils.edition.SelectAllAction;
import controlleur.menu_barreoutils.feuille.AjoutClasseAction;
import controlleur.menu_barreoutils.feuille.AjoutEnumerationAction;
import controlleur.menu_barreoutils.feuille.AjoutInterfaceAction;
import controlleur.menu_barreoutils.feuille.AjoutPaquetageAction;
import controlleur.menu_barreoutils.feuille.AssistantEntiteAction;
import controlleur.menu_barreoutils.feuille.NouvelleFeuilleAction;
import controlleur.menu_barreoutils.feuille.SupprimerFeuilleAction;
import controlleur.menu_barreoutils.feuille.ViderFeuilleAction;
import controlleur.menu_barreoutils.fichier.EnregistrerProjetAction;
import controlleur.menu_barreoutils.fichier.EnregistrerSousProjetAction;
import controlleur.menu_barreoutils.fichier.ExporterProjetImageAction;
import controlleur.menu_barreoutils.fichier.NouveauProjetAction;
import controlleur.menu_barreoutils.fichier.OuvrirProjetAction;
import controlleur.menu_barreoutils.fichier.ProprietesProjetAction;
import controlleur.menu_barreoutils.fichier.QuitterAction;
import controlleur.menu_barreoutils.fichier.RafraichirAction;

public class MenuPrincipal extends JMenuBar {

	//ATTRIBUTS
	
	private EditeurUmlModele modele;
	
	private JMenuItem menuCouper;
	private JMenu jMenu10;
	private JMenuItem menuProprietesProjet;
	private JPopupMenu.Separator separateur1;
	private JPopupMenu.Separator separateur10;
	private JPopupMenu.Separator separateur11;
	private JPopupMenu.Separator separateur12;
	private JPopupMenu.Separator separateur14;
	private JPopupMenu.Separator separateur16;
	private JPopupMenu.Separator separateur17;
	private JPopupMenu.Separator separateur18;
	private JPopupMenu.Separator separateur2;
	private JPopupMenu.Separator separateur3;
	private JPopupMenu.Separator separateur4;
	private JPopupMenu.Separator separateur5;
	private JPopupMenu.Separator separateur6;
	private JPopupMenu.Separator separateur7;
	private JPopupMenu.Separator separateur8;
	private JMenuItem menuAPropos;
	private JCheckBoxMenuItem menuAffBarreOutils;
	private JMenu menuAffichage;
	private JMenuItem menuAggregation;
	private JMenu menuAide;
	private JMenu menuAjouterEntite;
	private JMenuItem menuAjouterPaquetage;
	private JCheckBoxMenuItem menuAlwaysTop;
	private JMenuItem menuAnnuler;
	private JMenuItem menuAssistantEntite;
	private JMenuItem menuAssistantRelation;
	private JMenuItem menuAssociation;
	private JMenuItem menuAssociationDirect;
	private JMenuItem menuAutreWorkspace;
	private JMenu menuChangeWorkspace;
	private JMenuItem menuClasse;
	private JMenuItem menuColler;
	private JMenuItem menuComposition;
	private JCheckBoxMenuItem menuConsole;
	private JMenuItem menuCopier;
	private JMenuItem menuDependance;
	private JMenu menuEdition;
	private JMenuItem menuEffacer;
	private JMenuItem menuEnregistrer;
	private JMenuItem menuEnregistrerSous;
	private JMenuItem menuEnumeration;
	private JCheckBoxMenuItem menuExplorateurProjet;
	private JMenu menuExporter;
	private JMenu menuExporterFeuille;
	private JMenuItem menuExporterFeuilleImage;
	private JMenuItem menuExporterFeuilleXML;
	private JMenuItem menuExporterProjetXML;
	private JMenu menuFenetres;
	private JMenuItem menuFermerAutresOnglets;
	private JMenuItem menuFermerOnglet;
	private JMenuItem menuFermerToutOnglets;
	private JMenu menuFeuille;
	private JMenuItem menuGeneralisation;
	private JMenu menuGenerer;
	private JMenuItem menuGenererHTML;
	private JMenuItem menuGenererJava;
	private JCheckBoxMenuItem menuHistorique;
	private JMenuItem menuImporterFeuilleXML;
	private JMenuItem menuInterface;
	private JMenuItem menuNouveauProjet;
	private JMenuItem menuNouvelleFeuille;
	private JMenu menuOutils;
	private JMenuItem menuOuvrirProjet;
	private JMenu menuOuvrirProjetRecent;
	private JCheckBoxMenuItem menuPalette;
	private JMenuItem menuPreferences;
	private JMenu menuProjet;
	private JCheckBoxMenuItem menuProprietes;
	private JMenuItem menuQuitter;
	private JMenuItem menuRafraichir;
	private JMenuItem menuRealisation;
	private JMenuItem menuRechercher;
	private JMenuItem menuRefaire;
	private JMenuItem menuSelectAll;
	private JMenuItem menuSupprimerFeuille;
	private JMenuItem menuViderFeuille;


	public MenuPrincipal(final EditeurUmlModele modele) {
		
		menuProjet = new JMenu();
		menuNouveauProjet = new JMenuItem();
		menuOuvrirProjet = new JMenuItem();
		menuOuvrirProjetRecent = new JMenu();
		separateur2 = new JPopupMenu.Separator();
		menuEnregistrer = new JMenuItem();
		menuEnregistrerSous = new JMenuItem();
		separateur1 = new JPopupMenu.Separator();
		menuExporter = new JMenu();
		menuExporterProjetXML = new JMenuItem();
		separateur3 = new JPopupMenu.Separator();
		menuRafraichir = new JMenuItem();
		separateur16 = new JPopupMenu.Separator();
		menuProprietesProjet = new JMenuItem();
		separateur4 = new JPopupMenu.Separator();
		menuChangeWorkspace = new JMenu();
		menuAutreWorkspace = new JMenuItem();
		menuQuitter = new JMenuItem();
		menuEdition = new JMenu();
		menuAnnuler = new JMenuItem();
		menuRefaire = new JMenuItem();
		separateur5 = new JPopupMenu.Separator();
		menuCouper = new JMenuItem();
		menuCopier = new JMenuItem();
		menuColler = new JMenuItem();
		separateur8 = new JPopupMenu.Separator();
		menuEffacer = new JMenuItem();
		separateur6 = new JPopupMenu.Separator();
		menuSelectAll = new JMenuItem();
		separateur7 = new JPopupMenu.Separator();
		menuRechercher = new JMenuItem();
		menuAffichage = new JMenu();
		menuAlwaysTop = new JCheckBoxMenuItem();
		menuFenetres = new JMenu();
		menuPalette = new JCheckBoxMenuItem();
		menuExplorateurProjet = new JCheckBoxMenuItem();
		menuProprietes = new JCheckBoxMenuItem();
		menuConsole = new JCheckBoxMenuItem();
		menuHistorique = new JCheckBoxMenuItem();
		menuAffBarreOutils = new JCheckBoxMenuItem();
		separateur11 = new JPopupMenu.Separator();
		menuFermerOnglet = new JMenuItem();
		menuFermerToutOnglets = new JMenuItem();
		menuFermerAutresOnglets = new JMenuItem();
		menuFeuille = new JMenu();
		menuAjouterPaquetage = new JMenuItem();
		menuAjouterEntite = new JMenu();
		menuAssistantEntite = new JMenuItem();
		separateur17 = new JPopupMenu.Separator();
		menuClasse = new JMenuItem();
		menuInterface = new JMenuItem();
		menuEnumeration = new JMenuItem();
		jMenu10 = new JMenu();
		menuAssistantRelation = new JMenuItem();
		separateur18 = new JPopupMenu.Separator();
		menuAssociation = new JMenuItem();
		menuAssociationDirect = new JMenuItem();
		menuAggregation = new JMenuItem();
		menuComposition = new JMenuItem();
		menuGeneralisation = new JMenuItem();
		menuDependance = new JMenuItem();
		menuRealisation = new JMenuItem();
		separateur12 = new JPopupMenu.Separator();
		menuNouvelleFeuille = new JMenuItem();
		menuViderFeuille = new JMenuItem();
		menuSupprimerFeuille = new JMenuItem();
		separateur14 = new JPopupMenu.Separator();
		menuImporterFeuilleXML = new JMenuItem();
		menuExporterFeuille = new JMenu();
		menuExporterFeuilleImage = new JMenuItem();
		menuExporterFeuilleXML = new JMenuItem();
		menuGenerer = new JMenu();
		menuGenererHTML = new JMenuItem();
		separateur10 = new JPopupMenu.Separator();
		menuGenererJava = new JMenuItem();
		menuOutils = new JMenu();
		menuPreferences = new JMenuItem();
		menuAide = new JMenu();
		menuAPropos = new JMenuItem();


		

		menuProjet.setText("Projet");

		menuNouveauProjet.setAction(new NouveauProjetAction(modele));
		menuProjet.add(menuNouveauProjet);
		
		menuOuvrirProjet.setAction(new OuvrirProjetAction(modele));
		menuProjet.add(menuOuvrirProjet);

		menuOuvrirProjetRecent.setText("Ouvrir Projet Recent...");
		menuProjet.add(menuOuvrirProjetRecent);
		menuProjet.add(separateur2);

		
		menuEnregistrer.setAction(new EnregistrerProjetAction(modele));
		menuProjet.add(menuEnregistrer);

		menuEnregistrerSous.setAction(new EnregistrerSousProjetAction(modele));
		menuProjet.add(menuEnregistrerSous);
		menuProjet.add(separateur1);

		menuExporter.setIcon(new ImageIcon(getClass().getResource("/vue/icones/export.gif"))); // NOI18N
		menuExporter.setText("Exporter");

		menuExporterProjetXML.setAction(new ExporterProjetImageAction(modele));
		menuExporter.add(menuExporterProjetXML);

		menuProjet.add(menuExporter);
		menuProjet.add(separateur3);

		menuRafraichir.setAction(new RafraichirAction(modele));
		menuProjet.add(menuRafraichir);
		menuProjet.add(separateur16);

		menuProprietesProjet.setAction(new ProprietesProjetAction(modele));
		menuProjet.add(menuProprietesProjet);
		menuProjet.add(separateur4);

		menuChangeWorkspace.setIcon(new ImageIcon(getClass().getResource("/vue/icones/util-wiz-icon.gif"))); // NOI18N
		menuChangeWorkspace.setText("Changer de Workspace");

		menuAutreWorkspace.setText("Autre...");
		menuChangeWorkspace.add(menuAutreWorkspace);

		menuProjet.add(menuChangeWorkspace);

		menuQuitter.setAction(new QuitterAction(modele));
		menuProjet.add(menuQuitter);

		add(menuProjet);

		menuEdition.setText("Edition");

		menuAnnuler.setAction(new AnnulerAction(modele));
		menuEdition.add(menuAnnuler);

		menuRefaire.setAction(new RefaireAction(modele));
		menuEdition.add(menuRefaire);
		menuEdition.add(separateur5);

		menuCouper.setAction(new CouperAction(modele));
		menuEdition.add(menuCouper);

		menuCopier.setAction(new CopierAction(modele));
		menuEdition.add(menuCopier);

		menuColler.setAction(new CollerAction(modele));
		menuEdition.add(menuColler);
		menuEdition.add(separateur8);

		menuEffacer.setAction(new EffacerAction(modele));
		menuEdition.add(menuEffacer);
		menuEdition.add(separateur6);



		
		menuSelectAll.setAction(new SelectAllAction(modele));
		menuEdition.add(menuSelectAll);
		menuEdition.add(separateur7);

		menuRechercher.setAction(new RechercherAction(modele));
		menuEdition.add(menuRechercher);

		add(menuEdition);

		menuAffichage.setText("Affichage");

		menuAlwaysTop.setAction(new AlwaysOnTopAction(modele));
		menuAffichage.add(menuAlwaysTop);

		menuFenetres.setText("Fenetres");

		menuPalette.setSelected(true);
		menuPalette.setAction(new AfficherPaletteAction(modele));
		menuFenetres.add(menuPalette);

		menuExplorateurProjet.setSelected(true);
		menuExplorateurProjet.setAction(new AfficherExplorateurAction(modele));
		menuFenetres.add(menuExplorateurProjet);

		menuProprietes.setSelected(true);
		menuProprietes.setAction(new AfficherProprietes(modele));
		menuFenetres.add(menuProprietes);

		menuConsole.setSelected(true);
		menuConsole.setAction(new AfficherConsoleAction(modele));
		menuFenetres.add(menuConsole);

		menuHistorique.setSelected(true);
		menuHistorique.setAction(new AfficherHistoriqueAction(modele));
		menuFenetres.add(menuHistorique);

		menuAffichage.add(menuFenetres);

		menuAffBarreOutils.setSelected(true);
		menuAffBarreOutils.setAction(new AfficherBarreOutilsAction(modele));
		menuAffichage.add(menuAffBarreOutils);

		menuAffichage.add(separateur11);

		menuFermerOnglet.setAction(new FermerLOngletAction(modele));
		menuAffichage.add(menuFermerOnglet);

		menuFermerToutOnglets.setAction(new FermerToutOngletsAction(modele));
		menuAffichage.add(menuFermerToutOnglets);

		menuFermerAutresOnglets.setText("Fermer les autres onglets");
		menuAffichage.add(menuFermerAutresOnglets);

		add(menuAffichage);

		menuFeuille.setText("Feuille");

		menuAjouterPaquetage.setAction(new AjoutPaquetageAction(modele));
		menuFeuille.add(menuAjouterPaquetage);

		menuAjouterEntite.setIcon(new ImageIcon(getClass().getResource("/vue/icones/new_wiz.gif"))); // NOI18N
		menuAjouterEntite.setText("Ajouter Entité");

		menuAssistantEntite.setAction(new AssistantEntiteAction(modele));
		menuAjouterEntite.add(menuAssistantEntite);
		menuAjouterEntite.add(separateur17);

		menuClasse.setAction(new AjoutClasseAction(modele));
		menuAjouterEntite.add(menuClasse);

		menuInterface.setAction(new AjoutInterfaceAction(modele));
		menuAjouterEntite.add(menuInterface);

		menuEnumeration.setAction(new AjoutEnumerationAction(modele));
		menuAjouterEntite.add(menuEnumeration);

		menuFeuille.add(menuAjouterEntite);

		jMenu10.setText("Ajouter Relation");

		menuAssistantRelation.setIcon(new ImageIcon(getClass().getResource("/vue/icones/wizard.gif"))); // NOI18N
		menuAssistantRelation.setText("Assistant Relation...");
		jMenu10.add(menuAssistantRelation);
		jMenu10.add(separateur18);

		menuAssociation.setText("Association");
		jMenu10.add(menuAssociation);

		menuAssociationDirect.setText("Association Directe");
		jMenu10.add(menuAssociationDirect);

		menuAggregation.setText("Aggregation");
		jMenu10.add(menuAggregation);

		menuComposition.setText("Composition");
		jMenu10.add(menuComposition);

		menuGeneralisation.setText("Généralisation");
		jMenu10.add(menuGeneralisation);

		menuDependance.setText("Dépendance");
		jMenu10.add(menuDependance);

		menuRealisation.setText("Réalisation");
		jMenu10.add(menuRealisation);

		menuFeuille.add(jMenu10);
		menuFeuille.add(separateur12);

		menuNouvelleFeuille.setAction(new NouvelleFeuilleAction(modele));
		menuFeuille.add(menuNouvelleFeuille);

		menuViderFeuille.setAction(new ViderFeuilleAction(modele));
		menuFeuille.add(menuViderFeuille);

		menuSupprimerFeuille.setAction(new SupprimerFeuilleAction(modele));
		menuFeuille.add(menuSupprimerFeuille);
		menuFeuille.add(separateur14);

		menuImporterFeuilleXML.setIcon(new ImageIcon(getClass().getResource("/vue/icones/import_prj.gif"))); // NOI18N
		menuImporterFeuilleXML.setText("Importer depuis XML");
		menuFeuille.add(menuImporterFeuilleXML);

		menuExporterFeuille.setIcon(new ImageIcon(getClass().getResource("/vue/icones/export.gif"))); // NOI18N
		menuExporterFeuille.setText("Exporter...");

		menuExporterFeuilleImage.setAction(new ExporterProjetImageAction(modele));
		menuExporterFeuille.add(menuExporterFeuilleImage);

		menuExporterFeuilleXML.setIcon(new ImageIcon(getClass().getResource("/vue/icones/xml_perspective.gif"))); // NOI18N
		menuExporterFeuilleXML.setText("XML");
		menuExporterFeuille.add(menuExporterFeuilleXML);

		menuFeuille.add(menuExporterFeuille);

		add(menuFeuille);

		menuGenerer.setText("Générer");

		menuGenererHTML.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		menuGenererHTML.setText("Rapport XHTML");
		menuGenerer.add(menuGenererHTML);
		menuGenerer.add(separateur10);

		menuGenererJava.setAction(new GenererJavaAction(modele));
		
		menuGenerer.add(menuGenererJava);

		add(menuGenerer);

		menuOutils.setText("Outils");

		menuPreferences.setText("Préférences");
		menuPreferences.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PreferenceLogiciel(modele).setVisible(true);;
				
			}
		});
		menuOutils.add(menuPreferences);

		add(menuOutils);

		menuAide.setText("Aide");

		menuAPropos.setText("A Propos ...");
		menuAide.add(menuAPropos);

		add(menuAide);
		
		
	}


}
