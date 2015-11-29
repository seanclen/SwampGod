package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.event.MouseInputListener;

import swampgod.Main.GameState;

public class TitleViewController extends Observable implements MouseInputListener {
	
	public TitleViewController(){
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouseClicked @ " + e.getPoint());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void buttonClicked(ActionEvent e) {
		System.out.println("TitleViewController:buttonClicked");
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			/**
			 * The play button has clicked, so let the observer know
			 * that a change has been made.
			 * In this case, the play button has been hit so it is time
			 * to change the game state to MENU_STATE and load the MenuView
			 */
			if (btn.getText().equals("Play")) {
				setChanged();
				notifyObservers(GameState.MENU_STATE);
				clearChanged();
			}
			
		}
	}

	
}
