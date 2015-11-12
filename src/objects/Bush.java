package objects;

import java.awt.Point;

public class Bush extends Plant{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11052015;

	public Bush(Point p) {
		super(p);
		type= "Bush";
		position= p;
		radius = 5;
		percentEat= 55;
	}

}
