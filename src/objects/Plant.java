package objects;

/**
  * This class represents possible plants that can be placed in the estuary
  */

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Plant extends EstuaryObject implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 11082015;
	int pointsPerPlant;
	String type;
	Point position;
	int radius;
	int percentEat;
	
	/**
	 * @param p - the place on the board it is placed at
	 */
	public Plant(Point p){
		position=p;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public void setPosition(Point p){
		position=p;
	}
	
	public String getType(){
		return type;
	}
	
	/**
	 * removes the object from the list of objects
	 */
	public EstuaryObject eat(ArrayList<BadObject> bobj){
		for(BadObject bo : bobj){
			if (Math.abs(bo.position.getX() - this.position.getX()) <= 10 
					&& Math.abs(bo.position.getY() - this.position.getY()) <= 10){
				return bo;
			}
		}
		return null;
		
	}
	
	/**
	 * @return an object in the radius of the plant and computes the percentage to decide
	 * if it should be eaten, calls the eat function
	 */
	public EstuaryObject checkRadius(ArrayList<BadObject> boObj){
		Random rn = new Random();
		int chance = rn.nextInt(100)+1;
		if (chance <= percentEat){
			EstuaryObject eatObj = eat(boObj);
			return eatObj;
		}
		else{
			return null;
		}
	}
}
