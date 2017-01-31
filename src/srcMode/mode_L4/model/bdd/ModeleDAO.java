package mode_L4.model.bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mode_L4.view.bdd.DatabaseView;

/**
 * Méthodes à implémenter pour un ajouters des observateurs au DAO et les
 * notifier.
 */
interface ObservableDAO {

	/**
	 * Ajoute une vue récupérant les modifications de la BDD.
	 * 
	 * @param view
	 *            Un objet DatabaseView affichant les lignes sous forme de
	 *            liste.
	 */
	public void addObserver(DatabaseView observer);

	/**
	 * Réinitalise les observateurs
	 */
	public void removeObserver();

	/**
	 * Notifie de se mettre à jour tous les observateurs du DAO.
	 */
	public void notifyObserver();

}

/**
 * Singleton ayant pour but d'agir sur le base de données pour la modifier ou
 * d'en extraire des informations sous forme de ModeleData.
 */
public final class ModeleDAO implements ObservableDAO {

	private Connexion con;

	private static ModeleDAO dao = new ModeleDAO(Connexion.getInstance());

	private List<DatabaseView> observers = new ArrayList<>();

	/**
	 * Initialise un DAO à partir d'un Connexion à une base de données.
	 * 
	 * @param con
	 *            Connexion correspondant à la BDD.
	 */
	public ModeleDAO(Connexion con) {
		this.con = con;
	}

	/**
	 * @return Retourne l'instance de l'objet
	 */
	public static synchronized ModeleDAO getDAO() {
		return dao;
	}

	@Override
	public void addObserver(DatabaseView observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver() {
		observers = new ArrayList<DatabaseView>();
	}

	@Override
	public void notifyObserver() {
		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).update(this.getModeles());
		}
	}

	/**
	 * @return Retourne une liste de toutes les lignes de la BDD sous forme de
	 *         ModeleData.
	 */
	public List<ModeleData> getModeles() {

		try {

			List<ModeleData> liste = new ArrayList<>();

			PreparedStatement ps = con.getConnection().prepareStatement("SELECT * FROM Modele");
			ResultSet set = ps.executeQuery();

			while (set.next()) {
				liste.add(new ModeleData(set.getString(1), set.getString(2), set.getString(3), set.getString(4)));
			}

			return liste;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retourne le modèle correspondant à un nom précis.
	 * 
	 * @param name
	 *            nom du modèle recherché
	 * @return le modèle s'il existe, null sinon
	 */
	public ModeleData getModeleByName(String name) {

		List<ModeleData> liste = getModeles();
		for (int i = 0; i < liste.size(); i++) {

			if (liste.get(i).getName().contains(name)) {
				return liste.get(i);
			}
		}
		return null;
	}

	/**
	 * Retourne une ligne particulière.
	 * 
	 * @param name
	 *            Nom (clef primaire) de la ligne
	 * @return Une liste d'objet ModeleData des informations de cette ligne ou
	 *         null si elle n'existe pas.
	 */
	public List<ModeleData> getModeleByNames(String name) {

		List<ModeleData> liste = getModeles();
		List<ModeleData> res = new ArrayList<>();
		for (int i = 0; i < liste.size(); i++) {

			if (liste.get(i).getName().contains(name)) {
				res.add(liste.get(i));
			}
		}
		return res;
	}

	/**
	 * Retourne une ligne particulière.
	 * 
	 * @param path
	 *            Adresse dans la ligne
	 * @return Un objet ModeleData de la première apparition de cette adresse ou
	 *         null si elle n'existe pas.
	 */
	public ModeleData getModeleByPath(String path) {

		List<ModeleData> liste = getModeles();

		for (int i = 0; i < liste.size(); i++) {

			if (("../data/" + liste.get(i).getPath()).equals(path)) {
				return liste.get(i);
			}
		}
		return null;
	}

	/**
	 * Retourne une liste de ModeleData correspondant à un mot clef.
	 * 
	 * @param tag
	 *            Mot clef sous forme de chaine
	 * @return une liste de ModeleData.
	 */
	public List<ModeleData> getModeleByTag(String tag) {

		List<ModeleData> retour = new ArrayList<>();

		List<ModeleData> modeles = getModeles();

		for (int i = 0; i < modeles.size(); i++) {

			List<String> keys = modeles.get(i).getKeys();

			for (int j = 0; j < keys.size(); j++) {

				if (keys.get(j).equals(tag)) {
					retour.add(modeles.get(i));
				}

			}
		}
		return retour;
	}

	/**
	 * Ajoute une ligne dans la BDD.
	 * 
	 * @param modele
	 *            Objet ModeleData regroupant les données de la ligne.
	 * @return Retourne vrai si l'oppération n'a pas rencontré de problème.
	 */
	public boolean insert(ModeleData modele) {

		if (modele.getName().equals("") || modele.getPath().equals("")) {
			System.err.println("Erreur d'ajout: Le nom et l'adresse ne doivent pas être vides.");
			return false;
		}

		if (this.getModeleByName(modele.getName()) != null) {
			System.err.println("Erreur d'ajout: Ce nom est déjà utilisé.");
			return false;
		}

		try {

			PreparedStatement ps = con.getConnection().prepareStatement("INSERT INTO Modele VALUES (?, ?, ?, ?)");

			ps.setString(1, modele.getName());
			ps.setString(2, modele.getPath());
			ps.setString(3, modele.getDate());
			ps.setString(4, modele.getKeysInString());

			ps.executeUpdate();

			this.notifyObserver();
			return true;

		} catch (SQLException e) {
			System.err.println("Erreur d'ajout: " + e.toString());
			return false;
		}
	}

	/**
	 * Modifie une ligne dans la BDD.
	 * 
	 * @param modele
	 *            Objet ModeleData regroupant les données de la ligne.
	 * @return Retourne vrai si l'oppération n'a pas rencontré de problème.
	 */
	public boolean update(ModeleData modele) {

		if (modele.getName().equals("") || modele.getPath().equals("")) {
			System.err.println("Erreur de modification: Le nom et l'adresse ne doivent pas être vides.");
			return false;
		}

		try {

			PreparedStatement ps = con.getConnection()
					.prepareStatement("UPDATE Modele SET name = ?, path = ?, date = ?, keys = ? WHERE name = ?");

			ps.setString(1, modele.getName());
			ps.setString(2, modele.getPath());
			ps.setString(3, modele.getDate());
			ps.setString(4, modele.getKeysInString());
			ps.setString(5, modele.getName());

			ps.executeUpdate();
			Memory.getMemory().removeFromMemory(modele);
			Memory.getMemory().resetCurrent();
			this.notifyObserver();
			return true;

		} catch (SQLException e) {
			System.err.println("Erreur de modification: " + e.toString());
			return false;
		}
	}

	/**
	 * Supprime une ligne dans la BDD.
	 * 
	 * @param modele
	 *            Objet ModeleData regroupant les données de la ligne à
	 *            supprimer.
	 * @return Retourne vrai si l'oppération n'a pas rencontré de problème.
	 */
	public boolean delete(ModeleData modele) {

		if (this.getModeleByName(modele.getName()) == null) {
			System.err.println("Erreur de suppression: ce modèle n'existe pas.");
			return false;
		}

		try {

			PreparedStatement ps = con.getConnection().prepareStatement("DELETE FROM Modele WHERE name = ?");
			ps.setString(1, modele.getName());
			ps.executeUpdate();
			Memory.getMemory().removeFromMemory(modele);
			Memory.getMemory().resetCurrent();
			this.notifyObserver();
			return true;

		} catch (SQLException e) {
			System.err.println("Erreur de suppression: " + e.toString());
			return false;
		}

	}

}
