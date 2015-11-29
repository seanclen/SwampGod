package views;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class EndGameView extends JPanel{

	public EndGameView() {
		setName("EndGameView");
		setBackground(Color.MAGENTA);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		System.out.println("EndGameView:paintComponent()");
		g.setColor(Color.RED);
		g.drawString("YOU FAIL", 200, 200);
	}
}
