package controllers;

import java.util.Observable;
import java.util.Observer;

import swampgod.Game;

public class GameController implements Observer {
	private Game game;
	
	public GameController() {
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof Game)
		{
			game = (Game)arg;
			System.out.println("Got the update in GameController");
		}
	}
}
