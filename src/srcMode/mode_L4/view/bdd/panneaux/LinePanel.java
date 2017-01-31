package mode_L4.view.bdd.panneaux;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * Classe abstraite d'un panneau affichant une ligne d'informations
 */
public abstract class LinePanel extends JPanel {

	private static final long serialVersionUID = 3689797348309785075L;

	protected int tailleField;

	private GridBagLayout gBLayout;
	private GridBagConstraints gBConstraint;

	/**
	 * Initalise les paramètres généraux du panel.
	 */
	public LinePanel() {

		super.setBorder(new LineBorder(Color.LIGHT_GRAY));
		super.setBackground(Color.lightGray);

		this.gBLayout = new GridBagLayout();
		this.gBConstraint = new GridBagConstraints();
		this.tailleField = 15;
		super.setLayout(gBLayout);

	}

	/**
	 * Ajoute une étiquette servant de titre dans le panneau.
	 * 
	 * @param val
	 *            Valeur du titre
	 */
	protected void addTitleField(String val) {

		JTextField field = new JTextField();
		gBConstraint.insets = new Insets(5, 5, 5, 5);
		gBLayout.setConstraints(field, gBConstraint);

		field.setFont(new Font("Tahoma", Font.BOLD, 11));
		field.setBackground(null);
		field.setBorder(null);
		field.setText(val);
		field.setColumns(tailleField);
		field.setEditable(false);

		this.add(field);

	}

	/**
	 * Ajoute une étiquette dans le panneau.
	 * 
	 * @param val
	 *            Valeur affichée.
	 */
	protected void addField(String val) {

		JTextField field = new JTextField();
		gBConstraint.insets = new Insets(5, 5, 5, 5);
		gBLayout.setConstraints(field, gBConstraint);

		field.setBackground(null);
		field.setBorder(null);
		field.setText(val);
		field.setColumns(tailleField);
		field.setEditable(false);

		this.add(field);
	}

}
