package mode_L4.objets;
import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mode_L4.model.modele.objets.Face;
import mode_L4.model.modele.objets.Matrice;
import mode_L4.model.modele.objets.Point;
import mode_L4.model.modele.objets.Vecteur;

public class TestFace {
	
	@Test
	public void testFace() {
		List<Point> liste = new ArrayList<>();
		Face face = new Face(liste);
		assertEquals(liste, face.getListePoints());
	}
		
	@Test
	public void testGetBarycentreWhenThereIsntAnyPoints() {
		Face face = new Face();		
		assertTrue(face.getBarycentreZ() == 0.0);
	}
	
	@Test
	public void testGetBarycentreWhenThereIsTwoPoints() {
		Face face = new Face();				
		face.ajouterPoint(new Point(0, 0, 0));		
		face.ajouterPoint(new Point(5, 5, 5));
		assertTrue(face.getBarycentreZ() == 2.5);		
	}
	
	@Test
	public void testCompareToAEqualsA() {
		Face a = new Face();
		assertTrue(a.compareTo(a) == 0);		
	}
	
	@Test
	public void testCompareToWhenAEqualsB() {
		Face a = new Face();
		Face b = new Face();		
		assertTrue(a.compareTo(b) == 0);		
	}
	
	@Test
	public void testCompareToWhenADontEqualsB() {
		Face a = new Face();		
		Face b = new Face();
		b.ajouterPoint(new Point(10, 10, 10));		
		assertTrue(a.compareTo(b) == -1);
	}
	
	@Test
	public void testCompareToWhenBDontEqualsA() {
		Face a = new Face();		
		Face b = new Face();
		b.ajouterPoint(new Point(10, 10, 10));		
		assertTrue(b.compareTo(a) == 1);
	}
	
	@Test
	public void testGetColorWhenFaceIsEmpty() {
		Face face = new Face();		
		assertTrue(face.getColor(new Vecteur(0, 0, 0)).equals(new Color(0, 0, 0)));
	}
	
	@Test
	public void testGetColorWhenFaceContainsPoints() {
		Face face = new Face();	
		face.ajouterPoint(new Point(3, 3, 3));	
		face.ajouterPoint(new Point(2, 2, 2));	
		face.ajouterPoint(new Point(4, 4, 4));		
		assertTrue(face.getColor(new Vecteur(0, 0, 0)).equals(new Color(0, 0, 0)));		
	}	
	
	@Test
	public void testGetMatriceWithThreePoints() {
		Face face = new Face();		
		face.ajouterPoint(new Point(0, 1, 2));		
		face.ajouterPoint(new Point(3, 4, 5));
		face.ajouterPoint(new Point(6, 7, 8));
		
		Matrice expected = new Matrice(new double[][] {
			{0, 3, 6},
			{1, 4, 7},
			{2, 5, 8},
			{1.0, 1.0, 1.0}
		});
		
		assertArrayEquals(expected.getTableau(), face.getMatrice().getTableau());
	}
	
	@Test
	public void testEmptyToString() {
		Face a = new Face();
		assertEquals("", a.toString());
	}
	
	@Test
	public void testToString() {
		Face a = new Face();
		Point p = new Point();
		a.ajouterPoint(p);
		assertEquals(p.toString()+"\n", a.toString());
	}

}
