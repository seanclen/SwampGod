package swampgod;

/**
 * This class creates a instance of a trash can
 */

import java.awt.Point;
import java.awt.Rectangle;


public class TrashCan implements java.io.Serializable{
	private static final long serialVersionUID = 1152015;
	Point position;
	int radius;
	Rectangle bounds = new Rectangle();
	
	/**
	 * Constructs a trash can with a position and radius
	 */
	public TrashCan(){
		Point pt = new Point(800, 100);
		position = pt;
		setBounds(position.x, position.y, 100, 100);
		radius =200;
	}
	
	/**
	 * Sets the bounds for the trashcan
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void setBounds(int x, int y, int width, int height) {
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	/**
	 * gets the trashcans position
	 * @return - a point, the attribute position
	 */
	public Point getTrashPosition(){
		return position;
	}
	
	/**
	 * gets the bounds for the trashcan
	 * @return - a rectangle, the attribute bounds
	 */
	public Rectangle getBounds() {
		return this.bounds;
	}
}

