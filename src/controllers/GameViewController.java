package controllers;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import javax.swing.Timer;

import objects.Algae;
import objects.BadObject;
import objects.Bush;
import objects.Crab;
import objects.EstuaryObject;
import objects.GoodObject;
import objects.HazardWaste;
import objects.Plant;
import objects.Tree;
import swampgod.Game;
import swampgod.Stream;
import swampgod.Main.GameState;
import views.GameView;

public class GameViewController extends Observable implements MouseInputListener {
	protected Game game;
	protected static Timer runTimer;
	protected static Timer upgradeTimer;
	protected Rectangle bounds;
	private int tutorialStage;
	private boolean doneStage3 = false;
	
	public GameViewController(){
		
		// The main timer that runs the game.
		runTimer = new Timer(40, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent action) {
				if (game.getGameState().equals(GameState.RUNNING_STATE)){
					game.tick();
					setChanged();
					notifyObservers(game);
					clearChanged();
				} else if (game.getGameState().equals(GameState.UPGRADE_STATE)) {
					setChanged();
					notifyObservers(game);
					clearChanged();
					runTimer.stop();
					upgradeTimer.restart();
				} else if (game.getGameState().equals(GameState.ENDGAME_STATE)) {
					setChanged();
					notifyObservers(game);
					clearChanged();
					runTimer.stop();
				} else if (game.getGameState().equals(GameState.TUTORIAL_STATE)) {
					System.out.println("GameViewController.runTimer:TUTORIAL_STATE");
					runTutorial();
					setChanged();
					notifyObservers(game);
					clearChanged();
				} else {
					System.out.println("GameViewController:not running state");
					setChanged();
					notifyObservers(game);
					clearChanged();
				}
			}
		});
		upgradeTimer = new Timer(1000, new ActionListener() {
			private int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (count == 10) {
					count = 0;
					//game.setGameState(GameState.RUNNING_STATE);
					startNextWave();
					upgradeTimer.stop();
				} else {
					System.out.println("upgradeTimer.count: " + count);
					count++;
				}
			}
		});
	}
	
	public void updateGameSize(Rectangle newBounds) {
		bounds = newBounds;
		System.out.println("GameViewController.updateGameSize("+bounds+ ")");
	}
	
	public void startGame() {
		if (game.getGameState().equals(GameState.NEWGAME_STATE)){
			game.setGameState(GameState.RUNNING_STATE);
			game.updateWindowSize(bounds);
			runTimer.start();
		}
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return this.game;
	}

	/**
	 * Handles the mouseClicked mouse event. In particular handles:
	 * 		- The placement of currently selected plant
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("GameViewController:mouseClicked("+e.getPoint()+")");
		if(game.getChosenPlant()!=null && game.getPoints()<game.getChosenPlant().getPointsPerPlants()){
			game.setChosenPlant(null);
			setChanged();
			notifyObservers(game);
			clearChanged();
		}
		if(game.getChosenPlant()!=null && game.getChosenPlant().canPlace()
				&& game.getPoints()>=game.getChosenPlant().getPointsPerPlants()){
			Point p = e.getPoint();
			p.translate(game.getChosenPlant().getSize().width/-2,
					game.getChosenPlant().getSize().height/-2);
			game.getChosenPlant().setPosition(p);
			game.placePlant(game.getChosenPlant());
			game.setPoints(game.getChosenPlant().getPointValue());
			game.setChosenPlant(null);
			setChanged();
			notifyObservers(game);
			clearChanged();
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (game.getGameState().equals(GameState.RUNNING_STATE) ||
				game.getGameState().equals(GameState.TUTORIAL_STATE)) {
			int x = e.getX();
			int y = e.getY();
			int s=-1;
			for(int i=0;i<3;i++){
				if(game.getStreams()[i].getBounds().contains(x,y)){
					s=i;
				}
			}
			boolean found =false;
			EstuaryObject obj=null;
			for(GoodObject go : game.getStreams()[s].getGoodObjects()){
				if(!found){
					if(go.getBounds().contains(x,y)){
						obj = go;
						found=true;
					}
				}
			}
			if(!found){
				for(BadObject bo : game.getStreams()[s].getBadObjects()){
					if(!found){
						if(bo.getBounds().contains(x,y)){
							obj = bo;
							found=true;
						}
					}
				}
			}
			if (obj != null) {
				game.setClickedObject(obj);
				game.setPreviousPosition(obj.getPos());
				game.removeObjToTrash(obj);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(game.getClickedObject()!= null){
			int x=e.getX();
			int y=e.getY();
			if(game.getTrashCan().getBounds().contains(x,y)){
				if(!game.getClickedObject().isGood()){
					//game.updateScore(game.getClickedObject().getPointValue());
				}
				game.setClickedObject(null);
			}
			else{
				int stream = game.getClickedObject().getStream();
				game.getClickedObject().setMoving();
				if(game.getClickedObject().isGood()){
					game.getClickedObject().setPosition(game.getPreviousPosition());
					game.getStreams()[stream].getGoodObjects().add((GoodObject) game.getClickedObject());
					Collections.sort(game.getStreams()[stream].getGoodObjects());
					game.setClickedObject(null);
				}
				else{
					game.getClickedObject().setPosition(game.getPreviousPosition());
					game.getStreams()[game.getClickedObject().getStream()].getBadObjects().add((BadObject) game.getClickedObject());
					Collections.sort(game.getStreams()[stream].getBadObjects());
					game.setClickedObject(null);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
//		Point p = new Point(e.getX(), e.getY());
//		if(game.getClickedObject()!=null){
//			game.getClickedObject().setPosition(p);
//		}
		EstuaryObject obj = game.getClickedObject();
		if (obj != null) {
			obj.setPosition(e.getPoint());
			game.setClickedObject(obj);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (game.getChosenPlant() != null) {
			Point p = e.getPoint();
			p.translate(game.getChosenPlant().getSize().width/-2,
					game.getChosenPlant().getSize().height/-2);
			game.getChosenPlant().setPosition(p);
			for (Stream s : game.getStreams()) {
				if (s.intersectsStream(game.getChosenPlant().getBounds())){
					game.getChosenPlant().setCanPlace(false);
					break;
				}
				game.getChosenPlant().setCanPlace(true);
			}
			setChanged();
			notifyObservers(game);
			clearChanged();
		}
	}
	
	private void startNextWave(){
		game.startNextWave();
		setChanged();
		notifyObservers(game);
		clearChanged();
		game.setGameState(GameState.RUNNING_STATE);
		System.out.println("      waveNumber: " + game.getWaveNumber() + ")");
		runTimer.start();
	}

	public void buttonClicked(ActionEvent e) {
		System.out.println("GameViewController:buttonClicked()");
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			if (btn.getText().equals("Start")) {
				System.out.println("GameViewController:buttonClicked().Start");
				/**
				 * The start button has been clicked, so we must tell the Controller that
				 * the user is ready to play the game, thus calling the runGame() function.
				 * We accomplish by simply passing a string 'token' "RunGame" to the ViewController
				 * which will then pass the token to the Controller.
				 */
				if (game.getGameState().equals(GameState.PAUSE_STATE)){
					game.setGameState(GameState.RUNNING_STATE);
					runTimer.start();
				}
				else if (game.getGameState().equals(GameState.UPGRADE_STATE)){
					System.out.println("GameViewController:buttonClicked(Start):startNextWave()");
					System.out.println("      waveNumber: " + game.getWaveNumber() + ")");
					startNextWave();
				} else if (game.getGameState().equals(GameState.RUNNING_STATE)){
					runTimer.start();
				} else if (game.getGameState().equals(GameState.NEWGAME_STATE)){
					game.setGameState(GameState.RUNNING_STATE);
					game.updateWindowSize(bounds);
					runTimer.start();
				}
			}
			else if (btn.getText().equals("Pause")) {
				System.out.println("GameViewController:buttonClicked().Pause");
				game.setGameState(GameState.PAUSE_STATE);
				runTimer.stop();
			}
			else if (btn.getText().equals("Upgrade")) {
				game.setGameState(GameState.UPGRADE_STATE);
			}
			else if(btn.getText().equals("Collect Fish")){
				System.out.println("COLLECT FISH");
				game.collectFish();
				if (tutorialStage == 3) {
					doneStage3 = true;
				}
			}
			else if (btn.getText().equals("Win")) {
				game.setUserWon(true);
				game.setGameState(GameState.ENDGAME_STATE);
			}
			else if (btn.getText().equals("Lose")) {
				game.setUserWon(false);
				game.setGameState(GameState.ENDGAME_STATE);
			}
			else if(btn.getName().equals("AddBush")){
				Bush b = new Bush(null);
				game.setChosenPlant(b);
			}
			else if(btn.getName().equals("AddTree")){
				Tree t = new Tree(null);
				game.setChosenPlant(t);
			}
			
		}
	}
	
	public void tutorialButtonClicked(ActionEvent e) {
		System.out.println("ButtonClicked : Skip");
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			if (btn.getText().equals("Skip")) {
				runTimer.stop();
				game.updateGameState(GameState.NEWGAME_STATE);
			}
		}
	}
	boolean stageInitialized = false;
	private void runTutorial() {
		tutorialStage = game.getTutorialStage();
		switch (tutorialStage) {
		case 1:
			stageOne();
			break;
		case 2:
			stageTwo();
			break;
		case 3:
			stageThree();
			break;
		case 4:
			runTimer.stop();
			break;
		default:
			break;
		}
	}
	
	private void stageOne() {
		if (stageInitialized) {
			if (game.getStreams()[1].getBadObjects().isEmpty() && 
					game.getClickedObject() == null){
				game.nextTutorialStage();
				stageInitialized = false;
			}
		} else {
			stageInitialized = true;
			game.getStreams()[1].createBadObjectAt(new HazardWaste(), .45f);
			game.getStreams()[1].createBadObjectAt(new Algae(), .50f);
			game.getStreams()[1].createBadObjectAt(new Crab(), .55f);
		}
	}
	
	private void stageTwo() {
		if (stageInitialized) {
			System.out.println("Number of Plants: " + game.getPlants().size());
			if (game.getPlants().size() > 1){
				game.nextTutorialStage();
				stageInitialized = false;
			}
		} else {
			stageInitialized = true;
			game.setPoints(100);
		}
	}
	
	private void stageThree() {
		if (stageInitialized) {
			System.out.println("tick");
			game.tick();
			if (doneStage3){
				game.nextTutorialStage();
				stageInitialized = false;
			}
		} else {
			for (int i = 0; i < 40; i++) {
                int streamId = EstuaryObject.pickStream();
                game.getStreams()[streamId].createGoodObjects(1);
            }
			stageInitialized = true;
		}
	}
	
	public void startTutorial() {
		System.out.println("GameViewController.startTutorial:bounds :" + bounds);
		game.updateWindowSize(bounds);
		game.setIsTutorial(true);
		runTimer.start();
	}
	
	public void finishTutorial() {
		if (tutorialStage == 4) {
			game.updateGameState(GameState.NEWGAME_STATE);
			game.setTutorialStage(0);
		}
	}

}
