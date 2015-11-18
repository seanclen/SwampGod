package views;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.*;
import swampgod.*;
import swampgod.Main.GameState;

public class ViewDelegate extends JFrame {

	private static final long serialVersionUID = 20151111;
	private JPanel currentPanel;
	private Game game;

	public ViewDelegate(Game game) {
		this.game = game;
		
		// Load the initial view, automatically Title Screen at init
		if (game.getGameState() == GameState.TITLE_STATE) {
			currentPanel = new TitleView(game, this);
			currentPanel.setBackground(Color.GREEN);
		}
		add(currentPanel);
		
		//TODO should probably add dimension to game
		
		// Want to be able to click and/or press buttons
		currentPanel.requestFocus();
	}
	
	public void registerListeners(TitleMouseController titleMouseController,
			GameMouseController gameMouseController) {
		if (game.getGameState() == GameState.TITLE_STATE) {
			currentPanel.addMouseListener(titleMouseController);
		}
		else if (game.getGameState() == GameState.RUNNING_STATE) {
			currentPanel.addMouseListener(gameMouseController);
		}
	}
	
	public void updateGameState(GameState gameState) {
		System.out.println("updateGameState:current " + game.getGameState().toString());
		System.out.println("updateGameState:next" + gameState.toString());
		if (game.getGameState() == GameState.TITLE_STATE &&
				gameState == GameState.MENU_STATE) {
			System.out.println("ViewDelegate:updateGameState");
			game.updateGameState(gameState);
			removeAll();
			currentPanel = new MenuView(game, this);
			currentPanel.setBackground(Color.MAGENTA);
			add(currentPanel);
			currentPanel.requestFocus();
		}
	}


}
