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
	Point Position;
	int radius;
	Rectangle bounds = new Rectangle();
	
	/**
	 * Constructs a trash can with a position and radius
	 */
	public TrashCan(){
		Position.setLocation(100, 500);
		setBounds(Position.x, Position.y, 200, 200);
		
		radius =200;
	}
	public void setBounds(int x, int y, int width, int height) {
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}
}

