package modele.exceptions;

public class AlreadyExistException extends Exception {
	

	public AlreadyExistException(String texte) {
		super(texte);
	}
	
	public AlreadyExistException() {
		super("L'element existe deja !");
	}

}
