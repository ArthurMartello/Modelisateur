package mode_L4.model.modele.ply_import;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import mode_L4.model.bdd.ModeleData;
import mode_L4.model.modele.Modele;
import mode_L4.model.modele.maths.Cadrage;
import mode_L4.model.modele.objets.Face;
import mode_L4.model.modele.objets.Point;
import mode_L4.view.fenetre.Frame;
import mode_L4.view.fenetre.FrameObservable;

/**
 * Class destinee a importer un fichier .ply et de lui en extraire une liste de
 * points et de faces dans la Superclass Modele
 * 
 * @author Arthur Martello
 *
 */
public class ModeleFileReader extends Modele {

	private String adresse = "";
	private List<String> lignes = new ArrayList<>();
	private int nbPoints = -1;
	private int nbFaces = -1;
	private int dimensions = 3;
	public static boolean valide = true;

	/**
	 * Initalise le Modele depuis un objet de la base de données.
	 * 
	 * @param modeleData
	 *            ModeleData représentant les valeurs d'un Modèle dans la base
	 *            de données.
	 */
	public ModeleFileReader(ModeleData modeleData) {

		valide = true;

		this.adresse = "../data/" + modeleData.getPath();
		if (creation() && verifierFormat() && verifierMotsClefs()
				&& verifierNombreDeCoord(this.nbPoints, this.nbFaces)) {

			lectureCoord();
			this.setBorder(FrameObservable.DEFAULTXSIZE, FrameObservable.DEFAULTYSIZE);
			new Cadrage(this, this.borderX - Frame.OFFSETX, this.borderY - Frame.OFFSETY);
		} else {
			System.err.println(adresse + " : Impossible d'importer le fichier specifie (Format invalide)");
		}

	}

	private boolean creation() {

		try {

			// Verification du format .ply
			if (!adresse.endsWith(".ply")) {
				throw new FormatException(adresse + " (Fichier invalide. Le fichier n'est pas au format ply.)");
			}

			// Import ligne par ligne dans this.lignes
			InputStream ips = new FileInputStream(adresse);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			try {
				while ((ligne = br.readLine()) != null) {
					this.lignes.add(ligne + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			br.close();

			return true;

		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
			// JOptionPane.showMessageDialog(null, e.toString(), "Fichier
			// introuvable", 0);
			// System.exit(1);
			return false;
		} catch (IOException e) {
			System.out.println(e.toString());
			return false;
		} catch (FormatException e) {
			System.out.println(e.toString());
			// System.exit(1);
			return false;
		}
	}

	private boolean verifierFormat() {

		int tailleMax = endHeaderLine();

		// Verification des elements recurrents (element, property, comment)
		for (int i = 0; i < tailleMax; i++) {

			// Test des Elements et des Properties
			if (this.lignes.get(i).replace("\n", "").matches("^element\\s.*$")) {

				// Creation d'un Element via sa classe
				Element element = new Element(this.lignes.get(i));

				// Verification des properietes et recuperation du nombre de
				// points, faces, dimensions.
				int nbProperties = 0;

				if (element.getType().equals("vertex")) {
					this.nbPoints = element.getNombre();
					nbProperties = 3;

				} else if (element.getType().equals("face")) {
					this.nbFaces = element.getNombre();
					nbProperties = 1;

				}

				for (int j = i; j < i + nbProperties && j < tailleMax; j++) {
					element.ajouterProperty(this.lignes.get(j + 1));
				}

				if (element.getType().equals("vertex")) {
					this.dimensions = element.getNbDeDimensions();
				}

			} else

			// Test des Comments
			if (this.lignes.get(i).replace("\n", "").matches("^comment\\s.*$")) {
				new Comment(this.lignes.get(i));
			}

		}

		try {

			if (!this.lignes.get(0).replace("\n", "").matches("^ply$")) {
				throw new FormatException("La première ligne du fichier doit être \"ply\". Ici: \""
						+ lignes.get(0).replace("\n", "") + "\".");
			}
			if (!this.lignes.get(1).replace("\n", "").matches("^format\\s.*$")) {
				throw new FormatException("Impossible de détecter le format à la deuxième ligne. Ici: \""
						+ lignes.get(1).replace("\n", "") + "\".");
			}

		} catch (FormatException e) {
			System.out.println(e.toString());
			return false;
		}

		return true;
	}

	// Verifie les mots-clefs utilises et leur nombre
	private boolean verifierMotsClefs() {

		String[] authorized = new String[] { "^ply$", "^format\\s.*$", "^comment\\s.*$", "^element\\svertex\\s.*$",
				"^element\\sface\\s.*$", "^property\\s.*$", "^end_header$" };
		int[] unique = new int[authorized.length];

		for (int i = 0; i < unique.length; i++) {
			unique[i] = 0;
		}

		int tailleMax = endHeaderLine();

		for (int i = 0; i < tailleMax; i++) {

			boolean ligneValide = false;
			for (int j = 0; j < authorized.length; j++) {
				if (this.lignes.get(i).replace("\n", "").matches(authorized[j])) {
					ligneValide = true;
					unique[j]++;
				}
			}

			try {
				if (!ligneValide) {
					throw new FormatException("\"" + this.lignes.get(i).replace("\n", "") + "\" (Ligne " + (i + 1)
							+ "): Mot-clef inconnu.");
				}
			} catch (FormatException e) {
				System.out.println(e.toString());
				return false;
			}
		}

		try {
			if (unique[0] > 1 || unique[1] > 1 || unique[3] > 1 || unique[4] > 1 || unique[6] > 1) {
				throw new FormatException("Un mot clef unique est designe en double.");
			}
		} catch (FormatException e) {
			System.out.println(e.toString());
			return false;
		}

		return true;

	}

	// Retourne vrai si le nombre de points + faces correspond bien a celui sur
	// le fichier.
	private boolean verifierNombreDeCoord(int pts, int fcs) {

		try {

			if (this.nbPoints == -1 || this.nbFaces == -1) {
				throw new UninitializedException(
						"Les points et les faces ne peuvent être initialisés si leur nombre n'a pas été définit.");
			}

			int expect = pts + fcs;
			int here = this.lignes.size() - 1 - endHeaderLine();

			if (here != expect) {
				throw new FormatException(
						"Nombre de points et/ou faces incorrect: Attendu: " + expect + " - Ici: " + here);
			}

		} catch (FormatException e) {
			System.out.println(e.toString());
			return false;
		} catch (UninitializedException e) {
			System.out.println(e.toString());
			return false;
		}

		return true;
	}

	// Lit les coordonnes des points en fonction du nombre de points
	private void lectureCoord() {

		int taille = endHeaderLine() + 1;

		// Lecture des points
		for (int i = taille; i < taille + this.nbPoints; i++) {

			double[] coord = decouperPoint(lignes.get(i), this.dimensions);
			Point point = new Point(coord[0], coord[1], 0);

			if (this.dimensions == 3) {
				point.setZ(coord[2]);
			}

			super.ajouterPoint(point);
		}

		// Lecture des faces
		for (int i = taille + this.nbPoints; i < taille + this.nbPoints + this.nbFaces; i++) {

			if (valide) {
				Face face = new Face();
				face = decouperFace(lignes.get(i));
				super.ajouterFace(face);
			}

		}

	}

	private double[] decouperPoint(String ligne, int dim) {

		String chaine = ligne.replaceAll("( ){2,}", " ");
		chaine = chaine.trim();

		if (verifierLignePoint(chaine, dim)) {

			String[] tabloChaines = new String[dim];
			double[] tabloNombres = new double[dim];

			for (int j = 0; j < tabloChaines.length; j++) {

				tabloChaines[j] = "";

				int cpt = 0;
				for (int i = 0; cpt <= dim - 1 && i < chaine.length(); i++) {

					if (chaine.charAt(i) != ' ') {
						tabloChaines[cpt] += chaine.charAt(i);
					} else {
						cpt++;
					}
				}

				tabloNombres[j] = Double.valueOf(tabloChaines[j]);

			}

			return tabloNombres;

		} else {

			try {
				throw new FormatException(
						"Erreur sur la ligne: \"" + ligne.replaceAll("\n", "") + "\" (Format de point invalide)");
			} catch (FormatException e) {
				System.out.println(e.toString());
			}

		}

		return new double[] { 0, 0, 0 };

	}

	public static boolean verifierLignePoint(String ligne, int dim) {

		String pattern = "^(-?\\d+(e?\\.?-?\\d+)*)";

		for (int i = 1; i < dim; i++) {
			pattern += "\\s(-?\\d+(e?\\.?-?\\d+)*)";
		}

		pattern += "$";

		return ligne.replaceAll("(\\s){2,}", " ").matches(pattern);

	}

	public static boolean verifierLigneFace(String ligne) {

		if (!ligne.matches("^\\d(\\s*\\d*)*$")) {
			return false;
		}

		int taille = Integer.valueOf(ligne.charAt(0) - 47);
		String chaine = ligne.replaceAll("(\\s)", " ").replaceAll("( ){2,}", " ");

		int nbSpaces = 0;
		for (int i = 0; i < chaine.length(); i++) {

			if (chaine.charAt(i) == ' ') {
				nbSpaces++;
			}
		}

		return nbSpaces == taille;

	}

	private Face decouperFace(String ligne) {

		if (verifierLigneFace(ligne)) {

			String chaine = ligne.replaceAll("\n", "").replaceAll("(\\s)", " ").replaceAll("( ){2,}", " ");
			int taille = Integer.valueOf(ligne.charAt(0) - 47);

			Face face = new Face();
			String[] tabloChaines = new String[taille];
			int[] tabloNombres = new int[taille];

			for (int j = 0; j < taille; j++) {

				tabloChaines[j] = "";

				int cpt = 0;
				for (int i = 0; cpt <= taille - 1 && i < chaine.length(); i++) {

					if (chaine.charAt(i) != ' ') {
						tabloChaines[cpt] += chaine.charAt(i);
					} else {
						cpt++;
					}
				}

				if (j >= 1) {

					if (Integer.valueOf(tabloChaines[j]) < this.nbPoints) {
						tabloNombres[j] = Integer.valueOf(tabloChaines[j]);

						Point point = super.getListePoints().get(tabloNombres[j]);
						face.ajouterPoint(point);

					} else {
						try {
							throw new FormatException("\"" + tabloChaines[j] + "\" - Point inexistant");
						} catch (FormatException e) {
							System.out.println(e.toString());
						}
					}
				}
			}

			return face;

		} else {

			try {
				throw new FormatException(
						"Erreur sur la ligne: \"" + ligne.replaceAll("\n", "") + "\" (Format de face invalide)");
			} catch (FormatException e) {
				System.out.println(e.toString());
			}
		}

		return null;

	}

	// Retourne la ligne de la fin de l'entete
	private int endHeaderLine() {

		try {

			for (int i = 0; i < lignes.size(); i++) {

				if (lignes.get(i).replace("\n", "").matches("^end_header$")) {
					return i;
				}
			}
			throw new FormatException("Aucun #end_header spécifié dans le fichier.");

		} catch (FormatException e) {
			System.out.println(e.toString());
			return -1;
		}

	}

	/**
	 * Rend l'import invalide et empeche l'execution des actions suivantes.
	 */
	public static void unvalid() {
		valide = false;
	}

	/**
	 * Retourne la validite du modele
	 * 
	 * @return vrai si l'import n'a pas rencontré d'erreurs
	 */
	public static boolean estValide() {
		return valide;
	}

}
