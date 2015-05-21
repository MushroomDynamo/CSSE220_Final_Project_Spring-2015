import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class Digger extends JFrame {
	
	//Digger (based on the 1980s game of the same name)
	//Authors: Zachary Goldasich, Jaynatha Shankar, Daniel Brodie
	
	//Fixed seed for random movements--ensures that random movements, when compounded with various coordinate pairs, are always the same
	public static int seed = 24367599;
	
	//Set up basic game windows, boolean debounces, and the level list
	//Also handle how fast the game renders/ticks (the two are bound together)
	public static JFrame gameFrame = new JFrame("Digger");
	public static gameRenderer gameRenderer = new gameRenderer();
	public static InfoPanel menu = new InfoPanel();
	public static objectHero Hero;
	private static String[] levelList = {"lvl0.txt","lvl1.txt","lvl2.txt","lvl3.txt","lvl4.txt","lvl5.txt","lvl6.txt","lvl7.txt","lvl8.txt","lvl9.txt"};
	private static int levelPosition = 0;
	public static int gameWidth = 20;
	public static int gameHeight = 15;
	private static ArrayList<Object> tickableRegistry = new ArrayList<Object>();
	public static int frameInterval = 5;
	private static boolean dead;
	private static int lives = 3;
	public static String facing = "down";
	private static boolean shutdown = false;
	private static boolean gameStart = false;
	private static Container pane;
	
	//Set up and initialize game-clock and audio threads
	static gameClock gameClock = new gameClock();
	static gameAudio gameAudio = new gameAudio();
	static Thread gameClockThread = new Thread(gameClock);
	static Thread gameAudioThread = new Thread(gameAudio);
	
	//Store the player's next movement before the next game tick
	public static String bufferedAction = "null";
	
	//Texture fields (due to try/catch blocks being required by Eclipse we can't just load the images right here)
	public static BufferedImage dirtImage;
	public static BufferedImage emeraldImage;
	public static BufferedImage moneybagImage;
	public static BufferedImage goldImage;
	public static BufferedImage moneybagspaceImage;
	public static BufferedImage playerImage;
	public static BufferedImage monsterImage;
	public static BufferedImage monster2Image;
	public static BufferedImage titlescreen1Image;
	public static BufferedImage titlescreen2Image;
	//End texture fields
	
	public Digger() {
		
		//Load textures
		String texturepath;
		try {
			texturepath = new java.io.File(".").getCanonicalPath();
			texturepath = texturepath + "\\texture\\";
			dirtImage = ImageIO.read(new File(texturepath + "dirt.png"));
			emeraldImage = ImageIO.read(new File(texturepath + "emerald.png"));
			moneybagImage = ImageIO.read(new File(texturepath + "moneybag.png"));
			goldImage = ImageIO.read(new File(texturepath + "gold.png"));
			moneybagspaceImage = ImageIO.read(new File(texturepath + "moneybag_space.png"));
			playerImage = ImageIO.read(new File(texturepath + "player.png"));
			monsterImage = ImageIO.read(new File(texturepath + "monster.png"));
			monster2Image = ImageIO.read(new File(texturepath + "monster2.png"));
			titlescreen1Image = ImageIO.read(new File(texturepath + "digger_titlescreen.png"));
			titlescreen2Image = ImageIO.read(new File(texturepath + "digger_titlescreen2.png"));
		} catch (IOException e1) {
			//This is pretty much here just to keep Eclipse happy
			e1.printStackTrace();
		}
		
		//Set things up to store necessary keybinds
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_U, 0), "advance_level");
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "regress_level");
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "attack");
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "pause");
		
		//Set the bufferedAction with certain keybinds, execute actions directly with others
		gameRenderer.getActionMap().put("right", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bufferedAction = "right";
				facing = "right";
			}
		});
		gameRenderer.getActionMap().put("left", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bufferedAction = "left";
				facing = "left";
			}
		});
		gameRenderer.getActionMap().put("up", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bufferedAction = "up";
				facing = "up";
			}
		});
		gameRenderer.getActionMap().put("down", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bufferedAction = "down";
				facing = "down";
			}
		});
		gameRenderer.getActionMap().put("advance_level", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				levelPosition = levelPosition + 1;
				//gameClockThread.interrupt();
				//Hello World
				tickableRegistry.clear();
				levelManager.readLevelFile(levelList[levelPosition]);
				//gameClockThread.start();
			}
		});
		gameRenderer.getActionMap().put("regress_level", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				levelPosition = levelPosition - 1;
				tickableRegistry.clear();
				levelManager.readLevelFile(levelList[levelPosition]);
			}
		});
		gameRenderer.getActionMap().put("attack", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bufferedAction = "attack";
			}		
		});
		gameRenderer.getActionMap().put("pause", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameClock.doGameTicks == false) {
					gameClock.doGameTicks = true;
				} else
					gameClock.doGameTicks = false;
			}
		});
	}
	
	public static void main(String[] args){
		mainMenu mainFrame = new mainMenu();
		
		mainFrame.setSize(600, 600);
		mainFrame.setTitle("Digger - Title Screen");
		mainFrame.setVisible(true);
		
		while(!gameStart){
			try {
				Thread.sleep((long) .001);
			} catch (InterruptedException exception) {
				//Gotta keep that IDE chugging along, more auto-generated catch blocks
				exception.printStackTrace();
			}
		}
		if(gameStart){
			
			System.out.println("Running");
			mainFrame.dispose();
			
			new Digger();
			gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//Construct the game grid that stores all of the necessary information about where to render objects to the screen
			gameGrid.instantiateGameGrid(gameFrame,gameWidth,gameHeight);		
			
			pane = gameFrame.getContentPane();
			pane.setLayout(new BorderLayout());
			pane.add(gameRenderer, BorderLayout.CENTER);
			pane.add(menu, BorderLayout.NORTH);
			
			gameRenderer.setPreferredSize(new Dimension(640,480));
			gameFrame.pack();
			gameFrame.setVisible(true);
			
			//Load the first level
			levelManager.readLevelFile("lvl0.txt");
			gameClockThread.start();
			gameAudioThread.start();
			gameStart = true;
			
			//Re-render the game frame constantly with a framerate of 1/frameInterval fps
			//Break if shutdown boolean is set to true
			while (true) {
				pane.repaint();
				try {
					Thread.sleep(frameInterval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(shutdown == true){
					break;
				}
			}
			//Ran out of lives? No continuing for you, sonny!
			gameClockThread.stop();
			System.out.println("You are dead.");
			gameFrame.setVisible(false);
			gameFrame.dispose();
			gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			shutdown = true;
		}
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameStart = false;
	}
	
	//This method is likely junk
	public static void gameStart() {
		gameStart = true;
	}
	
	//Basic setter/getter methods for sensitive fields and other utility functions
	public int returnLevel(){
		return levelPosition;
	}
	
	public static void closeGame(){
		shutdown = true;
	 	}
	
	//Whenever an object is instantiated it must be inserted into the tickableRegistry ArrayList in order for gameClock to govern over it
	//All tickable objects call this method in their constructor methods in order to cut down on hassle
	public static boolean insertObjectIntoTickableRegistry(Object object) {
		tickableRegistry.add(object);
		return true;
	}
	
	//Expose the tickableRegistry for operation
	public static ArrayList<Object> dumpTickableRegistry() {
		return tickableRegistry;
	}
	
	//Christ, this method was a mess to implement.
	//This basically just clears everything off the tickable registry and clears it from the renderer IF it is governed by gameClock
	public static boolean clearTickableRegistry() {
		for (int i=0;i<gameHeight;i++){
			for (int j=0;j<gameWidth;j++) {
				String objectType = gameGrid.yGrid.get(i).get(j).getObjectType();
				switch (objectType) {
				case "monster": gameGrid.yGrid.get(i).get(j).setObjectType("null");
					break;
				case "monster2": gameGrid.yGrid.get(i).get(j).setObjectType("null");
					break;
				case "hero": gameGrid.yGrid.get(i).get(j).setObjectType("null");
					break;
				case "moneybag": gameGrid.yGrid.get(i).get(j).setObjectType("null");
					break;
				case "moneybag_lethal": gameGrid.yGrid.get(i).get(j).setObjectType("null");
					break;
				default:
					break;
				}
			}
		}
		tickableRegistry.clear();
		return true;
	}
	public static int[] returnHeroCoordinates() {
		int[] coordinatePair = {Hero.xPos,Hero.yPos};
		return coordinatePair;
	}
	public static String[] returnLevelList() {
		return levelList;
	}
	public static int returnLevelPosition() {
		return levelPosition;
	}
	public static int advanceLevelPosition() {
		return levelPosition = levelPosition +1;
	}
	public static int retreatLevelPosition() {
		return levelPosition = levelPosition -1;
	}
	public static void setHeroDead(boolean state){
		dead = state;
	}
	
	public static void removeHeroLives(){
			if(lives>=1){
			lives = lives - 1;}
	}
	
	public static boolean getHeroDead(){
		return dead;
	}
	
	public static int getHeroLives(){
		return lives;
	}
	
}
