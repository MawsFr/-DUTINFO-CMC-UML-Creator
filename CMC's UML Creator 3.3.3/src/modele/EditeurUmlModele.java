package modele;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Properties;
import java.util.Set;

import vue.fonctions.BoiteFonctionsVue;
import modele.entites.EntiteModele;
import modele.fonctions.BoiteFonctionsModele;

public class EditeurUmlModele extends Observable implements Serializable{

	
	private WorkspaceModele workspace;

	private ProjetModele projetSelectionne;
	private FeuilleDessinModele feuilleSelectionnee;
	
	
	//////////////////
	private Properties proprietesLogiciel;
	private Properties proprietesEditeur;

	public EditeurUmlModele() {

		this.projetSelectionne = new ProjetModele();
		this.workspace = new WorkspaceModele();

		initProprietes();
		notifier();
		
	}

	private void initProprietes() {
		//init proprietes logiciel
		this.proprietesLogiciel = new Properties();
		
		try {
			proprietesLogiciel = BoiteFonctionsModele.chargerProprietes("logiciel");
		} catch (Exception e) {
			//Le fichier n'existe pas
			proprietesLogiciel.setProperty("nom", "CMC UML Creator");
			proprietesLogiciel.setProperty("coordx", "" + ((Toolkit.getDefaultToolkit().getScreenSize().width /2) - 600/2));
			proprietesLogiciel.setProperty("coordy", "" + ((Toolkit.getDefaultToolkit().getScreenSize().height /2) - 600/2));
			proprietesLogiciel.setProperty("width", "" + 600);
			proprietesLogiciel.setProperty("height", "" + 600);
			proprietesLogiciel.setProperty("state", "" + 0);
			
			
			
		}
		//init proprietes editeur
		this.proprietesEditeur = new Properties();
		try {
			this.proprietesEditeur = BoiteFonctionsModele.chargerProprietes("editeur");
		} catch (IOException e) {
			this.proprietesEditeur.setProperty("antialiashing", "true");
			this.proprietesEditeur.setProperty("coordspecial", "true");
		}
		
	}

	private void notifier() {
		notifier(null);
	}

	private void notifier(Object o) {
		setChanged();
		notifyObservers(o);
	}

	public FeuilleDessinModele getFeuilleSelectionnee() {
		return this.feuilleSelectionnee;
	}

	public void setFeuilleSelectionnee(FeuilleDessinModele feuille) {
//		if(this.feuilles.contains(feuille)) {
			this.feuilleSelectionnee = feuille;
//		}

//		notifier();
	}

	public WorkspaceModele getWorkspace() {
		return workspace;
	}

	public void setWorkspace(WorkspaceModele workspace) {
		this.workspace = workspace;
		notifier(workspace);
	}

	public ProjetModele getProjetSelectionne() {
		return this.workspace.getProjetSelectionne();
	}

	public void setProjetSelectionne(ProjetModele projetSelectionne) {
		this.projetSelectionne = projetSelectionne;
		this.workspace.setProjetSelectionne(projetSelectionne);
		notifier(projetSelectionne);
		
	}

	public Properties getProprietesLogiciel() {
		return proprietesLogiciel;
	}

	public void setProprietesLogiciel(Properties proprietesLogiciel) {
		this.proprietesLogiciel = proprietesLogiciel;
	}

	public Properties getProprietesEditeur() {
		return proprietesEditeur;
	}

	public void setProprietesEditeur(Properties proprietesEditeur) {
		this.proprietesEditeur = proprietesEditeur;
	}
	
	
	
	
	


}
