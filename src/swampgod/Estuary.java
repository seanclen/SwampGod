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

}
