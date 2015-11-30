package controllers;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.event.MouseInputListener;
import javax.swing.Timer;

import objects.BadObject;
import objects.Bush;
import objects.EstuaryObject;
import objects.GoodObject;
import objects.Plant;
import objects.Tree;
import swampgod.Game;
import swampgod.Main.GameState;
import views.GameView;

public class GameViewController extends Observable implements MouseInputListener {
	Game game;
	private static Timer runTimer;
	//private TimerTask runGame;
	
	public GameViewController(){
		runTimer = new Timer(40, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent action) {
				if (game.getGameState().equals(GameState.RUNNING_STATE)){
					game.tick();
					setChanged();
					notifyObservers(game);
					clearChanged();
				} else if (game.getGameState().equals(GameState.UPGRADE_STATE)) {
					setChanged();
					notifyObservers(game);
					clearChanged();
					runTimer.stop();
				} else {
					System.out.println("GameViewController:not running state");
					setChanged();
					notifyObservers(game);
					clearChanged();
				}
			}
			
		});
	}
	
	public void updateGameSize(Rectangle bounds) {
		game.updateWindowSize(bounds);
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return this.game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("GameViewController:mouseClicked("+e.getPoint()+")");
		if(game.getGameStatus().equals(GameState.UPGRADE_STATE)){
			if(game.getChosenPlant()!=null){
				Point p = new Point(e.getX(), e.getY());
				Plant pl = game.getChosenPlant();
				pl.setPosition(p);
				game.setChosenPlant(pl);
				game.getPlants().add(game.getChosenPlant());
				game.setPoints(game.getChosenPlant().getPointValue());
				game.setChosenPlant(null);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (game.getGameState().equals(GameState.RUNNING_STATE)) {
			int x = e.getX();
			int y = e.getY();
			int s=-1;
			for(int i=0;i<3;i++){
				if(game.getStreams()[i].getBounds().contains(x,y)){
					s=i;
				}
			}
			boolean found =false;
			EstuaryObject obj=null;
			for(GoodObject go : game.getStreams()[s].getGoodObjects()){
				if(!found){
					if(go.getBounds().contains(x,y)){
						obj = go;
						found=true;
					}
				}
			}
			if(!found){
				for(BadObject bo : game.getStreams()[s].getBadObjects()){
					if(!found){
						if(bo.getBounds().contains(x,y)){
							obj = bo;
							found=true;
						}
					}
				}
			}
			if (obj != null) {
				game.setClickedObject(obj);
				game.setPreviousPosition(obj.getPos());
				game.removeObjToTrash(obj);
			}
		}
		else if (game.getGameState().equals(GameState.UPGRADE_STATE) && game.getChosenPlant() != null) {
			//TODO Need to add logic here to make sure the plant is being placed around Stream
			game.getPlants().add(game.getChosenPlant());
			game.setChosenPlant(null);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(game.getClickedObject()!= null){
			int x=e.getX();
			int y=e.getY();
			if(game.getTrashCan().getBounds().contains(x,y)){
				if(!game.getClickedObject().isGood()){
					//game.updateScore(game.getClickedObject().getPointValue());
				}
				game.setClickedObject(null);
			}
			else{
				if(game.getClickedObject().isGood()){
					game.getClickedObject().setPosition(game.getPreviousPosition());
					game.getStreams()[game.getClickedObject().getStream()].getGoodObjects().add((GoodObject) game.getClickedObject());
					game.setClickedObject(null);
				}
				else{
					game.getClickedObject().setPosition(game.getPreviousPosition());
					game.getStreams()[game.getClickedObject().getStream()].getBadObjects().add((BadObject) game.getClickedObject());
					game.setClickedObject(null);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
//		Point p = new Point(e.getX(), e.getY());
//		if(game.getClickedObject()!=null){
//			game.getClickedObject().setPosition(p);
//		}
		EstuaryObject obj = game.getClickedObject();
		if (obj != null) {
			obj.setPosition(e.getPoint());
			game.setClickedObject(obj);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	public void buttonClicked(ActionEvent e) {
		System.out.println("GameViewController:buttonClicked()");
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			if (btn.getText().equals("Start")) {
				System.out.println("GameViewController:buttonClicked().Start");
				/**
				 * The start button has been clicked, so we must tell the Controller that
				 * the user is ready to play the game, thus calling the runGame() function.
				 * We accomplish by simply passing a string 'token' "RunGame" to the ViewController
				 * which will then pass the token to the Controller.
				 */
				if (game.getGameState().equals(GameState.RUNNING_STATE)){
					runTimer.start();
				}
				else if (game.getGameState().equals(GameState.PAUSE_STATE)){
					game.setGameState(GameState.RUNNING_STATE);

				}
			}
			else if (btn.getText().equals("Pause")) {
				System.out.println("GameViewController:buttonClicked().Pause");
				game.setGameState(GameState.PAUSE_STATE);
			}
			else if (btn.getText().equals("Upgrade")) {
				game.setGameState(GameState.UPGRADE_STATE);
			}
			else if(btn.getText().equals("AddBush")&&game.getGameStatus().equals(GameState.UPGRADE_STATE)){
				Bush b = new Bush(null);
				game.setChosenPlant(b);
				
			}
			else if(btn.getText().equals("AddTree")&&game.getGameStatus().equals(GameState.UPGRADE_STATE)){
				Tree t = new Tree(null);
				game.setChosenPlant(t);
			}
		}
	}

}
