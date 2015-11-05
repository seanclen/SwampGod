package swampgod;

public class Crab extends BadObject {

	private static final long serialVersionUID = 11052015L;
	
	public Crab() {
		isGood = false;
		pointValue = 17;
		healthValue = 11;
		speed = 5;
		stream = this.pickStream();
		type="Crab";
	}

}
