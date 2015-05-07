import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

//TODO: This is tutorial code. Replace/heavily modify it because plagiarism is real bad.
//For reference: http://zetcode.com/tutorials/javaswingtutorial/painting/

public class gameRenderer extends JPanel {
	
	 private void doDrawing(Graphics g) {
		 	//Do drawing stuff in here!
	        Graphics2D g2d = (Graphics2D) g;
	        for (int i=0;i<gameGrid.yGrid.size();i++) {
	        	ArrayList<objectDrawable> xGrid = gameGrid.yGrid.get(i);
	        	for (int j=0;j<xGrid.size();j++) {
	        		String objectType = xGrid.get(j).getObjectType();
	        		if (objectType == "null") {
	        			g2d.setColor(Color.black);
	        			g2d.drawRect(i*32, j*32, (i*32)+32, (j*32)+32);
	        		}
	        	}
	        }
	        	
	        	
//	        g2d.setColor(Color.blue);
//	        for (int i = 0; i <= 1000; i++) {
//	            Dimension size = getSize();
//	            Insets insets = getInsets();
//	            int w = size.width - insets.left - insets.right;
//	            int h = size.height - insets.top - insets.bottom;
//	            Random r = new Random();
//	            int x = Math.abs(r.nextInt()) % w;
//	            int y = Math.abs(r.nextInt()) % h;
//	            g2d.drawLine(x, y, x, y);
//	        }
	    }
	 
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        doDrawing(g);
	    }
}
