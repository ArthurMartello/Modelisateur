package mode_L4.launcher;

import java.io.File;

/**
 * Classe gerant les arguments au lancement du programme d'import
 *
 */
public class Argument {

	private boolean valide = true;
	private boolean afficherFaces = false;
	private boolean afficherSegments = false;
	private String adresse;

	/**
	 * Constructeur: test la validite des arguments donnes a l'ouverture du
	 * fichier
	 * 
	 * @param args
	 *            Tableau des arguments
	 */
	public Argument(String[] args) {

		try {

			if (args.length > 0) {

				for (int i = 0; i < args.length; i++) {

					if (args[i].matches("^-\\w+$")) {

						if (args[i].equals("-f")) {
							this.afficherFaces = true;
						} else if (args[i].equals("-s")) {
							this.afficherSegments = true;
						} else if (args[i].equals("-fs") || args[i].equals("-sf")) {
							this.afficherFaces = true;
							this.afficherSegments = true;
						} else {
							throw new IllegalArgumentException("Erreur: Parametre [" + args[i] + "] inconnu.");
						}
					}

					if (args[i].matches("^.*(.ply)$")) {
						this.adresse = verifierFichier(args[i]);

					} else if (!args[i].matches("^-\\w+$")) {
						throw new IllegalArgumentException(
								"Erreur: Le fichier [" + args[i] + "] n'est pas au format .ply.");
					}

				}
				
				if (!afficherFaces && !afficherSegments) {
					afficherFaces = true;
				}

				if (adresse == null) {
					throw new IllegalArgumentException("Erreur: Aucun fichier n'a ete renseigne.");
				}

			}

		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
			this.valide = false;
			System.exit(1);
		}

	}

	/**
	 * Verifie la disponibilite d'un fichier
	 * 
	 * @param adresse
	 *            du fichier sous forme de chaine
	 * @return l'adresse s'il n'y a pas eu d'erreur
	 */
	public String verifierFichier(String adresse) {
		File fichier = new java.io.File(adresse);
		try {
			if (!fichier.exists()) {
				throw new IllegalArgumentException("Erreur: Le fichier [" + adresse + "] est introuvable.");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			this.valide = false;
		}
		return adresse;
	}

	/**
	 * @return vrai si les arguments et leurs positions sont valides
	 */
	public boolean isValide() {
		return valide;
	}

	/**
	 * @return vrai si l'utilisateur a choisi d'afficher les faces
	 */
	public boolean afficherFaces() {
		return this.afficherFaces;
	}

	/**
	 * @return vrai si l'utilisateur a choisi d'afficher les segments
	 */
	public boolean afficherSegments() {
		return this.afficherSegments;
	}

	/**
	 * @return l'adresse du fichier a exploiter
	 */
	public String getAdresse() {
		return this.adresse;
	}
}
