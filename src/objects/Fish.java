package objects;

/**
 * Fish extends GoodObject; Fish will flow down the streams
 */

public class Fish extends GoodObject{

	private static final long serialVersionUID = 11082015;

	public Fish(){
		isGood= true;
		pointValue= 7;
		healthValue= 5;
		type= "Fish";
		speed= 50;
	}
}
