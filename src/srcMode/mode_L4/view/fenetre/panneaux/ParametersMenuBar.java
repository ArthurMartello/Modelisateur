package mode_L4.view.fenetre.panneaux;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import mode_L4.controler.modele.CheckBoxListener;
import mode_L4.controler.modele.OptionControler;
import mode_L4.model.modele.Modele;
import mode_L4.model.modele.ModeleObservable;

/**
 * Barre de menu de la fenêtre
 */
public class ParametersMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private ModeleObservable modele;
	private OptionControler controler;

	/**
	 * Initialise la barre de menu en fonction d'un Modele et de son controleur
	 * pour en modifier les options.
	 * 
	 * @param modele
	 *            Un objet ModeleObservable
	 * @param controler
	 *            Un objet OptionControler
	 */
	public ParametersMenuBar(ModeleObservable modele, OptionControler controler) {

		this.modele = modele;
		this.controler = controler;

		this.setOpaque(false);

		for (int i = 0; i < ((Modele) modele).getParametres().getNombre(); i++) {
			this.add(boxFactory(i));

			if (i < ((Modele) modele).getParametres().getNombre() - 1) {
				this.add(new JLabel(new ImageIcon("../icons/box/thinseparator.png")));
			}
		}

		this.add(Box.createHorizontalGlue());

		JButton help = buttonKeyboardFactory("Commandes");
		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame(help.getText());
				frame.add(new JLabel(new ImageIcon("../icons/help.png")));
				frame.setPreferredSize(new Dimension(800, 321));
				frame.setMinimumSize(new Dimension(800, 350));
				frame.setLocationRelativeTo(help.getParent().getParent());
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});

		JButton about = buttonInfoFactory("À propos");
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame(about.getText());
				frame.add(new JLabel(new ImageIcon("../icons/about.png")));
				frame.setPreferredSize(new Dimension(800, 310));
				frame.setMinimumSize(new Dimension(800, 310));
				frame.setLocationRelativeTo(about.getParent().getParent());
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});

		this.add(help);
		this.add(new JLabel(new ImageIcon("../icons/box/thinseparator.png")));
		this.add(about);
		this.add(Box.createHorizontalStrut(5));
	}

	private JCheckBox boxFactory(int idx) {

		JCheckBox box = new JCheckBox(((Modele) modele).getParametres().getJoliNom(idx));
		box.setIcon(new ImageIcon("../icons/box/default.png"));
		box.setSelectedIcon(new ImageIcon("../icons/box/selected.png"));
		box.setRolloverIcon(new ImageIcon("../icons/box/defaultroll.png"));
		box.setRolloverSelectedIcon(new ImageIcon("../icons/box/selectedroll.png"));
		box.setSelected(((Modele) this.modele).getParametres().getParametre(idx));
		box.addActionListener(new CheckBoxListener(controler, idx));
		return box;
	}

	private JButton buttonInfoFactory(String name) {

		JButton b = new JButton(new ImageIcon("../icons/box/info.png"));
		b.setRolloverIcon(new ImageIcon("../icons/box/inforoll.png"));
		b.setPressedIcon(new ImageIcon("../icons/box/inforoll.png"));
		b.setText(name);
		b.setFocusPainted(false);
		b.setContentAreaFilled(false);
		b.setBorder(null);

		return b;
	}

	private JButton buttonKeyboardFactory(String name) {

		JButton b = new JButton(new ImageIcon("../icons/box/keyboard.png"));
		b.setRolloverIcon(new ImageIcon("../icons/box/keyboardroll.png"));
		b.setPressedIcon(new ImageIcon("../icons/box/keyboardroll.png"));
		b.setText(name);
		b.setFocusPainted(false);
		b.setContentAreaFilled(false);
		b.setBorder(null);

		return b;
	}

}
