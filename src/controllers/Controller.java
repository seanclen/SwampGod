package controllers;

import java.util.Observable;
import java.util.Observer;

import swampgod.Game;
import swampgod.Main.GameState;

public class Controller extends Observable implements Observer {
	private Game game;
	private ViewController viewController;
	
	public Controller(Game newGame) {
		game = newGame;
		game.addObserver(this);
		viewController = new ViewController();
		
		//This looks ridiculous but now they can talk to each other
		viewController.addObserver(this);
		this.addObserver(viewController);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ViewController) {
			System.out.println("GameController:update - ViewController");
			if (arg instanceof Game)
			{
				// Something happened, let's update the game and update the rest
				game = (Game) arg;
				updateGameUsers();
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
			System.out.println("GameController:update():Game");
			if (arg.equals(GameState.RUNNING_STATE)) {
				System.out.println("GameController:update():Game:RUNNING_STATE");
				viewController.handleStateChange((GameState) arg);
			}
			else if (arg instanceof GameState) {
				viewController.handleStateChange((GameState) arg);
			}
		}
	}
	
	/**
	 * Sends the game to GameControllers observers.
	 * In particular this should be used to send the game to the ViewController where it will
	 * then be sent to the GameViewController so it can draw and handle actions accordingly.
	 */
	private void updateGameUsers() {
		setChanged();
		notifyObservers(game);
		clearChanged();
	}
}
