package mode_L4.controler.modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener des CheckBoxes modifiant les options d'affichage d'un Modele.
 */
public class CheckBoxListener implements ActionListener {

	private OptionControler controler;

	private int id;

	/**
	 * Initialise un controleur sur le Modele et l'indice de son Parametre à
	 * modifier.
	 * 
	 * @param controler
	 *            Un objet OptionControler.
	 * @param id
	 *            L'indice du Parametre à modifier.
	 */
	public CheckBoxListener(OptionControler controler, int id) {
		this.id = id;
		this.controler = controler;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controler.invertOption(id);
	}

}
