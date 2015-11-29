package controllers;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import swampgod.Game;
import swampgod.Main.GameState;

public class Controller implements Observer {
	public static Game testGame;
	private Game game;
	private ViewController viewController;
	private static Timer runTimer = new Timer();
	private TimerTask runGame;
	
	public Controller(Game newGame) {
		game = newGame;
		game.addObserver(this);
		viewController = new ViewController();
		
		//This looks ridiculous but now they can talk to each other
		viewController.addObserver(this);
		
		runGame = new TimerTask() {
			@Override
			public void run() {
				if (game.getGameState().equals(GameState.RUNNING_STATE)){
					System.out.println("Running runGame TimerTask");
					game.tick();
					viewController.updateGameFromTick(game);
				}
			}
		};
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ViewController) {
			System.out.println("Controller:update - ViewController");
			if (arg instanceof Game)
			{
				/**
				 * Control any updates that come from the Game.
				 * Usually this will handle tick() updates. We will set this game
				 * equal to the received argument just to be safe
				 */
				game = (Game) arg;
			}
			else if (arg instanceof String) {
				String token = (String) arg;
				System.out.println("Controller:update():token("+token+")");
				if (token == "RunGame") {
					if (game.getGameState().equals(GameState.RUNNING_STATE)){
						runTimer.schedule(runGame, 0, 100);
					}
				}
				else if (token == "PauseGame")
				{
					game.setGameState(GameState.PAUSE_STATE);
				}
			}
			else if (arg.equals(GameState.MENU_STATE))
			{
				System.out.println("It's time to go to the menu");
				game.updateGameState((GameState) arg);
			}
			else if (arg.equals(GameState.NEWGAME_STATE))
			{
				System.out.println("It's time to go to the game (new game state)");
				game.updateGameState((GameState) arg);
			}
			else if (arg.equals(GameState.RUNNING_STATE))
			{
				System.out.println("It's time to go to the game (running state)");
				game.updateGameState((GameState) arg);
			}
		}
		else if (o instanceof Game)
		{
			System.out.println("Controller:update():Game");
			if (arg.equals(GameState.RUNNING_STATE)) {
				System.out.println("Controller:update():Game:RUNNING_STATE");
				viewController.handleStateChange((GameState) arg);
			}
			else if (arg instanceof GameState) {
				viewController.handleStateChange((GameState) arg);
			}
		}
	}
	
//	private void runGame() {
//		while (game.getGameState().equals(GameState.RUNNING_STATE)) {
//			System.out.println("GameController:runGame() -- tick");
//			game.tick();
////			setChanged();
////			notifyObservers(game);
////			clearChanged();
//			
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// If for whatever we can't put the thread to bed, "restart" the game
//				game.setGameState(GameState.TITLE_STATE);
//				//viewController.handleStateChange(GameState.TITLE_STATE);
//				e.printStackTrace();
//			}
//		}
//	}
}
