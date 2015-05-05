import java.awt.*;
import java.util.Random;
import javax.swing.*;

//TODO: This is tutorial code. Replace/heavily modify it because plagiarism is real bad.
//For reference: http://zetcode.com/tutorials/javaswingtutorial/painting/

public class gameRenderer extends JPanel {
	 private void doDrawing(Graphics g) {
		 	//Do drawing stuff in here!
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setColor(Color.blue);
	        for (int i = 0; i <= 1000; i++) {
	            Dimension size = getSize();
	            Insets insets = getInsets();
	            int w = size.width - insets.left - insets.right;
	            int h = size.height - insets.top - insets.bottom;
	            Random r = new Random();
	            int x = Math.abs(r.nextInt()) % w;
	            int y = Math.abs(r.nextInt()) % h;
	            g2d.drawLine(x, y, x, y);
	        }
	    }
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        doDrawing(g);
	    }
}
