package mode_L4.objets;


import static org.junit.Assert.*;

import org.junit.Test;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.objets.Matrice;
import mode_L4.model.modele.objets.Point;
import mode_L4.model.modele.objets.Vecteur;

public class TestMatrice {
	
	private double[][] tablo = new double[][]{{1},{2},{3}};

	@Test
	public void testGetTableau() {
		Matrice matrice = new Matrice(tablo);		
		assertArrayEquals(tablo, matrice.getTableau());	
	}
	
	@Test
	public void testSetTableau() {
		Matrice matrice = new Matrice(tablo);
		matrice.setMatrice(null);
		assertTrue(matrice.getTableau() == null);
	}
	
	@Test
	public void testToString() {
		Matrice matrice = new Matrice(tablo);
		assertEquals("Matrice 3*1\n1.0\n2.0\n3.0\n", matrice.toString());
	}
	
	@Test
	public void testToStringWithMultipleColumns() {
		Matrice matrice = new Matrice(new double[][]{{1, 1},{2, 2},{3, 3}});
		assertEquals("Matrice 3*2\n1.0 | 1.0\n2.0 | 2.0\n3.0 | 3.0\n", matrice.toString());
	}
	
	@Test
	public void testGetLineSizeWithThreeLines() {
		Matrice matrice = new Matrice(tablo);
		assertTrue(3 == matrice.getLineSize());
	}
	
	@Test
	public void testGetLineSizeWithThreeLinesAndTwoColumns() {
		tablo = new double[][]{{1, 1.1},{2},{3}};
		Matrice matrice = new Matrice(tablo);
		assertTrue(3 == matrice.getLineSize());
	}
	
	@Test
	public void testGetColumnSizeWithThreeLinesAndOneColumn() {
		tablo = new double[][]{{1, 1.1},{2},{3}};
		Matrice matrice = new Matrice(tablo);	
		assertTrue(2 == matrice.getColumnSize());
	}
	
	@Test
	public void testMultiplier() {
		
		double[][] a = new double[][]{{2, 1, 1}};
		Matrice mA = new Matrice(a);
		
		double[][] b = new double[][]{{1},{1},{1}};
		Matrice mB = new Matrice(b);
		
		double[][] expected = new double[][]{{4}};
		assertArrayEquals(expected, mA.multiplier(mB).getTableau());
	}
	
	@Test
	public void testGetMatriceTranslation() {
		
		Modele modele = new Modele();		
		modele.ajouterPoint(new Point(1, 2, 3));
		
		Matrice expected = new Matrice(new double[][]{
			{1, 0, 0, -1},
			{0, 1, 0, -2},
			{0, 0, 1, -3},
			{0, 0, 0, 1.0},
		});
		
		assertArrayEquals(expected.getTableau(), Matrice.getMatriceTranslation(modele.getCentre()).getTableau());
	
		expected = new Matrice(new double[][]{
				{1, 0, 0, -8},
				{0, 1, 0, -6},
				{0, 0, 1, -2},
				{0, 0, 0, 1.0},
		});
		
		assertArrayEquals(expected.getTableau(), Matrice.getMatriceTranslation(new Vecteur(8, 6, 2)).getTableau());
		
	}
	
	@Test
	public void testGetMatriceHomothetie() {
		
		Modele modele = new Modele();		
		modele.ajouterPoint(new Point(1, 2, 3));
		
		double zoom = 2;
		
		Matrice expected = new Matrice(new double[][]{
			{zoom, 0, 0, 0},
			{0, zoom, 0, 0},
			{0, 0, zoom, 0},
			{0, 0, 0, 1.0},
		});
		
		assertArrayEquals(expected.getTableau(), Matrice.getMatriceHomothetie(zoom).getTableau());
	
	}
	
	
	

}
