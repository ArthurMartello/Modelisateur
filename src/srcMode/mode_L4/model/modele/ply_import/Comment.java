package mode_L4.model.modele.ply_import;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe representant et testant la validite des commentaires contenus dans les
 * fichiers .ply
 *
 */
public class Comment {

	private String ligne = "";
	private String message = "";
	private boolean valide;

	private static List<Comment> liste = new ArrayList<>();

	public Comment(String ligne) {

		this.ligne = ligne;
		// this.ligne = ligne.substring(ligne.length() - 2, ligne.length() - 1);

		boolean flagMes = trouverMessage();
		boolean flagCom = trouverComment();
		valide = flagMes && flagCom;
		liste.add(this);

	}

	private boolean trouverComment() {
		Pattern regex = Pattern.compile("\\#comment.*|\\#comments.*/g");
		Matcher m = regex.matcher(this.ligne);
			if (!m.find()) {
				return false;
			}				
		return true;
	}

	private boolean trouverMessage() {
		
		if(ligne.startsWith("#comments")){
			this.message = this.ligne.substring("#comments ".length());
			return true;
		}

		if(ligne.startsWith("#comment")){
			this.message = this.ligne.substring("#comment ".length());
			return true;
		}


		
		return false;
	}

	/**
	 * Affiche la liste de tous les commentaires du fichier .ply
	 */
	public static void afficherCommentaires() {
		System.out.print("Comments:\n");
		for (int i = 0; i < Comment.getListe().size(); i++) {
			System.out.print("> " + liste.get(i).toString()+"\n");
		}

	}

	/**
	 * Validty of the Comment
	 * @return true if the comment starts with #comment
	 */
	public boolean getValide() {
		return valide;
	}

	/**
	 * Retourne le message du commentaire
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Retourne la liste des commentaires
	 * 
	 * @return
	 */
	public static List<Comment> getListe() {
		return liste;
	}

	@Override
	public String toString() {
		return message;
	}
	
	

}
