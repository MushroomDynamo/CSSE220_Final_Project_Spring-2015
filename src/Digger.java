import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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
	private static int gameWidth = 20;
	private static int gameHeight = 15;
	
	public Digger() {

	    gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
	    gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
	    gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
	    gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
	    gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_U, 0), "advance_level");
	    gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "regress_level");
	    gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "attack");
	    
	    
	    gameRenderer.getActionMap().put("attack", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("DIE!");
               //implement an attacking animation
            }
        });
        gameRenderer.getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (Hero.checkForCollisionAtCoordinate(Hero.xPos+1,Hero.yPos) == false) {
					Hero.shiftToCoordinate(Hero.xPos+1,Hero.yPos,"hero");
//				} else {
//					System.out.println("blargh");
//				}
					
            }
        });
        gameRenderer.getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (Hero.checkForCollisionAtCoordinate(Hero.xPos-1,Hero.yPos) == false) {
					Hero.shiftToCoordinate(Hero.xPos-1,Hero.yPos,"hero");
//				} else {
//					System.out.println("blargh");
//				}
            }
        });
        gameRenderer.getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (Hero.checkForCollisionAtCoordinate(Hero.xPos,Hero.yPos-1) == false) {
					Hero.shiftToCoordinate(Hero.xPos,Hero.yPos-1,"hero");
//				} else {
//					System.out.println("blargh");
//				}
            }
        });
        gameRenderer.getActionMap().put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (Hero.checkForCollisionAtCoordinate(Hero.xPos,Hero.yPos+1) == false) {
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
            	levelManager.readLevelFile(levelList[levelPosition]);
            }
        });
        gameRenderer.getActionMap().put("regress_level", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	levelPosition = levelPosition - 1;
            	levelManager.readLevelFile(levelList[levelPosition]);
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
		
		while (true) {
			gameFrame.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
