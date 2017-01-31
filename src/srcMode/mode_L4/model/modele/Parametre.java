package mode_L4.model.modele;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Classe regroupant les parametres du modele
 *
 */
public class Parametre {

	private LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();

	/**
	 * Constructeur: initalise aux positions par defaut les parametres dans une
	 * map. Les parametres par defaut sont ceux optimises pour l'affichage des
	 * modeles
	 */
	public Parametre() {
		map.put("points", false);
		map.put("segments", false);
		map.put("faces", true);
		map.put("lumiere", true);
		map.put("ombre", true);
		map.put("grille", true);
	}

	/**
	 * Retourne la taille de la map des parametres
	 * 
	 * @return le nombre de parametres
	 */
	public int getNombre() {
		return map.size();
	}

	/**
	 * Retourne le nom
	 * 
	 * @param id
	 *            la position du parametre dans la map
	 * @return le nom a la position id
	 */
	public String getNom(int id) {
		List<String> liste = new ArrayList<>(map.keySet());
		return liste.get(id);
	}

	/**
	 * Retourne le nom de la ligne de la map avec la premiere lettre en
	 * majuscule
	 * 
	 * @param id
	 *            la position du parametre dans le map
	 * @return le nom a la position id
	 */
	public String getJoliNom(int id) {
		String nom = getNom(id);
		return nom.substring(0, 1).toUpperCase() + nom.substring(1);
	}

	/**
	 * Retourne un parametre en fonction de son nom
	 * 
	 * @param key
	 *            le nom (String) du parametre
	 * @return le boolean correspondant au nom
	 */
	public boolean getParametre(String key) {
		return this.map.get(key);
	}

	/**
	 * Retourne un parametre en fonction de sa place dans la map
	 * 
	 * @param id
	 *            la position du parametre dans la map
	 * @return le boolean correspondant a l'id
	 */
	public boolean getParametre(int id) {
		List<String> liste = new ArrayList<>(map.keySet());
		return this.getParametre(liste.get(id));
	}

	/**
	 * Definit un parametre dans la map a partir de son nom et d'un boolean
	 * 
	 * @param key
	 *            nom (String) du parametre
	 * @param value
	 *            valeur (boolean) du parametre
	 */
	public void setParametre(String key, boolean value) {
		this.map.put(key, value);
	}

	/**
	 * Inverse la valeur du boolean d'un parametre
	 * 
	 * @param key
	 *            nom (String) du parametre a inverser
	 */
	public void changerParametre(String key) {
		setParametre(key, !getParametre(key));
	}

	/**
	 * Inverse la valeur du boolean d'un parametre
	 * 
	 * @param id
	 *            numero dans la Map du parametre a inverser
	 */
	public void changerParametre(int id) {

		List<String> liste = new ArrayList<>(map.keySet());
		changerParametre(liste.get(id));

	}
}
