import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

public class Digger extends JFrame {

	public static void main(String args[]) {
		JFrame gameFrame = new JFrame("Digger");
		gameGrid.instantiateGameGrid(gameFrame,15,20);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameRenderer gameRenderer = new gameRenderer();
		gameFrame.getContentPane().add(gameRenderer);
		gameRenderer.setPreferredSize(new Dimension(640,480));
		gameFrame.pack();
		gameFrame.setVisible(true);
	}
	
}
