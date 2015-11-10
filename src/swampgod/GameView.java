package swampgod;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Objects.EstuaryObject;

public class GameView extends JPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 11082015;
	private static Game game = new Game();
	static int frameWidth = 960, frameHeight = 640;

	public GameView() {
		
	}

	private static void loadView() {
		JFrame frame = new JFrame();
    	frame.getContentPane().add(new GameView());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setTitle("SwampGod");
    	frame.setVisible(true);
    	
    	for(int i = 0; i < 1000; i++){
    		frame.repaint();
    		try {
    			Thread.sleep(50);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
	}
	
	/**
	 * Calls loadView in GameView to create and paint frame.
	 */
	public void presentWindow() {
		loadView();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Stream[] streams = game.getStreams();
		for (Stream stream : streams) {
			paintStream(stream, g2);
		}
		paintEstuary(game.getEstuary(), g2);
	}

	private void paintStream(Stream stream, Graphics2D g) {
		ArrayList<EstuaryObject> streamObjects = stream.getObjectsToDraw();
		
		Rectangle bounds = stream.getBounds();
		int x = bounds.x, y = bounds.y, width = bounds.width, height = bounds.height;
		
		//Paint Background and bounds
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		g.setColor(Color.red);
		g.drawRect(x, y, width, height);
		g.setColor(Color.blue);
		Stroke temp = g.getStroke();
		g.setStroke(new BasicStroke(120f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		g.draw(stream.streamCurve);
		g.setStroke(temp);
		
		//Paint objects
		for(EstuaryObject obj : streamObjects) {
			g.setColor(Color.magenta);
			g.fillRect(obj.getBounds().x, obj.getBounds().y, obj.getBounds().width, obj.getBounds().height);
		}
	}
	
	private void paintEstuary(Estuary estuary, Graphics2D g) {
		ArrayList<EstuaryObject> estuaryObjects = estuary.getObjectsToDraw();
		
		Rectangle bounds = estuary.getBounds();
		int x = bounds.x, y = bounds.y, width = bounds.width, height = bounds.height;
		
		//Paint Background and bounds
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
		g.setColor(Color.red);
		g.drawRect(x, y, width, height);
		
//		for(EstuaryObject obj : estuaryObjects) {
//			g.setColor(Color.green);
//			g.drawRect(obj.getPosition().x, obj.getPosition().y, width, height);
//		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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
