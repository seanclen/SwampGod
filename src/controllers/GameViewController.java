package controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.event.MouseInputListener;

import objects.BadObject;
import objects.EstuaryObject;
import objects.GoodObject;
import swampgod.Game;
import swampgod.Main.GameState;
import views.ViewDelegate;

public class GameViewController extends Observable implements MouseInputListener {
	Game game;
	ViewDelegate view;
	
	public GameViewController(){
		
	}
	
	public void updateGame(Game update) {
		this.game = update;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	private void runGame() {
		//game.getGameState().equals(GameState.RUNNING_STATE)
		while (game.getGameState().equals(GameState.RUNNING_STATE)) {
			System.out.println("GameController:runGame() -- tick");
			game.tick();
			setChanged();
			notifyObservers(game);
			clearChanged();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// If for whatever we can't put the thread to bed, "restart" the game
				game.setGameState(GameState.TITLE_STATE);
				//viewController.handleStateChange(GameState.TITLE_STATE);
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("POOP---------------------------------------------------------");
		System.out.println("POOP---------------------------------------------------------");
		System.out.println("POOP---------------------------------------------------------");
		System.out.println("POOP---------------------------------------------------------");
		System.out.println("POOP---------------------------------------------------------");
		System.out.println("POOP---------------------------------------------------------");
		System.out.println("POOP---------------------------------------------------------");
		System.out.println("POOP---------------------------------------------------------");
		System.out.println("POOP---------------------------------------------------------");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
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
		game.setClickedObject(obj);
		game.setPreviousPosition(obj.getPos());
		game.removeObjects(obj);
		setChanged();
		notifyObservers(game);
		clearChanged();
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
		setChanged();
		notifyObservers(game);
		clearChanged();
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
		Point p = new Point(e.getX(), e.getY());
		if(game.getClickedObject()!=null){
			game.getClickedObject().setPosition(p);
		}
		setChanged();
		notifyObservers(game);
		clearChanged();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void buttonClicked(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("____________________________________________________________________________");
	}

}
