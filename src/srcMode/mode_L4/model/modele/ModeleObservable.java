package mode_L4.model.modele;

import java.util.ArrayList;
import java.util.List;

import mode_L4.model.modele.objets.Face;
import mode_L4.model.modele.objets.Point;
import mode_L4.view.modele.ModeleObserver;

/**
 * Méthodes à implémenter pour un ajouters des observateurs à un Modele et les
 * notifier.
 */
interface Observable {

	/**
	 * Ajoute un Observateur
	 * 
	 * @param observer
	 *            Un objet ModeleObserver
	 */
	public void addObserver(ModeleObserver observer);

	/**
	 * Réinitalise les observateurs
	 */
	public void removeObserver();

	/**
	 * Notifie de se mettre à jour tous les observateurs du Modele.
	 */
	public void notifyObserver();

}

/**
 * Modele pouvant être observé.
 */
public abstract class ModeleObservable implements Observable {

	private List<ModeleObserver> listObserver = new ArrayList<ModeleObserver>();

	protected List<Face> listeFaces;
	protected List<Point> listePoints;
	protected Parametre parametres;
	protected int borderX;
	protected int borderY;

	@Override
	public void addObserver(ModeleObserver observer) {
		this.listObserver.add(observer);
		observer.updatePainting(listeFaces, listePoints);
		observer.updateOptions(parametres);
	}

	@Override
	public void removeObserver() {
		listObserver = new ArrayList<ModeleObserver>();
	}

	@Override
	public void notifyObserver() {
		for (ModeleObserver observer : listObserver) {
			observer.updatePainting(listeFaces, listePoints);
			observer.updateOptions(parametres);
			observer.updateBorders(borderX, borderY);
		}
	}

}
