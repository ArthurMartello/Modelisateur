package mode_L4.controler.fenetre;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Listener de la Frame
 */
public class FrameListener implements ComponentListener {

	private FrameControler controler;

	/**
	 * Initialise le FrameControler de la fenêtre.
	 * 
	 * @param controler
	 *            Un controleur FrameControler
	 */
	public FrameListener(FrameControler controler) {
		this.controler = controler;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		controler.resize(e.getComponent().getWidth(), e.getComponent().getHeight());		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

}
