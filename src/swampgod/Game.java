package swampgod;

import java.awt.Point;


/**
 * This class contains all of the game logic and methods
 *
 */
public class Game {
	int health;
	int points;
	Plant[] plants;
	int waveNumber;
	Stream[] Streams = new Stream[3];
	int gameState;
	//3 game states 1- running	2-inbetween waves	3- end of game
	
	/**
	 * constructs the objects
	 */
	public Game(){
		
	}
	/**
	 * @return - game status int
	 */
	public int getGameStatus(){
		return 0;		
	}
	
	/**
	 * returns points
	 */
			
	public int getPoints(){
		return points;
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
	 * Changes the score based on input
	 * @param value - how much the score will change
	 */
	public void updateScore(int value){
		
	}
	
	/**
	 * Value-how much the heakth should change
	 * Takes in an int, adds that to the health
	 */
	public void updateHealth(int value){
		
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
	 * @return - if the player wins or not
	 */
	public boolean win(){
		return false;
	}
	
	/**
	 * @return - if its the end or not
	 */
	public boolean lose(){
		return false;
	}
	
	/**
	 * @param t - type
	 * @return - an object
	 * creates an object of t type
	 */
	public Object createObject(String t){
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
	
	/**
	 * Gets status of the game
	 */
	public void getStatus(){
		
	}
}
