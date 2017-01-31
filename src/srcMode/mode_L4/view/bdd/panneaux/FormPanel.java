package mode_L4.view.bdd.panneaux;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mode_L4.model.bdd.ModeleData;

/**
 * Classe regroupant les différents formulaires agissants sur la base de
 * données.
 */
public abstract class FormPanel extends JPanel {

	private static final long serialVersionUID = 3144396378095218321L;
	
	protected JButton send;

	protected List<JTextField> fields = new ArrayList<>(4);

	private JFrame frame;

	/**
	 * @return Retourne les valeurs dans les champs de texte sous la forme d'un
	 *         nouvel objet ModeleData.
	 */
	protected ModeleData getEntrees() {
		ModeleData data = new ModeleData(fields.get(0).getText(), fields.get(1).getText(), fields.get(2).getText(),
				fields.get(3).getText());
		return data;
	}

	/**
	 * Rend tous les champs de texte vide à nouveau.
	 */
	protected void clearFields() {
		for (int i = 0; i < fields.size(); i++) {
			if (i != 2)
				fields.get(i).setText("");
		}
	}
	
	/**
	 * @return le bouton d'envoi
	 */
	public JButton getButton() {
		return this.send;
	}
	
	/**
	 * @return la fenêtre d'affichage du JPanel
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Definit la fenêtre d'affichage
	 * @param frame Nouvelle fenêtre
	 */
	protected void setFrame(JFrame frame) {
		this.frame = frame;
	}


	/**
	 * Classe représentant le futur panneau des champs de texte
	 * 
	 * @return un nouvel objet JPanel
	 */
	protected abstract JPanel fieldsPanel();

	/**
	 * Classe représentant le bouton d'envoi qui lancera l'appel à la base de
	 * données.
	 * 
	 * @return un nouvel objet JButton
	 */
	protected abstract JButton sendButton();

	/**
	 * Ajoute un JTextfield a un panel
	 * 
	 * @param panel
	 *            Panel sur lequel on ajoutera le field
	 * @param valeur
	 *            Valeur par defaut du field, null si vide
	 * @param modifiable
	 *            Vrai si le field peut etre modifiable
	 */
	protected void addField(JPanel panel, String valeur, boolean modifiable) {

		JTextField field = new JTextField();
		field.setColumns(15);
		field.setText(valeur);
		field.setEditable(modifiable);
		this.fields.add(field);
		panel.add(field);
	}

}
