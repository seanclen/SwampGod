package swampgod;

public class Algae extends BadObject{
	
	private static final long serialVersionUID = 11052015L;

	public Algae() {
		pointValue = 15;
		healthValue = 10;
		speed = 2;
		stream = this.pickStream();
		isGood=false;
		type="Algae";
		
	}

}
