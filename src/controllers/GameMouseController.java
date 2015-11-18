package controllers;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import objects.BadObject;
import objects.EstuaryObject;
import objects.GoodObject;
import swampgod.Game;
import views.ViewDelegate;

public class GameMouseController implements MouseInputListener {
	Game game;
	ViewDelegate view;
	
	public GameMouseController(Game game, ViewDelegate view){
		this.game = game;
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		Point p = new Point(e.getX(), e.getY());
		if(game.getClickedObject()!=null){
			game.getClickedObject().setPosition(p);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
