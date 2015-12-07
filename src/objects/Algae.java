package objects;

/**
 * Algae extends BadObject; Algae will flow down the streams
 */

public class Algae extends BadObject{
	
	private static final long serialVersionUID = 11082015;

	public Algae() {
		pointValue = -15;
		healthValue = -15;
		speed = 45;
		isGood=false;
		type="Algae";
	}

}
