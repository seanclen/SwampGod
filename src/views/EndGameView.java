package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.EndGameViewController;

public class EndGameView extends JPanel{

	private JButton btnMenu;
	private EndGameViewController endGameViewController;
	public EndGameView() {
		setName("EndGameView");
		setBackground(Color.RED);
		
		btnMenu = new JButton("Main Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * At this point we have already added the view controller
				 * to this instance (refer back to ViewController).
				 * 
				 * Since the TitleViewController is technically a mouse listener,
				 * we must get the mouse listener from this instance and pass the 
				 * ActionEvent to the TitleViewController that a button has 
				 * been clicked.
				 */
				endGameViewController.buttonClicked(e);
			}
		});
	}
	
	public void initializeController() {
		endGameViewController = (EndGameViewController)this.getMouseListeners()[0];
	}
	
	@Override
	public void paintComponent(Graphics g) {
		System.out.println("EndGameView:paintComponent()");
		g.setColor(Color.RED);
		g.drawString("YOU FAIL", 200, 200);
	}
}
