package objects;

/**
 * Fish extends GoodObject; Fish will flow down the streams
 */

public class Fish extends GoodObject{

	private static final long serialVersionUID = 11082015;
	
	int swimX;
	int swimY;
	int dir;


	public Fish(){
		isGood= true;
		healthValue= 5;
		type= "Fish";
		speed= 49;
		swimX=2;
		swimY=1;
		dir=0;
	}
	
	public void setDir(int x){
		if(x>1){
			dir=0;
		}
		else{
			dir=x;
		}
	}
	public int getDir(){
		return dir;
	}
	
	public void setSwimX(int x){
		swimX=x;
	}
	
	public void setSwimY(int y){
		swimY=y;
	}
	
	public int getSwimX(){
		return swimX;
	}
	
	public int getSwimY(){
		return swimY;
	}
}
