package mode_L4.model.modele.objets;

/**
 * Interface regroupant les Faces, les points, et leurs classes heritantes
 * @author Arthur
 *
 */
public interface Figure {
	
	/**
	 * Cree une matrice correspondant a la taille de l'objet
	 * @return un objet Matrice
	 */
	public Matrice getMatrice();
	
	/**
	 * Retourne la figure sous forme de chaine
	 * @return
	 */
	public String toString();
}
