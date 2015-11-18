package views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JPanel;

import controllers.GameMouseController;
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
	static int panelWidth = 960, panelHeight = 640;
	protected static JPanel panel;
	private Graphics viewGraphics;
	
	private static Image imgAlgae;
	private static Image imgClam;
	private static Image imgTrash;

	public GameView(Game newGame) {
		game = newGame;
		loadView();
	}

	private static void loadView() {
		panel = new JPanel();
    	//panel.getContentPane().add(new GameView());
    	panel.setBackground(Color.gray);
    	panel.setSize(panelWidth, panelHeight);
    	panel.setVisible(true);
    	//GameMouseController m = new GameMouseController(game);
    	//panel.addMouseMotionListener(m);
    	//panel.addMouseListener(m);
    	
    	
    	try {
    		imgAlgae = ImageIO.read(new File("pics/algea.png"));
			imgClam = ImageIO.read(new File("pics/clam.png"));
			imgTrash = ImageIO.read(new File("pics/trash_can.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateGame(Game g) {
		game = g;
	}
	
	public void setGraphics(Graphics g) {
		viewGraphics = g;
	}
	
	public void paintIt() {
		Graphics g = viewGraphics;
		Graphics2D g2 = (Graphics2D) g;
		Stream[] streams = game.getStreams();
		for (Stream stream : streams) {
			paintStream(stream, g2);
		}
		paintEstuary(game.getEstuary(), g2);
		paintHUD(g2);
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
		EstuaryObject obj = game.getClickedObject();
		if (obj!=null) {
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
	}
	
	private void paintHUD(Graphics2D g2d) {
		Rectangle healthBar = new Rectangle(250, 500, 500, 100);
		Rectangle trash = new Rectangle(game.getTrashCan().getBounds());
		int health = game.getHealth();
		if (health < 30) {
			g2d.setColor(Color.RED);
		}
		else if (health < 70) {
			g2d.setColor(Color.YELLOW);
		}
		else {
			g2d.setColor(Color.GREEN);
		}
		g2d.fillRoundRect(healthBar.x, healthBar.y, healthBar.width, healthBar.height, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Purisa", Font.BOLD, 15));
		g2d.drawString("Health: " + health + "    Points: " +game.getPoints(), healthBar.x + 100, healthBar.y + 60);
		g2d.setColor(Color.GRAY);
		g2d.setStroke(new BasicStroke(10f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		g2d.drawRoundRect(healthBar.x, healthBar.y, healthBar.width, healthBar.height, 15, 15);
		g2d.drawImage(imgTrash, trash.x, trash.y, trash.width, trash.height,this);
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
