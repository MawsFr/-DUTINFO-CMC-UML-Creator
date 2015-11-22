package vue.editeur.feuille;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTabbedPane;

import modele.EditeurUmlModele;
import modele.FeuilleDessinModele;
import controlleur.feuille.OngletFeuilleListener;

public class GestionnaireFeuille extends JTabbedPane implements Observer{

	private EditeurUmlModele modele;
//	private Map<FeuilleDessinModele, OngletFeuilleDessin> feuillesOuvertes;
	
	public GestionnaireFeuille(EditeurUmlModele modele) {
		this.modele = modele;
		modele.addObserver(this);
		this.addChangeListener(new OngletFeuilleListener(modele));
//		this.feuillesOuvertes = new HashMap<FeuilleDessinModele, OngletFeuilleDessin>();
		
	}
	
	public OngletFeuilleDessin ajouterFeuille(FeuilleDessinModele feuille) {
		OngletFeuilleDessin onglet = new OngletFeuilleDessin(modele, feuille);
//		this.feuillesOuvertes.put(feuille, onglet);
		this.addTab(feuille.getNom(), onglet);
		this.setSelectedComponent(onglet);
		return onglet;
		
		
	}
	
//	public void fermerUnOnglet(FeuilleDessin feuille) {
//		this.remove(feuille);
//	}
//	
//	public void fermerAutreOnglet(FeuilleDessin feuille) {
//	}

//	public void supprimerFeuille(FeuilleDessinModele next) {
//		this.remove(feuillesOuvertes.get(next));
//		this.feuillesOuvertes.remove(next);
//	}

	@Override
	public void update(Observable arg0, Object arg1) {
//		for(Iterator<FeuilleDessinModele> it = modele.getWorkspace().getProjetSelectionne().getPileFeuilleAjoutees().iterator(); it.hasNext();) {
//			ajouterFeuille(it.next());
//		}
//		
//		modele.getWorkspace().getProjetSelectionne().viderPileAjoutFeuille();
		
//		for(Iterator<FeuilleDessinModele> it = modele.getWorkspace().getProjetSelectionne().getPileFeuilleAjoutees().iterator(); it.hasNext();) {
//			supprimerFeuille(it.next());
//		}
//		
//		modele.getWorkspace().getProjetSelectionne().viderPileSuppression();		
	}
}
