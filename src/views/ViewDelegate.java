package views;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewDelegate extends JPanel {
	
	private static final long serialVersionUID = 20151111;
	JFrame currentView;
	
	public void showTitleView(){
		TitleView window = new TitleView(this);
		window.presentView();
	}
	
}
