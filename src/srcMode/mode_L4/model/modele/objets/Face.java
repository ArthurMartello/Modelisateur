package mode_L4.model.modele.objets;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Objet Face compose de n Points ranges dans une liste.
 *
 */
public class Face implements Figure, Comparable<Face> {

	private List<Point> listePoints = new ArrayList<>();
	private Point barycentre = new Point();

	/**
	 * Constructeur: cree une face a partir d'une liste de points
	 * 
	 * @param listePoints
	 */
	public Face(List<Point> listePoints) {
		this.listePoints = listePoints;
		this.setBarycentre();
	}

	/**
	 * Constructeur par defaut
	 */
	public Face() {
	}

	/**
	 * Retourne le barycentre de la face
	 * 
	 * @return
	 */
	public double getBarycentreZ() {
		return this.barycentre.getZ();
	}

	/**
	 * Compare une face par rapport a celle-ci en fonction de leur barycentre
	 * des points sur l'axe Z
	 */
	public int compareTo(Face f) {
		if (this.getBarycentreZ() > f.getBarycentreZ()) {
			return 1;
		} else if (this.getBarycentreZ() < f.getBarycentreZ()) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * Calcul et definit le barycentre de la face
	 */
	public void setBarycentre() {

		double moyX = 0;
		double moyY = 0;
		double moyZ = 0;

		for (int i = 0; i < listePoints.size(); i++) {
			// moyX += listePoints.get(i).getX();
			// moyY += listePoints.get(i).getY();
			moyZ += listePoints.get(i).getZ();
		}

		if (listePoints.size() > 0) {
			// moyX /= listePoints.size();
			// moyY /= listePoints.size();
			moyZ /= listePoints.size();
		}

		this.barycentre = new Point(moyX, moyY, moyZ);
	}

	private Vecteur getVecteurNormal() {

		if (this.getListePoints().size() >= 3) {

			Vecteur ab = new Vecteur(this.getListePoints().get(0), this.getListePoints().get(1));
			Vecteur ac = new Vecteur(this.getListePoints().get(0), this.getListePoints().get(2));

			return ab.produitVectoriel(ac);
		}
		return new Vecteur();

	}

	private double getTauxLumiere(Vecteur l) {

		double scalaire = l.produitScalaire(this.getVecteurNormal());
		double normes = l.getNorme() * this.getVecteurNormal().getNorme();

		if (normes == 0) {
			return 0;
		}

		return Math.abs(scalaire / normes);
	}

	/**
	 * Retourne la nuance de gris d'une face en fonction de son orientation
	 * lumineuse face a un vecteur
	 * 
	 * @param l
	 *            Vecteur representant l'angle de la lumiere
	 * @return un objet Color
	 */
	public Color getColor(Vecteur l) {

		int gris = (int) (this.getTauxLumiere(l) * 255);
		return new Color(gris, gris, gris);

	}

	@Override
	public Matrice getMatrice() {

		double[][] matrice = new double[4][listePoints.size()];

		for (int i = 0; i < matrice[0].length; i++) {

			for (int j = 0; j < matrice.length; j++) {

				matrice[j][i] = listePoints.get(i).getMatrice().getTableau()[j][0];

			}
		}

		return new Matrice(matrice);
	}

	/**
	 * Retourne la liste des points de la face
	 * 
	 * @return la liste des points de la face
	 */
	public List<Point> getListePoints() {
		return listePoints;
	}

	/**
	 * Ajoute un Point a la liste des poits d'une Face
	 * 
	 * @param un
	 *            objet Point
	 */
	public void ajouterPoint(Point point) {
		this.listePoints.add(point);
		this.setBarycentre();
	}

	@Override
	public String toString() {

		String r = "";
		for (int i = 0; i < listePoints.size(); i++) {
			r += listePoints.get(i) + "\n";
		}

		return r;
	}
}
