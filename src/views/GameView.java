package views;

import java.awt.AlphaComposite;
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
import objects.Plant;
import swampgod.Estuary;
import swampgod.Game;
import swampgod.Main.GameState;
import swampgod.Stream;

public class GameView extends JPanel implements Observer{

	private static final long serialVersionUID = 11082015;
	private Game game;
	static int panelWidth = 960, panelHeight = 640;
	private JPanel upgradesPanel;
	
	private static Image imgAlgae;
	private static Image imgClam;
	private static Image imgTrash;
	private static Image imgTrashBag;
	private static Image imgMussel;
	private static Image imgBackground;
	private static Image imgTree;
	private static Image imgBush;
	private static Image imgFish;
	private static Image imgLilyPad;

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
		
		JButton btnUpgrade = new JButton("Upgrade");
		btnUpgrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameViewController) getMouseListeners()[0]).buttonClicked(e);
			}
		});
		add(btnUpgrade, BorderLayout.PAGE_START);
		
		upgradesPanel = new JPanel();
		upgradesPanel.setSize(300,100);
		upgradesPanel.setVisible(false);
		
		JButton btnBush = new JButton("AddBush");
		btnBush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameViewController) getMouseListeners()[0]).buttonClicked(e);
			}
		});
		upgradesPanel.add(btnBush);
		
		JButton btnTree = new JButton("AddTree");
		btnTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameViewController) getMouseListeners()[0]).buttonClicked(e);
			}
		});
		upgradesPanel.add(btnTree);
		
		add(upgradesPanel);
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
			imgTrashBag = ImageIO.read(new File("pics/trash.png"));
			imgMussel = ImageIO.read(new File("pics/zebra_musscle.png"));
			imgLilyPad = ImageIO.read(new File("pics/lilypad.png"));
			imgFish = ImageIO.read(new File("pics/whaleishFish.png"));
			imgTree = ImageIO.read(new File("pics/tree.png"));
			imgBush = ImageIO.read(new File("pics/azalea.png"));
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
		Graphics g = super.getGraphics();
		//super.paintComponent(g);
		drawView(g);
	}
	
	private void drawView(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		BufferedImage combinedImage = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D gci = combinedImage.createGraphics();
		AlphaComposite ac = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 1f);

		gci.drawImage(imgBackground, 0, 0, null);
		Stream[] streams = game.getStreams();
		for (Stream stream : streams) {
			paintStream(stream, gci);
		}
		paintEstuary(game.getEstuary(), gci);
		paintHUD(gci);

        g2d.drawImage(combinedImage, 0, 0, null);

        gci.dispose();
        g2d.dispose();
	}

	private void paintStream(Stream stream, Graphics2D g) {
		ArrayList<EstuaryObject> streamObjects = stream.getObjectsToDraw();
		
		//Paint Background and bounds
		g.setColor(Color.BLUE);
		Stroke temp = g.getStroke();
		g.setStroke(new BasicStroke(100f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		g.draw(stream.getStreamCurve());
		g.setStroke(temp);
		
		//Paint objects
		for(EstuaryObject obj : streamObjects) {
			Image img = null;
			if (obj.getType() == "Algae") {
				img = imgAlgae;
			} else if(obj.getType() == "Clam") {
				img = imgClam;
			} else if(obj.getType() == "Hazard Waste"){
				img = imgTrashBag;
			} else if(obj.getType() == "Fish"){
				img = imgFish;
			} else if(obj.getType() == "LilyPad"){
				img = imgLilyPad;
			} else{
				img = imgMussel;
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
			if (obj.getType() == "Algae") {
				img = imgAlgae;
			} else if(obj.getType() == "Clam") {
				img = imgClam;
			} else if(obj.getType() == "Hazard Waste"){
				img = imgTrashBag;
			} else if(obj.getType() == "Fish"){
				img = imgFish;
			} else if(obj.getType() == "LilyPad"){
				img = imgLilyPad;
			} else{
				img = imgMussel;
			}
			g.drawImage(img,
					obj.getBounds().x, 
					obj.getBounds().y, 
					obj.getBounds().width, 
					obj.getBounds().height,this);
		}
		for(Plant pl: game.getPlants()){
			Image img = null;
			if (pl.getType() == "Tree") {
				img = imgTree;
			}
			else{
				img = imgBush;
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
		Rectangle healthBar = new Rectangle((int)(getSize().width * 0.25), (int)(getSize().height * 0.85), 500, 80);
		Rectangle trash = new Rectangle(game.getTrashCan().getBounds());
		int health = game.getHealth();
		
		// health bar gray background
		g2d.setColor(Color.GRAY);
		g2d.setStroke(new BasicStroke(10f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		g2d.drawRoundRect(healthBar.x, healthBar.y, healthBar.width, healthBar.height, 15, 15);
		g2d.fillRoundRect(healthBar.x, healthBar.y, healthBar.width, healthBar.height, 15, 15);
		
		// health bar colored foreground
		if (health < 30) {
			g2d.setColor(Color.RED);
		}
		else if (health < 70) {
			g2d.setColor(Color.YELLOW);
		}
		else {
			g2d.setColor(Color.GREEN);
		}
		g2d.fillRoundRect(healthBar.x, healthBar.y, health * 5, healthBar.height, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Purisa", Font.BOLD, 22));
		g2d.drawString("Health: " + health + "    Points: " +game.getPoints(), healthBar.x + 140, healthBar.y + 50);
		
		g2d.drawImage(imgTrash, trash.x, trash.y, trash.width, trash.height,this);
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
			} else if (game.getGameState().equals(GameState.UPGRADE_STATE)) {
				upgradesPanel.setVisible(true);
				repaint();
			}
		}
	}
}
