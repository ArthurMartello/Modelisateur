package mode_L4.controler.modele;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.ModeleObservable;

/**
 * Controleur des options d'affichage du Modele.
 */
public class OptionControler extends ModeleControler {

	/**
	 * Initialise le Modele à observer
	 * 
	 * @param modele
	 *            Un objet ModeleObservable
	 */
	public OptionControler(ModeleObservable modele) {
		super(modele);
	}

	/**
	 * Inverse l'option à l'indice donné en paramètre.
	 * 
	 * @param idx
	 *            Indice de l'option
	 */
	public void invertOption(int idx) {
		((Modele) modele).getParametres().changerParametre(idx);
		super.notifyModele();
	}

	/**
	 * Inverse l'option donnée en paramètre.
	 * 
	 * @param key
	 *            Nom de l'option
	 */
	public void invertOption(String key) {
		((Modele) modele).getParametres().changerParametre(key);
		super.notifyModele();
	}

}
