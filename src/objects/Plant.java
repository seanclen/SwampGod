package objects;

/**
  * This class represents possible plants that can be placed in the estuary
  */

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
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
	private boolean canPlace = false;
	
	/**
	 * @param p - the place on the board it is placed at
	 */
	public Plant(Point p){
		position=p;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public Shape getRadiusShape() {
		int radiusMultiplied = radius*(getSize().width/5);
		Shape r = new Ellipse2D.Double(
				getBounds().x-radiusMultiplied,
				getBounds().y-radiusMultiplied,
				getBounds().width+(radiusMultiplied*2),
				getBounds().height+(radiusMultiplied*2));
		System.out.println("Plant:getRadiusShape "+r.getBounds());
		return r;
	}
	
	public void setPosition(Point p){
		position=p;
		bounds.setLocation(p.x, p.y);
	}
	
	public String getType(){
		return type;
	}
	
	public Point getPosition(){
		return position;
	}

	
	/**
	 * removes the object from the list of objects
	 */
	public EstuaryObject eat(ArrayList<BadObject> bobj){
		System.out.println("eat called");
		int radiusMultiplied = radius*(getSize().width/5);
		for(BadObject bo : bobj){
			if (Math.abs(bo.position.getX() - this.position.getX()) <=  radiusMultiplied
					&& Math.abs(bo.position.getY() - this.position.getY()) <= radiusMultiplied){
				System.out.println("IM EATING");
				return bo;
			}
		}
		return null;
		
	}
	
	/**
	 * @return an object in the radius of the plant and computes the percentage to decide
	 * if it should be eaten, calls the eat function
	 */
	public EstuaryObject checkRadius(EstuaryObject tempO){
		Random rn = new Random();
		int chance = rn.nextInt(1000)+1;
		if (chance <= percentEat){
			//EstuaryObject eatObj = eat(boObj);
			return tempO;
		}
		else{
			return null;
		}
	}
	
	public int getPointsPerPlants(){
		return pointsPerPlant;
	}
	
	public boolean canPlace() {
		return this.canPlace;
	}
	
	public void setCanPlace(boolean b) {
		canPlace = b;
	}
	
	public String toString(){
		return("This is a " + type + " at " + position.getX() + ", " + position.getY());
	}
}
