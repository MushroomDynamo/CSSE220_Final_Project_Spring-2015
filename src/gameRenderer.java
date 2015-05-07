import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class gameRenderer extends JPanel {
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (int i=0;i<gameGrid.yGrid.size();i++) {
			ArrayList<objectDrawable> xGrid = gameGrid.yGrid.get(i);
			for (int j=0;j<xGrid.size();j++) {
				String objectType = xGrid.get(j).getObjectType();
				if (objectType == "null") {
					g2d.setColor(Color.black);
					g2d.fillRect(i*32, j*32, (i*32)+32, (j*32)+32);
				}
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
}
