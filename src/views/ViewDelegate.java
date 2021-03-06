package views;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import swampgod.Game;

public class ViewDelegate extends JPanel {
	
	private static final long serialVersionUID = 20151111;
	JFrame currentView;
	
	public ViewDelegate() {
		
	}
	
	public void showTitleView(){
		TitleView title = new TitleView(this);
		add(title);
		
	}
	
	public void showGameView(Game newGame) {
		GameView gameView = new GameView(newGame);
		add(gameView);
	}
	
}
