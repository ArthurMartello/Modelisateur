package mode_L4.view.fenetre.panneaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import mode_L4.controler.fenetre.FrameControler;
import mode_L4.controler.modele.Action;
import mode_L4.controler.modele.ButtonListener;
import mode_L4.controler.modele.Direction;
import mode_L4.controler.modele.PaintControler;

/**
 * Classe creant un Panel regroupant tous les boutons d'actions
 *
 */
public class ButtonsPanel extends JPanel {

	private static final long serialVersionUID = 4188920689871296823L;
	
	private PaintControler paintControler;
	private FrameControler frameControler;

	/**
	 * Initialise le panel à partir des contrôleurs avec lesquels il interragit
	 * @param paintControler Contoleur pour modifier l'affichage
	 * @param frameControler Controleur pour redonner le focus sur la fenêtre
	 */
	public ButtonsPanel(PaintControler paintControler, FrameControler frameControler) {

		this.paintControler = paintControler;
		this.frameControler = frameControler;

		setBackground(new Color(238, 238, 238));
		setBorder(new LineBorder(new Color(204, 204, 204)));
		setPreferredSize(new Dimension(100, 105));		
		setMaximumSize(new Dimension(10000, 105));

		JPanel translation = move("Déplacement");
		JPanel rotation = rotate("Rotation");
		JPanel options = options("Options");

		add(translation);
		add(options);
		add(rotation);
	}

	private JButton newButton(String name, Action action, Direction direction, double nombre) {
		JButton b = new JButton(new ImageIcon("../icons/move/default/"+name+".png"));
		b.setBackground(new Color(240, 240, 240));
		b.setRolloverIcon(new ImageIcon("../icons/move/pressed/"+name+".png"));
		b.setPressedIcon(new ImageIcon("../icons/move/pressed/"+name+".png"));
		b.setFocusPainted(false);
		
        b.setContentAreaFilled(false); 
		ButtonListener listener = new ButtonListener(this.paintControler, this.frameControler, action, direction, nombre);
		b.addChangeListener(listener);
		return b;
	}
	
	private JPanel options(String titre) {

		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(100, 95));

		panel.setBorder(new TitledBorder(titre));

		// Bouton reset

		JButton reset = newButton("center", Action.CADRAGE, null, 0);
		reset.setMargin(new Insets(15, 5, 15, 5));
		reset.setToolTipText("Recentre le modele");
		panel.add(reset);

		JPanel midPanel = new JPanel();
		panel.add(midPanel);
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));

		JButton unzoom = newButton("zoomout", Action.ZOOM, null, 0.8);
		unzoom.setAlignmentX(CENTER_ALIGNMENT);
		unzoom.setMargin(new Insets(1, 8, 0, 8));
		unzoom.setToolTipText("Dezoom le modele");
		midPanel.add(unzoom);

		// Separateur
		midPanel.add(Box.createVerticalStrut(3));

		JButton zoom = newButton("zoomin", Action.ZOOM, null, 1.2);
		zoom.setAlignmentX(CENTER_ALIGNMENT);
		zoom.setMargin(new Insets(2, 8, 0, 8));
		zoom.setToolTipText("Zoom le modele");
		midPanel.add(zoom);
		;

		return panel;

	}

	private JPanel move(String titre) {

		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(186, 95));

		panel.setBorder(new TitledBorder(titre));

		// Bouton de gauche
		panel.add(newButton("left", Action.TRANSLATION, Direction.GAUCHE, 3.0));

		// Panel regroupant les boutons du haut et du bas
		JPanel midPanel = new JPanel();
		panel.add(midPanel);
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));

		// Bouton du haut
		JButton top = newButton("up", Action.TRANSLATION, Direction.HAUT, 3.0);
		top.setAlignmentX(CENTER_ALIGNMENT);
		midPanel.add(top);

		// Separateur
		midPanel.add(Box.createVerticalStrut(3));

		// Bouton du bas
		JButton down = newButton("down", Action.TRANSLATION, Direction.BAS, 3.0);
		down.setAlignmentX(CENTER_ALIGNMENT);
		midPanel.add(down);

		// Bouton de droite
		panel.add(newButton("right", Action.TRANSLATION, Direction.DROITE, 3.0));

		return panel;
	}

	private JPanel rotate(String titre) {			

		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(180, 95));


		panel.setBorder(new TitledBorder(titre));

		JPanel leftPanel = new JPanel();
		panel.add(leftPanel);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		// Separateur
		leftPanel.add(Box.createVerticalStrut(3));

		// Vers gauche
		JButton midLeft = newButton("left", Action.ROTATION, Direction.AXEY, 5.0);
		midLeft.setPreferredSize(new Dimension(50, 20));
		midLeft.setMargin(new Insets(0, 15, 0, 15));
		leftPanel.add(midLeft);

		// Separateur
		leftPanel.add(Box.createVerticalStrut(3));

		// Vers gauche
		JButton downLeft = newButton("rotateleft", Action.ROTATION, Direction.AXEZ, -5.0);
		downLeft.setPreferredSize(new Dimension(50, 20));
		downLeft.setMargin(new Insets(0, 15, 0, 15));
		leftPanel.add(downLeft);

		JPanel midPanel = new JPanel();
		panel.add(midPanel);
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));

		// Vers haut-gauche
		JButton topMid = newButton("up", Action.ROTATION, Direction.AXEX, 5.0);
		topMid.setPreferredSize(new Dimension(50, 20));
		topMid.setMargin(new Insets(0, 15, 0, 15));
		midPanel.add(topMid);

		// Separateur
		midPanel.add(Box.createVerticalStrut(26));

		// Vers gauche
		JButton downMid = newButton("down", Action.ROTATION, Direction.AXEX, -5.0);
		downMid.setPreferredSize(new Dimension(50, 20));
		downMid.setMargin(new Insets(0, 15, 0, 15));
		midPanel.add(downMid);

		JPanel rightPanel = new JPanel();
		panel.add(rightPanel);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		// Separateur
		rightPanel.add(Box.createVerticalStrut(3));

		// Vers droite
		JButton midRight = newButton("right", Action.ROTATION, Direction.AXEY, -5.0);
		midRight.setPreferredSize(new Dimension(50, 20));
		midRight.setMargin(new Insets(0, 15, 0, 15));
		rightPanel.add(midRight);

		// Separateur
		rightPanel.add(Box.createVerticalStrut(3));

		// Vers gauche
		JButton downRight = newButton("rotateright", Action.ROTATION, Direction.AXEZ, 5.0);
		downRight.setPreferredSize(new Dimension(50, 20));
		downRight.setMargin(new Insets(0, 15, 0, 15));
		rightPanel.add(downRight);

		return panel;
	}
}
