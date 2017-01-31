package mode_L4.view.fenetre;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import mode_L4.controler.fenetre.FrameControler;
import mode_L4.controler.fenetre.FrameListener;
import mode_L4.controler.modele.KeyboardListener;
import mode_L4.controler.modele.ModeleControler;
import mode_L4.controler.modele.OptionControler;
import mode_L4.controler.modele.PaintControler;
import mode_L4.controler.modele.ResetControler;
import mode_L4.model.bdd.ModeleDAO;
import mode_L4.model.modele.Modele;
import mode_L4.model.modele.ModeleObservable;
import mode_L4.view.bdd.DatabaseView;
import mode_L4.view.fenetre.panneaux.ButtonsPanel;
import mode_L4.view.fenetre.panneaux.DatabasePanel;
import mode_L4.view.fenetre.panneaux.ParametersMenuBar;
import mode_L4.view.modele.ModeleObserver;
import mode_L4.view.modele.Painter;
import mode_L4.view.modele.PainterState;

/**
 * Fenêtre d'affichage des composants, notemment le panneau d'affichage
 */
public class Frame extends FrameObservable {

	private static final long serialVersionUID = -2491772232138630507L;

	public final static int OFFSETX = 228;
	public final static int OFFSETY = 157;

	/**
	 * Initialise la fenêtre
	 * 
	 * @param modeleObservable
	 *            Un objet ModeleObservable
	 * @param title
	 *            L'adresse du Modele à afficher
	 */
	public Frame(ModeleObservable modeleObservable) {

		super();

		// Controleurs
		ModeleControler paintControler = new PaintControler(modeleObservable);
		ModeleControler optionControler = new OptionControler(modeleObservable);
		ModeleControler resetControler = new ResetControler(modeleObservable);
		FrameControler frameControler = new FrameControler(this, modeleObservable);

		// Affichage
		super.pack();
		super.setVisible(true);

		PainterState state;

		if (((Modele) modeleObservable).getListePoints().isEmpty())
			state = PainterState.FIRST_OPENING;
		else
			state = PainterState.MODEL_DISPLAY;

		// JPanel BDD
		super.add(new DatabasePanel(
				new DatabaseView((ResetControler) resetControler, frameControler, ModeleDAO.getDAO().getModeles())),
				BorderLayout.LINE_START);

		// Barre de menus
		super.setJMenuBar(new ParametersMenuBar(modeleObservable, (OptionControler) optionControler));
		
		// Panel d'affichage
		Painter painter = new Painter((PaintControler) paintControler, state);		
		JPanel modelView = new JPanel();
		modelView.setLayout(new BoxLayout(modelView, BoxLayout.Y_AXIS));
		modelView.add(painter);
		modelView.add(new ButtonsPanel((PaintControler) paintControler, frameControler)); //boutons de déplacement
		super.add(modelView);

		// Observateurs
		ModeleObserver modeleObserver = painter;
		modeleObservable.addObserver(modeleObserver);

		// Listeners sur la Frame
		super.addKeyListener(new KeyboardListener((PaintControler) paintControler));
		super.addComponentListener(new FrameListener(frameControler));

	}

	/**
	 * Initialise une fenêtre à partir d'un modèle vide
	 */
	public Frame() {
		this(new Modele());
	}

}
