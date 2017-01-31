package mode_L4.controler.modele;

import mode_L4.model.bdd.Memory;
import mode_L4.model.bdd.ModeleData;
import mode_L4.model.modele.Modele;
import mode_L4.model.modele.ModeleObservable;

/**
 * Controleur des actualisations du Modele
 *
 */
public class ResetControler extends ModeleControler {

	/**
	 * Initialise le Modele à observer
	 * 
	 * @param modele
	 *            Un objet ModeleObservable
	 */
	public ResetControler(ModeleObservable modele) {
		super(modele);
	}

	/**
	 * Réinitalise le Modele. S'il n'est pas déjà dans la mémoire, il l'importe
	 * tout en l'ajoutant dans celle-ci.
	 * 
	 * @param modeleData
	 *            Un objet ModeleData correspondant à ses informations dans la base de
	 *            données.
	 */
	public void resetModele(ModeleData modeleData) {

		if (!Memory.getMemory().isSaved(modeleData)) {
			Memory.getMemory().addToMemory(modeleData);
		}

		((Modele) modele).reimport(Memory.getMemory().getFromMemory(modeleData));

		super.notifyModele();
	}

}
