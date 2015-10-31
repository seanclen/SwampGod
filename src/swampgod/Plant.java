package swampgod;

/**
  * This class represents possible plants that can be placed in the estuary
  */

import java.awt.Point;

public class Plant {
	String type;
	Point position;
	int radius;
	int percentEat;
	
	/**
	 * @param t - type of plant 
	 * @param p - the place on the board it is placed at
	 */
	public Plant(String t, Point p){
		
	}
	
	/**
	 * removes the object from the list of objects
	 */
	public void eat(){
		
	}
	
	/**
	 * @return an object in the radius of the plant and computes the percentage to decide
	 * if it should be eaten, calls the eat function
	 */
	public EstuaryObject checkRadius(){
		return null;
	}
}
