package views;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import swampgod.Game;
import swampgod.Main.GameState;

import javax.swing.JButton;

public class TitleView extends JPanel{

	private static final long serialVersionUID = 11082015;
	private Game game;
	private ViewDelegate view;
	private Rectangle btnStart;
	
	public TitleView() {
		System.out.println("Title View");
		
		JButton btnChangeBG = new JButton("Change Background");
		btnChangeBG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBackground(Color.red);
			}
		});
		btnChangeBG.setBounds(500, 50, 178, 29);
		add(btnChangeBG);
		
		// Make sure we can use the listeners
		this.setFocusable(true);
	}

	protected void paintComponent(Graphics g) {
		System.out.println("Paint Component");
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(4, 4, 100, 100);
		
	}
	
	private boolean pointWithinStart(Point p) {
		return btnStart.contains(p);
	}

}
