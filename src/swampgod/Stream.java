package swampgod;

/**
 * Keeps track of good and bad objects and the path of the estuary
 * 
 */
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import objects.BadObject;
import objects.EstuaryObject;
import objects.GoodObject;
import objects.Plant;
import static swampgod.Level.*;

public class Stream implements java.io.Serializable{
	
	private static final long serialVersionUID = 11082015;
	private int id; // 0, 1, 2
	ArrayList<GoodObject> goodObjects;
	ArrayList<BadObject> badObjects;
	//ArrayList<Plant> plants;
	private Rectangle bounds;
	private CubicCurve2D streamCurve;
	
	/**
	 * constructs the stream (rectangle)
	 */
	public Stream(int streamNumber){
		this.id = streamNumber;
		badObjects = new ArrayList<BadObject>();
		goodObjects = new ArrayList<GoodObject>();
		//plants = new ArrayList<Plant>();
		bounds = new Rectangle((this.id * 320), 0, 320, 400);
		streamCurve = streamCurves[id];
	}
	
	/**
	 * initializes bad objects
	 * @param num
	 */
	public void createBadObjects(int num){
		for (int i = 0; i < num; i++) {
			BadObject newObject = BadObject.createRandom();
			newObject.setStream(id);
			newObject.setPosition(new Point(this.bounds.x + 100, -100));
			newObject.setBounds(this.bounds.x + 100, -100, 32, 32);
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
			newObject.setPosition(new Point(this.bounds.x + 100, -100));
			newObject.setBounds(this.bounds.x + 100, -100, 32, 32);
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
		//updatedObjects.addAll(plants);
		//moveObjects();
		return updatedObjects;
	}
	
	/**
	 * Generates a new CubicCurve2D from double percentage parameters. This way
	 * the curves can resize and are independent on the screen resolution.
	 * @param pCurve
	 * @return CubicCurve2D
	 */
	private CubicCurve2D generateCurveFromPercentage(CubicCurve2D pCurve) { //TODO
		double xStart = pCurve.getX1(),
				yStart = pCurve.getY1(),
				ctrlx1 = pCurve.getCtrlX1(),
				ctrly1 = pCurve.getCtrlY1(),
				ctrlx2 = pCurve.getCtrlX2(),
				ctrly2 = pCurve.getCtrlY1(),
				xEnd = pCurve.getX2(),
				yEnd = pCurve.getY2();
		return new CubicCurve2D.Double(xStart, yStart, ctrlx1, ctrly1, ctrlx2, ctrly2, xEnd, yEnd);
	}
	/* Bezier Curve in the form of:
	 * [x,y]=(1–t)^3*P0+3(1–t)^2*t*P1+3(1–t)t^2*P2+t^3*P3
	 * t is time where 0 is the start 1 is the end)
	 * */
	Point CalculateBezierPoint(double t){
		Point2D start = streamCurve.getP1(), ctrlP1 = streamCurve.getCtrlP1(),
				ctrlP2 = streamCurve.getCtrlP2(), end = streamCurve.getP2();
		double u = (1 - t);
		double tt = t*t;
		double uu = u*u;
		double uuu = uu * u;
		double ttt = tt * t;

		Point p = new Point((int)(start.getX() * uuu), (int)(start.getY() * uuu));
		p.x += 3 * uu * t * ctrlP1.getX();
		p.y += 3 * uu * t * ctrlP1.getY();
		p.x += 3 * u * tt * ctrlP2.getX();
		p.y += 3 * u * tt * ctrlP2.getY();
		p.x += ttt * end.getX();
		p.y += ttt * end.getY();

		return p;
	}
	
//	/**
//	 * Moves an object down the stream by calculating cartesian points
//	 * based on the objects progress down the stream (streamCompletion).
//	 * @param obj the EstuaryObject to move down the stream
//	 */

	public CubicCurve2D getStreamCurve() {
		return streamCurve;
	}

	public void setStreamCurve(CubicCurve2D streamCurve) {
		this.streamCurve = streamCurve;
	}
	
	public int getId() {
		return this.id;
	}
	
	public ArrayList<BadObject> getBadObjects() {
		return badObjects;
	}
	
	public ArrayList<GoodObject> getGoodObjects() {
		return goodObjects;
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

}
