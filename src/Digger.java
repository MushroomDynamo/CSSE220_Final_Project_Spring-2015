import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.event.*;

import javax.swing.*;

public class Digger extends JFrame {
	
	public static JFrame gameFrame = new JFrame("Digger");
	public static gameRenderer gameRenderer = new gameRenderer();
	public static objectHero Hero;
	private static String[] levelList = {"test_level.txt","test_level_2.txt"};
	private static int levelPosition = 0;
	
	public Digger() {
//		Action rightAction = new AbstractAction(){
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("dogbagels");
//				if (Hero.checkForCollisionAtCoordinate(Hero.xPos+1,Hero.yPos) == false) {
//					Hero.shiftToCoordinate(Hero.xPos+1,Hero.yPos,"hero");
//				}
//			}
//	    };

	    gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "up");
	    gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "left");
	    gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "right");
	    gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "down");
	    gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "advance_level");
	    gameRenderer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "regress_level");
	    
        gameRenderer.getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("dingbats");
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
                System.out.println("dingbats");
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
                System.out.println("dingbats");
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
                System.out.println("dingbats");
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
		//JFrame gameFrame = new JFrame("Digger");
		
		new Digger();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameGrid.instantiateGameGrid(gameFrame,20,15);
		
		//gameRenderer gameRenderer = new gameRenderer();
		gameFrame.getContentPane().add(gameRenderer);
		gameRenderer.setPreferredSize(new Dimension(640,480));
		gameFrame.pack();
		gameFrame.setVisible(true);
		levelManager.readLevelFile("test_level.txt");
		
		//levelManager.readLevelFile("test_level_2.txt");
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
