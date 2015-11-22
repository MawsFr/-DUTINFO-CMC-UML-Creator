package test;

import java.awt.Point;

import javax.naming.OperationNotSupportedException;

import modele.FeuilleDessinModele;
import modele.entites.ClasseModele;
import modele.entites.EntiteModele;
import modele.entites.EnumerationModele;
import modele.entites.InterfaceModele;
import modele.entites.caracteristiques.Visibilite;
import modele.relation.GeneralisationModele;
import modele.relation.RealisationModele;
import modele.relation.RelationModele;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import vue.relation.RealisationFigure;
import vue.relation.RelationFigure;


public class TestRelationModele {

	private FeuilleDessinModele feuille;
	private EntiteModele classeDepart;
	private EntiteModele classeArrivee;
	private EntiteModele interfaceDepart;
	private EntiteModele interfaceArrivee;
	private EntiteModele enumDepart;
	private EntiteModele enumArrivee;
	private RelationModele relation;
	
	
	@Before
	public void init() throws IllegalArgumentException, UnsupportedOperationException, OperationNotSupportedException {
		
		this.feuille = new FeuilleDessinModele("Feuille");
		this.classeDepart = new ClasseModele(feuille, "ClasseDepart", Visibilite.PUBLIC, false, false, new Point(0, 0));
		this.classeArrivee= new ClasseModele(feuille, "ClasseArrivee", Visibilite.PUBLIC, false, false, new Point(100, 100));
		
		this.interfaceDepart = new InterfaceModele(feuille, "InterfaceDepart", Visibilite.PUBLIC, new Point(0, 0));
		this.interfaceArrivee= new InterfaceModele(feuille, "InterfaceArrivee", Visibilite.PUBLIC, new Point(100, 100));
		
		this.enumDepart = new EnumerationModele(feuille, "ClasseDepart", Visibilite.PUBLIC, new Point(0, 0));
		this.enumArrivee= new EnumerationModele(feuille, "ClasseArrivee", Visibilite.PUBLIC, new Point(100, 100));
		
		
		feuille.ajouterEntite(classeDepart);
		feuille.ajouterEntite(classeArrivee);
		
		
	}
	
	//Test Valide
	@Test
	public void testValideGeneralisationClasseClasse() {
		relation = new GeneralisationModele(feuille);
		relation.setEntiteDepart(classeDepart);
		
		assertTrue(relation.departExiste());
		
		relation.setEntiteArrive(classeArrivee);
		
		assertEquals(classeDepart, relation.getEntiteDepart());
		assertEquals(classeArrivee, relation.getEntiteArrive());
	
	}
	
	@Test
	public void testValideRealisationClasseInterface() {
		relation = new RealisationModele(feuille);
		relation.setEntiteDepart(classeDepart);
		
		assertTrue(relation.departExiste());
		
		relation.setEntiteArrive(interfaceArrivee);
		
		assertEquals(classeDepart, relation.getEntiteDepart());
		assertEquals(interfaceArrivee, relation.getEntiteArrive());
	}
	
	@Test
	public void testValideGeneralisationInterfaceInterface() {
		relation = new GeneralisationModele(feuille);
		relation.setEntiteDepart(interfaceDepart);
		
		assertTrue(relation.departExiste());
		
		relation.setEntiteArrive(interfaceArrivee);
		
		assertEquals(interfaceDepart, relation.getEntiteDepart());
		assertEquals(interfaceArrivee, relation.getEntiteArrive());
	}
	
	//test Invalide
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalideGeneralisationClasseInterface() {
		relation = new GeneralisationModele(feuille);
		relation.setEntiteDepart(classeDepart);
		
		assertTrue(relation.departExiste());
		
		relation.setEntiteArrive(interfaceArrivee);
	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalideRealisationInterface() {
		relation = new RealisationModele(feuille);
		relation.setEntiteDepart(interfaceDepart);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalideRealisationClasseClasse() {
		relation = new RealisationModele(feuille);
		relation.setEntiteDepart(classeDepart);
		
		assertTrue(relation.departExiste());
		
		relation.setEntiteArrive(classeArrivee);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalideMemeDepart() {
		relation = new GeneralisationModele(feuille);
		relation.setEntiteDepart(classeDepart);
		relation.setEntiteArrive(classeDepart);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalideEnumEnumGeneralisation() {
		relation = new GeneralisationModele(feuille);
		relation.setEntiteDepart(enumDepart);
		relation.setEntiteArrive(enumArrivee);
	}

}
