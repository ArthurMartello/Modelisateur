package mode_L4.view.bdd.panneaux;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mode_L4.model.bdd.ModeleDAO;
import mode_L4.model.bdd.ModeleData;

/**
 * FormPanel d'édition d'une ligne de la base de données.
 */
public class EditFormPanel extends FormPanel {

	private static final long serialVersionUID = -2782367617940274678L;

	private ModeleData oldData;

	/**
	 * Initialise le panel avec les anciennes valeurs de la ligne dans les
	 * champs de texte.
	 * 
	 * @param oldData
	 *            Ancien ModeleData que l'on va modifier.
	 */
	public EditFormPanel(ModeleData oldData) {

		this.oldData = oldData;

		super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		super.add(new FullLinePanel());
		super.add(this.fieldsPanel());
		
		JPanel panelButton = new JPanel();
		super.send = this.sendButton();
		panelButton.add(send, BorderLayout.EAST);
		
		super.add(panelButton);
		
		super.setFrame(new EditFormFrame(this));
	}

	@Override
	protected JPanel fieldsPanel() {

		JPanel panel = new JPanel();

		addField(panel, oldData.getName(), false);
		addField(panel, oldData.getPath(), true);
		addField(panel, oldData.getDate(), false);
		addField(panel, oldData.getKeysInString(), true);

		return panel;
	}

	@Override
	protected JButton sendButton() {

		JButton button = new JButton(new ImageIcon("../icons/bdd/edit.png"));
		button.setBackground(new Color(230, 230, 230));
		return button;
	}
}

class EditFormFrame extends JFrame {

	private static final long serialVersionUID = -859276295433270174L;

	public EditFormFrame(EditFormPanel panel) {
		
		super.add(panel);
		super.setVisible(true);
		super.setTitle("Edition");
		super.pack();
		super.setResizable(false);

		panel.getButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (ModeleDAO.getDAO().update(panel.getEntrees()) == true) {
					panel.clearFields();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Il y a eu un problème pendant l'édition.");
				}
			}
		});
	}
}