package objects;

public class HazardWaste extends BadObject{

	private static final long serialVersionUID = 11082015;
	
	public HazardWaste() {
		pointValue = -13;
		healthValue = -40;
		speed = 20;
		isGood=false;
		type = "Hazard Waste";
		
	}
}
