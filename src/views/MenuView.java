package views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.MenuViewController;
import controllers.TitleViewController;
import swampgod.Game;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MenuView extends JPanel{

	private static final long serialVersionUID = 11082015;
	private MenuViewController menuViewController;
	private JLabel lblSwampSweeper;
	private JButton btnNewGame;
	
	public MenuView() {
		System.out.println("MenuView() initialized");
		setName("MenuView");
		setLayout(new BorderLayout());
		setBackground(new Color (36,228,149));
		setVisible(false);
		
		lblSwampSweeper = new JLabel("Swamp Sweeper");
		lblSwampSweeper.setPreferredSize(new Dimension(0, 200));
		
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
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
				menuViewController.buttonClicked(e);
			}
		});
		add(lblSwampSweeper, BorderLayout.PAGE_START);
		add(btnNewGame, BorderLayout.CENTER);
		setFocusable(true);
	}
	
	/**
	 * Make the view aware of its controller. This lets components call custom methods from
	 * the controller class.
	 */
	public void initializeController() {
		menuViewController = (MenuViewController) getMouseListeners()[0];
	}

	protected void paintComponent(Graphics g) {
		System.out.println("Paint Component:MenuView");
		super.paintComponent(g);
		
	}
}
