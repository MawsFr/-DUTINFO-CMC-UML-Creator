package modele.relation;

import modele.FeuilleDessinModele;
import modele.entites.EntiteModele;

public class RealisationModele extends RelationModele {
	
	

	public RealisationModele(FeuilleDessinModele feuille, String nom, EntiteModele entiteDepart, EntiteModele entiteArrive) throws IllegalArgumentException {
		super(feuille, nom, entiteDepart, entiteArrive);
	}

	public RealisationModele(FeuilleDessinModele feuille) {
		this(feuille, "Realisation", null, null);
	}
	
	@Override
	protected void verifierDepart(EntiteModele entiteDepart) throws IllegalArgumentException {
		super.verifierDepart(entiteDepart);
		if(entiteDepart.isInterface()) {
			throw new IllegalArgumentException("Une interface ne peut pas implémenter une entité");
		}
		
	}
	
	@Override
	protected void verifierArrive(EntiteModele entiteArrive) throws IllegalArgumentException {
		super.verifierArrive(entiteArrive);
		if(entiteDepart == entiteArrive) {
			throw new IllegalArgumentException("Une entité ne peut pas s'auto-implémenter");
		}
		
		if(entiteDepart.isClasse()) {
			if(entiteArrive.isClasse()) {
				throw new IllegalArgumentException("Une classe ne peut pas implémenter une autre classe");
			}
			
			if(entiteArrive.isEnum()) {
				throw new IllegalArgumentException("Une classe ne peut pas implémenter une énumération");
			}
			
		}
		
		if(entiteDepart.isEnum()) {
			if(entiteArrive.isClasse()) {
				throw new IllegalArgumentException("Une énumération ne peut pas implémenter une classe");
			}
			
			if(entiteArrive.isEnum()) {
				throw new IllegalArgumentException("Une énumération ne peut pas implémenter une autre énumération");
			}
		}
		
	}

	
	@Override
	public String toString() {
		return this.entiteDepart.getNom() + " implémente " + entiteArrive.getNom();
	}

	@Override
	public boolean isGeneralisation() {
		return false;
	}

	@Override
	public boolean isRealisation() {
		return true;
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
		return false;
	}

	@Override
	public boolean isDependance() {
		return false;
	}
	

}
