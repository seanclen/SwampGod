/**
 * The point of this class is to keep track of all objects good and bad specfically in the basin of the estuary
 */
package swampgod;

import java.util.ArrayList;

public class Estuary implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5964163876971167697L;
	ArrayList<GoodObject> goodObjectList;
	ArrayList<BadObject> badObjectList;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	// location of top-left point of the rectangle
	static int startX = 0;
	static int startY = screenSize.getHeight() * 0.75;
	
	// width and height of the estuary
	static double estWidth = screenSize.getWidth();
	static double estHeight = screenSize.getHeight() * 0.25;
	
	// a rectangle that defines the boundaries of the estuary
	public static Rectangle est;
	est = new Rectangle(startX, startY, estWidth, estHeight);
	
}
