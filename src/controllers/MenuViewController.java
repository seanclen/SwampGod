package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.event.MouseInputListener;

import swampgod.Main.GameState;

public class MenuViewController extends Observable implements MouseInputListener {
	
	public MenuViewController(){
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void buttonClicked(ActionEvent e) {
		System.out.println("MenuViewController:buttonClicked");
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			/**
			 * The play button has clicked, so let the observer know
			 * that a change has been made.
			 * In this case, the play button has been hit so it is time
			 * to change the game state to MENU_STATE and load the MenuView
			 */
			if (btn.getText().equals("New Game")) {
				setChanged();
				//TODO this is where we can control whether to go straight to running
				notifyObservers(GameState.NEWGAME_STATE);
				clearChanged();
			}
			
		}
	}

}
