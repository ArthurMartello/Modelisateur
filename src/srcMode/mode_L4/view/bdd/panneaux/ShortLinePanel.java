package mode_L4.view.bdd.panneaux;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import mode_L4.model.bdd.Memory;
import mode_L4.model.bdd.ModeleData;

/**
 * LinePanel affichant le titre et la date d'ajout d'un ModeleData
 */
public class ShortLinePanel extends LinePanel {
	
	private static final long serialVersionUID = 2180320372693263153L;
	
	/**
	 * Initialise les valeurs de titres.
	 */
	public ShortLinePanel() {
		super();
		super.tailleField = 8;
		super.addTitleField("Nom");
		super.addTitleField("Date d'ajout");
		
		JButton b = new JButton();
		b.setBorderPainted(false);
		b.setBackground(null);
		b.setPreferredSize(new Dimension(10, 10));
		super.add(b);

	}

	/**
	 * Initalise le panneau avec comme valeurs le titre et la date d'ajout d'un ModeleData
	 * 
	 * @param modele
	 *            ModeleData dont on affiche les valeurs
	 */
	public ShortLinePanel(ModeleData modele) {
		super();
		try{
		super.tailleField = 8;
		
		super.addField(modele.getName());
		super.addField(modele.getDate());
		
		
		JButton b = new JButton();
		b.setBorderPainted(false);
		b.setBackground(null);
		b.setPreferredSize(new Dimension(10, 10));
		
		if (!Memory.getMemory().isSaved(modele)) {
			b.setIcon(new ImageIcon("../icons/bdd/load.png"));			
		} else {			
			b.setIcon(new ImageIcon("../icons/bdd/loaded.png"));			
		}
			
		super.add(b);
		}catch(NullPointerException e){
	//		System.err.println("NullPointerException, mais ca casse rien."
	//				+ "ShortLinePanel > Constructor. À voir plus tard");
		}
	}

}
