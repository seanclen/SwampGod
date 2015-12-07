package objects;

/**
 * Clam extends GoodObject; Clam will flow down the streams
 */

public class Clam extends GoodObject{
	
	private static final long serialVersionUID = 11082015;

	public Clam(){
		isGood= true;
		pointValue= 18;
		healthValue= 8;
		type= "Clam";
		speed= 40;
	}
}
