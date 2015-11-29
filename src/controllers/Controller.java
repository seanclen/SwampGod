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
	
	public Controller(Game newGame) {
		game = newGame;
		game.addObserver(this);
		viewController = new ViewController();
		
		
		viewController.addObserver(this);
		viewController.setGame(game);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ViewController) {
			System.out.println("Controller:update - ViewController");
			if (arg instanceof Game)
			{
				game = (Game) arg;
			}
			else if (arg instanceof String) {
				String token = (String) arg;
				System.out.println("Controller:update():token("+token+")");
				
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
			if (arg.equals(GameState.RUNNING_STATE)) {
				System.out.println("Controller:update():Game:RUNNING_STATE");
				viewController.handleStateChange((GameState) arg);
			}
			else if (arg instanceof GameState) {
				viewController.handleStateChange((GameState) arg);
			}
		}
	}
}