package mode_L4.model.modele.maths;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.objets.Matrice;
import mode_L4.model.modele.objets.Point;
import mode_L4.model.modele.objets.Vecteur;

/**
 * Classe destinee a zoomer ou dezoomer un Modele, c'est a dire de multiplier
 * l'ensemble de ses points par un pourcentage
 * 
 * @author Arthur Martello
 *
 */
public class Zoom extends ModeleChanges {

	/**
	 * Constructeur: initalise un Modele a modifier par rapport a un coefficient
	 * 
	 * @param modele
	 *            Modele a modifier
	 * @param nombre
	 *            coefficient de zoom: par exemple, 1 zoomera de 0%; 1,1 zoomera
	 *            de 10%; 0.9 dezoomera de 10%
	 */
	public Zoom(Modele modele, double nombre) {
		super(modele);
		Point centre = modele.getCentre();
		new Translation(modele, new Vecteur(centre.getX(), centre.getY(), centre.getZ()));
		super.setChanges(Matrice.getMatriceHomothetie(nombre).multiplier(super.getModele().getMatrice()));
		new Translation(modele, new Vecteur(-centre.getX(), -centre.getY(), -centre.getZ()));
	}
	
	

}
