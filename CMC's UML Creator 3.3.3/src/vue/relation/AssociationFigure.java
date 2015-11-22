package vue.relation;

import java.awt.BasicStroke;

import modele.relation.RelationModele;
import vue.editeur.feuille.FeuilleDessin;

public class AssociationFigure extends RelationFigure {

	public AssociationFigure(FeuilleDessin feuille, RelationModele relation) {
		super(feuille, relation);
		this.bordure = new BasicStroke(3f);
	}

}
