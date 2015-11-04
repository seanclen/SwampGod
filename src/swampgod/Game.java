package swampgod;

import java.awt.Point;
import java.util.ArrayList;
import swampgod.Main.GameState;

/**
 * This class contains all of the game logic and methods
 *
 */
public class Game implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8247021508864248922L;
	
	//Halth range -100 to 100. 100 = max health. -100 = dead
	int health;
	int points;
	ArrayList<Plant> plants;
	int waveNumber;
	Stream[] streams = new Stream[3];
	Estuary estuary;
	GameState gameState;
	//TITLE_STATE, MENU_STATE, PAUSE_STATE, RUNNING_STATE, UPGRADE_STATE, ENDGAME_STATE
	
	/**
	 * constructs the objects
	 */
	public Game(){
		health = 0;
		points = 0;
		gameState = GameState.TITLE_STATE;
		waveNumber = 0;
		streams[0].createBadObjects(10);
		streams[0].createGoodObjects(10);
		streams[1].createBadObjects(10);
		streams[1].createGoodObjects(10);
		streams[2].createBadObjects(10);
		streams[2].createGoodObjects(10);
		
	}
	
	/**
	 * builds the menu
	 */
	public void initialize(){
		
	}
	
	/**
	 * starts ticker
	 */
	public void startGame(){
		
	}
	
	/**
	 * @return - game state
	 */
	public GameState getGameStatus(){
		return gameState;		
	}
	
	/**
	 * returns points
	 */
			
	public int getPoints(){
		return points;
	}
	
	/**
	 * Changes the score based on input
	 * @param value - how much the score will change
	 */
	public void updateScore(int value){
		points += value;
	}
	
	/**
	 * Value-how much the health should change
	 * Takes in an int, adds that to the health
	 */
	public void updateHealth(int value){
		health += value;
		if(health < -99){
			lose();
		}
		
	}
	
	/**
	 * 
	 * @param obj - take in a good or bad object
	 * removes it from the game
	 * changes the score
	 */
	public void removeObjects(Object obj){
		
	}
	
	/**
	 * if the player collects fish this modifies the score and health
	 */
	public void collectFish(){
	 updateHealth(-50);
	 updateScore(50);
	}
	
	/**
	 * controls the timing of the game
	 * updates & movements & draw happens on ticks
	 */
	public void Tick(){
		
	}
	
	/**
	 * @param pos - position
	 * @param t - type
	 * @return - an object
	 */
	public Object placePlant(Point pos, String t){
		return null;
	}
	
	
	/**
	 * @param t - type
	 * @return - an object
	 * creates an object of t type
	 */
	public EstuaryObject createObject(String t){
		return null;
		
	}
	
	/**
	 * 
	 * @param stream - which stream to add the obj to
	 * @param obj - takes in an object (use createObject to make one
	 * adds the input object to a stream
	 */
	public void addObject(int stream,EstuaryObject obj ){
		
	}
	
	/**
	 * selects the object at the current position of the mouse pointer
	 */
	public void pickUpObject(){
		
	}
	
	/**
	 * ends the current wave
	 * changes the game state
	 */
	public void endWave(){
		
	}
	
	/**
	 * @return - call the end of game functions, end screen and clean up
	 */
	public void lose(){

	}
	
	/**
	 * @return - End of game, player has won call win screen
	 */
	public void win(){

	}
	
	/**
	 * @return - is it the end of the game
	 */
	public boolean isEnd(){
		return false;
	}
	
	/**
	 * starts the next wave
	 */
	public void startWave(){
		
	}

}
