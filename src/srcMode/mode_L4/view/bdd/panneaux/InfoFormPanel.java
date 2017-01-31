package mode_L4.view.bdd.panneaux;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mode_L4.model.bdd.ModeleData;

/**
 * Form panel non-editable pour visualiser des informations
 *
 */
public class InfoFormPanel extends FormPanel {

	private static final long serialVersionUID = 8951279102171012606L;
	
	private ModeleData oldData;

	/**
	 * Initialise le panneau en fonction de ses valeurs
	 * @param oldData ModeleData des valeurs à afficher
	 */
	public InfoFormPanel(ModeleData oldData) {

		this.oldData = oldData;
		
		super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		super.add(new FullLinePanel());
		super.add(this.fieldsPanel());

		super.setFrame(new InfoFormFrame(this));
	}
	
	protected ModeleData getOldData() {
		return this.oldData;
	}

	@Override
	protected JPanel fieldsPanel() {

		JPanel panel = new JPanel();

		addField(panel, oldData.getName(), false);
		addField(panel, oldData.getPath(), false);
		addField(panel, oldData.getDate(), false);
		addField(panel, oldData.getKeysInString(), false);

		return panel;

	}

	@Override
	protected JButton sendButton() {
		return null;
	}

}

class InfoFormFrame extends JFrame {

	private static final long serialVersionUID = -859276295433270174L;

	public InfoFormFrame(InfoFormPanel panel) {
		
		super.add(panel);
		super.setVisible(true);
		super.setTitle("Informations - "+panel.getOldData().getName());
		super.pack();
		super.setResizable(false);

	}
}
