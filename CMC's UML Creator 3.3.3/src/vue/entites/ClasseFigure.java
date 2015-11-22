package vue.entites;

import java.awt.Color;

import modele.entites.EntiteModele;
import vue.editeur.feuille.FeuilleDessin;


public class ClasseFigure extends EntiteFigure {
	
	
	
	public ClasseFigure(FeuilleDessin feuilleDessin, EntiteModele classe) {
		super(feuilleDessin, classe);
		
		couleurDegrade2 = new Color(156,194,167);
//		init();
	}
	
	
	
	

}

