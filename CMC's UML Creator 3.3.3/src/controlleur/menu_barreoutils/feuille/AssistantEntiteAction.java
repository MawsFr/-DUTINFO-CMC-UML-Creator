package controlleur.menu_barreoutils.feuille;

import java.awt.event.ActionEvent;

import controlleur.AbstractMenuAction;
import modele.EditeurUmlModele;

public class AssistantEntiteAction extends AbstractMenuAction {

	public AssistantEntiteAction(EditeurUmlModele modele) {
		super(modele, "Assistant Entité...", "wizard.gif", "Ouvrir l'assistant de création d'entité");
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Assistant Entite !");

	}
}
