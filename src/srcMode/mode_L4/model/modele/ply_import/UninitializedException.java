package mode_L4.model.modele.ply_import;

@SuppressWarnings("serial")
public class UninitializedException extends Exception {
	
	public UninitializedException() {
		super();
		ModeleFileReader.unvalid();

	}
	
	public UninitializedException(String message) {
		super(message);
		ModeleFileReader.unvalid();
	}

}
