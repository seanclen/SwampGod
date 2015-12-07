package objects;

import java.awt.Point;

/**
 * Bush extends Plant; Bush will be able to be placed on the game screen
 */

public class Bush extends Plant{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11052015;

	public Bush(Point p) {
		super(p);
		pointsPerPlant= 10;
		type= "Bush";
		position= p;
		radius = 7;
		percentEat= 60;

	}

}
