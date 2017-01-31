package mode_L4.objets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import mode_L4.model.modele.objets.Point;
import mode_L4.model.modele.objets.Vecteur;

public class TestVecteur {
	
	@Test
	public void testVecteur() {
		Vecteur v = new Vecteur();
		assertTrue(v.getX() == 0);
		assertTrue(v.getY() == 0);
		assertTrue(v.getZ() == 0);
	}
	
	@Test
	public void testVectorOfTwoPoints() {
		Vecteur v = new Vecteur(new Point(), new Point());
		assertTrue(v.getX() == 0);
		assertTrue(v.getY() == 0);
		assertTrue(v.getZ() == 0);		
	}

	@Test
	public void testProduitVectoriel() {
		Vecteur a = new Vecteur(1, 1, 1);
		Vecteur b = new Vecteur(1, 2, 1);
		assertTrue(new Vecteur(-1, 0, 1).getX() == a.produitVectoriel(b).getX());
		assertTrue(new Vecteur(-1, 0, 1).getY() == a.produitVectoriel(b).getY());
		assertTrue(new Vecteur(-1, 0, 1).getZ() == a.produitVectoriel(b).getZ());
	}
	
	@Test
	public void testProduitScalaireWhenAAndBAreNull() {
		Vecteur a = new Vecteur();
		Vecteur b = new Vecteur();
		assertTrue(0.0 == a.produitScalaire(b));
	}
	
	@Test
	public void testProduitScalaireWhenBIsNull() {
		Vecteur a = new Vecteur(1, 1, 1);
		Vecteur b = new Vecteur();
		assertTrue(0.0 == a.produitScalaire(b));
	}
	
	@Test
	public void testProduitScalaireWhenAOrBAreNotNull() {
		Vecteur a = new Vecteur(1, 1, 1);
		Vecteur b = new Vecteur(1, 1, 1);
		assertTrue(3.0 == b.produitScalaire(a));
	}

	@Test
	public void testGetNormeWhenVecteurIsNull() {
		Vecteur v = new Vecteur();
		assertTrue(0.0 == v.getNorme());
	}
	
	@Test
	public void testGetNormeWhenVecteurIsNotNull() {
		Vecteur v = new Vecteur(1, 0, 0);
		assertEquals(1.0, v.getNorme(), 0);
	}

}
