package mode_L4.maths;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.maths.Translation;
import mode_L4.model.modele.objets.Point;
import mode_L4.model.modele.objets.Vecteur;

public class TestTranslation {
	
	private Modele modele;
	
	@Before
	public void before() {
		modele = new Modele();		
		modele.ajouterPoint(new Point(0, 1, 2));		
		modele.ajouterPoint(new Point(3, 4, 5));
		modele.ajouterPoint(new Point(5, 4, 5));
		modele.ajouterPoint(new Point(2, 4, 5));
	}
	
	@Test
	public void testTranslationOf0() {
		assertArrayEquals(modele.getMatrice().getTableau(), new Translation(modele, new Vecteur(0, 0, 0)).getModele().getMatrice().getTableau());
	}
	
	@Test
	public void testTranslationBy1OnAbscissaAxis() {
		double[][] expected = new double[][] {
			{-1.0 , 2.0 , 4.0 , 1.0},
			{1.0 , 4.0 , 4.0 , 4.0},
			{2.0 , 5.0 , 5.0 , 5.0},
			{1.0 , 1.0 , 1.0 , 1.0}
		};
		assertArrayEquals(expected, new Translation(modele, new Vecteur(1, 0, 0)).getModele().getMatrice().getTableau());
	}
	
	@Test
	public void testTranslationBy1OnOrdinateAxis() {
		double[][] expected = new double[][] {
			{0.0 , 3.0 , 5.0 , 2.0},
			{0.0 , 3.0 , 3.0 , 3.0},
			{2.0 , 5.0 , 5.0 , 5.0},
			{1.0 , 1.0 , 1.0 , 1.0}
		};
		assertArrayEquals(expected, new Translation(modele, new Vecteur(0, 1, 0)).getModele().getMatrice().getTableau());
	}
	
	@Test
	public void testTranslationBy1OnSideAxis() {
		double[][] expected = new double[][] {
			{0.0 , 3.0 , 5.0 , 2.0},
			{1.0 , 4.0 , 4.0 , 4.0},
			{1.0 , 4.0 , 4.0 , 4.0},
			{1.0 , 1.0 , 1.0 , 1.0}
		};
		assertArrayEquals(expected, new Translation(modele, new Vecteur(0, 0, 1)).getModele().getMatrice().getTableau());
	}

}
