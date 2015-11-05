package swampgod;

public class Fish extends GoodObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11052015;

	public Fish(){
		isGood= true;
		pointValue= 7;
		healthValue= 7;
		type= "Fish";
		speed= 18;
		stream= this.pickStream();
	}
}
