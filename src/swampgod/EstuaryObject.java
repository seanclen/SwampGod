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

public class EstuaryObject implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1216807115822693876L;
	
	Point position;
	boolean isGood;
	//how many points are added when in estuary/removed when in trash
	//goo have positive values, bad have negative
	int pointValue;
	//how much health it adds or removes when in estuary
	int healthValue;
	//What creature it is
	//bad - "crab" "algea" "waste"
	//good - "fish" "clam" "lily pad"
	String type;
	//How fast it will move
	int speed;
	//What stream it will be contained in
	int stream;
	
	
	/**
	 * Moves the object down the stream towards the estuary
	 */
	public void move(){
		
	}
	
	/**
	 * checks to see if element is in the estuary needs to be removed from stream.
	 * return - 1 if the object is past line and 0 if it's still in stream
	 */
	public boolean checkPosition(){
		
		return true;
		// :)
		
	}
	/**
	 * Sets the location of the object setPosition is being used on
	 * @param l - takes in a Point of the location you want to set
	 */
	public void setPosition(Point l) {
		this.position = l;
	}
	
	/**
	 * Randomly generates what stream the object being called on is going to go down
	 * @return an int based on the which stream it picks
	 */
	public int pickStream(){
		
		return 0;
	}
	
	/**
	 * gets the position of a certain object
	 * @return - the point the object its being called on is at
	 */
	public Point getPos(){
		return this.position;
	}
	
	/**
	 * gets the health value of the estuary
	 * @return - an int, the health value
	 */
	public int getHealthValue(){
		return this.healthValue;
	}
	
	/**
	 * 
	 * @return - a boolean, describing if the object is good or bad and needs to be removed
	 */
	public boolean isGood(){
		return this.isGood;
	}
	
	/**
	 * 
	 * @return - an int, how many points you have earned
	 */
	public int getPointValue(){
		return this.pointValue;
	}
	
	/**
	 * 
	 * @return - what type of object that it is called on
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * 
	 * @return - the speed at which a certain object is traveling at
	 */
	public int getSpeed(){
		return this.speed;
	}
}
