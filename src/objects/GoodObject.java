package objects;

/**
 * This class creates good instances of object
 */

public class GoodObject extends EstuaryObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 11082015;

	/**
	 * constructs a instance of object
	 */
	public GoodObject(){
		isGood= true;
	}
	
	public static GoodObject createRandom() {
		GoodObject returnObject = null;
		int randomInt = (int) (1 + (Math.floor(Math.random() * 3))); // random int 1, 2, or 3
		switch (randomInt) {
		case 1:
			returnObject = new Clam();
		case 2:
			returnObject = new Fish();
		case 3:
			returnObject = new LilyPad();
		}
		return returnObject;
	}
}
