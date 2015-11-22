package modele.relation;

import java.awt.geom.Line2D;

import modele.FeuilleDessinModele;
import modele.entites.EntiteModele;

public class GeneralisationModele extends RelationModele {

	public GeneralisationModele(FeuilleDessinModele feuille) {
		this(feuille, "Relation", null, null);
	}
	
	public GeneralisationModele(FeuilleDessinModele feuille, String nom, EntiteModele entiteDepart, EntiteModele entiteArrive) throws IllegalArgumentException {
		super(feuille, nom, entiteDepart, entiteArrive);
	}

	
	@Override
	protected void verifierDepart(EntiteModele entiteDepart) throws IllegalArgumentException {
		super.verifierDepart(entiteDepart);
		
		if(entiteDepart.isEnum()) {
			throw new IllegalArgumentException("Une énumération ne peut pas hériter d'une entité !");
		}
		
		
	}
	
	@Override
	protected void verifierArrive(EntiteModele entiteArrive) throws IllegalArgumentException {
		super.verifierArrive(entiteArrive);
		
		if(entiteDepart == entiteArrive) {
			throw new IllegalArgumentException("L'entité ne peut pas hériter d'elle même !");
		}
		
		if(entiteDepart.isClasse()) {
			if(entiteArrive.isInterface()) {
				throw new IllegalArgumentException("Une classe ne peut pas hériter d'une interface !");
			}
			
			if(entiteArrive.isEnum()) {
				throw new IllegalArgumentException("Une classe ne peut pas hériter d'une énumération !");
			}
		}
		
		if(entiteDepart.isInterface()) {
			if(entiteArrive.isClasse()) {
				throw new IllegalArgumentException("Une interface ne peut pas hériter d'une classe !");
			}
			
			if(entiteArrive.isEnum()) {
				throw new IllegalArgumentException("Une interface ne peut pas hériter d'une énumération !");
			}
		}
		
	}
	
	
	
	
	@Override
	public String toString() {
		return this.entiteDepart.getNom() + " hérite de " + entiteArrive.getNom();
	}

	@Override
	public boolean isGeneralisation() {
		return true;
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
		return false;
	}

	@Override
	public boolean isDependance() {
		return false;
	}
	

}
