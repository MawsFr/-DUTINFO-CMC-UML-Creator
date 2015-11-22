package vue.editeur;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import vue.fonctions.BoiteFonctionsVue;
import modele.EditeurUmlModele;
import modele.FeuilleDessinModele;
import controlleur.feuille.ChangerModeAction;
import controlleur.menu_barreoutils.FloatingToolbarListener;

public class Palette extends JToolBar {

	private EditeurUmlModele modele;
	
	private JButton toolBtnSelection;
	private JButton toolBtnClasse;
	private JButton toolBtnInterface;
	private JButton toolBtnEnum;
	private JButton toolBtnPaquetage;
	private JButton toolBtnGeneralisation;
	private JButton toolBtnRealisation;
	private JButton toolBtnAssociation;
	private JButton toolBtnDirectAssociation;
	private JButton toolBtnDependance;
	private JButton toolBtnAggregation;
	private JButton toolBtnComposition;
	
	
	public Palette(EditeurUmlModele modele) {
		this.modele = modele;
		
		//TODO peut etre plus tard si on choisi de faire plusieurs type de feuille, on peut decider de ne pas afficher certain outils
		toolBtnSelection = new JButton();
		toolBtnSelection.setAction(new ChangerModeAction(modele, 
				"Selection", 
				"curseur.png",
				"Mode Sélection", KeyStroke.getKeyStroke(0,0)));
		toolBtnSelection.setActionCommand("SELECTION");
		
		toolBtnClasse = new JButton();
		toolBtnClasse.setAction(new ChangerModeAction(modele, 
				"Classe", 
				"newclass_wiz.gif",
				"Mode création Classe", KeyStroke.getKeyStroke(0,0)));
		toolBtnClasse.setActionCommand("CLASSE");
		
		toolBtnInterface = new JButton();
		toolBtnInterface.setAction(new ChangerModeAction(modele, 
				"Interface", 
				"newint_wiz.gif",
				"Mode création Interface", KeyStroke.getKeyStroke(0,0)));
		toolBtnInterface.setActionCommand("INTERFACE");
		
		toolBtnEnum = new JButton();
		toolBtnEnum.setAction(new ChangerModeAction(modele, 
				"Énumération", 
				"newenum_wiz.gif",
				"Mode création Énumération", KeyStroke.getKeyStroke(0,0)));
		toolBtnEnum.setActionCommand("ENUMERATION");
		
		toolBtnPaquetage = new JButton();
		toolBtnPaquetage.setAction(new ChangerModeAction(modele, 
				"Paquetage", 
				"newpack_wiz.gif",
				"Mode création Paquetage", KeyStroke.getKeyStroke(0,0)));
		toolBtnPaquetage.setActionCommand("PAQUETAGE");
		
		
		
		toolBtnGeneralisation = new JButton();
		toolBtnGeneralisation.setAction(new ChangerModeAction(modele, 
				"Généralisation", 
				"generalisation.png",
				"Mode création Relation d'héritage (généralisation)", null));
		toolBtnGeneralisation.setActionCommand("GENERALISATION");
		
		toolBtnRealisation = new JButton();
		toolBtnRealisation.setAction(new ChangerModeAction(modele, 
				"Réalisation", 
				"realisation.png",
				"Mode création relation d'implémentation (réalisation)", null));
		toolBtnRealisation.setActionCommand("REALISATION");
		
		toolBtnAssociation = new JButton();
		toolBtnAssociation.setAction(new ChangerModeAction(modele, 
				"Association", 
				"association.png",
				"Mode création relation d'association", null));
		toolBtnAssociation.setActionCommand("ASSOCIATION");
		
		toolBtnDirectAssociation = new JButton();
		toolBtnDirectAssociation.setAction(new ChangerModeAction(modele, 
				"Association Directe", 
				"directassociation.png",
				"Mode création relation d'association direct", null));
		toolBtnDirectAssociation.setActionCommand("DIRECTASSOCIATION");
		
		toolBtnAggregation = new JButton();
		toolBtnAggregation.setAction(new ChangerModeAction(modele, 
				"Aggregation", 
				"aggregation.png",
				"Mode création relation d'aggregation", null));
		toolBtnAggregation.setActionCommand("AGGREGATION");
		
		toolBtnComposition = new JButton();
		toolBtnComposition.setAction(new ChangerModeAction(modele, 
				"Composition", 
				"composition.png",
				"Mode création relation de composition", null));
		toolBtnComposition.setActionCommand("COMPOSITION");
		
		toolBtnDependance = new JButton();
		toolBtnDependance.setAction(new ChangerModeAction(modele, 
				"Dépendance", 
				"dependance.png",
				"Mode création relation de dépendance", null));
		toolBtnDependance.setActionCommand("DEPENDANCE");
		
		
		
		
		add(toolBtnSelection);
		add(toolBtnClasse);
		add(toolBtnInterface);
		add(toolBtnEnum);
		add(toolBtnPaquetage);
		addSeparator();
		add(toolBtnAssociation);
		add(toolBtnDirectAssociation);
		add(toolBtnGeneralisation);
		add(toolBtnRealisation);
		add(toolBtnAggregation);
		add(toolBtnComposition);
		add(toolBtnDependance);
		
		setFloatable(true);
//		System.out.println(getDropTarget());
		setOrientation(JToolBar.VERTICAL);
		
//		addHierarchyListener(new FloatingToolbarListener());
	}
	
}
