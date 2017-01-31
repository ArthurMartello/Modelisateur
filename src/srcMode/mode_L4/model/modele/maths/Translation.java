package mode_L4.model.modele.maths;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.objets.Matrice;
import mode_L4.model.modele.objets.Vecteur;

/**
 * Classe desitinee a deplacer un Modele en fonction d'un Vecteur
 * 
 * @author Arthur
 *
 */
public class Translation extends ModeleChanges {

	/**
	 * Constructeur: definit le Modele a modifier en fonction d'un Vecteur
	 * 
	 * @param modele
	 *            Modele a modifier
	 * @param vecteur
	 *            Vecteur directeur
	 */
	public Translation(Modele modele, Vecteur vecteur) {
		super(modele);
		super.setChanges(Matrice.getMatriceTranslation(vecteur).multiplier(super.getModele().getMatrice()));
	}

}
