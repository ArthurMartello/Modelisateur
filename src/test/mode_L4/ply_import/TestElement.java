package mode_L4.ply_import;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import mode_L4.model.modele.ply_import.Element;

public class TestElement {

	@Test
	public void testTrouverElement() {

		Element element = new Element("element vertex 10");
		assertTrue(element.trouverElement());

	}

	@Test
	public void testTrouverType() {

		Element element = new Element("element face 10");
		assertTrue(element.trouverType());

	}

	@Test
	public void testTrouverNombre() {

		Element element = new Element("element vertex 1");
		assertTrue(element.trouverType());

	}

}
