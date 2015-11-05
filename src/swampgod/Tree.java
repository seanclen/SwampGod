package swampgod;

import java.awt.Point;

public class Tree extends Plant{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11052015;

	public Tree(Point p) {
		super(p);
		type= "Tree";
		position= p;
		radius = 8;
		percentEat= 35;
	}

}
