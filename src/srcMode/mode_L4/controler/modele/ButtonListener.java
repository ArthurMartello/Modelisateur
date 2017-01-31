package mode_L4.controler.modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mode_L4.controler.fenetre.FrameControler;

/**
 * Listener d'un bouton destiné à une action sur un Modele.
 */
public class ButtonListener implements ActionListener, ChangeListener {

	private PaintControler paintControler;
	private FrameControler frameControler;

	private Action action;
	private Direction direction;
	private double nombre;

	private final Timer timer;

	/**
	 * Initialisation du controleur du Modèle à modifier et des paramètres du
	 * type d'action que le bouton effectuera, et initalisation d'un controleur
	 * de la fenêtre pour lui rendre le focus une fois l'appui terminé.
	 * 
	 * @param paintControler
	 *            Un controleur PaintControler, controlant l'affichage du
	 *            Modele.
	 * @param frameControler
	 *            Un controleur FrameControler, rétablissant le focus sur la
	 *            fenêtre.
	 * @param action
	 *            Un type d'action.
	 * @param direction
	 *            Une direction vers laquelle effectuer l'action.
	 * @param nombre
	 *            Paramètre supplémentaire pour certain cas d'action.
	 */
	public ButtonListener(PaintControler paintControler, FrameControler frameControler, Action action,
			Direction direction, double nombre) {
		this.paintControler = paintControler;
		this.frameControler = frameControler;
		this.action = action;
		this.direction = direction;
		this.nombre = nombre;
		this.timer = new Timer(50, this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		paintControler.action(action, direction, nombre);
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		JButton button = (JButton) e.getSource();
		ButtonModel model = button.getModel();

		if (model.isPressed() && !timer.isRunning()) {
			timer.start();
		} else if (!model.isPressed() && timer.isRunning()) {
			timer.stop();
			frameControler.requestFocus();
		}
	}

}
