/**
 * The point of this class is to keep track of all objects good and bad specfically in the basin of the estuary
 */
package swampgod;

import java.awt.Rectangle;
import java.util.ArrayList;

import objects.BadObject;
import objects.EstuaryObject;
import objects.GoodObject;

public class Estuary implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 11082015;
	ArrayList<GoodObject> goodObjects;
	ArrayList<BadObject> badObjects;
	private Rectangle bounds;
	
	public Estuary() {
		goodObjects = new ArrayList<GoodObject>();
		badObjects = new ArrayList<BadObject>();
		bounds = new Rectangle(0, 400, 960, 240);
	}
	
	/**
	 * Implement this to get the objects that have changed and must be redrawn.
	 * @return list of objects to be updated
	 */
	public ArrayList<EstuaryObject> getObjectsToDraw() {
		ArrayList<EstuaryObject> updatedObjects = new ArrayList<EstuaryObject>();
		//TODO need to come back and implement a check
		updatedObjects.addAll(badObjects);
		updatedObjects.addAll(goodObjects);
		return updatedObjects;
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
}
