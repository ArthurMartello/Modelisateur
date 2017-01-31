package mode_L4.model.modele.maths;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.objets.Matrice;

/**
 * Classe regroupant les differentes classes d'action sur un Modele
 * 
 * @author Arthur Martello
 *
 */
public class ModeleChanges {

	private Modele modele;

	/**
	 * Constructeur: initalise le Modele
	 * 
	 * @param un
	 *            Modele sur lequel on apportera des modifications
	 */
	public ModeleChanges(Modele modele) {
		this.modele = modele;
	}

	/**
	 * Retourne le modele modifie
	 * 
	 * @return le Modele modifie
	 */
	public Modele getModele() {
		return this.modele;
	}

	/**
	 * Applique au modele une nouvelle matrice
	 * 
	 * @param Matrice
	 *            de meme dimmension que la precedente
	 */
	public void setChanges(Matrice matrice) { 

		if (matrice.getLineSize() == modele.getMatrice().getLineSize()
				&& matrice.getColumnSize() == modele.getMatrice().getColumnSize()) {

			int taille = modele.getListePoints().size();

			for (int i = 0; i < taille; i++) {
				modele.getListePoints().get(i).set(matrice.getTableau()[0][i], matrice.getTableau()[1][i],
						matrice.getTableau()[2][i]);
			}

		} else {
			try {
				throw new Exception(
						"Erreur: impossible d'importer une matrice dans celle d'un modele de taille differente.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
