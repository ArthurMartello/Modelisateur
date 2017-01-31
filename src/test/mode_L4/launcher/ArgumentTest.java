package mode_L4.launcher;

import static org.junit.Assert.*;
import org.junit.Test;

import mode_L4.launcher.Argument;

public class ArgumentTest {

	@Test
	public void testVerifierFichier() {
		Argument arg = new Argument(new String[]{"../data/cow.ply"});
		assertEquals("../data/cow.ply", arg.verifierFichier("../data/cow.ply"));
		assertNotEquals("../data/lol.ply", arg.verifierFichier("../data/cow.ply"));
	}

	@Test
	public void testIsValide() {
		Argument arg = new Argument(new String[]{"../data/cow.ply"});
		Argument arg2 = new Argument(new String[]{"-s", "../data/cow.ply"});
		Argument arg3 = new Argument(new String[]{"-f", "../data/cow.ply"});
		Argument arg4 = new Argument(new String[]{"-fs", "../data/cow.ply"});
		assertTrue(arg.isValide());
		assertTrue(arg2.isValide());
		assertTrue(arg3.isValide());
		assertTrue(arg4.isValide());
	}

	@Test
	public void testAfficherFaces() {
		Argument arg = new Argument(new String[]{"../data/cow.ply"});
		Argument arg2 = new Argument(new String[]{"-s", "../data/cow.ply"});
		Argument arg3 = new Argument(new String[]{"-f", "../data/cow.ply"});
		Argument arg4 = new Argument(new String[]{"-fs", "../data/cow.ply"});
		assertTrue(arg.afficherFaces());
		assertFalse(arg2.afficherFaces());
		assertTrue(arg3.afficherFaces());
		assertTrue(arg4.afficherFaces());
	}

	@Test
	public void testAfficherSegments() {
		Argument arg = new Argument(new String[]{"../data/cow.ply"});
		Argument arg2 = new Argument(new String[]{"-s", "../data/cow.ply"});
		Argument arg3 = new Argument(new String[]{"-f", "../data/cow.ply"});
		Argument arg4 = new Argument(new String[]{"-fs", "../data/cow.ply"});
		assertFalse(arg.afficherSegments());
		assertTrue(arg2.afficherSegments());
		assertFalse(arg3.afficherSegments());
		assertTrue(arg4.afficherSegments());
	}

	@Test
	public void testGetAdresse() {
		Argument arg = new Argument(new String[]{"../data/cow.ply"});
		assertEquals("../data/cow.ply", arg.getAdresse());
		assertNotEquals("../data/lol.ply", arg.getAdresse());
	}

}
