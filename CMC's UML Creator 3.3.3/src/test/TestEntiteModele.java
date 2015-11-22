package test;

import static org.junit.Assert.*;
import modele.FeuilleDessinModele;
import modele.entites.ClasseModele;
import modele.entites.EntiteModele;
import modele.entites.caracteristiques.Visibilite;
import modele.entites.caracteristiques.attribut.AttributEntite;
import modele.entites.caracteristiques.methode.MethodeEntite;
import modele.entites.caracteristiques.methode.ParametreMethode;
import modele.exceptions.AlreadyExistException;

import org.junit.Before;
import org.junit.Test;

public class TestEntiteModele {

	private EntiteModele entiteModele;
	private MethodeEntite methode;
	private AttributEntite attribut;

	@Before
	public void init() {
		entiteModele=new ClasseModele(new FeuilleDessinModele("String"), "Entite", Visibilite.PUBLIC, false, false, null);
		methode = new MethodeEntite("String", "methode");
		attribut=new AttributEntite("String","attribut");
	}
	
	
	//Test Valide SetNom()
	
		@Test
		public void testValide() {
			this.entiteModele.setNom("Classe");
			assertEquals("Classe", entiteModele.getNom());
		}
		
		@Test
		public void testValideMajusculeDebutAuto() {
			this.entiteModele.setNom("classe");
			assertEquals("Classe", entiteModele.getNom());
		}
		
		@Test
		public void testValideMajusculeDebutAuto2() {
			this.entiteModele.setNom("c");
			assertEquals("C", entiteModele.getNom());
		}

	//Test Invalide setNom()
	
	@Test(expected=IllegalArgumentException.class)
	public void testNomVide() {
		this.entiteModele.setNom("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTrimNom() {
		this.entiteModele.setNom("      ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCaractereSpeciauxNom() {
		this.entiteModele.setNom("/*");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCaracSpecNom2() {
		this.entiteModele.setNom("dds*");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCommenceNombre() {
		this.entiteModele.setNom("0qsdq");
	}
	
	//Test Valide ajouterAttribut et Methode
	@Test
	public void testValideAjouterAttribut() throws AlreadyExistException {
		entiteModele.ajouterAttribut(attribut);
		assertEquals(attribut, entiteModele.getAttributs().get(0));
	}
	
	@Test
	public void testAjouterMethode() throws AlreadyExistException {
		entiteModele.ajouterAttribut(attribut);
		assertEquals(attribut, entiteModele.getAttributs().get(0));
	}
	
	@Test
	public void testAjouterMemeNomParametredifferent() throws AlreadyExistException {
		methode.ajouterParametre(new ParametreMethode("String", "hey"));
			entiteModele.ajouterMethode(methode);
			entiteModele.ajouterMethode(new MethodeEntite("String", "methode"));
	}
	
	//Test Invalide ajouterAttribut et Methode
	@Test(expected=AlreadyExistException.class)
	public void testInvalideAjouterMemeAttribut() throws AlreadyExistException {
		entiteModele.ajouterAttribut(attribut);
		entiteModele.ajouterAttribut(attribut);
		
	}
	
	@Test(expected=AlreadyExistException.class)
	public void testInvalideAjouterMemeAttributMemeNom() throws AlreadyExistException {
		entiteModele.ajouterAttribut(attribut);
		entiteModele.ajouterAttribut(new AttributEntite("String", "attribut"));
	}
	
	@Test(expected=AlreadyExistException.class)
	public void testAjouterMemeMethode() throws AlreadyExistException {
		entiteModele.ajouterMethode(methode);
		entiteModele.ajouterMethode(methode);
	}
	
	@Test(expected=AlreadyExistException.class)
	public void testAjouterMemeMethodeMemeNomMemeNombreParametre() throws AlreadyExistException {
		entiteModele.ajouterMethode(methode);
		MethodeEntite m2 = new MethodeEntite("String", "methode");
		entiteModele.ajouterMethode(m2);
	}
	
	
	
	@Test(expected=AlreadyExistException.class)
	public void testAjouterMemeNomPasMemeType() throws AlreadyExistException {

			entiteModele.ajouterMethode(methode);

			entiteModele.ajouterMethode(new MethodeEntite("Integer", "methode"));
	}
	
	@Test
	public void testContient(){	
		entiteModele=new ClasseModele(new FeuilleDessinModele("String"), "Entite", Visibilite.PUBLIC, false, false, null);
		try {
			entiteModele.ajouterMethode(methode);
			assertTrue(entiteModele.contient(methode));
		} catch (AlreadyExistException e) {
		}
		
		
	}
	@Test
	public void testContientAttribut(){	
		entiteModele=new ClasseModele(new FeuilleDessinModele("String"), "Entite", Visibilite.PUBLIC, false, false, null);
		try {
			entiteModele.ajouterAttribut(attribut);
		} catch (AlreadyExistException e) {}
		assertTrue(entiteModele.contient(attribut));
	}
	

}