package mode_L4.model.bdd;

import java.util.HashMap;
import java.util.Map;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.ply_import.ModeleFileReader;

/**
 * Mémoire des modèles déjà importés.
 * 
 * @author Arthur Martello
 *
 */
public class Memory {

	private static Memory instance = new Memory();
	private ModeleData current = new ModeleData();
	private Map<String, Modele> memory = new HashMap<>();

	/**
	 * @return l'instance principale de la mémoire.
	 */
	public static synchronized Memory getMemory() {
		return instance;
	}

	/**
	 * @return vrai si la mémoire est vide.
	 */
	public boolean isEmpty() {
		return this.memory.isEmpty();
	}

	/**
	 * @return le nombre d'objet importés dans la mémoire.
	 */
	public int size() {
		return this.memory.size();
	}

	/**
	 * Importe un objet et l'ajoute à la mémoire
	 * 
	 * @param modeleData
	 *            Informations de l'objet dans la base de données.
	 */
	public void addToMemory(ModeleData modeleData) {
		memory.put(modeleData.getName(), new ModeleFileReader(modeleData));
		ModeleDAO.getDAO().notifyObserver();
	}

	/**
	 * Supprime un objet de la mémoire.
	 * 
	 * @param modeleData
	 *            Informations de l'objet dans la base de données.
	 */
	public void removeFromMemory(ModeleData modeleData) {
		memory.remove(modeleData.getName());
		ModeleDAO.getDAO().notifyObserver();
	}

	/**
	 * @param modeleData
	 *            Nom de l'objet voulu dans la base de données.
	 * @return un Modele
	 */
	public Modele getFromMemory(ModeleData modeleData) {

		if (isSaved(modeleData)) {
			this.current = modeleData;
			ModeleDAO.getDAO().notifyObserver();
			return memory.get(modeleData.getName());
		}
		return null;
	}

	/**
	 * Réinitialise le modèle courant.
	 */
	public void resetCurrent() {
		this.current = new ModeleData();
	}

	/**
	 * @return retourne le dernier modèle utilisé via getFromMemory()
	 */
	public ModeleData getCurrent() {
		return this.current;
	}

	/**
	 * Dit si l'objet est déjà dans la mémoire
	 * 
	 * @param modeleData
	 *            objet dans la BDD
	 * @return Retourne vrai si l'objet est déjà importé dans la mémoire.
	 */
	public boolean isSaved(ModeleData modeleData) {
		return memory.containsKey(modeleData.getName());
	}

}
