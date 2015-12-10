package views;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import controllers.GameViewController;
import objects.EstuaryObject;
import objects.Fish;
import objects.Plant;
import swampgod.Estuary;
import swampgod.Game;
import swampgod.Main.GameState;
import swampgod.Stream;

public class GameView extends JPanel implements Observer{

	private static final long serialVersionUID = 11082015;
	protected Game game;
	static int panelWidth = 960, panelHeight = 640;
	protected JPanel upgradesPanel;
	protected JPanel controlPanel;
	protected JPanel dialogPanel;
	protected JPanel tutorialPanel;
	protected JTextPane dialogMessage;
	protected JButton btnBush;
	protected JButton btnTree;
	protected Graphics graphics;
	
	protected static Image imgAlgae;
	protected static Image imgClam;
	protected static Image imgTrash;
	protected static Image imgTrashBag;
	protected static Image imgMussel;
	protected static Image imgBackground;
	protected static Image imgTree;
	protected static Image imgBush;
	protected static Image imgTreeUpgrade;
	protected static Image imgBushUpgrade;
	protected static Image imgFish;
	protected static Image imgLilyPad;
	protected static Image imgItemScreen;
	protected static Image imgRiverDirt;

	public GameView() {
		System.out.println("GameView() initialized");
		loadView();
		setName("GameView");
		setBackground(new Color (36,228,149));
		setLayout(null);
		setVisible(false);
		
		/**
		 * At this point we have already added the view controller
		 * to this instance (refer back to ViewController).
		 * 
		 * Since the TitleViewController is technically a mouse listener,
		 * we must get the mouse listener from this instance and pass the 
		 * ActionEvent to the TitleViewController that a button has 
		 * been clicked.
		 */
		tutorialPanel = new JPanel();
		tutorialPanel.setVisible(false);
		
		controlPanel = new JPanel();
		controlPanel.setBackground(Color.BLUE);
		controlPanel.setVisible(false);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameViewController) getMouseListeners()[0]).buttonClicked(e);
			}
		});
		
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameViewController) getMouseListeners()[0]).buttonClicked(e);
			}
		});
		
		JButton btnUpgrade = new JButton("Upgrade");
		btnUpgrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameViewController) getMouseListeners()[0]).buttonClicked(e);
			}
		});
		
		JButton btnWin = new JButton("Win");
		btnWin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameViewController) getMouseListeners()[0]).buttonClicked(e);
			}
		});
		
		JButton btnLose = new JButton("Lose");
		btnLose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameViewController) getMouseListeners()[0]).buttonClicked(e);
			}
		});

		controlPanel.add(btnStart, BorderLayout.PAGE_START);
		controlPanel.add(btnPause, BorderLayout.PAGE_START);
		controlPanel.add(btnUpgrade, BorderLayout.PAGE_START);
		controlPanel.add(btnWin, BorderLayout.PAGE_START);
		controlPanel.add(btnLose, BorderLayout.PAGE_START);
		
		upgradesPanel = new JPanel();
		upgradesPanel.setSize(300,100);
		upgradesPanel.setBackground(Color.GREEN);
		upgradesPanel.setVisible(true);
		
		ImageIcon btnBush2 = new ImageIcon(imgBushUpgrade);
	    btnBush = new JButton (btnBush2);
	    btnBush.setName("AddBush");
	    btnBush.setPreferredSize(new Dimension(imgBushUpgrade.getWidth(btnBush),
	    		imgBushUpgrade.getHeight(btnBush)));
		btnBush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameViewController) getMouseListeners()[0]).buttonClicked(e);
			}
		});
		
		ImageIcon btnTree2 = new ImageIcon(imgTreeUpgrade);
	    btnTree = new JButton (btnTree2);
	    btnTree.setName("AddTree");
	    btnTree.setPreferredSize(new Dimension(50,50));
		btnTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameViewController) getMouseListeners()[0]).buttonClicked(e);
			}
		});
		
		JButton btnFish = new JButton("Collect Fish");
		btnFish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameViewController) getMouseListeners()[0]).buttonClicked(e);
			}
		});
		
		JLabel lblStage = new JLabel("Upgrades");
		
		upgradesPanel.add(lblStage, BorderLayout.PAGE_START);
		upgradesPanel.add(btnBush);
		upgradesPanel.add(btnTree);
		upgradesPanel.add(btnFish, BorderLayout.PAGE_END);
		
		dialogPanel = new JPanel(new BorderLayout());
		dialogPanel.setBackground(new Color(0,0,0,0));
		dialogPanel.setVisible(false);
		dialogPanel.setPreferredSize(new Dimension(300, 100));
		dialogMessage = new JTextPane();
		dialogMessage.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				//e.getComponent().removeMouseListener(this);
				dialogPanel.setVisible(false);
				upgradesPanel.setVisible(true);
				((GameViewController) getMouseListeners()[0]).finishTutorial();
			}
		});
		dialogMessage.setBounds(dialogPanel.getBounds());
		dialogMessage.setEditable(false);
		SimpleAttributeSet as = new SimpleAttributeSet();
		StyleConstants.setAlignment(as,
		                    StyleConstants.ALIGN_CENTER);
		dialogMessage.setParagraphAttributes(as,true);
		
		dialogPanel.add(dialogMessage, BorderLayout.CENTER);
		
		add(controlPanel);
		add(upgradesPanel);
		add(dialogPanel);
		add(tutorialPanel);
	}
	
	public void setGame(Game update) {
		this.game = update;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public void setGraphics(Graphics g){
		graphics = g;
		rescale();
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
			imgTree = ImageIO.read(new File("pics/Tree.png"));
			imgBush = ImageIO.read(new File("pics/Bush.png"));
			imgTreeUpgrade = ImageIO.read(new File("pics/TreeUpgrade.png"));
			imgBushUpgrade = ImageIO.read(new File("pics/BushUpgrade.png"));
			imgBackground = ImageIO.read(new File("pics/newBG.png"));
			imgItemScreen = ImageIO.read(new File("pics/itemScreen.png"));
			imgRiverDirt = ImageIO.read(new File("pics/riverDirt.png"));
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void rescale() {
		Image newImg;
		imgBackground = imgBackground.getScaledInstance(getSize().width, getSize().height, Image.SCALE_SMOOTH);
		imgRiverDirt = imgRiverDirt.getScaledInstance(getSize().width, getSize().height, Image.SCALE_SMOOTH);
		int xunit = getSize().width / 10;
		int yunit = getSize().height/10;
		btnBush.setPreferredSize(new Dimension(xunit, xunit));
		btnBush.setIcon(new ImageIcon(imgBushUpgrade.getScaledInstance(xunit, xunit, Image.SCALE_SMOOTH)));
		btnTree.setPreferredSize(new Dimension(xunit, xunit));
		btnTree.setIcon(new ImageIcon(imgTreeUpgrade.getScaledInstance(xunit, xunit, Image.SCALE_SMOOTH)));
		controlPanel.setBounds(200, 0, 500, 60);
		upgradesPanel.setBounds(getSize().width-xunit,(yunit*3),xunit,(xunit * 3));
	}
	
	enum DialogType{TUTORIAL, ALERT};
	/**
	 * Present a dialog box on top of the view with an okay button to remove.
	 * @param bounds - Rectange bounds of the dialog box
	 * @param toComponent - The component to focus upon
	 * @param type - Tutorial or Alert
	 * @param message - The text to display in the box
	 */
	public void displayDialog(Rectangle bounds, String toComponent, DialogType type, String message) {
		if (type.equals(DialogType.TUTORIAL)) {
			bounds = this.getBounds();
			bounds.grow(-50, -50);
			Font f = new Font("Arial", Font.BOLD, 20);
			setJTextPaneFont(dialogMessage, f, Color.BLACK);
		}
		dialogPanel.setBounds(bounds);
		if (message != null) {
			dialogMessage.setText(message);
		}
		upgradesPanel.setVisible(false);
		dialogPanel.setVisible(true);
	}
	
	/**
     * Utility method for setting the font and color of a JTextPane. The
     * result is roughly equivalent to calling setFont(...) and 
     * setForeground(...) on an AWT TextArea.
     */
    public static void setJTextPaneFont(JTextPane jtp, Font font, Color c) {
        // Start with the current input attributes for the JTextPane. This
        // should ensure that we do not wipe out any existing attributes
        // (such as alignment or other paragraph attributes) currently
        // set on the text area.
        MutableAttributeSet attrs = jtp.getInputAttributes();

        // Set the font family, size, and style, based on properties of
        // the Font object. Note that JTextPane supports a number of
        // character attributes beyond those supported by the Font class.
        // For example, underline, strike-through, super- and sub-script.
        StyleConstants.setFontFamily(attrs, font.getFamily());
        StyleConstants.setFontSize(attrs, font.getSize());
        StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0);
        StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);

        // Set the font color
        StyleConstants.setForeground(attrs, c);

        // Retrieve the pane's document object
        StyledDocument doc = jtp.getStyledDocument();

        // Replace the style for the entire document. We exceed the length
        // of the document by 1 so that text entered at the end of the
        // document uses the attributes.
        doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, false);
    }
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(imgBackground, 0, 0, getSize().width, getSize().height, null);
	}
	
	public void paintSwamp() {
		drawView(graphics);
	}
	
	protected void drawView(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		BufferedImage combinedImage = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);

		Graphics2D gci = combinedImage.createGraphics();
		
		paintBackground(gci);
		Stream[] streams = game.getStreams();
		for (Stream stream : streams) {
			paintStream(stream, gci);
		}
		paintEstuary(game.getEstuary(), gci);
		paintPlacePlant(gci);
		paintHUD(gci);

		Area outside = new Area(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
		if (controlPanel.isVisible()) {outside.subtract(new Area(controlPanel.getBounds()));}
		if (upgradesPanel.isVisible()) {outside.subtract(new Area(upgradesPanel.getBounds()));}
		if (tutorialPanel.isVisible()) {outside.subtract(new Area(tutorialPanel.getBounds()));}
		if (dialogPanel.isVisible())
		{
			outside.subtract(new Area(dialogPanel.getBounds()));
			paintDialog(gci);
			//repaint(dialogPanel.getBounds());
		}

		g2d.setClip(outside);
		g2d.drawImage(combinedImage, 0, 0, null);

		gci.dispose();
		g2d.dispose();
	}
	
	private void paintDialog(Graphics2D g) {
		Rectangle r = dialogPanel.getBounds();
		r.grow(10, 20);
		r.setSize(r.width, r.height+10);
		Shape bg = new RoundRectangle2D.Double(r.x,r.y,r.width,r.height,30,30);
		g.setColor(Color.WHITE);
		
		GradientPaint grad = new GradientPaint(
				0, r.y+r.height-30,Color.WHITE,
				0, r.y+r.height,Color.GRAY);
		g.setPaint(grad);
		g.setClip(bg);
		g.fill(bg);
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		g.draw(bg);
	}
	
	private void paintBackground(Graphics2D g){
		float opacity = 1.0f - (game.getHealth() / (float) 100);
		if (opacity < 0) {
			opacity = 0;
		}
		g.drawImage(imgBackground, 0, 0, getSize().width, getSize().height, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g.drawImage(imgRiverDirt, 0, 0, getSize().width, getSize().height, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	}

	private void paintStream(Stream stream, Graphics2D g) {
		ArrayList<EstuaryObject> streamObjects = stream.getObjectsToDraw();
		
		//Paint Background and bounds
		g.setColor(Color.BLUE);
		//Stroke temp = g.getStroke();
		//g.setStroke(new BasicStroke(100f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		//g.draw(stream.getStreamCurve());
		//g.setStroke(temp);
		
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
		
		/**
		 * Draw all the plants
		 */
		for(Plant pl: game.getPlants()){
			Image img = null;
			if (pl.getType() == "Tree") {
				img = imgTree;
			}
			else{
				img = imgBush;
			}
			g.drawImage(img,
					pl.getBounds().x, 
					pl.getBounds().y, 
					pl.getBounds().width, 
					pl.getBounds().height,null);
		}
	}
	
	private void paintPlacePlant(Graphics2D g){
		/**
		 * Draw the plant after click. Will follow pointer and paint a circle around the plant
		 * showing the eat radius.
		 */
		if (game.getChosenPlant() != null) {
			Image img = null;
			Plant pl = game.getChosenPlant();
			if (pl.getType() == "Tree") {
				img = imgTree;
			}
			else{
				img = imgBush;
			}
			
			if (pl.canPlace()) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.RED);
			}
			g.draw(pl.getRadiusShape());
			g.drawImage(img,
					pl.getBounds().x, 
					pl.getBounds().y, 
					pl.getBounds().width, 
					pl.getBounds().height,null);
		}
	}
	
	
	private void paintEstuary(Estuary estuary, Graphics2D g) {
		ArrayList<EstuaryObject> estuaryObjects = estuary.getObjectsToDraw();
		
		Rectangle bounds = estuary.getBounds();
		int x = bounds.x, y = bounds.y, width = bounds.width, height = bounds.height;
		
		//Paint Background and bounds
		g.setColor(Color.blue);
		//g.fillRect(x, y, width, height);
		
		for(EstuaryObject obj : estuaryObjects) {
			Image img = null;
			if(obj.getType() == "Fish"){
				img = imgFish;
			if(((Fish)obj).getDir()==1){
				g.drawImage(img,
						obj.getBounds().x, 
						obj.getBounds().y, 
						-40, 
						40,this);
			}
			else{
			g.drawImage(img,
					obj.getBounds().x, 
					obj.getBounds().y, 
					40, 
					40,this);
			}
			}
		}
	}
	
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
		g2d.setFont(new Font("Arial", Font.BOLD, 20));
		g2d.drawString("Health: " + health + "  Points: " +game.getPoints() + "   Fish: " + game.getFishCount(), healthBar.x + 140, healthBar.y + 50);
		
		g2d.drawImage(imgTrash, trash.x, trash.y, trash.width, trash.height,this);
		
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Arial", Font.BOLD, 14));
		g2d.drawString("Level " + (game.getWaveNumber()+1), 100, 20);
	}
	
	private static BufferedImage dye(BufferedImage image, Color color)
    {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dyed = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, 0,0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0,0,w,h);
        g.dispose();
        return dyed;
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
				imgItemScreen = imgBackground;
			} else if (game.getGameState().equals(GameState.UPGRADE_STATE)) {
				paintSwamp();
			} else if (game.getGameState().equals(GameState.NEXTWAVE_STATE)) {
				System.out.println("GameView: NEXT WAVE");
				upgradesPanel.setVisible(true);
				paintSwamp();
				game.setGameState(GameState.RUNNING_STATE);
			} else {
				System.out.println("DO NOTHING");
			}
		}
	}
}
