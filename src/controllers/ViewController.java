package controllers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import swampgod.Game;
import swampgod.Main.GameState;
import views.GameView;
import views.MenuView;
import views.TitleView;
import views.ViewDelegate;

public class ViewController extends Observable implements Observer{
	private ArrayList<JPanel> panels;
	private ViewDelegate viewDelegate;
	private TitleView titleView;
	private TitleViewController titleViewController;
	private MenuView menuView;
	private MenuViewController menuViewController;
	private GameView gameView;
	private GameViewController gameViewController;

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
		gameView.addMouseListener(gameViewController);
		gameView.initializeController();
		panels.add(gameView);
		
		viewDelegate.loadPanels(panels);
	}
	
	public void setGameViewControllerObserver(Observer o) {
		gameViewController.addObserver(o);
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
		if (o instanceof TitleViewController || o instanceof MenuViewController)
		{
			if (arg instanceof GameState) {
				setChanged();
				notifyObservers(arg);
				clearChanged();
			}
		}
//		else if (o instanceof GameViewController && arg instanceof Game)
//		{
//			// Something happened in GameView, let the Controller know
//			setChanged();
//			notifyObservers(arg);
//			clearChanged();
//		}
		else if (o instanceof Controller && arg instanceof Game)
		{
			gameViewController.updateGame((Game) arg);
			viewDelegate.repaintCurrentPanel();
		}
	}
	
	public void handleStateChange(GameState gameState) {
		viewDelegate.presentPanelFromGameState(gameState);
	}
}
