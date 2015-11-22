package vue.relation;

import java.awt.BasicStroke;

import modele.relation.RelationModele;
import vue.editeur.feuille.FeuilleDessin;

public class CompositionFigure extends RelationFigure {

	public CompositionFigure(FeuilleDessin feuille, RelationModele relation) {
		super(feuille, relation);
		this.bordure = new BasicStroke(3f);
	}

}
