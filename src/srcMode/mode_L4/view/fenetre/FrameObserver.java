package mode_L4.view.fenetre;

/**
 * Observateur de la fenêtre
 */
public interface FrameObserver {

	/**
	 * Attend une notification pour mettre à jour la taille de la fenêtre.
	 * 
	 * @param x
	 *            Taille en hauteur
	 * @param y
	 *            Taille en largeur
	 */
	public void updateFrame(int x, int y);

}
