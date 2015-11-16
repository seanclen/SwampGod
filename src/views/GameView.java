package views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.EstuaryObject;
import swampgod.Estuary;
import swampgod.Game;
import swampgod.Stream;

public class GameView extends JPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 11082015;
	private static Game game;
	static int frameWidth = 960, frameHeight = 640;
	protected static JFrame frame;
	
	private static Image imgAlgae;
	private static Image imgClam;
	private static Image imgTrash;

	public GameView(Game newGame) {
		game = newGame;
		loadView();
	}

	private static void loadView() {
		frame = new JFrame();
    	//frame.getContentPane().add(new GameView());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setTitle("SwampGod");
    	frame.setVisible(true);
    	
    	try {
    		imgAlgae = ImageIO.read(new File("pics/algea.png"));
			imgClam = ImageIO.read(new File("pics/clam.png"));
			imgTrash = ImageIO.read(new File("pics/trash.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
//    	for(int i = 0; i < 1000; i++){
//    		frame.repaint();
//    		try {
//    			Thread.sleep(50);
//    		} catch (InterruptedException e) {
//    			e.printStackTrace();
//    		}
//    	}
	}
	
	/**
	 * Calls loadView in GameView to create and paint frame.
	 */
	public void presentWindow() {
		loadView();
	}
	
	
	public void paintIt() {
		Graphics g = frame.getGraphics();
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
		g.draw(stream.getStreamCurve());
		g.setStroke(temp);
		
		//Paint objects
		for(EstuaryObject obj : streamObjects) {
			Image img = null;
			if (obj.getType() == "Algae" || obj.getType() == "Clam" || obj.getType() == "LilyPad") {
				img = imgAlgae;
			} else {
				img = imgClam;
			}
			g.drawImage(img,
					obj.getBounds().x, 
					obj.getBounds().y, 
					obj.getBounds().width, 
					obj.getBounds().height,this);
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
