package controlleur.feuille;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modele.EditeurUmlModele;
import vue.editeur.feuille.OngletFeuilleDessin;

public class OngletFeuilleListener implements ChangeListener {

	
	private EditeurUmlModele modele;

	public OngletFeuilleListener(EditeurUmlModele modele) {
		this.modele = modele;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane feuilles = ((JTabbedPane) e.getSource());
		OngletFeuilleDessin onglet = (OngletFeuilleDessin) feuilles.getSelectedComponent();
		if(onglet != null && feuilles.getSelectedIndex() != -1) {
			modele.setFeuilleSelectionnee(onglet.getFeuille().getFeuilleModele());
		} else {
			modele.setFeuilleSelectionnee(null);
		}
		
		
		
	}

}
