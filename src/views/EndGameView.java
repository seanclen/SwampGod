package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.EndGameViewController;
import swampgod.Game;

public class EndGameView extends JPanel implements Observer{

	private JButton btnMenu;
	private EndGameViewController endGameViewController;
	private Game game;
	private JPanel picPanel = new JPanel();
	private static Image imgWinScreen;
	private static Image imgLoseScreen;
	
	public EndGameView() {
		try
		{
			imgWinScreen= ImageIO.read(new File("pics/WinScreen_Temp.png"));
			imgLoseScreen = ImageIO.read(new File("pics/LoseScreen_Temp.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		setName("EndGameView");
		setBackground(new Color (36,228,149));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		picPanel.setSize(400, 200);
		picPanel.setPreferredSize(new Dimension(400,200));
		picPanel.setBackground(Color.RED);
		picPanel.setVisible(true);
		
		game = null;
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
		
		//add(picPanel);
		add(btnMenu);
		
	}
	
	public void initializeController() {
		endGameViewController = (EndGameViewController)this.getMouseListeners()[0];
	}
	
	@Override
	public void paintComponent(Graphics g) {
		System.out.println("EndGameView:paintComponent()");
		g.setColor(Color.RED);
		if (game != null && game.userWon()) {
			g.drawImage(imgWinScreen, 0, 0, getSize().width, getSize().height, null);
		} else {
			g.drawImage(imgLoseScreen, 0, 0, getSize().width, getSize().height, null);
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o instanceof EndGameViewController && arg instanceof Game) {
			game = (Game) arg;
			repaint();
		}
	}
}
