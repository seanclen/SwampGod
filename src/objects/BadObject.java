package objects;

import java.util.Random;

/**
 * This class is a sub class of Estuaryobject its main purpose is to be a constructor for bad ojects
 */
public class BadObject extends EstuaryObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11082015;

	/**
	 * @param t - a type of plant used to set that specific plants attributes
	 * constructs a instance of object with type t
	 */
	public BadObject(){
		
	}
	
	/**
	 * @return a boolean
	 * how to find out if the objects edible or not
	 */
	public boolean edible(){
		return true;
	}
	
	public static BadObject createRandom() {
		BadObject returnObject = null;
		int randomInt = (int) (1 + (Math.floor(Math.random() * 3))); // random int 1, 2, or 3
		switch (randomInt) {
		case 1:
			returnObject = new Algae();
			break;
		case 2:
			returnObject = new Crab();
			break;
		case 3:
			returnObject = new HazardWaste();
		}
		return returnObject;
	}
}