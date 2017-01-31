package mode_L4.model.bdd;

import java.util.ArrayList;
import java.util.List;

/**
 * Objet représentant un Modèle stocké dans la base de données.
 */
public class ModeleData {

	private String name;
	private String path;
	private String date;
	private List<String> keys;

	/**
	 * Initialise un objet ModeleData vide.
	 */
	public ModeleData(){
		name = "";
		path = "";
		date = "";
		keys = new ArrayList<>();
	}
	
	/**
	 * Crée un objet conformément à ses colonnes dans la base de données.
	 * 
	 * @param name
	 *            Un chaine de caractères pour son nom
	 * @param path
	 *            Un chaine de caractères pour son adresse
	 * @param date
	 *            Un chaine de caractères pour sa date de création
	 * @param keys
	 *            Un chaine de caractères pour ses mots clefs
	 */
	public ModeleData(String name, String path, String date, String keys) {
		this.name = name;
		this.path = path;
		this.date = date;
		this.keys = this.getKeysFromString(keys);
	}
	
	private List<String> getKeysFromString(String string) {

		List<String> liste = new ArrayList<>();
		string = string.replaceAll("\\W", " ");
		string = string.replaceAll("( ){2,}", " ");

		String tmp = "";
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == ' ') {
				liste.add(tmp);
				tmp = "";
			} else {
				tmp += string.charAt(i);
			}
		}
		liste.add(tmp);

		return liste;
	}

	/**
	 * @return Retourne vrai si le nom ou l'adresse est vide
	 */
	public boolean isNull() {		
		return name.equals("") || path.equals("");
	}

	/**
	 * @return Retourne une chaine de caractères de son nom
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Retourne une chaine de caractères de son adresse
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return Retourne une chaine de caractères de sa date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return Retourne une liste des ses mots clefs
	 */
	public List<String> getKeys() {
		return keys;
	}

	/**
	 * @return Retourne une chaine de caractères de ses mots clefs séparés par
	 *         une virgule
	 */
	public String getKeysInString() {
		String keys = "";
		for (int i = 0; i < this.keys.size(); i++) {
			keys += this.keys.get(i);
			if (i < this.keys.size() - 1) {
				keys += ", ";
			}
		}
		return keys;
	}
	
	/**
	 * @return Retourne une chaine de caractères de l'objet
	 */
	public String toString() {
		return "[name=" + name + ", path=" + path + ", date=" + date + ", keys=" + getKeysInString() + "]";
	}

}
