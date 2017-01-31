package mode_L4.database;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mode_L4.model.bdd.Connexion;
import mode_L4.model.bdd.ModeleDAO;
import mode_L4.model.bdd.ModeleData;

public class ModeleDAOTest {
	
	private Connexion con;
	private ModeleDAO dao;
	
	@Before
	public void before() {
		System.setOut(new PrintStream(new ByteArrayOutputStream()));
		System.setErr(new PrintStream(new ByteArrayOutputStream()));
		con = new Connexion("../test-data/test-modeles.db");
		dao = new ModeleDAO(con);		
	}
	
	@After
	public void after() {
		try {
			Connexion.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM Modele WHERE \"name\" = \"valueName\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.setOut(null);
		System.setErr(null);
	}
	
	@Test
	public void testGetModeles() {
		ModeleData data = new ModeleData("valueName", "valuePath", "valueDate", "tag1, tag2, tag3");
		dao.insert(data);
		List<ModeleData> expected = new ArrayList<>();
		expected.add(data);
		assertEquals(expected.size(), dao.getModeles().size());
	}

	@Test
	public void testGetModeleByName() {
		ModeleData data = new ModeleData("valueName", "valuePath", "valueDate", "tag1, tag2, tag3");
		dao.insert(data);
		assertEquals(data.toString(), dao.getModeleByName("valueName").toString());
	}
	
	@Test
	public void testGetModeleByNameWithError() {
		ModeleData data = new ModeleData("valueName", "valuePath", "valueDate", "tag1, tag2, tag3");
		dao.insert(data);
		assertTrue(dao.getModeleByName("unexistingName") == null);
	}
	
	@Test
	public void testGetModeleByPath(){
		ModeleData data = new ModeleData("valueName", "valuePath", "valueDate", "tag1, tag2, tag3");
		dao.insert(data);
		assertEquals(data.toString(), dao.getModeleByPath("../data/valuePath").toString());
	}
	
	@Test
	public void testGetModeleByPathWithError() {
		ModeleData data = new ModeleData("valueName", "valuePath", "valueDate", "tag1, tag2, tag3");
		dao.insert(data);
		assertTrue(dao.getModeleByPath("unexistingPath") == null);
	}

	@Test
	public void testGetModeleByTag() {
		ModeleData data = new ModeleData("valueName", "valuePath", "valueDate", "tag1, tag2, tag3");
		dao.insert(data);
		List<ModeleData> expected = new ArrayList<>();
		expected.add(data);
		assertEquals(expected.size(), dao.getModeleByTag("tag1").size());		
	}

	@Test
	public void testInsert() {
		ModeleData data = new ModeleData("valueName", "valuePath", "valueDate", "tag1, tag2, tag3");
		assertTrue(dao.insert(data));
	}
	
	@Test
	public void testInsertWithEmptyName() {
		ModeleData data = new ModeleData("", "valuePath", "valueDate", "tag1, tag2, tag3");
		assertFalse(dao.insert(data));
	}
	
	@Test
	public void testInsertWithEmptyPath() {
		ModeleData data = new ModeleData("valueName", "", "valueDate", "tag1, tag2, tag3");
		assertFalse(dao.insert(data));
	}
	
	@Test
	public void testInsertWithExistingName() {
		ModeleData data = new ModeleData("valueName", "valuePath", "valueDate", "tag1, tag2, tag3");
		dao.insert(data);
		assertFalse(dao.insert(data));
	}

	@Test
	public void testUpdate() {
		ModeleData data = new ModeleData("valueName", "valuePath", "valueDate", "tag1, tag2, tag3");
		assertTrue(dao.update(data));
	}
	
	@Test
	public void testUpdateWithEmptyName() {
		ModeleData data = new ModeleData("", "valuePath", "valueDate", "tag1, tag2, tag3");
		assertFalse(dao.update(data));
	}
	
	@Test
	public void testUpdateWithEmptyPath() {
		ModeleData data = new ModeleData("valueName", "", "valueDate", "tag1, tag2, tag3");
		assertFalse(dao.update(data));
	}

	@Test
	public void testDelete() {
		ModeleData data = new ModeleData("valueName", "valuePath", "valueDate", "tag1, tag2, tag3");
		dao.insert(data);
		assertTrue(dao.delete(data));
	}
	
	@Test
	public void testDeleteWithUnexistingName() {
		ModeleData data = new ModeleData("valueData", "valuePath", "valueDate", "tag1, tag2, tag3");
		assertFalse(dao.delete(data));
	}

}
