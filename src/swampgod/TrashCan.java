package swampgod;

/**
 * This class creates a instance of a trash can
 */

import java.awt.Point;

public class TrashCan implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1152015;
	Point Position;
	int radius;
	
	
	/**
	 * Constructs a trash can with a position and radius
	 */
	public TrashCan(){
		Position.setLocation(100, 500);
		radius =200;
	}
}

