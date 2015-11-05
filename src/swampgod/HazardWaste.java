package swampgod;

public class HazardWaste extends BadObject{

	private static final long serialVersionUID = 11052015L;
	
	public HazardWaste() {
		pointValue = 13;
		healthValue = 16;
		speed = 7;
		stream = pickStream();
		isGood=false;
		
	}
}
