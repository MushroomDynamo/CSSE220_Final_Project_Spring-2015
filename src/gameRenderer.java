import java.awt.*;
import java.io.FileReader;
import java.io.Reader;
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
					g2d.fillRect(j*32, i*32, (j*32)+32, (i*32)+32);
				} else if (objectType == "ground") {
					g2d.drawImage(Digger.dirtImage,j*32,i*32,Color.orange,null);
				} else if (objectType == "emerald") {
					g2d.drawImage(Digger.emeraldImage,j*32,i*32,Color.green,null);
				} else if (objectType == "hero") {
					g2d.setColor(Color.magenta);
					g2d.fillRect(j*32, i*32, (j*32)+32, (i*32)+32);
				} else if (objectType == "monster") {
					g2d.setColor(Color.red);
					g2d.fillRect(j*32, i*32, (j*32)+32, (i*32)+32);
				} else if (objectType == "moneybag") {
					g2d.drawImage(Digger.moneybagImage,j*32,i*32,Color.yellow,null);
				} else if (objectType == "gold") {
					g2d.drawImage(Digger.goldImage,j*32,i*32,Color.yellow,null);
				} else if (objectType == "moneybag_lethal") {
					g2d.drawImage(Digger.moneybagspaceImage,j*32,i*32,Color.yellow,null);
				}
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
}
