package controllers;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.event.MouseInputListener;

import objects.BadObject;
import objects.EstuaryObject;
import objects.GoodObject;
import swampgod.Game;
import swampgod.Main.GameState;
import views.GameView;

public class GameViewController extends Observable implements MouseInputListener {
	Game game;
	private static Timer runTimer = new Timer();
	private TimerTask runGame;
	
	public GameViewController(){
		runGame = new TimerTask() {
			@Override
			public void run() {
				if (game.getGameState().equals(GameState.RUNNING_STATE)){
					game.tick();
					setChanged();
					notifyObservers(game);
					clearChanged();
				} else {
					System.out.println("GameViewController:not running state");
					setChanged();
					notifyObservers(game);
					clearChanged();
				}
			}
		};
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
				game.removeObjects(obj);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(game.getClickedObject()!= null){
			int x=e.getX();
			int y=e.getY();
			if(game.getTrashCan().getBounds().contains(x,y)){
				if(!game.getClickedObject().isGood()){
					game.updateScore(game.getClickedObject().getPointValue());
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
					runTimer.schedule(runGame, 0, 10);
				}
				else if (game.getGameState().equals(GameState.PAUSE_STATE)){
					game.setGameState(GameState.RUNNING_STATE);

				}
			}
			else if (btn.getText().equals("Pause")) {
				System.out.println("GameViewController:buttonClicked().Pause");
				game.setGameState(GameState.PAUSE_STATE);
			}
		}
	}

}
