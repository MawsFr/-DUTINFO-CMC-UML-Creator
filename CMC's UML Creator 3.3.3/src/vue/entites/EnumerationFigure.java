package vue.entites;

import java.awt.Color;

import modele.entites.EntiteModele;
import vue.editeur.feuille.FeuilleDessin;

public class EnumerationFigure extends EntiteFigure {

	private EntiteModele entite;
	
	public EnumerationFigure(FeuilleDessin modele, EntiteModele classe) {
		super(modele, classe);
		couleurDegrade2 = new Color(211,193,170);
		
//		try {
//			((EnumerationModele) entite).ajouterLitteral(new LitteralModele("Yop"));
//		} catch (AlreadyExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	

}
