import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

public class Digger extends JFrame {

	public static void main(String args[]) {
		JFrame gameFrame = new JFrame("Digger");
		gameFrame.setPreferredSize(new Dimension(640,480));
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.pack();
		gameFrame.setLayout(null);
		
		gameFrame.setVisible(true);
//		Graphics gameRenderer = new Graphics2D();
//		gameFrame.paint(gameRenderer.draw3DRect(64, 64, 64, 64, true));
	}
}
