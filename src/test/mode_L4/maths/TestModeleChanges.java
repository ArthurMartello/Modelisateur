package mode_L4.maths;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.maths.ModeleChanges;
import mode_L4.model.modele.objets.Matrice;
import mode_L4.model.modele.objets.Point;

public class TestModeleChanges {
	
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
	public void testSetChanges() {
		Matrice expected = modele.getMatrice();
		new ModeleChanges(modele).setChanges(modele.getMatrice());
		assertArrayEquals(expected.getTableau(), modele.getMatrice().getTableau());
	}
	
	@Test
	public void testGetModele() {
		assertEquals(modele, new ModeleChanges(modele).getModele());
	}
	
	

}
