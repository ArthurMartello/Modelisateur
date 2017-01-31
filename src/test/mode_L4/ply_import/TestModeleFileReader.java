package mode_L4.ply_import;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import mode_L4.model.modele.ply_import.ModeleFileReader;

public class TestModeleFileReader {

	@Test
	public void testVerifierLignePoint() {
		
		
		assertTrue(ModeleFileReader.verifierLignePoint("0", 1));		
		assertFalse(ModeleFileReader.verifierLignePoint(" 1", 1));		
		assertTrue(ModeleFileReader.verifierLignePoint("128e28", 1));		
		assertTrue(ModeleFileReader.verifierLignePoint("-2", 1));		
		assertFalse(ModeleFileReader.verifierLignePoint("err", 1));
		assertTrue(ModeleFileReader.verifierLignePoint("0.0001", 1));		
		assertFalse(ModeleFileReader.verifierLignePoint("0.0a-1", 1));
		assertFalse(ModeleFileReader.verifierLignePoint("0.0e-8   ", 1));		
		assertTrue(ModeleFileReader.verifierLignePoint("0 0", 2));
		assertFalse(ModeleFileReader.verifierLignePoint("0 0", 1));
		assertTrue(ModeleFileReader.verifierLignePoint("128e14  1    28.35", 3));
		assertFalse(ModeleFileReader.verifierLignePoint("128e14  1  e  28.35", 3));
		assertFalse(ModeleFileReader.verifierLignePoint("e", 1));
		
	}
	
	@Test
	public void testVerifierLigneFace() {
		
		assertTrue(ModeleFileReader.verifierLigneFace("0\n"));
		assertFalse(ModeleFileReader.verifierLigneFace("0"));
		assertFalse(ModeleFileReader.verifierLigneFace("1\n"));
		assertTrue(ModeleFileReader.verifierLigneFace("1 1\n"));
		assertTrue(ModeleFileReader.verifierLigneFace("2 0 0\n"));
		assertFalse(ModeleFileReader.verifierLigneFace("hello\n"));
		assertFalse(ModeleFileReader.verifierLigneFace("9 9\n"));
		assertFalse(ModeleFileReader.verifierLigneFace("1 hi\n"));
		assertTrue(ModeleFileReader.verifierLigneFace("2   1  2\n"));
		assertTrue(ModeleFileReader.verifierLigneFace("1 10\n"));
		assertFalse(ModeleFileReader.verifierLigneFace("1 0 0\n"));
		
	}
	
	


}
