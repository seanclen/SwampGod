package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	private JPanel endPanel;
	private EndGameViewController endGameViewController;
	private Game game;
	//private JPanel picPanel = new JPanel();
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
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(null);
		setVisible(false);
		
		/*
		picPanel.setSize(400, 200);
		picPanel.setPreferredSize(new Dimension(400,200));
		picPanel.setBackground(Color.RED);
		picPanel.setVisible(true);
		*/
		
		game = null;
		btnMenu = new JButton("Main Menu");
		btnMenu.setFont(new Font("Arial", Font.PLAIN, 20));
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
		
		btnMenu.setBounds(600, 550, 200, 80);
		
		
		endPanel = new JPanel();
		endPanel.setSize(super.getSize().width,getSize().height);
		endPanel.add(btnMenu);
		endPanel.setVisible(true);this.add(endPanel);
		
		
		//add(picPanel);
		add(btnMenu);
		
	}
	
	public void initializeController() {
		endGameViewController = (EndGameViewController)this.getMouseListeners()[0];
	}
	
	@Override
	public void paintComponent(Graphics g) {
		System.out.println("EndGameView:paintComponent()");
		//super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 28));
		if (game != null && game.userWon()) {
			g.drawImage(imgWinScreen, 0, 0, getSize().width, getSize().height, null);
			g.drawString("Final Score: " + game.getPoints(), (getSize().width/2)-150, getSize().height/2);
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
