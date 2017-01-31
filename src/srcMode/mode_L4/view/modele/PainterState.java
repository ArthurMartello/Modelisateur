package mode_L4.view.modele;

import java.awt.Color;

/**
 * Liste des messages informatifs s'affichant sur l'écran à la place d'un modèle.
 * @author Arthur Martello
 *
 */
public enum PainterState {
	
	IMPORT_ERROR("Impossible d'importer ce modèle. Sélectionnez-en un à gauche.", Color.RED),
	FIRST_OPENING("Aucun modèle choisi. Sélectionnez-en un à gauche.", Color.BLUE),
	MODEL_DISPLAY(null, null);
	
	private String message;
	private Color color;
	
	private PainterState(String message, Color color) {
		this.message = message;
		this.color = color;
	}
	
	/**
	 * @return le message destiné à être affiché.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return la couleur dans laquelle le message est destiné à être affiché.
	 */
	public Color getColor() {
		return color;
	}

}
