package swampgod;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import objects.BadObject;
import objects.EstuaryObject;
import objects.GoodObject;

public class MouseMovement implements MouseInputListener {
	Game g;
	
	public MouseMovement(Game g){
		this.g = g;
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
			if(g.streams[i].getBounds().contains(x,y)){
				s=i;
			}
		}
		boolean found =false;
		EstuaryObject obj=null;
		for(GoodObject go : g.streams[s].goodObjects){
			if(!found){
				if(go.getBounds().contains(x,y)){
					obj = go;
					found=true;
				}
			}
		}
		if(!found){
			for(BadObject bo : g.streams[s].badObjects){
				if(!found){
					if(bo.getBounds().contains(x,y)){
						obj = bo;
						found=true;
					}
				}
			}
		}
		g.clickedObject = obj;
		g.previousPosition= obj.getPos();
		g.removeObjects(obj);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if(g.clickedObject!= null){
			int x=e.getX();
			int y=e.getY();
			if(g.getTrashCan().getBounds().contains(x,y)){
				if(!g.clickedObject.isGood()){
					g.updateScore(g.clickedObject.getPointValue());
				}
				g.clickedObject = null;
			}
			else{
				if(g.clickedObject.isGood()){
					g.clickedObject.setPosition(g.previousPosition);
					g.streams[g.clickedObject.getStream()].goodObjects.add((GoodObject) g.clickedObject);
					g.clickedObject=null;
				}
				else{
					g.clickedObject.setPosition(g.previousPosition);
					g.streams[g.clickedObject.getStream()].badObjects.add((BadObject) g.clickedObject);
					g.clickedObject=null;
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
		if(g.clickedObject!=null){
			g.clickedObject.setPosition(p);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
