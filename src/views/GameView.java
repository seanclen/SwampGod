package views;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.GameViewController;
import objects.EstuaryObject;
import swampgod.Estuary;
import swampgod.Game;
import swampgod.Main.GameState;
import swampgod.Stream;

public class GameView extends JPanel implements Observer{

	private static final long serialVersionUID = 11082015;
	private Game game;
	static int panelWidth = 960, panelHeight = 640;
	
	private static Image imgAlgae;
	private static Image imgClam;
	private static Image imgTrash;
	private static Image imgBackground;

	public GameView() {
		System.out.println("GameView() initialized");
		loadView();
		setName("GameView");
		setBackground(Color.BLUE);
		setVisible(false);
		//setBackground(new Color (36,228,149));
		
		/**
		 * At this point we have already added the view controller
		 * to this instance (refer back to ViewController).
		 * 
		 * Since the TitleViewController is technically a mouse listener,
		 * we must get the mouse listener from this instance and pass the 
		 * ActionEvent to the TitleViewController that a button has 
		 * been clicked.
		 */
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameViewController) getMouseListeners()[0]).buttonClicked(e);
			}
		});
		add(btnStart, BorderLayout.PAGE_START);
		
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameViewController) getMouseListeners()[0]).buttonClicked(e);
			}
		});
		add(btnPause, BorderLayout.PAGE_START);
	}
	
	public void setGame(Game update) {
		this.game = update;
	}
	
	public Game getGame() {
		return this.game;
	}

	private void loadView() {
    	try {
    		imgAlgae = ImageIO.read(new File("pics/algea.png"));
			imgClam = ImageIO.read(new File("pics/clam.png"));
			imgTrash = ImageIO.read(new File("pics/trash_can.png"));
			imgBackground = ImageIO.read(new File("pics/EstuaryBackground.png"));
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		System.out.println("GameView:paintComponent()");
		g.drawImage(imgBackground, 0, 0, null);
	}
	
	public void paintSwamp() {
		Graphics g = getGraphics();
		//super.paintComponent(g);
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
		
		//Paint Background and bounds
		//g.drawImage(imgBackground, 0, 0, getSize().width, getSize().height, null);
		g.setColor(Color.BLUE);
		Stroke temp = g.getStroke();
		g.setStroke(new BasicStroke(70f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
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
	}
	private boolean healthChanged;
	private void paintHUD(Graphics2D g2d) {
		Rectangle healthBar = new Rectangle(250, 500, 500, 100);
		Rectangle trash = new Rectangle(game.getTrashCan().getBounds());
		int health = game.getHealth();
		if (health < 30) {
			g2d.setColor(Color.RED);
			paintHealthBar(g2d, healthBar, health);
		}
		else if (health < 70) {
			g2d.setColor(Color.YELLOW);
			paintHealthBar(g2d, healthBar, health);
		}
		else {
			g2d.setColor(Color.GREEN);
			paintHealthBar(g2d, healthBar, health);
		}
		
		g2d.drawImage(imgTrash, trash.x, trash.y, trash.width, trash.height,this);
	}
	
	private void paintHealthBar(Graphics2D g2d, Rectangle healthBar, int health) {
		g2d.fillRoundRect(healthBar.x, healthBar.y, healthBar.width, healthBar.height, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Purisa", Font.BOLD, 15));
		g2d.drawString("Health: " + health + "    Points: " +game.getPoints(), healthBar.x + 100, healthBar.y + 60);
		g2d.setColor(Color.GRAY);
		g2d.setStroke(new BasicStroke(10f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		g2d.drawRoundRect(healthBar.x, healthBar.y, healthBar.width, healthBar.height, 15, 15);
	}

	/**
	 * When we get an update from the GameViewController, repaint the things!
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GameViewController && arg instanceof Game) {
			game = (Game) arg;
			if (game.getGameState().equals(GameState.RUNNING_STATE)) {
				paintSwamp();
			}
		}
	}
}
