/**
 * The point of this class is to keep track of all objects good and bad specfically in the basin of the estuary
 */
package swampgod;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import objects.BadObject;
import objects.EstuaryObject;
import objects.Fish;
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
	
	public void addFish(){
		Fish fish = new Fish();
		int x = (int) (bounds.getMinX() + Math.floor(Math.random()*(bounds.getMaxX() + 10 - bounds.getMinX())));
		int y = (int) (bounds.getMinY() + Math.floor(Math.random()*(bounds.getMaxY() + 1 - bounds.getMinY())));
		//int x = 50;
		//int y=450;
		Point p = new Point(x,y);
		fish.setPosition(p);
		goodObjects.add(fish);
		
		
	}
	
	public void removeFish(int numFish){
		int fishToGo = goodObjects.size()-numFish;
		for(int i=0; i<fishToGo; i++){
			goodObjects.remove(0);
		}
		
	}
	
}
