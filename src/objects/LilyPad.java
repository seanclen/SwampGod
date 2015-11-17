package objects;

public class LilyPad extends GoodObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11082015;

	public LilyPad() {
		isGood= true;
		pointValue= 15;
		healthValue= 15;
		type= "LilyPad";
		speed= 60;
	}

}
