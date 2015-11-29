package controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.event.MouseInputListener;

import objects.BadObject;
import objects.EstuaryObject;
import objects.GoodObject;
import swampgod.Game;
import swampgod.Main.GameState;

public class GameViewController extends Observable implements MouseInputListener {
	Game game;
	
	public GameViewController(){
		
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
				setChanged();
				notifyObservers("RunGame");
				clearChanged();
			}
			else if (btn.getText().equals("Pause")) {
				System.out.println("GameViewController:buttonClicked().Pause");
				setChanged();
				notifyObservers("PauseGame");
				clearChanged();
			}
			
		}
	}

}
