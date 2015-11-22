package vue.entites.propriete;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import vue.CMCUmlView;
import vue.entites.EntiteFigure;
import modele.EditeurUmlModele;
import modele.entites.EntiteModele;
import controlleur.entites.AjouterAttributAction;
import controlleur.entites.AjouterMethodeAction;
import controlleur.entites.AjouterProprieteAction;
import controlleur.menu_barreoutils.edition.CollerAction;
import controlleur.menu_barreoutils.edition.CopierAction;
import controlleur.menu_barreoutils.edition.CouperAction;
import controlleur.menu_barreoutils.edition.EffacerAction;

public class PopUpEntiteFigure extends JPopupMenu {

	private EntiteFigure entite;

	private JMenuItem menuAjouterAttribut;
	private JMenuItem menuAjouterMethodes;
	private JMenuItem menuProprietes;
	private JMenuItem menuCopier;
	private JMenuItem menuColler;
	private JMenuItem menuCouper;
	private JMenuItem menuEffacer;
	private JMenuItem menuExporter;
	private JMenuItem menuAjouterUneRelation;

	public PopUpEntiteFigure(EntiteFigure entite) {
		
		this.entite = entite;
		
		menuAjouterAttribut = new JMenuItem(new AjouterAttributAction(entite));
		menuAjouterMethodes = new JMenuItem(new AjouterMethodeAction(entite));
		menuProprietes = new JMenuItem(new AjouterProprieteAction(entite));
//		menuCopier=new JMenuItem(new CopierAction(entite));
//		menuColler=new JMenuItem(new CollerAction(entite));
//		menuCouper=new JMenuItem(new CouperAction(entite));
		menuEffacer=new JMenuItem(new EffacerAction(entite.getFeuille().getModele()));
	//	menuExporter=new JMenuItem(new )
	//	menuAjouterUneRelation
		
		
		this.add(menuAjouterAttribut);
		this.add(menuAjouterMethodes);
		this.addSeparator();
		this.add(menuEffacer);
		this.addSeparator();
		this.add(menuProprietes);
//		this.add(menuCopier);
//		this.add(menuColler);
//		this.add(menuCouper);

//		this.add(menuExporter);
//		this.add(menuAjouterUneRelation);
		
		}
	
}
