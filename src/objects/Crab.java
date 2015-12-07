package objects;

/**
 * Crab extends BadObject; Crab will flow down the streams
 */

public class Crab extends BadObject {

	private static final long serialVersionUID = 11082015;
	
	public Crab() {
		isGood = false;
		pointValue = -17;
		healthValue = -25;
		speed = 33;
		type="Crab";
	}

}
