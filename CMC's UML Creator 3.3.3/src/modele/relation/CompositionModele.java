package modele.relation;

import modele.FeuilleDessinModele;

public class CompositionModele extends RelationModele {

	public CompositionModele(FeuilleDessinModele feuille) {
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
		return false;
	}

	@Override
	public boolean isComposition() {
		return true;
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
