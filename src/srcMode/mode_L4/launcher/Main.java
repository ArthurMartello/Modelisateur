package mode_L4.launcher;

import java.awt.EventQueue;

import mode_L4.model.bdd.Memory;
import mode_L4.model.bdd.ModeleDAO;
import mode_L4.model.bdd.ModeleData;
import mode_L4.model.modele.Modele;
import mode_L4.model.modele.ModeleObservable;
import mode_L4.model.modele.Parametre;
import mode_L4.model.modele.ply_import.Comment;
import mode_L4.model.modele.ply_import.ModeleFileReader;
import mode_L4.view.fenetre.Frame;

/**
 * Classe destinee a lancer une fenetre d'affichage d'un modele rentre en
 * parametre
 *
 */
public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				Argument argument = new Argument(args);

				if (argument.isValide()) {

					if (argument.getAdresse() != null) {
						
						ModeleData modeleData = ModeleDAO.getDAO().getModeleByPath(argument.getAdresse());

						Memory.getMemory().addToMemory(modeleData);
						ModeleObservable modele = new Modele();
						((Modele) modele).reimport(Memory.getMemory().getFromMemory(modeleData));

						if (ModeleFileReader.estValide()) {

							Parametre options = new Parametre();
							options.setParametre("segments", argument.afficherSegments());
							options.setParametre("faces", argument.afficherFaces());

							((Modele) modele).setParametres(options);

							Frame frame = new Frame(modele);
							frame.setTitle("Modelisateur - "+modeleData.getPath());

						} else {
							System.err.println("\n" + argument.getAdresse()
									+ " : Impossible d'importer le fichier specifie (Format invalide)");
							System.exit(1);
						}

						if (Comment.getListe().size() != 0) {
							System.out.println();
							Comment.afficherCommentaires();
						}
						
					} else {
						new Frame();					
					}				
				}
			}
		});

	}
}
