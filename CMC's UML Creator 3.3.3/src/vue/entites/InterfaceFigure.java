package vue.entites;

import java.awt.Color;

import vue.editeur.feuille.FeuilleDessin;
import modele.FeuilleDessinModele;
import modele.entites.EntiteModele;

public class InterfaceFigure extends EntiteFigure {
	


	public InterfaceFigure(FeuilleDessin feuilleDessin, EntiteModele entite) {
		super(feuilleDessin, entite);
		
		couleurDegrade2 = new Color(195,188,217);
		
//		init();
	}
	
	

}
