package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.EndGameViewController;
import controllers.TutorialViewController;

public class TutorialView extends JPanel implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID =20151204;
	private TutorialViewController tutorialViewController;
	
	public TutorialView() {
		setName("TutorialViewController");
		setBackground(new Color (36,228,149));
		setVisible(true);
		
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
				tutorialViewController.buttonClicked(e);
			}
		});
		add(btnSkip);
	}
	
	public void initializeController() {
		tutorialViewController = (TutorialViewController)this.getMouseListeners()[0];
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
