package mode_L4.maths;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.maths.Rotation;
import mode_L4.model.modele.objets.Point;

public class TestRotation {
	
	private Modele modele;
	
	@Before
	public void before() {
		modele = new Modele();		
		modele.ajouterPoint(new Point(0, 1, 2));		
		modele.ajouterPoint(new Point(3, 4, 5));
	}
	
	@Test
	public void testRotationBy0() {
		assertArrayEquals(modele.getMatrice().getTableau(), new Rotation(modele, 0, 0, 0).getModele().getMatrice().getTableau());
	}
	
	@Test
	public void testRotationOf90DegreesOnXAxis() {
		double[][] expected = new double[][] {
			{0, 3},
			{1, 4},
			{5, 2},
			{1, 1}
		};
		assertArrayEquals(expected, new Rotation(modele, 90, 0, 0).getModele().getMatrice().getTableau());
	}
	
	@Test
	public void testRotationOf90DegreesOnYAxis() {
		double[][] expected = new double[][] {
			{3, 0},
			{1, 4},
			{2, 5},
			{1, 1}
		};
		assertArrayEquals(expected, new Rotation(modele, 0, 90, 0).getModele().getMatrice().getTableau());
	}
	
	@Test
	public void testRotationOf90DegreesOnZAxis() {
		double[][] expected = new double[][] {
			{0, 3},
			{4, 1},
			{2, 5},
			{1, 1}
		};
		assertArrayEquals(expected, new Rotation(modele, 0, 0, 90).getModele().getMatrice().getTableau());
	}
}