package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.event.MouseInputListener;

import swampgod.Game;
import swampgod.Main.GameState;

public class EndGameViewController  extends Observable implements MouseInputListener{

	public void setGame(Game game) {
		setChanged();
		notifyObservers(game);
		clearChanged();
	}
	
	public void buttonClicked(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			/**
			 * The play button has clicked, so let the observer know
			 * that a change has been made.
			 * In this case, the play button has been hit so it is time
			 * to change the game state to MENU_STATE and load the MenuView
			 */
			if (btn.getText().equals("Main Menu")) {
				setChanged();
				//TODO this is where we can control whether to go straight to running
				notifyObservers(GameState.MENU_STATE);
				clearChanged();
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
