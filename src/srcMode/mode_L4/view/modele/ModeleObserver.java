package mode_L4.view.modele;

import java.util.List;

import mode_L4.model.modele.Parametre;
import mode_L4.model.modele.objets.Face;
import mode_L4.model.modele.objets.Point;

/**
 * Observateur du Modele
 */
public interface ModeleObserver {

	/**
	 * Attend une notification pour mettre à jour la liste des faces et la liste
	 * des points du Modele.
	 * 
	 * @param faces
	 *            Nouvelle liste des faces
	 * @param points
	 *            Nouvelle liste des points
	 */
	public void updatePainting(List<Face> faces, List<Point> points);

	/**
	 * Attend une notification pour mettre à jour l'objet Parametre du Modele.
	 * 
	 * @param parametres
	 *            Nouveau Parametre
	 */
	public void updateOptions(Parametre parametres);

	/**
	 * Attend une notification pour mettre à jour la taille du cadre du Modele.
	 * 
	 * @param x
	 *            Nouvelle longueur du cadre
	 * @param y
	 *            Nouvelle hauteur du cadre
	 */
	public void updateBorders(int x, int y);

}
