package mode_L4.view.fenetre;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * Méthodes à implémenter pour un ajouters des observateurs à un la Frame et les
 * notifier.
 */
interface Observable {

	/**
	 * Ajoute un Observateur.
	 * 
	 * @param observer
	 *            Un objet FrameObserver
	 */
	public void addObserver(FrameObserver observer);

	/**
	 * Réinitalise les observateurs.
	 */
	public void removeObserver();

	/**
	 * Notifie de se mettre à jour tous les observateurs de Frame.
	 */
	public void notifyObserver();

}

/**
 * Fenêtre pouvant être observée.
 */
public abstract class FrameObservable extends JFrame implements Observable {

	private static final long serialVersionUID = -1488750720994309497L;

	private List<FrameObserver> listObserver = new ArrayList<>();

	public static final int DEFAULTXSIZE = 1000;
	public static final int DEFAULTYSIZE = 700;

	/**
	 * Initialise les paramètres par défaut de la fenêtre.
	 * 
	 * @param title
	 *            Adresse du modèle qui sera en titre de la fenêtre.
	 */
	protected FrameObservable() {
		super.setTitle("Modelisateur");
		super.setResizable(true);
		super.setMinimumSize(new Dimension(720, 300));
		super.setPreferredSize(new Dimension(DEFAULTXSIZE, DEFAULTYSIZE));
		super.setLocation(500, 100);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setFocusable(true);
	}

	@Override
	public void addObserver(FrameObserver observer) {
		this.listObserver.add(observer);
		observer.updateFrame(this.getHeight(), this.getWidth());
	}

	@Override
	public void removeObserver() {
		listObserver = new ArrayList<FrameObserver>();
	}

	@Override
	public void notifyObserver() {
		for (FrameObserver observer : listObserver) {
			observer.updateFrame(this.getWidth(), this.getHeight());
		}
	}

}
