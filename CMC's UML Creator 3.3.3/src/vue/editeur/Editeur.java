package vue.editeur;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import modele.EditeurUmlModele;
import modele.FeuilleDessinModele;
import vue.editeur.feuille.FeuilleDessin;
import vue.editeur.feuille.GestionnaireFeuille;
import vue.editeur.feuille.OngletFeuilleDessin;

public class Editeur extends JPanel {
	
	private EditeurUmlModele modele;
	
	private GestionnaireFeuille feuilles;
	
	private Palette paletteOutils;
	
	public Editeur(EditeurUmlModele modele) {
		this.modele = modele;
		
		//TODO PEUT ETRE AJOUTER DES ADDOBSERVERS ICI SI BESOIN
		
		setLayout(new BorderLayout());
		
		feuilles = new GestionnaireFeuille(modele);
		feuilles.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		paletteOutils = new Palette(modele);
		
		add(feuilles, BorderLayout.CENTER);
		add(paletteOutils, BorderLayout.EAST);
		
		setDoubleBuffered(true);
	}
	
	public boolean antialiashingActive() {
		return modele.getProprietesEditeur().getProperty("antialiashing").equals("true");
	}

	public GestionnaireFeuille getGestionnaireFeuilles() {
		return feuilles;
	}

	/**
	 * @return the paletteOutils
	 */
	public Palette getPaletteOutils() {
		return paletteOutils;
	}
	
	
	
	
}
