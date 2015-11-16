package swampgod;

/**
 * This class creates a instance of a trash can
 */

import java.awt.Point;
import java.awt.Rectangle;


public class TrashCan implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1152015;
	Point position;
	int radius;
	Rectangle bounds = new Rectangle();
	
	/**
	 * Constructs a trash can with a position and radius
	 */
	public TrashCan(){
		Point pt = new Point(100, 500);
		position = pt;
		setBounds(position.x, position.y, 200, 200);
		radius =200;
	}
	public void setBounds(int x, int y, int width, int height) {
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	public Point getTrashPosition(){
		return position;
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}
}

