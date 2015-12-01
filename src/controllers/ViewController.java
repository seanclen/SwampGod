package controllers;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import swampgod.Game;
import swampgod.Main.GameState;
import views.EndGameView;
import views.GameView;
import views.MenuView;
import views.TitleView;

public class ViewController extends Observable implements Observer{
	protected ArrayList<JPanel> panels;
	private ViewDelegate viewDelegate;
	private TitleView titleView;
	private TitleViewController titleViewController;
	private MenuView menuView;
	private MenuViewController menuViewController;
	private GameView gameView;
	private GameViewController gameViewController;
	private EndGameView endGameView;
	private EndGameViewController endGameViewController;

	public ViewController () {
		System.out.println("ViewController");
		
		//Initialize private variables
		viewDelegate = new ViewDelegate();
		panels = new ArrayList<JPanel>();
		
		// Load the initial view, automatically Title Screen at init
		titleView = new TitleView();
		titleViewController = new TitleViewController();
		titleViewController.addObserver(this);
		titleView.addMouseListener(titleViewController);
		titleView.initializeController();
		panels.add(titleView);
		
		menuView = new MenuView();
		menuViewController = new MenuViewController();
		menuViewController.addObserver(this);
		menuView.addMouseListener(menuViewController);
		menuView.initializeController();
		panels.add(menuView);
		
		gameView = new GameView();
		gameViewController = new GameViewController();
		gameViewController.addObserver(this);
		gameViewController.addObserver(gameView);
		gameView.addMouseListener(gameViewController);
		gameView.addMouseMotionListener(gameViewController);
		panels.add(gameView);
		
		endGameView = new EndGameView();
		endGameViewController = new EndGameViewController();
		endGameViewController.addObserver(this);
		endGameView.addMouseListener(endGameViewController);
		endGameView.initializeController();
		panels.add(endGameView);
		
		viewDelegate.loadPanels(panels);
	}

	/**
	 * This is where the logic is handled after any object that this controller
	 * is listening to publishes and update. In the most common case this
	 * is where we will handle changing the game state as well as any other
	 * global game changes that may occur. Will also push the game from the
	 * game controller to the GameViewController so it can redraw and handle
	 * actions accordingly.
	 * 
	 * @param o
	 * @param arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof GameState)
		{
			setChanged();
			notifyObservers(arg);
			clearChanged();
		}
		if (o instanceof GameViewController && arg instanceof Game) {
			GameState state = ((Game) arg).getGameState();
			System.out.println("Got the game change"+state);
		}
	}
	
	/**
	 * Get the game from the Controller and push it to the GameView and GameViewController. This
	 * is also where we set the layout of the game for variable resolutions
	 * @param game
	 */
	public void setGame(Game game) {
		gameView.setGame(game);
		gameViewController.setGame(game);
	}
	
	public void handleStateChange(GameState gameState) {
		boolean presented = viewDelegate.presentPanelFromGameState(gameState);
		if (presented && gameState.equals(GameState.RUNNING_STATE)) {
			System.out.println("ViewController:handleStateChange -- time to run the game.");
		}
	}
	
// VIEW DELEGATE *********************************************************************** VIEW DELEGATE	
	
	public class ViewDelegate extends JFrame {

		private static final long serialVersionUID = 20151122;
		private GameState gameState;
		private ArrayList<JPanel> viewPanels;
		private int currentPanelIndex;

		public ViewDelegate() {
			System.out.println("ViewDelegate() INSIDE VIEWCONTROLLER initialized");
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
			for(int i = 0; i < panels.size(); i++) {
				System.out.println("ViewDelegate:loadPanels.add()");
				viewPanels.get(i).setSize(getSize());
				add(viewPanels.get(i));
			}
			getContentPane().getComponent(currentPanelIndex).setSize(getSize());
			getContentPane().getComponent(currentPanelIndex).setVisible(true);
			setVisible(true);
		}
		
		public boolean presentPanelFromGameState(GameState gameState) {
			if (!gameState.equals(this.gameState)) {
				System.out.println("ViewDelegate:presentPanelFromGameState() -- " + gameState);
				this.gameState = gameState;
				this.getContentPane().removeAll();
				if (gameState.equals(GameState.TITLE_STATE))
				{
					//switchPanelTo("TitleView");
					titleView.setSize(this.getSize());
					titleView.setVisible(true);
					add(titleView);
					repaint();
					return true;
				}
				else if (gameState.equals(GameState.MENU_STATE))
				{
					//switchPanelTo("MenuView");
					menuView.setSize(this.getSize());
					menuView.setVisible(true);
					add(menuView);
					repaint();
					return true;
				}
				else if (gameState.equals(GameState.NEWGAME_STATE))
				{
					//switchPanelTo("GameView");
					gameView.setSize(this.getSize());
					gameView.setVisible(true);
					gameViewController.updateGameSize(getBounds());
					add(gameView);
					repaint();
					return true;
				}
				else if (gameState.equals(GameState.RUNNING_STATE))
				{
					//switchPanelTo("GameView");
					gameView.setSize(this.getSize());
					gameView.setVisible(true);
					gameViewController.updateGameSize(getBounds());
					add(gameView);
					repaint();
					return true;
				}
				else if (gameState.equals(GameState.ENDGAME_STATE))
				{
					System.out.println("ViewController:update(ENDGAME_STATE)");
					endGameView.setSize(this.getSize());
					endGameView.setVisible(true);
					add(endGameView);
					repaint();
					return true;
				}
				return false;
			}
			return false;
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

}
