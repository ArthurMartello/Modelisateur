package mode_L4.controler.modele;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listener des touches du clavier modifiant le Modele selon la touche pressée.
 */
public class KeyboardListener implements KeyListener {

	private PaintControler controler;

	/**
	 * Initialise le controleur sur le Modele à modifier au clavier.
	 * 
	 * @param controler
	 *            Un objet PaintControleur modifiant l'affichage du Modele.
	 */
	public KeyboardListener(PaintControler controler) {
		this.controler = controler;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		final double defTrans = 4.0;
		final double defZoom = 0.2;
		final double defRotat = 3.0;

		switch (e.getKeyCode()) {

		case KeyEvent.VK_Z:
		case KeyEvent.VK_UP:
			controler.action(Action.TRANSLATION, Direction.HAUT, defTrans);
			break;

		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			controler.action(Action.TRANSLATION, Direction.BAS, defTrans);
			break;

		case KeyEvent.VK_Q:
		case KeyEvent.VK_LEFT:
			controler.action(Action.TRANSLATION, Direction.GAUCHE, defTrans);
			break;

		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			controler.action(Action.TRANSLATION, Direction.DROITE, defTrans);
			break;

		case KeyEvent.VK_M:
		case KeyEvent.VK_SUBTRACT:
			controler.action(Action.ZOOM, Direction.AXEZ, 1 - defZoom);
			break;

		case KeyEvent.VK_P:
		case KeyEvent.VK_ADD:
			controler.action(Action.ZOOM, Direction.AXEZ, 1 + defZoom);
			break;

		case KeyEvent.VK_NUMPAD8:
			controler.action(Action.ROTATION, Direction.AXEX, defRotat);
			break;

		case KeyEvent.VK_NUMPAD2:
			controler.action(Action.ROTATION, Direction.AXEX, -defRotat);
			break;

		case KeyEvent.VK_NUMPAD4:
			controler.action(Action.ROTATION, Direction.AXEY, defRotat);
			break;

		case KeyEvent.VK_NUMPAD6:
			controler.action(Action.ROTATION, Direction.AXEY, -defRotat);
			break;

		default:
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
