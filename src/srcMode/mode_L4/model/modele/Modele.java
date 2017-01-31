package mode_L4.model.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mode_L4.model.modele.objets.Face;
import mode_L4.model.modele.objets.Matrice;
import mode_L4.model.modele.objets.Point;

/**
 * Objet comportant tous les points et faces d'un modele importe.
 *
 */
public class Modele extends ModeleObservable {

	/**
	 * Cree un modele vide
	 */
	public Modele() {
		this.listePoints = new ArrayList<>();
		this.listeFaces = new ArrayList<>();
		this.parametres = new Parametre();
	}

	/**
	 * Cree un modele prenant en parametre trois listes
	 * 
	 * @param listePoints
	 * @param listeFaces
	 */
	public Modele(List<Point> listePoints, List<Face> listeFaces) {
		this();
		this.listePoints = listePoints;
		this.listeFaces = listeFaces;
	}

	public Parametre getParametres() {
		return super.parametres;
	}

	public void setParametres(Parametre parametres) {
		this.parametres = parametres;
	}

	public void reimport(Modele modele) {
		this.listePoints = modele.getListePoints();
		this.listeFaces = modele.getListeFaces();
	}
	
	public void setBorder(int x, int y) {
		this.borderX = x;
		this.borderY = y;
	}
	
	public int getMaxWidth() {
		return this.borderX;
	}
	
	public int getMaxHeight() {
		return this.borderY;
	}

	/**
	 * Trie les faces en fonction de leur barycentre
	 */
	public void trierListeFaces() {
		Collections.sort(listeFaces);
	}

	/**
	 * Calcule le point le plus a l'Est et le retourne
	 * 
	 * @return un double representant la plus grande valeur des points en X
	 */
	public double maxX() {
		double max = -2047;
		for (int i = 0; i < listePoints.size(); i++) {
			if (listePoints.get(i).getX() > max) {
				max = listePoints.get(i).getX();
			}
		}
		return max;
	}

	/**
	 * Calcule le point le plus au Sud et le retourne
	 * 
	 * @return un double representant la plus grande valeur des points en Y
	 */
	public double maxY() {
		double max = -2047;
		for (int i = 0; i < listePoints.size(); i++) {
			if (listePoints.get(i).getY() > max) {
				max = listePoints.get(i).getY();
			}
		}
		return max;
	}

	/**
	 * Calcule le point le plus a l'Ouest et le retourne
	 * 
	 * @return un double representant la plus petite valeur des points en X
	 */
	public double minX() {
		double min = 2047;
		for (int i = 0; i < listePoints.size(); i++) {
			if (listePoints.get(i).getX() < min) {
				min = listePoints.get(i).getX();
			}
		}
		return min;
	}

	/**
	 * Calcule le point le plus au Nord et le retourne
	 * 
	 * @return un double representant la plus petite valeur des points en Y
	 */
	public double minY() {
		double min = 2047;
		for (int i = 0; i < listePoints.size(); i++) {
			if (listePoints.get(i).getY() < min) {
				min = listePoints.get(i).getY();
			}
		}
		return min;
	}

	/**
	 * Retourne une matrice composee de tous les points du modele
	 * 
	 * @return une matrice
	 */
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
	 * Calcule le centre du modele a partir de tous ces points.
	 * 
	 * @return un Point ayant pour coordonnees la moyenne de x, y et z.
	 */
	public Point getCentre() {

		double moyX = 0;
		double moyY = 0;
		double moyZ = 0;

		int taille = this.getListePoints().size();

		for (int i = 0; i < taille; i++) {
			moyX += this.getListePoints().get(i).getX();
			moyY += this.getListePoints().get(i).getY();
			moyZ += this.getListePoints().get(i).getZ();
		}

		return new Point(moyX / taille, moyY / taille, moyZ / taille);

	}

	/**
	 * Ajoute un point a la liste
	 * 
	 * @param point
	 */
	public void ajouterPoint(Point point) {
		this.getListePoints().add(point);
	}

	/**
	 * Ajoute une face a la liste
	 * 
	 * @param face
	 */
	public void ajouterFace(Face face) {
		this.getListeFaces().add(face);
	}

	/**
	 * Retourne la liste des points du modele
	 * 
	 * @return la liste des points du modele
	 */
	public List<Point> getListePoints() {
		return listePoints;
	}

	/**
	 * Retourne la liste des faces du modele
	 * 
	 * @return la liste des faces du modele
	 */
	public List<Face> getListeFaces() {
		return listeFaces;
	}

	@Override
	public String toString() {
		String r = "";
		for (int i = 0; i < getListeFaces().size(); i++) {
			r += getListeFaces().get(i).toString();
		}
		return r;
	}
}
