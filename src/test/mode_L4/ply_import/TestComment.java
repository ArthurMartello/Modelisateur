package mode_L4.ply_import;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mode_L4.model.modele.ply_import.Comment;

public class TestComment {

	String ligne = "#comment SwiftRage SadFace";
	String ligne1 = "#comments BabyRage";
	String ligne2 = "comment Kappa";
	String ligne3 = "KappaPride";
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}
	
	@Test
	public void ConstructorTest() {
		Comment com = new Comment(ligne);
		assertTrue(com.getValide());
		com = new Comment(ligne1);
		assertTrue(com.getValide());
		com = new Comment(ligne2);
		assertFalse(com.getValide());
		com = new Comment(ligne3);
		assertFalse(com.getValide());
	}
	
	@Test
	public void TrouverMessageTest(){
		Comment com = new Comment(ligne);
		
		assertFalse("jdhqdfjhqiufd".equals(com.getMessage()));
		assertTrue("SwiftRage SadFace".equals(com.getMessage()));
		com = new Comment(ligne1);
		assertFalse("qdsfqsdf".equals(com.getMessage()));
		assertTrue("BabyRage".equals(com.getMessage()));
		com = new Comment(ligne2);
		assertFalse(com.getValide());
		
	}

	@Test
	public void afficherCommentairesTest(){
		new Comment(ligne);
		Comment.afficherCommentaires();
		assertEquals("Comments:\n> SwiftRage SadFace\n", outContent.toString());
	}
}
