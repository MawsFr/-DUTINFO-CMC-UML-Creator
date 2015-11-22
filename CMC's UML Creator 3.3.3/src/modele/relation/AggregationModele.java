package modele.relation;

import modele.FeuilleDessinModele;

public class AggregationModele extends RelationModele {

	public AggregationModele(FeuilleDessinModele feuille) {
		super(feuille);
	}

	@Override
	public boolean isGeneralisation() {
		return false;
	}

	@Override
	public boolean isRealisation() {
		return false;
	}

	@Override
	public boolean isAggregation() {
		return true;
	}

	@Override
	public boolean isComposition() {
		return false;
	}

	@Override
	public boolean isAssociation() {
		return false;
	}

	@Override
	public boolean isDirectAssociation() {
		return false;
	}

	@Override
	public boolean isDependance() {
		return false;
	}

}
