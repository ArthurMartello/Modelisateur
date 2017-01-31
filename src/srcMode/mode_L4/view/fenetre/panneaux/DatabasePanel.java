package mode_L4.view.fenetre.panneaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mode_L4.model.bdd.Memory;
import mode_L4.model.bdd.ModeleDAO;
import mode_L4.model.bdd.ModeleData;
import mode_L4.view.bdd.DatabaseView;
import mode_L4.view.bdd.panneaux.AddFormPanel;
import mode_L4.view.bdd.panneaux.EditFormPanel;
import mode_L4.view.bdd.panneaux.InfoFormPanel;

/**
 * Panel regroupant tous les élements du menu de la BDD
 */
public class DatabasePanel extends JPanel {

	private static final long serialVersionUID = 7764001871339454140L;
	
	private JScrollPane dataView;

	/**
	 * Initialise le panneau
	 * @param sd Vue de la base de données
	 */
	public DatabasePanel(DatabaseView sd) {

		this.dataView = sd;
		this.dataView.setBorder(null);

		this.setBackground(Color.white);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createVerticalStrut(3));
		this.add(panelRecherche());
		this.add(Box.createVerticalStrut(3));
		this.add(dataView);
		this.add(Box.createVerticalStrut(3));
		this.add(panelBoutons());
		this.add(Box.createVerticalStrut(3));

	}

	private JButton iconButton(String iconName) {
		JButton button = new JButton();
		button.setIcon(new ImageIcon("../icons/bdd/" + iconName + ".png"));
		button.setBackground(new Color(230, 230, 230));
		button.setPreferredSize(new Dimension(40, 30));
		return button;
	}

	private JPanel panelRecherche() {

		final String FINDMSG = "Rechercher...";

		JPanel panel = new JPanel();

		panel.setBackground(Color.white);

		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setMaximumSize(new Dimension(10000, 50));

		JTextField field = new JTextField(FINDMSG);
		field.setColumns(15);
		field.setForeground(Color.GRAY);

		field.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

				if (field.getText().equals("") || field.getText().equals(FINDMSG)) {
					((DatabaseView) dataView).update(ModeleDAO.getDAO().getModeles());
				} else {
					List<ModeleData> ls = ModeleDAO.getDAO().getModeleByTag(field.getText());
					List<ModeleData> ls1 = (ModeleDAO.getDAO().getModeleByNames(field.getText()));
					List<ModeleData> res = new ArrayList<ModeleData>(ls1);
					res.addAll(ls);
					((DatabaseView) dataView).update(res);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		field.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (field.getText().trim().equals("")) {
					field.setText(FINDMSG);
					field.setForeground(Color.GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (field.getText().trim().equals(FINDMSG)) {
					field.setText("");
					field.setForeground(Color.BLACK);
				}
			}
		});

		JButton button = iconButton("clear");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				field.setText(FINDMSG);
				field.setForeground(Color.GRAY);
				((DatabaseView) dataView).update(ModeleDAO.getDAO().getModeles());
			}
		});

		panel.add(field);
		panel.add(Box.createHorizontalStrut(3));
		panel.add(button);

		return panel;
	}

	private JPanel panelBoutons() {

		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setMaximumSize(new Dimension(10000, 50));

		JButton add = iconButton("add");
		JButton edit = iconButton("edit");
		JButton delete = iconButton("delete");
		JButton info = iconButton("info");

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new AddFormPanel().getFrame();
				frame.setLocationRelativeTo(add.getParent().getParent().getParent());
				frame.setVisible(true);
			}
		});

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!Memory.getMemory().getCurrent().isNull()) {
					JFrame frame = new EditFormPanel(Memory.getMemory().getCurrent()).getFrame();
					frame.setLocationRelativeTo(edit.getParent().getParent().getParent());
					frame.setVisible(true);

				} else {
					JOptionPane.showMessageDialog(edit.getParent().getParent().getParent(), "Aucun modèle sélectionné.",
							"Impossible de modifier", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!Memory.getMemory().getCurrent().isNull()) {

					if (JOptionPane.showConfirmDialog(delete.getParent().getParent().getParent(),
							"Voulez-vous vraiment supprimer le modèle: " + Memory.getMemory().getCurrent().getName()
									+ " ?",
							"Supprimer", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {

						ModeleDAO.getDAO().delete(Memory.getMemory().getCurrent());
						Memory.getMemory().resetCurrent();
					}

				} else {
					JOptionPane.showMessageDialog(delete.getParent().getParent().getParent(),
							"Aucun modèle sélectionné.", "Impossible de supprimer", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!Memory.getMemory().getCurrent().isNull()) {
					JFrame frame = new InfoFormPanel(Memory.getMemory().getCurrent()).getFrame();
					frame.setLocationRelativeTo(info.getParent().getParent().getParent());
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(edit.getParent().getParent().getParent(), "Aucun modèle sélectionné.",
							"Action impossible", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel.add(add);
		panel.add(Box.createHorizontalStrut(2));
		panel.add(edit);
		panel.add(Box.createHorizontalStrut(2));
		panel.add(delete);
		panel.add(Box.createHorizontalStrut(5));
		panel.add(new JSeparator(SwingConstants.VERTICAL));
		panel.add(info);

		return panel;
	}
}
