import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class Digger extends JFrame {
	
	public static JFrame gameFrame = new JFrame("Digger");
	public static gameRenderer gameRenderer = new gameRenderer();
	public static objectHero Hero;
	private static String[] levelList = {"test_level.txt","test_level_2.txt"};
	private static int levelPosition = 0;
	public static moneyBag moneyBag;
	public static int gameWidth = 20;
	public static int gameHeight = 15;
	private static ArrayList<Object> tickableRegistry = new ArrayList<Object>();
	public static int frameInterval = 50;
	
	static gameClock gameClock = new gameClock();
	static Thread gameClockThread = new Thread(gameClock);
	
	//Texture fields
	public static BufferedImage dirtImage;
	public static BufferedImage emeraldImage;
	//End texture fields
	
	public Digger() {
		
		//Load textures
		String texturepath;
		try {
			texturepath = new java.io.File(".").getCanonicalPath();
			texturepath = texturepath + "\\texture\\";
			dirtImage = ImageIO.read(new File(texturepath + "dirt.png"));
			emeraldImage = ImageIO.read(new File(texturepath + "emerald.png"));
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
					//if (Hero.checkForCollisionAtCoordinate(Hero.xPos+1,Hero.yPos) == false) {
					Hero.shiftToCoordinate(Hero.xPos+1,Hero.yPos,"hero");
//				} else {
//					System.out.println("blargh");
//				}
					
			}
		});
		gameRenderer.getActionMap().put("left", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//if (Hero.checkForCollisionAtCoordinate(Hero.xPos-1,Hero.yPos) == false) {
					Hero.shiftToCoordinate(Hero.xPos-1,Hero.yPos,"hero");
//				} else {
//					System.out.println("blargh");
//				}
			}
		});
		gameRenderer.getActionMap().put("up", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//if (Hero.checkForCollisionAtCoordinate(Hero.xPos,Hero.yPos-1) == false) {
					Hero.shiftToCoordinate(Hero.xPos,Hero.yPos-1,"hero");
//				} else {
//					System.out.println("blargh");
//				}
			}
		});
		gameRenderer.getActionMap().put("down", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//if (Hero.checkForCollisionAtCoordinate(Hero.xPos,Hero.yPos+1) == false) {
					Hero.shiftToCoordinate(Hero.xPos,Hero.yPos+1,"hero");
//				} else {
//					System.out.println("blargh");
//				}
			}
		});
		gameRenderer.getActionMap().put("advance_level", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				levelPosition = levelPosition + 1;
				//gameClockThread.interrupt();
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
		gameRenderer.getActionMap().put("attack", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("DIE!");
				//implement an attacking animation
			}
		});
	}

	public static void main(String args[]) {
		
		new Digger();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameGrid.instantiateGameGrid(gameFrame,gameWidth,gameHeight);
		
		gameFrame.getContentPane().add(gameRenderer);
		gameRenderer.setPreferredSize(new Dimension(640,480));
		gameFrame.pack();
		gameFrame.setVisible(true);
		
		levelManager.readLevelFile("test_level.txt");
		gameClockThread.start();
		
		while (true) {
			gameFrame.repaint();
			try {
				Thread.sleep(frameInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean insertObjectIntoTickableRegistry(Object object) {
		tickableRegistry.add(object);
		return true;
	}
	
	public static ArrayList<Object> dumpTickableRegistry() {
		return tickableRegistry;
	}
	
	public static boolean clearTickableRegistry() {
		for (int i=1;i<tickableRegistry.size();i++) {
			int[] objectCoordinates = ((objectMonster) tickableRegistry.get(i)).returnCoordinates();
			//Figure out better casting method later
			gameGrid.yGrid.get(objectCoordinates[1]).get(objectCoordinates[0]).setObjectType("null");
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
	
}
