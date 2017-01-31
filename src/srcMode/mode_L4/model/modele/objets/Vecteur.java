package mode_L4.model.modele.objets;

/**
 * Point ayant des methodes destinees a l'orientation
 * @author Arthur Martello
 *
 */
public class Vecteur extends Point {

	/**
	 * Construteur: cree un vecteur a partir d'un seul point nul
	 */
	public Vecteur() {
		super();
	}

	/**
	 * Constructeur: cree un vecteur a partir d'un seul points dans l'espace
	 * 
	 * @param x
	 *            coordonnee x du point
	 * @param y
	 *            coordonnee y du point
	 * @param z
	 *            coordonnee z du point
	 */
	public Vecteur(double x, double y, double z) {
		super(x, y, z);
	}

	/**
	 * Constructeur: cree un vecteur a partir de deux points
	 * 
	 * @param a
	 *            premier point
	 * @param b
	 *            deuxieme point
	 */
	public Vecteur(Point a, Point b) {
		super(b.getX() - a.getX(), b.getY() - a.getY(), b.getZ() - a.getZ());
	}

	/**
	 * Calcul d'un vecteur egal au produit vectoriel de celui-ci par un point
	 * 
	 * @param p
	 *            point avel lequel on fait le produit vectoriel
	 * @return retourne un nouveau Vecteur de coordonnes egales au produit
	 *         vectoriel
	 */
	public Vecteur produitVectoriel(Point p) {

		double x = this.getY() * p.getZ() - this.getZ() * p.getY();
		double y = this.getX() * p.getZ() - this.getZ() * p.getX();
		double z = this.getX() * p.getY() - this.getY() * p.getX();

		if (y == 0) {
			return new Vecteur(x, y, z);
		}
		return new Vecteur(x, -y, z);

	}

	/**
	 * Calcul du produit scalaire entre ce vecteur et un autre point
	 * 
	 * @param p
	 *            point avec lequel on fait le produit scalaire
	 * @return retourne un decimal egal au produit scalaire des deux points
	 */
	public double produitScalaire(Point p) {

		double x = this.getX() * p.getX();
		double y = this.getY() * p.getY();
		double z = this.getZ() * p.getZ();

		return x + y + z;
	}
	
	/**
	 * Definit le vecteur en fonction de ses coordonnees puis calcule sa norme
	 */
	public void set(double x, double y, double z) {
		super.set(x, y, z);
	}
	
	/**
	 * @return retourne la norme du vecteur
	 */
	public double getNorme() {
		double norme = Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2);
		return Math.sqrt(norme);
	}
}
