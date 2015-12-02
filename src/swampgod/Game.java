package swampgod;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;
import java.util.Observable;

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
public class Game extends Observable implements java.io.Serializable{
	private static final long serialVersionUID = 11082015;
	int tickCount = 0;
	//Health range 0 to 100. 100 = max health. -0 = dead
	int health;
	int points;
	private ArrayList<Plant> plants;
	private static Level level= new Level();
	int waveNumber;
	Stream[] streams;
	Estuary estuary;
	private GameState gameState;
	int fishCount;
	TrashCan trashcan = new TrashCan();
	private EstuaryObject clickedObject;
	private Point previousPosition;
	Plant chosenPlant;
	Rectangle bounds;
	Dimension objectSize;
	Dimension plantSize;
	private boolean userWins;

	/**
	 * constructs the objects
	 */
	public Game(){
		this.gameState = GameState.INITIALIZE;
	}

	public void updateGameState(GameState gameState) {
		System.out.println("\nGame:updateGameState() -- begin");
		// Make sure value has changed
		if(!this.gameState.equals(gameState)) {
			this.gameState = gameState;
			System.out.println("Game:updateGameState() -- " + this.gameState);
			if (gameState.equals(GameState.NEWGAME_STATE))
			{
				initialize();
				this.gameState = GameState.RUNNING_STATE;
			}
			setChanged();
			notifyObservers(this.gameState);
			clearChanged();
		}
		System.out.println("Game:updateGameState() -- end");
	}

	public void initialize(){
		System.out.println("\nGame:initialize() -- begin");
		health = 50;
		points = 0;
		estuary = new Estuary();
		streams  = new Stream[3];
		waveNumber = 0;
		fishCount=0;
		objectSize = new Dimension(40,40);
		plantSize = new Dimension(40,40);
		setPlants(new ArrayList<Plant>());
		setClickedObject(null);
		setPreviousPosition(null);
		setChosenPlant(null);	
		for (int i = 0; i < streams.length; i++) {
			streams[i] = new Stream(i);
			streams[i].setObjectSize(objectSize);
		}

		//Good Objects
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

		System.out.println("Game:initialize() -- end");
	}

	/**
	 * Complete the upgrade stage and start the next wave.
	 * The current GameState must be UPGRADE_STATE otherwise this method will
	 * not produce any results. When this method is called the GameState will 
	 * change to NEXTGAME_STATE and initialize the next wave.
	 */
	public void startNextWave() {
		//Check to make sure that the state is still in upgrade
		if (gameState.equals(GameState.UPGRADE_STATE))
		{
			System.out.println("Game.startNextWave()");
			waveNumber++;
			//Good Objects
			int goodObjects = level.getTotalGoodObjects(waveNumber);
			for (int i = 0; i < streams.length; i++) {
				streams[i].clear();
			}
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
			setGameState(GameState.NEXTWAVE_STATE);
			System.out.println("Game.startNextWave() -- Completed");
		}
	}

	public void updateWindowSize(Rectangle bounds) {
		System.out.println("updateWindow");
		this.bounds = bounds;
		double estuaryBorder = (bounds.height * .7);
		int streamWidth = (bounds.width / 3);
		estuary.setBounds(new Rectangle(
				0, 
				(int) estuaryBorder,
				bounds.width,
				(int) (bounds.height - estuaryBorder)
				));
		objectSize.setSize(bounds.width*.03, bounds.width*.03);
		plantSize.setSize(bounds.width*.04, bounds.width*.04);

		// Update streams
		for (Stream s : streams) {
			s.setObjectSize(objectSize);
			s.setBounds(new Rectangle(
					s.getId()*streamWidth,
					0,
					streamWidth,
					(int) estuaryBorder));
			CubicCurve2D curve = s.getStreamCurve();
			s.setStreamCurve(new CubicCurve2D.Double(
					s.getBounds().x + ((curve.getX1()/100)*s.getBounds().width), (curve.getY1()/100)*s.getBounds().height, 
					s.getBounds().x + ((curve.getCtrlX1()/100)*s.getBounds().width), (curve.getCtrlY1()/100)*s.getBounds().height,
					s.getBounds().x + ((curve.getCtrlX2()/100)*s.getBounds().width), (curve.getCtrlY2()/100)*s.getBounds().height,
					s.getBounds().x + ((curve.getX2()/100)*s.getBounds().width), (curve.getY2()/100)*s.getBounds().height));
		}
		Point foo = getPointFromPercentage(65, 15);
		trashcan.setBounds(foo.x, foo.y, 100, 100);
	}

	/**
	 * Get the relative point based on the percentage of the frame from
	 *     the top left to the bottom right. Use this to find the Cartesian
	 *     points for layout purposes to avoid resizing issues.
	 *     
	 * The scale is 0.0 (top left) to 100.0 (bottom right)
	 *     
	 * @param x : the percentage from the left to right.
	 * @param y : the percentage from top to bottom
	 * @return Point containing x and y.
	 */
	private Point getPointFromPercentage(double x, double y) {
		if (x <= 100 && x>=0 && y<=100 && y>=0) {
			return new Point((int) (bounds.getWidth() * (x / 100)), (int) (bounds.getHeight() * (y / 100)));
		} else {
			return null;
		}
	}

	public TrashCan getTrashCan(){
		return trashcan;
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

	public void setChosenPlant(Plant pl){
		chosenPlant=pl;

		if (pl != null) {
			pl.getBounds().setSize(plantSize);
		}

		System.out.println(chosenPlant);
	}

	public Plant getChosenPlant(){
		return chosenPlant;
	}

	/**
	 * @return - game state
	 */
	public GameState getGameStatus(){
		return getGameState();		
	}

	/**
	 * returns points
	 */

	public int getPoints(){
		return points;
	}

	public void setPoints(int po){
		points = points+po;
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
		// TODO Three cases when the score should be updated
		boolean removed = false;
		//remove from streams
		if(obj==null){

			if(obj.isGood()){	
				if(obj.getType()== "Fish"){
					estuary.addFish();
					fishCount++;
				}
				updateScore(obj.getPointValue());
				removed = streams[obj.getStream()].goodObjects.remove(obj);
			}
			else{
				if(obj.isGood()){	
					if(obj.getType()== "Fish"){
						fishCount++;
					}
					updateScore(obj.getPointValue());
					removed = streams[obj.getStream()].goodObjects.remove(obj);
				}
				else{
					updateScore(obj.getPointValue());
					removed = streams[obj.getStream()].badObjects.remove(obj);
				}
			}
		}
		return removed;
	}

	public boolean removeObjToTrash(EstuaryObject obj){
		boolean removed;
		if(obj.isGood()){
			removed= streams[obj.getStream()].goodObjects.remove(obj);
		}
		else{
			removed = streams[obj.getStream()].badObjects.remove(obj);
		}
		return removed;
	}

	/**
	 * if the player collects fish this modifies the score and health
	 */
	public void collectFish(){
		if(fishCount>5){
			updateHealth(-15);
			Fish f = new Fish();
			updateScore(f.getPointValue()*fishCount);
			fishCount=Math.abs(fishCount/2);
		}
		else if(fishCount>0){
			updateHealth(-35);
			Fish f = new Fish();
			updateScore(f.getPointValue()*fishCount);
			fishCount=0;
		}
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
					Point p = streams[i].CalculateBezierPoint(go.getStreamCompletion());
					p.translate(go.getSize().width/-2, go.getSize().height/-2);
					go.setPosition(p);
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
					Point p = streams[i].CalculateBezierPoint(bo.getStreamCompletion());
					p.translate(bo.getSize().width/-2, bo.getSize().height/-2);
					bo.setPosition(p);
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
		if(tickCount%10==0){
			for(Plant pl : getPlants()){
				double x = pl.getPos().getX();
				double y= pl.getPos().getY();
				double radiusMultiplied = pl.getRadius()*(pl.getSize().width/5);
				System.out.println("rad multy is " +radiusMultiplied);

				for(Stream st : streams){
					if(st.getStreamCurve().intersects(pl.getRadiusShape().getBounds2D())){
						System.out.println("this plant is in stream " + st.getId() +pl);
						System.out.println(st.getBounds());
						if(pl.eat(st.badObjects)!=null){
							removeObjects(pl.checkRadius(st.badObjects));
						}

					}
				}

			}
		}
		isEnd();
		if(isEndWave()){
			endWave();
		}
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
	public Object placePlant(Plant pl){
		getPlants().add(pl);
		points= points-pl.getPointsPerPlants();
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
			setGameState(GameState.ENDGAME_STATE);
		}
		else{
			setGameState(GameState.UPGRADE_STATE);
			System.out.println("UPGRADE_STATE");
		}
	}

	/**
	 * @return - call the end of game functions, end screen and clean up
	 */
	public void lose(){
		setGameState(GameState.ENDGAME_STATE);
		userWins = false;
	}

	/**
	 * @return - End of game, player has won call win screen
	 */
	public void win(){
		setGameState(GameState.ENDGAME_STATE);
		userWins = true;
	}

	public void setUserWon(boolean b) {
		userWins = b;
	}

	public boolean userWon() {
		return userWins;
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

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public void setClickedObject(EstuaryObject clickedObject) {
		this.clickedObject = clickedObject;
	}

	public void setPreviousPosition(Point previousPosition) {
		this.previousPosition = previousPosition;
	}

	public ArrayList<Plant> getPlants() {
		return plants;
	}

	public void setPlants(ArrayList<Plant> plants) {
		this.plants = plants;
	}

	public int getFishCount() {
		return fishCount;
	}

}
