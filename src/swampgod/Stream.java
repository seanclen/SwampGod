package swampgod;

/**
 * Keeps track of good and bad objects and the path of the estuary
 * 
 */
import java.awt.Point;
import java.awt.Rectangle;

public class Stream implements java.io.Serializable{
	
	GoodObject goodObjects[];
	BadObject badObjects[];
	Rectangle bounds;
	Plant plants[];
	
	/**
	 * constructs the stream (rectangle)
	 */
	public Stream(){
		
	}
	
	/**
	 * initializes bad objects
	 */
	void createBadObject(){

	}
	
	/**
	 * initializes good objects
	 */
	void createGoodObject(){
		
	}
	
	/**
	 * sets the plant object at the position entered
	 * @param pl - an object plant
	 * @param pos - a point on the board
	 */
	void placePlantAt(Plant pl, Point pos){
		
	}

}
