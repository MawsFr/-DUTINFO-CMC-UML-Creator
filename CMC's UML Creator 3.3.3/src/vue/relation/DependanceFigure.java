package vue.relation;

import java.awt.BasicStroke;

import modele.relation.RelationModele;
import vue.editeur.feuille.FeuilleDessin;

public class DependanceFigure extends RelationFigure {

	public DependanceFigure(FeuilleDessin feuille, RelationModele relation) {
		super(feuille, relation);
		final float dash[] = {10.0f};
		this.bordure = new BasicStroke(2.0f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
	}

}
