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
	
	/**
	 * 
	 * @return - a rectanfgle, the bounds of the estuary
	 */
	public Rectangle getBounds() {
		return this.bounds;
	}
	
	/**
	 * 
	 * @param bounds - the bounds you want the game to have
	 * sets the attribute bounds as the param
	 */
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	/**
	 * CHECK TO MAKE SURE THIS IS RIGHT
	 * adds fish to the estuary
	 */
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
	public void moveFish(){
		for(GoodObject fish: goodObjects){
			Fish fish1 = (Fish)fish;
			int x=(int) fish1.getPosition().getX();
			int y=(int) fish1.getPosition().getY();
			int xx = fish1.getSwimX();
			int yy=fish1.getSwimY();
		  	if(x>bounds.getMaxX()-40){

		  		fish1.setSwimX(0-xx);
		  		fish1.setDir(fish1.getDir()+1);
		  	}
		  	else if(x<bounds.getMinX()){ 
		  		fish1.setSwimX(0-xx);
		  		fish1.setDir(fish1.getDir()+1);
		  	}
		  	else if(y>bounds.getMaxY()-40){
		  		fish1.setSwimY(0-yy);
		  	}
		  	else if(y<bounds.getMinY()){

		  		fish1.setSwimY(0-yy);
		  	}
		  	//System.out.println(x + "  "+y+ "        "+bounds.getMinX()+ " "+bounds.getMaxX()+"   "+ bounds.getMinY()+" "+bounds.getMaxY());
		  	x+=fish1.getSwimX();
		  	y+=fish1.getSwimY();
		  	int i = (int) (1 + (Math.floor(Math.random() * 2)));
		  	if(i==2){
		  		y++;
		  	}
		  	else{
		  		y--;
		  	}
		  	Point p = new Point(x+=fish1.getSwimX(), y+=fish1.getSwimY());
		  	fish.setPosition(p);
		}
	}
	/**
	 * @param numFish - takes in the attribute fishCount
	 * removes the fish from the list of estuary objects
	 */
	public void removeFish(int numFish){
		int fishToGo = goodObjects.size()-numFish;
		for(int i=0; i<fishToGo; i++){
			goodObjects.remove(0);
		}
		
	}
	
}
