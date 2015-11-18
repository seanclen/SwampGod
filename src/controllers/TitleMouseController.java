package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import swampgod.Game;
import views.ViewDelegate;

public class TitleMouseController implements MouseListener {
	private Game game;
	private ViewDelegate view;
	
	public TitleMouseController(Game game, ViewDelegate view){
		this.game = game;
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
