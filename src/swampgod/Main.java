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
		TUTORIAL_STATE,
		NEWGAME_STATE,
		RUNNING_STATE,
		NEXTWAVE_STATE,
		PAUSE_STATE,
		UPGRADE_STATE,
		ENDGAME_STATE
	}

	public static void main(String[] args) {
		game = new Game();
		Controller controller = new Controller(game);
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
