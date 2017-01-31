package mode_L4.controler.fenetre;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.ModeleObservable;
import mode_L4.view.fenetre.FrameObservable;

/**
 * Controleur de la Frame
 */
public class FrameControler {

	protected FrameObservable frame;
	protected ModeleObservable modele;

	/**
	 * Initialise la FrameObservable contenant un ModeleObservable que l'on va
	 * modifier.
	 * 
	 * @param frame
	 *            Un objet FrameObservable
	 * @param modele
	 *            Un objet ModeleObservable
	 */
	public FrameControler(FrameObservable frame, ModeleObservable modele) {
		this.frame = frame;
		this.modele = modele;
	}

	/**
	 * Redimentionne la fenêtre et son Modele.
	 * 
	 * @param x
	 *            Nouvelle taille en longueur
	 * @param y
	 *            Nouvelle taille en largeur
	 */
	public void resize(int x, int y) {
		((Modele) this.modele).setBorder(x, y);
		this.modele.notifyObserver();
	}

	/**
	 * Redéfinit le focus sur la Frame.
	 */
	public void requestFocus() {
		this.frame.requestFocusInWindow();
		this.frame.notifyObserver();
	}
	
	/**
	 * Redéfinit le nom de la Frame
	 * @param name Nouveau nom
	 */
	public void setName(String name) {
		this.frame.setTitle(name);
	}

}
