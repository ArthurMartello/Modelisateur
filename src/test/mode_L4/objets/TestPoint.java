package mode_L4.objets;
import static org.junit.Assert.*;

import org.junit.Test;

import mode_L4.model.modele.objets.Matrice;
import mode_L4.model.modele.objets.Point;

public class TestPoint {
	
	@Test
	public void testPoint() {
		Point p = new Point();
		assertTrue(p.getX() == 0);
		assertTrue(p.getY() == 0);
		assertTrue(p.getZ() == 0);
	}
	
	@Test
	public void testPointWithArguments() {
		Point p = new Point(1, 2, 3);
		assertTrue(p.getX() == 1);
		assertTrue(p.getY() == 2);
		assertTrue(p.getZ() == 3);
	}
	
	@Test
	public void testSetX() {
		Point p = new Point();
		p.setX(1);
		assertTrue(p.getX() == 1);
	}
	
	@Test
	public void testSetY() {
		Point p = new Point();
		p.setY(1);
		assertTrue(p.getY() == 1);
	}
	
	@Test
	public void testSetZ() {
		Point p = new Point();
		p.setZ(1);
		assertTrue(p.getZ() == 1);
	}
	
	@Test
	public void testGetTableau() {
		Point p = new Point(1, 2, 3);
		assertArrayEquals(new double[]{1, 2, 3}, p.getTableau(), 2);
	}
	
	@Test
	public void testToString() {
		Point p = new Point(1, 2, 3);
		assertEquals("[1.0,2.0,3.0]", p.toString());
	}	
		
	@Test
	public void testGetMatrice() {
		
		Point point = new Point();		
		point.set(5, 2, 9);
		
		Matrice expected = new Matrice(new double[][] {
			{ 5 }, { 2 }, { 9 }, { 1.0 }
		});
		
		assertArrayEquals(expected.getTableau(), point.getMatrice().getTableau());		
	}
	
}
