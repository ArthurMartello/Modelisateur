package mode_L4.view.bdd.panneaux;

import mode_L4.model.bdd.ModeleData;

/**
 * LinePanel affichant toutes les valeurs d'un ModeleData
 */
public class FullLinePanel extends LinePanel {

	private static final long serialVersionUID = -3180472953377947410L;

	/**
	 * Initialise les valeurs de titres.
	 */
	public FullLinePanel() {
		super();
		super.addTitleField("Nom");
		super.addTitleField("Adresse");
		super.addTitleField("Date d'ajout");
		super.addTitleField("Mots-clefs");

	}

	/**
	 * Initalise le panneau avec comme valeurs les attributs d'un ModeleData
	 * 
	 * @param modele
	 *            ModeleData dont on affiche les valeurs
	 */
	public FullLinePanel(ModeleData modele) {
		super();
		super.addField(modele.getName());
		super.addField(modele.getPath());
		super.addField(modele.getDate());
		super.addField(modele.getKeysInString());
	}

}
