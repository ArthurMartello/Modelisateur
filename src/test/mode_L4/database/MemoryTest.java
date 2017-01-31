package mode_L4.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import mode_L4.model.bdd.Memory;
import mode_L4.model.bdd.ModeleData;
import mode_L4.model.modele.ply_import.ModeleFileReader;

public class MemoryTest {

	private Memory memory = new Memory();
	private ModeleData data = new ModeleData("teapot", "teapot.ply", "valueDate", "tag1, tag2, tag3");

	@Test
	public void testIsEmpty() {
		assertTrue(memory.isEmpty());
	}

	@Test
	public void testSize() {
		assertTrue(memory.size() == 0);
	}

	@Test
	public void testAddToMemory() {
		memory.addToMemory(data);
		assertTrue(memory.size() == 1);
	}

	@Test
	public void testRemoveFromMemory() {
		memory.removeFromMemory(data);
		assertTrue(memory.size() == 0);
	}

	@Test
	public void testGetFromMemory() {
		memory.addToMemory(data);
		assertEquals(new ModeleFileReader(data).toString(), memory.getFromMemory(data).toString());
	}
	
	@Test
	public void testGetCurrent() {
		memory.addToMemory(data);
		memory.getFromMemory(data).toString();
		assertEquals(data, memory.getCurrent());
	}
	
	@Test
	public void testResetCurrent() {
		memory.resetCurrent();
		assertEquals(new ModeleData().toString(), memory.getCurrent().toString());
	}

	@Test
	public void testIsSaved() {
		memory.addToMemory(data);
		assertTrue(memory.isSaved(data));
	}

}
