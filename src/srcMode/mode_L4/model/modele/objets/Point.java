package mode_L4.model.modele.objets;

/**
 * Objet Point ayant trois coordonnees.
 * 
 * @author martella
 *
 */
public class Point implements Figure {

	private double x;
	private double y;
	private double z;

	/**
	 * Cree un point aux coordonnees 0,0,0
	 */
	public Point() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	/**
	 * Cree un point en trois dimensions
	 * 
	 * @param x
	 *            coordonnee x du point
	 * @param y
	 *            coordonnee y du point
	 * @param z
	 *            coordonnee z du point
	 */
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Retourne les coordonnes du point sous forme de tableau
	 * 
	 * @return un tableau de double de taille 3
	 */
	public double[] getTableau() {
		return new double[] { x, y, z };
	}
	
	@Override
	public Matrice getMatrice() {
		return new Matrice(new double[][] { { x }, { y }, { z }, { 1.0 } });
	}

	/**
	 * 
	 * @return la coordonnee x du point
	 */
	public double getX() {
		return x;
	}

	/**
	 * Definit la coordonnee x du point
	 * 
	 * @param x
	 *            coordonnee x du point
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * 
	 * @return la coordonnee y du point
	 */
	public double getY() {
		return y;
	}

	/**
	 * Definit la coordonnee y du point
	 * 
	 * @param y
	 *            coordonnee y du point
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * 
	 * @return la coordonnee z du point
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Definit la coordonnee z du point
	 * 
	 * @param z
	 *            coordonnee z du point
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Reinitalise les trois coordonnes du point
	 * 
	 * @param x
	 *            coordonnee x du point
	 * @param y
	 *            coordonnee y du point
	 * @param z
	 *            coordonnee z du point
	 */
	public void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return "[" + x + "," + y + "," + z + "]";
	}

}
