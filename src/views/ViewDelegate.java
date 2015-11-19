package views;

import java.awt.Color;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.*;
import swampgod.*;
import swampgod.Main.GameState;

public class ViewDelegate extends JFrame implements Observer{

	private static final long serialVersionUID = 20151111;
	private JPanel currentPanel;
	private Game game;

	public ViewDelegate() {
		
		// Load the initial view, automatically Title Screen at init
		TitleView titleView = new TitleView();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o instanceof Game)
		{
			if (arg instanceof Game) {
				game = (Game)arg;
				System.out.println("Got the update in ViewDelegate : Changed GameState");
			}
			if (arg instanceof ArrayList) {
				if (((ArrayList<?>) arg).get(0) instanceof Stream) {
					//Then this is a stream!
				}
			}
			
		}
	}
	
	public void updateView() {
		
	}


}
