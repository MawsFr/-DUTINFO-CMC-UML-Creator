package modele.relation;

import modele.FeuilleDessinModele;

public class DirectAssociationModele extends RelationModele {

	public DirectAssociationModele(FeuilleDessinModele feuille) {
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
		return false;
	}

	@Override
	public boolean isAssociation() {
		return false;
	}

	@Override
	public boolean isDirectAssociation() {
		return true;
	}

	@Override
	public boolean isDependance() {
		return false;
	}

}
