package mode_L4.controler.modele;

import mode_L4.model.modele.ModeleObservable;

/**
 * Controleur du Modele
 */
public abstract class ModeleControler {

	protected ModeleObservable modele;

	/**
	 * Initialise le Modele à modifier à travers son ModeleObservable
	 * 
	 * @param modele
	 *            Un objet ModeleObservable
	 */
	public ModeleControler(ModeleObservable modele) {
		this.modele = modele;
	}

	/**
	 * Notifie les observateurs du Modele
	 */
	protected void notifyModele() {
		this.modele.notifyObserver();
	}
}
