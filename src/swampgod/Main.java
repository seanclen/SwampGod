package swampgod;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controllers.*;
import views.*;

public class Main {

	private static final long serialVersionUID = 11082015;
	private static Game game;

	public enum GameState {
		INITIALIZE,
		TITLE_STATE,
		MENU_STATE,
		PAUSE_STATE,
		RUNNING_STATE,
		UPGRADE_STATE,
		ENDGAME_STATE
	}

	public static void main(String[] args) {
		game = new Game(GameState.INITIALIZE);
		GameController controller = new GameController();
		ViewDelegate view = new ViewDelegate();
		game.addObserver(controller);
		game.addObserver(view);
		game.updateGameState(GameState.TITLE_STATE);
//		ViewRepaintController repaintController =
//				new ViewRepaintController(game, view);
//		TitleMouseController titleMouseController =
//				new TitleMouseController(game, view);
//		GameMouseController gameMouseController =
//				new GameMouseController(game, view);
//		
//		// a repaint timer so that the window will update every 25 ms
//		new Timer(25, repaintController).start();
//		
//		// register controllers as listeners
//		view.registerListeners(titleMouseController, gameMouseController);
//		
//		// Set the final dimensions and jink and start!
//		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		view.setSize(1000,700);
//		view.setVisible(true);
		
		
		
//		while (g.getGameState() == GameState.RUNNING_STATE) {
//			if(!(g.getGameState()==GameState.PAUSE_STATE)){
//				Thread.sleep(100);
//				g.tick();
//				viewController.updateGame(g);
//				viewController.paintIt();
//			}
//		}
	}

	public static void saveGame(String fileName, Object obj) throws IOException{
		FileOutputStream fileOut = new FileOutputStream(fileName);

		ObjectOutputStream objectOut = new ObjectOutputStream (fileOut);

		objectOut.writeObject (obj);
	}

	public static Game loadGame(String fileName) throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(fileName);

		ObjectInputStream objectIn = new ObjectInputStream (fileIn);

		Object obj = objectIn.readObject();

		if (obj instanceof Game)
		{
			return (Game)obj;
		} 
		else 
		{
			return null;
		}
	}
}
