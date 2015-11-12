package views;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class TitleView extends JPanel{

	private static final long serialVersionUID = 11082015;
	protected JButton btnChangeBG;
	protected ViewDelegate viewDelegate;
	
	public TitleView(ViewDelegate delegate) {
		System.out.println("Title View");
		this.viewDelegate = delegate;
	}
	
	public void presentView() {
		JFrame frame = new JFrame();
		//frame.setContentPane(this.viewDelegate);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setBackground(new Color(100,150,70));
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
//		for(int i = 0; i < 1000; i++){
//    		frame.repaint();
//    		try {
//    			Thread.sleep(50);
//    		} catch (InterruptedException e) {
//    			e.printStackTrace();
//    		}
//    	}
		
	}

	protected void paintComponent(Graphics g) {
		System.out.println("Paint Component");
	}
}
