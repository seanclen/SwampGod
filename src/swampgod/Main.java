package swampgod;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import views.*;
import objects.*;
import swampgod.Main.GameState;

public class Main implements java.io.Serializable{

	private static final long serialVersionUID = 11082015;
	protected static ViewDelegate viewDelegate;
	public enum GameState {
		TITLE_STATE,
		MENU_STATE,
		PAUSE_STATE,
		RUNNING_STATE,
		UPGRADE_STATE,
		ENDGAME_STATE
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Swamp God");

//		viewDelegate = new ViewDelegate();
//		viewDelegate.showGameView();

		//		TitleView titleScreen = new TitleView();
		//		titleScreen.presentView();

		//		GameView GameWindow = new GameView();
		//		GameWindow.presentWindow();
		
		
		Game g = new Game();
		
		g.startGame();
		GameView gameView = new GameView(g);
		
		while (g.gameState == GameState.RUNNING_STATE) {
			if(g.gameState==GameState.PAUSE_STATE){

			}
			while(!(g.gameState==GameState.PAUSE_STATE)){
				gameView.paintIt();
				Thread.sleep(250);
				g.tick();
				
			}
		}
	}
	public void updateGameState() {

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
