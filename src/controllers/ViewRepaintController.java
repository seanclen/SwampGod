package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import swampgod.Game;
import views.ViewDelegate;

public class ViewRepaintController implements ActionListener{
	private Game game;
	private ViewDelegate view;
	
	public ViewRepaintController (Game game, ViewDelegate view) {
		this.game = game;
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO This is where all of the spiffy updates happen
		
	}

}
