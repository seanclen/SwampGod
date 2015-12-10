package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.EndGameViewController;
import controllers.GameViewController;
import objects.*;
import swampgod.Game;
import swampgod.Main.GameState;

public class TutorialView extends GameView implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID =20151204;
	private int currentStage = 0;
	
	public TutorialView() {
		setName("TutorialViewController");
		setBackground(new Color (36,228,149));
		setVisible(true);
		
		tutorialPanel.setVisible(true);
		controlPanel.setVisible(false);
		upgradesPanel.setVisible(false);
		
		JButton btnSkip = new JButton("Skip");
		btnSkip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * At this point we have already added the view controller
				 * to this instance (refer back to ViewController).
				 * 
				 * Since the TitleViewController is technically a mouse listener,
				 * we must get the mouse listener from this instance and pass the 
				 * ActionEvent to the TitleViewController that a button has 
				 * been clicked.
				 */
				((GameViewController) getMouseListeners()[0]).tutorialButtonClicked(e);
			}
		});
		upgradesPanel.add(btnSkip, BorderLayout.LINE_START);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("TutorialView.update");
		if (o instanceof GameViewController && arg instanceof Game) {
			if (((Game) arg).getGameState().equals(GameState.TUTORIAL_STATE)) {
				game = (Game) arg;
				if (game.getTutorialStage() != currentStage) {
					currentStage++;
					nextStage();
				}
				paintSwamp();
			}
		}
	}
	
	public void resetStage() {
		currentStage = 0;
	}
	
	private void nextStage() {
		switch (currentStage){
		case 1:
			displayDialog(null, null, DialogType.TUTORIAL, "\n\nProtect the Estuary!\n\n"
					+ "Remove Hazardous Waste, Algae Blooms, and the invasive Zebra Mussels from the estuary.\n\n"
					+"Drag and drop the dangerous items into the trashcan!\n\n\n\n"
					+"Hit the Skip button to proceed directly to game");
			break;
		case 2:
			displayDialog(null, null, DialogType.TUTORIAL, "\n\nGood Job!\n\n"
					+ "As healthy wildlife enter the estuary you will earn points for estuary upgrades.\n"
					+"Each upgrade can be placed along the estuary to eat the harmful objects that float down the"
					+ "streams. Some upgrades are stronger than others!\n\n"
					+"Select and place upgrades along the waterline.");
			break;
		case 3:
			displayDialog(null, null, DialogType.TUTORIAL, "\n\nGood Job!\n\n"
					+ "As healthy wildlife enter the estuary, fish population in the estuary will grow.\n\n"
					+ "Hit the Collect Fish button to catch them and earn points!\n\n"
					+ "But be careful, the health of the estuary will change." );
			break;
		case 4:
			displayDialog(null, null, DialogType.TUTORIAL, "\n\nCongratulations!\n\n"
					+ "You have completed your training!\n\n" +
					"You are now prepared to advance. \n\n\n Continue to Game...");
			break;
		default: 
			paintSwamp();
			break;
		}
	}
}
