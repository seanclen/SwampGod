package swampgod;

/**
 * Keeps track of good and bad objects and the path of the estuary
 * 
 */
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import Objects.BadObject;
import Objects.EstuaryObject;
import Objects.GoodObject;
import Objects.Plant;

public class Stream implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 11082015;
	private int id; // 0, 1, 2
	ArrayList<GoodObject> goodObjects;
	ArrayList<BadObject> badObjects;
	ArrayList<Plant> plants;
	private Rectangle bounds;
	CubicCurve2D streamCurve;
	/**
	 * constructs the stream (rectangle)
	 */
	public Stream(int streamNumber){
		this.id = streamNumber;
		badObjects = new ArrayList<BadObject>();
		goodObjects = new ArrayList<GoodObject>();
		plants = new ArrayList<Plant>();
		bounds = new Rectangle((this.id * 320), 0, 320, 400);
		streamCurve = new CubicCurve2D.Double();
		// draw CubicCurve2D.Double with set coordinates
		streamCurve.setCurve(bounds.x, 0, bounds.x+280, 200, bounds.x+100, 250, bounds.x+200, 400);
	}
	
	public int getId() {
		return this.id;
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}
	
	/**
	 * initializes bad objects
	 * @param num
	 */
	public void createBadObjects(int num){
		for (int i = 0; i < num; i++) {
			BadObject newObject = BadObject.createRandom();
			newObject.setStream(id);
			newObject.setPosition(new Point(this.bounds.x + 100, 100));
			newObject.setBounds(this.bounds.x + 100, 0, 32, 32);
			badObjects.add(newObject);
		}
		
	}
	
	/**
	 * initializes good objects
	 * @param num
	 */
	public void createGoodObjects(int num){
		for (int i = 0; i < num; i++) {
			GoodObject newObject = GoodObject.createRandom();
			newObject.setStream(id);
			newObject.setPosition(new Point(this.bounds.x + 100, 100));
			newObject.setBounds(this.bounds.x + 100, 0, 32, 32);
			goodObjects.add(newObject);
		}
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
		updatedObjects.addAll(plants);
		moveObjects();
		return updatedObjects;
	}
	
	/**
	 * sets the plant object at the position entered
	 * @param pl - an object plant
	 * @param pos - a point on the board
	 */
	public void placePlantAt(Plant pl, Point pos){
		
	}
	
	private void moveObjects() {
		for (GoodObject obj : goodObjects) {
			update(obj);
		}
	}
	
	/* Bezier Curve in the form of:
	 * [x,y]=(1–t)^3*P0+3(1–t)^2*t*P1+3(1–t)t^2*P2+t^3*P3
	 * t is time(value of 0.0f-1.0f; 0 is the start 1 is the end) 
	 * */
	Point CalculateBezierPoint(float t, Point2D start, Point2D ctrlP1, Point2D ctrlP2, Point2D end)
	{
	  float u = (1 - t);
	  float tt = t*t;
	  float uu = u*u;
	  float uuu = uu * u;
	  float ttt = tt * t;

	  Point p = new Point((int)(start.getX() * uuu), (int)(start.getY() * uuu));
	  p.x += 3 * uu * t * ctrlP1.getX();
	  p.y += 3 * uu * t * ctrlP1.getY();
	  p.x += 3 * u * tt * ctrlP2.getX();
	  p.y += 3 * u * tt * ctrlP2.getY();
	  p.x += ttt * end.getX();
	  p.y += ttt * end.getY();

	  return p;
	}
	
	int percentMovedPerFrame = 1;// Will complete path in 100 frames
	int currentPercent = 0;
	private void update(EstuaryObject obj) {
	   if (currentPercent < 500) {
	      Point p = CalculateBezierPoint(currentPercent / 500.0f, streamCurve.getP1(), streamCurve.getCtrlP1(), streamCurve.getCtrlP2(), streamCurve.getP2());
	      currentPercent += percentMovedPerFrame;
	      obj.setPosition(p);
	   }
	}

}
