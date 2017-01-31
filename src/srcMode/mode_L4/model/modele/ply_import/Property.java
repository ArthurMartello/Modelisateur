package mode_L4.model.modele.ply_import;

public class Property {

	private String ligne = "";
	private String type = "";
	private String param = "";
	private boolean valide;

	public Property(String ligne) {

		this.ligne = ligne.trim();
		this.ligne = ligne.replaceAll("( ){2,}", " ");
		this.ligne = ligne.replaceAll("\n", "");

		this.valide = trouverProperty() && trouverType() && trouverParam();

	}

	private boolean trouverProperty() {

		try {

			if (!this.ligne.matches("^property\\s.*$")) {
				throw new FormatException("La ligne " + ligne + " ne contient pas le mot-clef #property");
			} else {
				return true;
			}

		} catch (FormatException e) {
			System.out.println(e.toString());
			return false;
		}

	}

	private boolean trouverType() {

		String type = "";

		type = this.ligne.substring("property ".length());

		try {
			
			if (type.matches("^float32\\s.*$")) {
				this.type = "float32";
				return true;
			} else if (type.matches("^list\\s.*$")) {
				this.type = "list";
				return true;
			} else {
				throw new FormatException("Type pour #property inconnu.");
			}
			
		} catch (FormatException e) {
			System.out.println(e.toString());
			return false;
		}

	}

	private boolean trouverParam() {

		this.param = this.ligne.substring("property".length() + type.length() + 2);
		
		return true;

	}

	public boolean isValide() {
		return valide;
	}

	public String toString() {
		return "Property [type=" + type + ", param=" + param + "]";
	}

}
