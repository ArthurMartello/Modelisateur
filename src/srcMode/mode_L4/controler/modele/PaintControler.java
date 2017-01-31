package mode_L4.controler.modele;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.ModeleObservable;
import mode_L4.model.modele.maths.Cadrage;
import mode_L4.model.modele.maths.Rotation;
import mode_L4.model.modele.maths.Translation;
import mode_L4.model.modele.maths.Zoom;
import mode_L4.model.modele.objets.Vecteur;
import mode_L4.view.fenetre.Frame;

/**
 * Controleur de l'affichage du Modele
 */
public class PaintControler extends ModeleControler {

	/**
	 * Initialise le Modele à observer
	 * 
	 * @param modele
	 *            Un objet ModeleObservable
	 */
	public PaintControler(ModeleObservable modele) {
		super(modele);
	}

	private void zoom(double percentage) {

		if (percentage > 0) {
			new Zoom((Modele) modele, percentage);
		}

		notifyModele();
	}

	private void translate(Direction d, double offset) {

		switch (d) {

		case HAUT:
			new Translation((Modele) modele, new Vecteur(0, -offset, 0));
			break;
		case BAS:
			new Translation((Modele) modele, new Vecteur(0, offset, 0));
			break;
		case DROITE:
			new Translation((Modele) modele, new Vecteur(-offset, 0, 0));
			break;
		case GAUCHE:
			new Translation((Modele) modele, new Vecteur(offset, 0, 0));
			break;
		default:
			break;

		}

		notifyModele();
	}

	private void rotate(Direction d, double angle) {

		switch (d) {

		case AXEX:
			new Rotation((Modele) modele, angle, 0, 0);
			break;
		case AXEY:
			new Rotation((Modele) modele, 0, angle, 0);
			break;
		case AXEZ:
			new Rotation((Modele) modele, 0, 0, angle);
			break;
		default:
			break;
		}

		notifyModele();
	}

	/**
	 * Réalise une Action précisée en paramètre.
	 * 
	 * @param action
	 *            Un type d'Action
	 * @param direction
	 *            Un type de Direction
	 * @param number
	 *            Parametre pour certaines actions
	 */
	public void action(Action action, Direction direction, double number) {

		switch (action) {

		case ZOOM:
			this.zoom(number);
			break;

		case TRANSLATION:
			this.translate(direction, number);
			break;

		case ROTATION:
			this.rotate(direction, number);
			break;

		case CADRAGE:
			new Cadrage((Modele) modele, ((Modele) modele).getMaxWidth()-Frame.OFFSETX, ((Modele) modele).getMaxHeight()-Frame.OFFSETY);
			notifyModele();
			break;

		default:
			break;

		}
	}

}
