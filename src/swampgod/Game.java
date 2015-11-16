package swampgod;

import java.awt.Point;
import java.util.ArrayList;

import objects.BadObject;
import objects.Bush;

import objects.Fish;
import objects.EstuaryObject;
import objects.GoodObject;
import objects.Plant;
import objects.Tree;
import swampgod.Main.GameState;

/**
 * This class contains all of the game logic and methods
 *
 */
public class Game implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 11082015;
	int tickCount = 0;
	//Health range 0 to 100. 100 = max health. -0 = dead
	int health;
	int points;
	ArrayList<Plant> plants;
	int waveNumber;
	Stream[] streams;
	Estuary estuary;
	GameState gameState;
	//TITLE_STATE, MENU_STATE, PAUSE_STATE, RUNNING_STATE, UPGRADE_STATE, ENDGAME_STATE
	int fishCount;
	TrashCan trashcan = new TrashCan();
	EstuaryObject clickedObject;
	Point previousPosition;

	/**
	 * constructs the objects
	 */
	public Game(){
		gameState = GameState.MENU_STATE;
		switch (gameState) {
		case MENU_STATE: break;
		default: initialize();
		}
		initialize();

	}

	/**
	 * builds the menu
	 */
	public void initialize(){
		health = 50;
		points = 0;
		estuary = new Estuary();
		streams  = new Stream[3];
		waveNumber = 0;
		gameState = GameState.TITLE_STATE;
		fishCount=0;
		plants = new ArrayList<Plant>();
		clickedObject = null;
		previousPosition = null;
	}
	
	public TrashCan getTrashCan(){
		return trashcan;
	}

	/**
	 * starts ticker and starts first wave
	 */
	public void startGame(){
		gameState = GameState.RUNNING_STATE;
		waveNumber++;

		//Instantiate and initialize streams
		for (int i = 0; i < streams.length; i++) {
			streams[i] = new Stream(i);
		}

		//Good Objects
		Level level= new Level();
		int goodObjects = level.getTotalGoodObjects(waveNumber);
		for (int i = 0; i < goodObjects; i++) {
			int streamId = EstuaryObject.pickStream();
			streams[streamId].createBadObjects(1);
		}

		//Bad Objects
		int badObjects= level.getTotalBadObjects(waveNumber);
		for (int i = 0; i < badObjects; i++){
			int streamId = EstuaryObject.pickStream();
			streams[streamId].createGoodObjects(1);
		}

		
	}
	public EstuaryObject getClickedObject(){
		return clickedObject;
	}
	
	public Point getPreviousPosition(){
		return previousPosition;
	}
	
	public int getWaveNumber(){
		return waveNumber;
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

	public Stream[] getStreams() {
		return streams;
	}

	public Estuary getEstuary() {
		return estuary;
	}

	/**
	 * Value-how much the health should change
	 * Takes in an int, adds that to the health
	 */
	public void updateHealth(int value){
		health += value;
		if(health > 100){
			health = 100;
		}
		if(health < 1){
			lose();
		}

	}

	/**
	 * 
	 * @param obj - take in a good or bad object
	 * removes it from the game
	 * changes the score
	 */
	public boolean removeObjects(EstuaryObject obj){

		boolean removed;
		//remove from streams
		if(obj.isGood()){		
			updateScore(obj.getPointValue());
			removed = streams[obj.getStream()].goodObjects.remove(obj);
		}
		else{
			updateScore(obj.getPointValue());
			removed = streams[obj.getStream()].badObjects.remove(obj);
		}

		return removed;
	}

	/**
	 * if the player collects fish this modifies the score and health
	 */
	public void collectFish(){
		updateHealth(-25);
		Fish f = new Fish();
		updateScore(f.getPointValue()*fishCount);
		fishCount=0;
		//NEEDS TO REMOVE FISH FROM DISPLAY SOMEHOW -- MAYBE REMOVE FROM THE ESTUARY LIST?
	}

	/**
	 * controls the timing of the game
	 * updates & movements & draw happens on ticks
	 */

	public void tick(){
		//CALL MOVE ON ALL OBJECTS IN ALL STREAMS
		//iterate through all streams

		for(int i = 0; i<3; i++){

			EstuaryObject temp = null;
			//move all objects in a streams good objects
			for(GoodObject go:  streams[i].goodObjects){
				if(go.getMoving()){
					if(go.move(waveNumber)){
						temp=go;
					}
					go.setPosition(streams[i].CalculateBezierPoint(go.getStreamCompletion()));
				}
				else{
					if (tickCount%Level.goodObjectReleaseFrequency[waveNumber]==0){
						go.setMoving();
					}
					break;
				}
			}
			//move all objects in a streams bad objects
			for(BadObject bo : streams[i].badObjects){
				if(bo.getMoving()){
					if(bo.move(waveNumber)){
						temp= bo;
					}
					bo.setPosition(streams[i].CalculateBezierPoint(bo.getStreamCompletion()));	
				}
				else{
					if (tickCount%Level.badObjectReleaseFrequency[waveNumber]==0){
						bo.setMoving();
					}
					break;
				}
			}
			if(temp != null){
				updateHealth(temp.getHealthValue());
				removeObjects(temp);
			}
		}
		// call eat function for plants
		for(Plant pl : plants){
			double x = pl.getPos().getX();
			double y= pl.getPos().getY();

			for(Stream st : streams){
				if(st.getBounds().contains(x,y) || st.getBounds().contains(x+pl.getRadius(),y) || 
						st.getBounds().contains(x-pl.getRadius(), y)){
					removeObjects(pl.eat(st.badObjects));
				}
			}

		}
		System.out.println("score: " + points + " health: " + health);
		//CHECK IF ANY OBJECTS ARE NOT INSTREAMS, I.E. IN ESTUARY -- this probably happens in the move function
		// this also happens in the move thing
		
		//RELEASE NEW OBJECTS FROM PURGATORY
		//this happens in the move thing
		
		//CHECK IF GAME IS OGRE (HEALTH TOO LOW, ALL WAVES DONE)
	//isEnd();
		//CHECK IF WAVE OVER (IF SO CHANGE TO UPGRADE SCREEN OR GAME OGRE -- MORE LIKELY CALL END WAVE AND IT DOES THAT)
		if(isEndWave()){
			endWave();
		}
		
		//WAIT SOME AMOUNT OF TIME BEFORE NEXT ( SLEEP )

		//AM I MISSING ANYTHING THAT NEED TO HAPPEN HERE?
		tickCount++;
	}
	/*
	 * reutrns if all streams are empty
	 */
	public boolean isEndWave(){
		boolean empty=true;
		for(int i = 0; i<3; i++){
			if((!streams[i].badObjects.isEmpty()||!streams[i].goodObjects.isEmpty())){
				empty = false;
			}
		}
		return empty;
	}
	/**
	 * @param pos - position
	 * @param t - type
	 * @return - an object
	 */
	public Object placePlant(Point pos, String t){
		Plant pl;
		if(t == "Bush"){
			pl = new Bush(pos);
		}
		else {
			pl = new Tree(pos);
		}
		pl.setPosition(pos);
		plants.add(pl);;
		return pl;
	}
	
	public int getHealth(){
		return health;
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
		if(waveNumber == 3){
			gameState= GameState.ENDGAME_STATE;
		}
		else{
			gameState = GameState.UPGRADE_STATE;
		}
	}

	/**
	 * @return - call the end of game functions, end screen and clean up
	 */
	public void lose(){
		gameState = GameState.ENDGAME_STATE;
	}

	/**
	 * @return - End of game, player has won call win screen
	 */
	public void win(){
		gameState = GameState.ENDGAME_STATE;
	}

	/**
	 * @return - is it the end of the game
	 */
	public boolean isEnd(){
		if(health <=0){
			lose();
			return true;
		}
		if(waveNumber == 3 && streams[1].getBadObjects().isEmpty() && streams[2].getBadObjects().size()==0 &&
				streams[3].getBadObjects().isEmpty() && streams[1].getGoodObjects().isEmpty() && 
				streams[2].getGoodObjects().isEmpty() && streams[3].getGoodObjects().isEmpty()){
			win();
			return true;
		}
		return false;
	}

}
