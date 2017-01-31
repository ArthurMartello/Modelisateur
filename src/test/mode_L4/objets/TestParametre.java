package mode_L4.objets;

import static org.junit.Assert.*;

import org.junit.Test;

import mode_L4.model.modele.Parametre;

public class TestParametre {

	private Parametre parametre = new Parametre();

	@Test
	public void testGetNombre() {
		assertTrue(6 == parametre.getNombre());
	}

	@Test
	public void testGetNom() {
		assertEquals("points", parametre.getNom(0));
		assertEquals("grille", parametre.getNom(5));
		assertEquals("faces", parametre.getNom(2));
		assertEquals("segments", parametre.getNom(1));
		assertEquals("lumiere", parametre.getNom(3));
		assertEquals("ombre", parametre.getNom(4));
	}

	@Test
	public void testGetJoliNom() {
		assertEquals("Points", parametre.getJoliNom(0));
		assertEquals("Grille", parametre.getJoliNom(5));
		assertEquals("Faces", parametre.getJoliNom(2));
		assertEquals("Segments", parametre.getJoliNom(1));
		assertEquals("Lumiere", parametre.getJoliNom(3));
		assertEquals("Ombre", parametre.getJoliNom(4));
	}

	@Test
	public void testGetParametreString() {
		assertFalse(parametre.getParametre("points"));
		assertTrue(parametre.getParametre("faces"));
	}

	@Test
	public void testGetParametreInt() {
		assertFalse(parametre.getParametre(0));
	}

	@Test
	public void testSetParametre() {
		parametre.setParametre("points", false);
		assertFalse(parametre.getParametre("points"));
	}

	@Test
	public void testChangerParametreString() {
		boolean unexpected = parametre.getParametre("points");
		parametre.changerParametre("points");
		assertSame(!unexpected, parametre.getParametre("points"));
	}

	@Test
	public void testChangerParametreInt() {
		boolean unexpected = parametre.getParametre(0);
		parametre.changerParametre(0);
		assertSame(!unexpected, parametre.getParametre(0));
	}

}
