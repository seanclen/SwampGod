package views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.TestMouse;
import swampgod.Main.GameState;

public class ViewDelegate extends JFrame {

	private static final long serialVersionUID = 20151122;
	private GameState gameState;
	private ArrayList<JPanel> viewPanels;
	private CardLayout cardLayout;
	private JPanel currentPanel;

	public ViewDelegate() {
		System.out.println("ViewDelegate() initialized");
		//Set the Layout of the frame
		viewPanels = new ArrayList<JPanel>();
		
		// Set the frame attributes
		setTitle("Swamp Sweeper");
		gameState = GameState.INITIALIZE;
		setBackground(Color.BLUE);
		setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void loadPanels(ArrayList<JPanel> panels) {
		viewPanels = panels;
		System.out.println("ViewDelegate:loadPanels("+panels+")");
		// Since this only gets called during the initialization stage
		// set the currentPanel to TitleView
		currentPanel = viewPanels.get(getViewPanelIndex("TitleView"));
		currentPanel.setVisible(true);
		repaint();
		System.out.println("ViewDelegate.currentPanel: "+currentPanel.getName());
	}
	
	public void presentPanelFromGameState(GameState gameState) {
		if (!gameState.equals(this.gameState)) {
			System.out.println("ViewDelegate:presentPanelFromGameState() -- " + gameState);
			this.gameState = gameState;
			
			if (gameState.equals(GameState.TITLE_STATE))
			{
				if (updateCurrentPanel("TitleView")) {
					currentPanel.setVisible(true);
				}
			}
			else if (gameState.equals(GameState.MENU_STATE))
			{
				if (updateCurrentPanel("MenuView")) {
					currentPanel.setVisible(true);
				}
			}
			else if (gameState.equals(GameState.NEWGAME_STATE))
			{
				if (updateCurrentPanel("GameView")) {
					currentPanel.setVisible(true);
				}
			}
			else if (gameState.equals(GameState.RUNNING_STATE))
			{
				if (updateCurrentPanel("GameView")) {
					currentPanel.setVisible(true);
				}
			}	
		}
	}
	
	/**
	 * Set the ViewDelegate's currentPanel to the JPanel with given name panelName.
	 * Since the JPanel currentPanel is instantiated outside of the ArrayList, it is
	 * necessary to return the current currentPanel back into the ArrayList in order 
	 * to store any changes that may have occurred. Otherwise every time this function is
	 * called it would just return the initialized state of each panel.
	 * @param panelName
	 * @return true if the current panel exists and has been update
	 */
	private boolean updateCurrentPanel(String panelName) {
		System.out.println("ViewDelegate:updateCurrentPanel("+panelName+")");
		int updateIndex = getViewPanelIndex(panelName);
		if (updateIndex >= 0) {
			// "Update" the panel in the viewPanels array list
			int currentIndex = getViewPanelIndex(currentPanel.getName());
			viewPanels.set(currentIndex, currentPanel);
			currentPanel = viewPanels.get(updateIndex);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Get the index of the JPanel with name panelName within viewPanels
	 * @param panelName
	 * @return -1 if the panel does not exist in the collection
	 */
	private int getViewPanelIndex(String panelName) {
		int i = 0;
		while (i < viewPanels.size()) {
			if (viewPanels.get(i).getName().equals(panelName)){
				return i;
			}
		}
		return -1;
	}
	
	public void repaintCurrentPanel() {
		System.out.println("ViewDelegate:repaintCurrentPanel()");
		
	}
	
	/**
	 * Get the relative point based on the percentage of the frame from
	 *     the top left to the bottom right. Use this to find the Cartesian
	 *     points for layout purposes to avoid resizing issues.
	 *     
	 * The scale is 0.0 (top left) to 100.0 (bottom right)
	 *     
	 * @param x : the percentage from the left to right.
	 * @param y : the percentage from top to bottom
	 * @return Point containing x and y.
	 */
	public Point getPointFromPercentage(double x, double y) {
		if (x <= 100 && x>=0 && y<=100 && y>=0) {
			return new Point((int) (getWidth() * (x / 100)), (int) (getHeight() * (y / 100)));
		} else {
			return null;
		}
	}
}
