package mode_L4.controler.modele;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Listener de la souris de l'utilisateur
 */
public class SourisListener implements MouseWheelListener, MouseMotionListener, MouseListener {

	private PaintControler controler;

	private static boolean clickGauche = false;
	private static boolean clickDroit = false;
	private static int locX1;
	private static int locY1;

	private final double defZoom = 0.2;

	/**
	 * Initialise le controleur
	 * 
	 * @param controler
	 *            Un objet PaintControler
	 */
	public SourisListener(PaintControler controler) {
		this.controler = controler;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() < 0) {
			controler.action(Action.ZOOM, Direction.AXEZ, 1 + defZoom);
		} else {
			controler.action(Action.ZOOM, Direction.AXEZ, 1 - defZoom);
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (clickDroit) {

			if (e.getX() > locX1) {
				controler.action(Action.TRANSLATION, Direction.DROITE, Math.abs(e.getX() - locX1));
			} else if (e.getX() < locX1) {
				controler.action(Action.TRANSLATION, Direction.GAUCHE, Math.abs(e.getX() - locX1));
			}

			if (e.getY() > locY1) {
				controler.action(Action.TRANSLATION, Direction.BAS, Math.abs(e.getY() - locY1));
			} else if (e.getY() < locY1) {
				controler.action(Action.TRANSLATION, Direction.HAUT, Math.abs(e.getY() - locY1));
			}

			SourisListener.locX1 = e.getX();
			SourisListener.locY1 = e.getY();

		} else if (clickGauche) {

			controler.action(Action.ROTATION, Direction.AXEX, -(e.getY() - locY1));
			controler.action(Action.ROTATION, Direction.AXEY, -(e.getX() - locX1));

			SourisListener.locX1 = e.getX();
			SourisListener.locY1 = e.getY();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
			SourisListener.clickGauche = true;
			SourisListener.locX1 = e.getX();
			SourisListener.locY1 = e.getY();
		}
		if (e.getButton() == 3) {
			SourisListener.clickDroit = true;
			SourisListener.locX1 = e.getX();
			SourisListener.locY1 = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 1) {
			SourisListener.clickGauche = false;
		}
		if (e.getButton() == 3) {
			SourisListener.clickDroit = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {			
			if (e.getButton() == 1) {
				controler.action(Action.ZOOM, Direction.AXEZ, 1.5);
			} else if (e.getButton() == 3){
				controler.action(Action.ZOOM, Direction.AXEZ, 0.5);
			}			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}
