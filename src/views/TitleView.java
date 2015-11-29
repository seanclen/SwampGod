package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import controllers.TitleViewController;

import javax.swing.JButton;

public class TitleView extends JPanel {

	private static final long serialVersionUID = 11082015;
	private TitleViewController titleViewController;
	
	public TitleView() {
		System.out.println("TitleView() initialized");
		setName("TitleView");
		setLayout(new BorderLayout());
		setBackground(new Color (36,228,149));
		setVisible(true);
		//Add panels to take up space
		
		
		// Lets add the fancy buttons!
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
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
				titleViewController.buttonClicked(e);
			}
		});
		add(btnPlay, BorderLayout.CENTER);
	}
	
	/**
	 * Make the view aware of its controller. This lets components call custom methods from
	 * the controller class.
	 */
	public void initializeController() {
		titleViewController = (TitleViewController) getMouseListeners()[0];
	}

	protected void paintComponent(Graphics g) {
		System.out.println("Paint Component:TitleView");
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(4, 4, 100, 100);
		
	}

}
