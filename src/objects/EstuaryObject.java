package objects;
/**
 * This class is the superclass for all objects good and bad
 * 
 * @author Natalie Corrado
 * @author Kate Travers
 * @author Jarrod Bieber
 * @author Zac Merrit
 * @author Sean Clendening
 */

import java.awt.Point;
import java.awt.Rectangle;
import swampgod.Stream;
import swampgod.Level;
public class EstuaryObject implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 11082015;
	
	boolean moving = false;
	Point position = new Point();
	double streamCompletion = 0;
	Rectangle bounds = new Rectangle();
	boolean isGood;
	
	//how many points are added when in estuary/removed when in trash
	//good have positive values, bad have negative
	int pointValue;
	//how much health it adds or removes when in estuary
	int healthValue;
	String type;
	//How fast it will move
	double speed;
	//What stream it will be contained in
	int stream;
	

	public boolean getMoving(){
		return moving;
	}
	public void setMoving(){
		moving = true;
	}
	
	/**
	 * Moves the object down the stream towards the estuary
	 */

	public boolean move(int level){
		streamCompletion+=((speed/10000)*((double) Level.speedMultiplier[level]));
		return checkPosition();
	}
	
	/**
	 * checks to see if element is in the estuary needs to be removed from stream.
	 * return - 1 if the object is past line and 0 if it's still in stream
	 */
	
	//TODO 
	public boolean checkPosition(){
		if(streamCompletion >= 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Sets the location of the object setPosition is being used on
	 * @param l - takes in a Point of the location you want to set
	 */
	public void setPosition(Point l) {
		this.position = l;
		bounds.x = l.x;
		bounds.y = l.y;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public void setStreamCompletion(double percentage) {
		this.streamCompletion = percentage;
	}
	
	public double getStreamCompletion() {
		return this.streamCompletion;
	}
	
	public void setBounds(int x, int y, int width, int height) {
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}
	
	/**
	 * Randomly generates what stream the object being called on is going to go down
	 * @return an int based on the which stream it picks
	 */
	public static int pickStream(){
		
		return (int) (Math.floor(Math.random() * 3)); // random int 0, 1, or 2
	}
	
	public int getStream() {
		return this.stream;
	}
	
	public void setStream(int i) {
		this.stream = i;
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
	public double getSpeed(){
		return this.speed;
	}
}
