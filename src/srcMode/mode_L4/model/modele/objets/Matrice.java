package mode_L4.model.modele.objets;

/**
 * Class composee d'un tableau a deux dimensions
 */
public class Matrice {

	private double[][] m;

	/**
	 * Constructeur: Cree une matrice a partir d'un tableau a deux dimensions
	 * 
	 * @param m
	 *            tableau a deux dimensions
	 */
	public Matrice(double[][] m) {
		this.m = m;
	}

	/**
	 * @return le tableau en attribut
	 */
	public double[][] getTableau() {
		return this.m;
	}

	/**
	 * Definit le tableau de la matrice
	 * 
	 * @param m
	 */
	public void setMatrice(double[][] m) {
		this.m = m;
	}

	/**
	 * Retourne un objet String contenant tous les elements du tableaux de la matrice, separes par des '|' ou des retours a la ligne
	 */
	public String toString() {

		String r = "Matrice " + m.length + "*" + m[0].length + "\n";
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				r += m[i][j];

				if (j < m[0].length - 1) {
					r += " | ";
				}

			}
			r += "\n";
		}
		return r;

	}

	/**
	 * Retourne le nombre de lignes de la matrice
	 * @return un entier egal aux nombre de lignes
	 */
	public int getLineSize() {
		return m.length;
	}

	/**
	 * Retourne le nombre de colonnes de la matrice
	 * @return un entier egal aux nombre de colonnes
	 */
	public int getColumnSize() {
		return m[0].length;
	}
	
	/**
	 * Multiplie une matrice par celle-ci
	 * 
	 * @param m
	 *            autre Matrice
	 * @return la matrice this*m
	 */
	public Matrice multiplier(Matrice matrice) {
		
		double[][] a = this.getTableau();
		double[][] b = matrice.getTableau();
		
		int rowsInA = a.length;
		int columnsInA = a[0].length; // same as rows in B
		int columnsInB = b[0].length;
		double[][] c = new double[rowsInA][columnsInB];
		for (int i = 0; i < rowsInA; i++) {
			for (int j = 0; j < columnsInB; j++) {
				for (int k = 0; k < columnsInA; k++) {
					c[i][j] = c[i][j] + a[i][k] * b[k][j];
				}
			}
		}
		return new Matrice(c);
	}
	
	/**
	 * Cree une matrice de Translation de Vecteur v
	 * 
	 * @param v
	 *            Vecteur de translation
	 * @return la matrice translation
	 */
	public static Matrice getMatriceTranslation(Point p) {

		double[][] matrice = new double[][] { { 1.0, 0, 0, -p.getX() }, { 0, 1.0, 0, -p.getY() },
				{ 0, 0, 1.0, -p.getZ() }, { 0, 0, 0, 1.0 }, };

		return new Matrice(matrice);
	}
	
	/**
	 * Cree une matrice homothetie de pourcentage en parametre
	 * 
	 * @param zoom
	 *            Nombre decimal
	 * @return la matrice Homothetie
	 */
	public static Matrice getMatriceHomothetie(double zoom) {

		double[][] matrice = new double[][] { { zoom, 0, 0, 0 }, { 0, zoom, 0, 0 }, { 0, 0, zoom, 0 },
				{ 0, 0, 0, 1 }, };

		return new Matrice(matrice);
	}

}
