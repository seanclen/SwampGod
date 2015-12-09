package objects;

/**
 * LilyPad extends GoodObject; LilyPad will flow down the streams
 */

public class LilyPad extends GoodObject{
	
	private static final long serialVersionUID = 11082015;

	public LilyPad() {
		isGood= true;
		pointValue= 15;
		healthValue= 7;
		type= "LilyPad";
		speed= 53; //28
	}

}
