package modele.relation;

public enum CardinaliteModele {

	ZERO_UN("0,1"),
	ZERO_N("0,N"),
	UN_UN("1,1"),
	UN_N("1,N");
	
	private String nom;
	
	CardinaliteModele(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return this.nom;
	}
}
