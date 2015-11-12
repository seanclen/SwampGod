package swampgod;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import views.*;
import objects.*;

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
	
	public static void main(String[] args) {
		System.out.println("Swamp God");
		
		viewDelegate = new ViewDelegate();
		viewDelegate.showTitleView();
		
//		TitleView titleScreen = new TitleView();
//		titleScreen.presentView();
		
//		GameView GameWindow = new GameView();
//		GameWindow.presentWindow();

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
