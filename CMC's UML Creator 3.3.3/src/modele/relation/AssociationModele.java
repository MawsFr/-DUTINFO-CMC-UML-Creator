package modele.relation;

import modele.FeuilleDessinModele;
import modele.entites.EntiteModele;

public class AssociationModele extends RelationModele {

	
	@Override
	protected void verifierArrive(EntiteModele entiteArrive) throws IllegalArgumentException {
		if(entiteDepart == entiteArrive) {
			throw new IllegalArgumentException("L'entité ne peut pas hériter d'elle même !");
		}
	}
	
	public AssociationModele(FeuilleDessinModele feuille) {
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
		return true;
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
