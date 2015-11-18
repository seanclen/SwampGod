package views;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import swampgod.Game;

import javax.swing.JButton;

public class MenuView extends JPanel{

	private static final long serialVersionUID = 11082015;
	private Game game;
	private ViewDelegate view;
	
	public MenuView(Game game, ViewDelegate view) {
		System.out.println("Menu View");
		this.game = game;
		this.view = view;
		
		// Make sure we can use the listeners
		this.setFocusable(true);
	}

	protected void paintComponent(Graphics g) {
		System.out.println("Paint Component:MenuVIew");
		super.paintComponent(g);
		g.setColor(Color.MAGENTA);
		g.fillRect(104, 104, 100, 100);
		
	}
}
