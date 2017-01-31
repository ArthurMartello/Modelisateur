package mode_L4.model.modele.ply_import;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe definissant un objet Element destine a definir le nombre de points et de faces
 *
 */
public class Element {

	// Ligne dans le fichier .ply
	private String ligne = "";

	// Type d'element (vertex pour les points, face pour les faces)
	private String type = "";

	// Nombre de points/faces selon le type
	private int nombre = -1;

	// True si l'element est dans le bon format
	@SuppressWarnings("unused")
	private boolean valide;

	// Liste des proprietes de l'element
	private List<Property> properties = new ArrayList<>();

	/**
	 * Constructeur: cree des elements a partir d'une ligne d'un fichier .ply
	 * 
	 * @param ligne
	 * @throws FormatException
	 */
	public Element(String ligne) {

		this.ligne = ligne.trim();
		this.ligne = ligne.replaceAll("( ){2,}", " ");
		this.ligne = ligne.replaceAll("\n", "");

		this.valide = trouverElement() && trouverType() && trouverNombre();
	}

	/**
	 * Retourne vrai si un tag element a ete trouve
	 * @return
	 */
	public boolean trouverElement() {

		try {
			if (!this.ligne.matches("^element\\s.*$")) {
				throw new FormatException("La ligne " + ligne + " ne contient pas le mot-clef #element");
			}
		} catch (FormatException e) {
			System.out.println(e.toString());
		}
		return true;

	}

	/**
	 * Retourne vrai si un type valide a ete trouve et le definit
	 * @return
	 */
	public boolean trouverType() {

		String type = "";

		type = this.ligne.substring("element ".length());

		try {

			if (type.matches("^vertex\\s.*$")) {
				this.type = "vertex";
				return true;
			} else if (type.matches("^face\\s.*$")) {
				this.type = "face";
				return true;
			} else {
				throw new FormatException("Type pour #element inconnu.");
			}

		} catch (FormatException e) {
			System.out.println(e.toString());
			return false;
		}
		

	}

	/**
	 * Retourne vrai si un nombre valide a ete trouve et le definit
	 * @return
	 */
	public boolean trouverNombre() {

		String nombre = "";

		int i = -1;

		if (this.type.equals("vertex")) {
			nombre = this.ligne.substring("element".length() + type.length() + 2);
		} else {
			nombre = this.ligne.substring("element".length() + type.length() + 2);
		}

		try {
			i = Integer.valueOf(nombre);
		} catch (NumberFormatException e) {
			System.out.println(e.toString());
			return false;
		}

		this.nombre = i;
		return true;

	}

	/**
	 * Ajoute une propriete a la liste des proprietes.
	 * 
	 * @param ligne
	 */
	public void ajouterProperty(String ligne) {

		Property p = new Property(ligne);

		try {
			if (p.isValide()) {
				this.properties.add(p);
			} else {
				throw new UninitializedException("Impossible d'ajouter une propri�t� non valide.");
			}
		} catch (UninitializedException e) {
			System.out.println(e.toString());
		}

	}

	/***
	 * Retourne le nombre de proprietes (cf PlyFormat.Property.java)
	 * @return
	 */
	public int nombreProperties() {
		return this.properties.size();
	}

	/**
	 * Retourne le nombre de points ou de faces
	 * 
	 * @return
	 */
	public int getNombre() {

		if (this.nombre == -1) {
			try {
				throw new UninitializedException("Le nombre de points ou de faces n'est pas encore initialis�");
			} catch (UninitializedException e) {
				System.out.println(e.toString());
			}
		}
		return nombre;

	}

	/**
	 * Definit le nombre de points ou de faces manuellement
	 * 
	 * @param nombre
	 */
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retourne le type d'element
	 * 
	 * @return
	 */
	public String getType() {

		if (this.type.equals("")) {
			try {
				throw new UninitializedException("Le type n'est pas encore initialis�");
			} catch (UninitializedException e) {
				System.out.println(e.toString());
			}
		}
		return type;
	}

	/**
	 * Retourne le nombre de dimensions 
	 * @return
	 */
	public int getNbDeDimensions() {

		if (this.getType().equals("vertex")) {
			return this.properties.size();
		} else {
			try {
				throw new Exception("Impossible de savoir le nombre de dimensions d'une face par cette methode");
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		return -1;

	}

	/**
	 * Retourne l'element sous forme de chaine
	 */
	public String toString() {
		String retour = "";
		retour = "element " + this.getType() + " " + this.getNombre();
		return retour;
	}
}
