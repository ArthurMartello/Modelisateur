package mode_L4.maths;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.maths.Zoom;
import mode_L4.model.modele.objets.Point;

public class TestZoom {
	
	private Modele modele;
	
	@Test
	public void testZoomWithZeroPoints() {		
		modele = new Modele();
		assertArrayEquals(modele.getMatrice().getTableau(), new Zoom(modele, 1).getModele().getMatrice().getTableau());
	}
	
	@Test
	public void testZoomWithNullPoints() {		
		modele = new Modele();		
		modele.ajouterPoint(new Point());		
		modele.ajouterPoint(new Point());
		modele.ajouterPoint(new Point());		
		assertArrayEquals(modele.getMatrice().getTableau(), new Zoom(modele, 2).getModele().getMatrice().getTableau());
	}
	
	@Test
	public void testZoomBy2() {
		modele = new Modele();
		modele.ajouterPoint(new Point(1.0, 2.0, 3.0));		
		modele.ajouterPoint(new Point(1.0, 2.0, 3.0));
		modele.ajouterPoint(new Point(1.0, 2.0, 3.0));
		
		double[][] expected = new double[][] {
			{1.0 , 1.0, 1.0},
			{2.0 , 2.0, 2.0},
			{3.0 , 3.0, 3.0},
			{1.0 , 1.0, 1.0}
		};		
		assertArrayEquals(expected, new Zoom(modele, 2).getModele().getMatrice().getTableau());
	}

}
