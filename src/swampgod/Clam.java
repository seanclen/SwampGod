package swampgod;

public class Clam extends GoodObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11052015;

	public Clam(){
		isGood= true;
		pointValue= 18;
		healthValue= 18;
		type= "LilyPad";
		speed= 23;
		stream= this.pickStream();
	}
}
