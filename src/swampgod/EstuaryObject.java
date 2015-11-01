package swampgod;
/**
 * This class is the superclass for all objects good and bad
 * 
 * @author Natalie Corrado
 * @author Kate Travers
 * @author Jarrod Bieber
 * @author Zac Merrit
 * @author Sean Clending
 */

import java.awt.Point;

public class EstuaryObject {
	Point position;
	boolean isGood;
	int pointValue;
	int healthValue;
	String type;
	double speed;
	
	
	
	/**
	 * Moves the object down the stream towards the estuary
	 */
	public void Move(){
		
	}
	
	/**
	 * checks to see if element is in the estuary
	 * and needs to be removed.
	 * return - 1 is past line 0 is still in stream
	 */
	public boolean checkPosition(){
		
		return true;
		// :)
		
	}
	
	public void setPosition(Point l) {
		this.position = l;
	}
	
	/**
	 * @return an int based on the which stream it picked
	 */
	public int pickStream(){
		
		return 0;
	}
	
	public Point getPos(){
		return this.position;
	}
}
