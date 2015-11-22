package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.LinkedList;

import javax.naming.OperationNotSupportedException;

import modele.FeuilleDessinModele;
import modele.entites.ClasseModele;
import modele.entites.EntiteModele;
import modele.entites.caracteristiques.Visibilite;

import org.junit.Before;
import org.junit.Test;

public class TestFeuilleDessinModele {

	private FeuilleDessinModele feuille;
	private EntiteModele entite;
	
	@Before
	public void init() {
		feuille = new FeuilleDessinModele("Feuille");
		entite = new ClasseModele(feuille, "classe", Visibilite.PUBLIC, false, false, new Point(100,100));
	}
	
	@Test
	public void testPileAjout() throws IllegalArgumentException, UnsupportedOperationException, OperationNotSupportedException {
		
		feuille.ajouterEntite(entite);
		assertNull(feuille.getEntiteSelectionnee());
		assertEquals(entite, ((LinkedList<EntiteModele>) feuille.getPileEntiteAjoutees()).get(0));
		feuille.setEntiteSelectionnee(entite);
		assertEquals(entite, feuille.getEntiteSelectionnee());
		
		feuille.supprimerEntite(entite);
		assertNull(feuille.getEntiteSelectionnee());
		assertEquals(entite, ((LinkedList<EntiteModele>) feuille.getPileEntiteSupprimees()).get(0));
		feuille.viderPileSuppressionEntite();
		assertTrue(feuille.getPileEntiteSupprimees().isEmpty());
		
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void entiteNull() throws IllegalArgumentException, UnsupportedOperationException, OperationNotSupportedException {
		feuille.ajouterEntite(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void entiteEnDouble() throws IllegalArgumentException, UnsupportedOperationException, OperationNotSupportedException {
		feuille.ajouterEntite(entite);
		feuille.ajouterEntite(entite);
	}

}
