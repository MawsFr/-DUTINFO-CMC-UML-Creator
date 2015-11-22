package vue.relation;

import java.awt.BasicStroke;

import modele.relation.RelationModele;
import vue.editeur.feuille.FeuilleDessin;

public class DirectAssociationFigure extends RelationFigure {

	public DirectAssociationFigure(FeuilleDessin feuille,
			RelationModele relation) {
		super(feuille, relation);
		
		this.bordure = new BasicStroke(3f);
	}

}
