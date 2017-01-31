package mode_L4.view.bdd;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import mode_L4.controler.fenetre.FrameControler;
import mode_L4.controler.modele.ResetControler;
import mode_L4.model.bdd.ModeleDAO;
import mode_L4.model.bdd.ModeleData;
import mode_L4.view.bdd.panneaux.LinePanel;
import mode_L4.view.bdd.panneaux.ShortLinePanel;

/**
 * Interface pour mettre à jour la vue en cas de modification dans la liste des
 * modèles dans la base de données.
 */
interface DatabaseObserver {

	/**
	 * Met à jour la liste des modèles de la BDD.
	 * 
	 * @param modeles
	 *            Liste de ModeleData.
	 */
	public void update(List<ModeleData> modeles);

}

/**
 * Vue de la base de données via une liste de ModeleData
 *
 */
public class DatabaseView extends JScrollPane implements DatabaseObserver {

	private static final long serialVersionUID = 6266449487249387120L;

	private ResetControler resetControler;
	private FrameControler frameControler;
	private List<ModeleData> modeles;
	private DefaultListModel<LinePanel> dlm = new DefaultListModel<>();
	private JList<LinePanel> liste = new JList<>(dlm);

	static int current = 0;

	/**
	 * Initialise la vue à partir d'un controleur du modele et de la fenêtre et
	 * de la liste des modèles à afficher.
	 * 
	 * @param resetControler
	 *            Controleur du Modele
	 * @param frameControler
	 *            Controleur de la Frame
	 * @param modeles
	 *            Liste des modèles à afficher
	 */
	public DatabaseView(ResetControler resetControler, FrameControler frameControler, List<ModeleData> modeles) {
		this.resetControler = resetControler;
		this.frameControler = frameControler;
		this.modeles = modeles;
		this.liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ModeleDAO.getDAO().addObserver(this);
		super.setViewportView(liste);
		this.refreshList();
	}

	private void refreshList() {
		this.dlm.clear();
		this.dlm.addElement(new ShortLinePanel());
		for (int i = 0; i < modeles.size(); i++) {
			this.dlm.addElement(new ShortLinePanel(modeles.get(i)));
		}
		this.liste.setCellRenderer(new DataJListRenderer(resetControler, frameControler, modeles));
	}

	@Override
	public void update(List<ModeleData> modeles) {
		this.modeles = modeles;
		this.refreshList();
	}

}

/**
 * Class destinée à initialiser le rendu de la liste de la vue.
 */
class DataJListRenderer implements ListCellRenderer<LinePanel> {

	private ResetControler resetControler;
	private FrameControler frameControler;
	private List<ModeleData> modeles;

	/**
	 * Initialise le renderer
	 * @param resetControler Controleur pour réinitialiser le modèle en cas de changement
	 * @param frameControler Controleur pour redonner le focus à la fenêtre
	 * @param modeles liste des ModeleData à afficher
	 */
	public DataJListRenderer(ResetControler resetControler, FrameControler frameControler, List<ModeleData> modeles) {
		this.resetControler = resetControler;
		this.frameControler = frameControler;
		this.modeles = modeles;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends LinePanel> list, LinePanel value, int index,
			boolean isSelected, boolean cellHasFocus) {

		if (isSelected && index != 0 && DatabaseView.current != index && cellHasFocus) {
			value.setBackground(new Color(230, 230, 230));
			resetControler.resetModele(modeles.get(index - 1));
			DatabaseView.current = index;
			frameControler.requestFocus();
			frameControler.setName("Modelisateur - " + modeles.get(index - 1).getPath());

		} else {
			value.setBackground(Color.white);
		}

		if (index == DatabaseView.current) {
			value.setBackground(new Color(150, 200, 200));
		}

		if (index == 0) {
			value.setBackground(new Color(200, 200, 200));
		}

		return value;
	}

}
