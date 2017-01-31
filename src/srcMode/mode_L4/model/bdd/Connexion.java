package mode_L4.model.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe creant un objet Connection SQLite et dont l'adresse de la table est
 * donnee en parametre
 *
 */
public final class Connexion {

	private Connection connect = null;

	private static Connexion instance = new Connexion("../data/modeles.db");

	/**
	 * Initialise une connexion à partir de son nom de fichier de base de
	 * données.
	 * 
	 * @param pathBDD
	 *            Adresse du fichier de la BDD
	 */
	public Connexion(String pathBDD) {

		try {

			Class.forName("org.sqlite.JDBC");
			this.connect = DriverManager.getConnection("jdbc:sqlite:" + pathBDD);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Retourne la connection
	 * 
	 * @return un objet Connection
	 */
	public Connection getConnection() {
		return this.connect;
	}

	/**
	 * @return Retourne l'instance de la Connexion
	 */
	public static synchronized Connexion getInstance() {
		return instance;
	}

}
