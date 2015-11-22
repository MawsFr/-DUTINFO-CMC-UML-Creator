package modele.entites.caracteristiques;

public enum Visibilite {

	DEFAULT("default", " "),
	PUBLIC("public", "+"),
	PRIVATE("private", "-"),
	PROTECTED("protected", "#");
	
	private String nom;
	private String symbole;
	
	Visibilite(String nom, String symbole) {
		this.nom = nom;
		this.symbole = symbole;
	}
	
	public String toStringUML() {
		return this.symbole;
	}
	
	public String toString() {
		return this.nom;
	}
	
}
