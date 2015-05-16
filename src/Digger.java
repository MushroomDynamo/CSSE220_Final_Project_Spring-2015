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
	
	public static int seed = 24367599;
	
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
	
	static gameClock gameClock = new gameClock();
	static Thread gameClockThread = new Thread(gameClock);
	
	public static String bufferedAction = "null";
	
	//Texture fields
	public static BufferedImage dirtImage;
	public static BufferedImage emeraldImage;
	public static BufferedImage moneybagImage;
	public static BufferedImage goldImage;
	public static BufferedImage moneybagspaceImage;
	public static BufferedImage playerImage;
	public static BufferedImage monsterImage;
	public static BufferedImage monster2Image;
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
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_U, 0), "advance_level");
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "regress_level");
		gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "attack");

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
				//gameClockThread.interrupt();
				tickableRegistry.clear();
				levelManager.readLevelFile(levelList[levelPosition]);
				//gameClockThread.start();
			}
		});
		gameRenderer.getActionMap().put("attack", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				bufferedAction = "attack";
				//System.out.println("DIE!");
				//implement an attacking animation
//				for(int i = 0; i < tickableRegistry.size(); i++){
//					
//				
//					Object objectToTick = dumpTickableRegistry().get(i);
//					if (objectToTick instanceof objectMonster){
//					int[] heroCoordinates = returnHeroCoordinates();
//					int[] monsterCoordinates = ((objectMonster) objectToTick).returnCoordinates();
//					
//					
//						if(facing == "right" && heroCoordinates[0] == monsterCoordinates[0]-1 && heroCoordinates[1] == monsterCoordinates[1]){
//							System.out.println("Yay");
//						}else if(facing == "left"){
//							//
//						}else if(facing == "up"){
//							//
//						}else if(facing == "down"){
//							//
//						}
//					
//
//				}
//					}
		
		
			}		
		});
		
	}

	

	public static void main(String args[]) {
		
		new Digger();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameGrid.instantiateGameGrid(gameFrame,gameWidth,gameHeight);
		
		
		
		Container pane = gameFrame.getContentPane();
		pane.setLayout(new BorderLayout());
		pane.add(gameRenderer, BorderLayout.CENTER);
		pane.add(menu, BorderLayout.NORTH);
		
		//gameFrame.getContentPane().add(gameRenderer);
		gameRenderer.setPreferredSize(new Dimension(640,480));
		gameFrame.pack();
		gameFrame.setVisible(true);
		
		levelManager.readLevelFile("lvl0.txt");
		gameClockThread.start();
		
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
			
			
			
//			if(getHeroLives()<0){
//				gameClockThread.stop();
//				System.out.println("You are dead.");
//				gameFrame.setVisible(false);
//				gameFrame.dispose();
//				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				
//				break;
//			}
		}
		gameClockThread.stop();
		System.out.println("You are dead.");
		gameFrame.setVisible(false);
		gameFrame.dispose();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public int returnLevel(){
		return levelPosition;
	}
	
	public static void closeGame(){
		shutdown = true;
	 	}

	public static boolean insertObjectIntoTickableRegistry(Object object) {
		tickableRegistry.add(object);
		return true;
	}
	
	public static ArrayList<Object> dumpTickableRegistry() {
		return tickableRegistry;
	}
	
	public static boolean clearTickableRegistry() {
		for (int i=0;i<tickableRegistry.size();i++) {
			if (tickableRegistry.get(i) instanceof objectMonster) {
				int[] objectCoordinates = ((objectMonster) tickableRegistry.get(i)).returnCoordinates();
				//Figure out better casting method later
				gameGrid.yGrid.get(objectCoordinates[1]).get(objectCoordinates[0]).setObjectType("null");
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
