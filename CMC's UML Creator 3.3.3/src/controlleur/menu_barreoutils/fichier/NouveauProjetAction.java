package controlleur.menu_barreoutils.fichier;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;
import modele.ProjetModele;

public class NouveauProjetAction extends AbstractMenuAction {

	public NouveauProjetAction(EditeurUmlModele modele) {
		super(modele, "Nouveau Projet...", "newprj_wiz.gif", "Créer un nouveau projet", KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK));
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
//		new NouveauProjetView().setVisible(true);
		modele.getWorkspace().ajouterProjet(new ProjetModele("Heyyyy"));

	}

}
