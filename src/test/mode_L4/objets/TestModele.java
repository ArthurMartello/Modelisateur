package mode_L4.objets;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.objets.Face;
import mode_L4.model.modele.objets.Matrice;
import mode_L4.model.modele.objets.Point;

public class TestModele {

	@Test
	public void testGetCentre() {

		Modele modele = new Modele();		
		modele.ajouterPoint(new Point(0, 0, 0));		
		assertTrue(0.0 == modele.getCentre().getY());

		modele.ajouterPoint(new Point(5, 5, 5));
		assertTrue(2.5 == modele.getCentre().getX());

		modele.ajouterPoint(new Point(10, 10, 10));
		assertTrue(5.0 == modele.getCentre().getZ());

	}
	
	@Test
	public void testGetMatrice() {
		
		Modele modele = new Modele();		
		modele.ajouterPoint(new Point(0, 1, 2));		
		modele.ajouterPoint(new Point(3, 4, 5));
		
		Matrice expected = new Matrice(new double[][] {
			{0, 3},
			{1, 4},
			{2, 5},
			{1.0, 1.0}
		});
		
		assertArrayEquals(expected.getTableau(), modele.getMatrice().getTableau());
		
		modele.ajouterPoint(new Point(6, 7, 8));
		
		expected = new Matrice(new double[][] {
			{0, 3, 6},
			{1, 4, 7},
			{2, 5, 8},
			{1.0, 1.0, 1.0}
		});

		assertArrayEquals(expected.getTableau(), modele.getMatrice().getTableau());
		
	}
	
	@Test
	public void testGetMatriceCentre() {

		Modele modele = new Modele();
		modele.ajouterPoint(new Point(0, 0, 0));
		modele.ajouterPoint(new Point(5, 5, 5));

		assertArrayEquals(new Point(2.5, 2.5, 2.5).getTableau(), modele.getCentre().getTableau(), 10);

		modele.ajouterPoint(new Point(10, 10, 10));
		assertArrayEquals(new Point(5, 5, 5).getTableau(), modele.getCentre().getTableau(), 10);

	}

	@Test
	public void testTrierListeFaces() {

		Modele actual = new Modele();
		Modele expected = new Modele();

		// barycentre = 5.0
		List<Point> listePoints1 = new ArrayList<>();
		listePoints1.add(new Point(0, 0, 10));
		listePoints1.add(new Point(0, 0, 5));
		listePoints1.add(new Point(0, 0, 0));

		// barycentre = 2.5
		List<Point> listePoints2 = new ArrayList<>();
		listePoints2.add(new Point(0, 0, 5));
		listePoints2.add(new Point(0, 0, 2.5));
		listePoints2.add(new Point(0, 0, 0));

		// barycentre = 0.0
		List<Point> listePoints3 = new ArrayList<>();
		listePoints3.add(new Point());
		listePoints3.add(new Point());
		listePoints3.add(new Point());

		actual.ajouterFace(new Face(listePoints2));
		actual.ajouterFace(new Face(listePoints1));
		actual.ajouterFace(new Face(listePoints3));

		expected.ajouterFace(new Face(listePoints3));
		expected.ajouterFace(new Face(listePoints2));
		expected.ajouterFace(new Face(listePoints1));

		for (int i = 0; i < actual.getListeFaces().size(); i++) {
			assertFalse(
					actual.getListeFaces().get(i).getBarycentreZ() == expected.getListeFaces().get(i).getBarycentreZ());
		}

		actual.trierListeFaces();

		for (int i = 0; i < actual.getListeFaces().size(); i++) {
			assertTrue(
					actual.getListeFaces().get(i).getBarycentreZ() == expected.getListeFaces().get(i).getBarycentreZ());
		}

	}
}
