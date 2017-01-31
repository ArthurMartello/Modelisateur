package mode_L4.model.modele.ply_import;

public class FormatException extends Exception {

	private static final long serialVersionUID = -2347376950916677688L;

	public FormatException() {
		super();
		ModeleFileReader.unvalid();
	}

	public FormatException(String message) {
		super(message);
		ModeleFileReader.unvalid();
	}

}
