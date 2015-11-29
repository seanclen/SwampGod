package views;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class UpgradeView extends JPanel{

	public UpgradeView() {
		System.out.println("UpgradeView() initialized");
		setName("MenuView");
		setBackground(Color.RED);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		System.out.println("GameView:paintComponent()");
		g.setColor(Color.RED);
		g.drawRect(100, 100, 300, 300);
	}
}
