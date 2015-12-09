package objects;

/**
 * Tree extends Plant; Tree will be able to be placed on the game screen
 */

import java.awt.Point;

public class Tree extends Plant{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11052015;

	public Tree(Point p) {
		super(p);
		pointsPerPlant= 60;
		type= "Tree";
		position= p;
		radius = 8;
		percentEat= 1;
	}

}
