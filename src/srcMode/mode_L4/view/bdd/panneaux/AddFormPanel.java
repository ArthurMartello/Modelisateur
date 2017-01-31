package mode_L4.view.bdd.panneaux;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mode_L4.model.bdd.ModeleDAO;

/**
 * FormPanel d'ajout d'une ligne de la base de données.
 */
public class AddFormPanel extends FormPanel {

	private static final long serialVersionUID = -2782367617940274678L;

	/**
	 * Initalise le panel.
	 */
	public AddFormPanel() {
		super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		super.add(new FullLinePanel());
		super.add(this.fieldsPanel());

		JPanel panelButton = new JPanel();
		super.send = this.sendButton();
		panelButton.add(send, BorderLayout.EAST);

		super.add(panelButton);

		super.setFrame(new AddFormFrame(this));
	}

	@Override
	protected JPanel fieldsPanel() {

		JPanel panel = new JPanel();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		addField(panel, null, true);
		addField(panel, null, true);
		addField(panel, sdf.format(date), false);
		addField(panel, null, true);

		return panel;
	}

	@Override
	protected JButton sendButton() {

		JButton button = new JButton(new ImageIcon("../icons/bdd/add2.png"));
		button.setBackground(new Color(230, 230, 230));
		return button;
	}
}

/**
 * Fenetre correspondant au AddFormPanel
 */
class AddFormFrame extends JFrame {

	private static final long serialVersionUID = -6648559357545707371L;

	/**
	 * Initialise la fenêtre
	 * 
	 * @param panel
	 *            AddFormPanel à l'interieur
	 */
	public AddFormFrame(AddFormPanel panel) {

		super.add(panel);
		super.setTitle("Ajout d'un modèle");
		super.pack();
		super.setResizable(false);

		panel.getButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (ModeleDAO.getDAO().insert(panel.getEntrees()) == true) {
					panel.clearFields();
					dispose();
				}
			}
		});
	}
}