import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class gameRenderer extends JPanel {
	
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (int i=0;i<gameGrid.yGrid.size();i++) {
			ArrayList<objectDrawable> xGrid = gameGrid.yGrid.get(i);
			for (int j=0;j<xGrid.size();j++) {
				String objectType = xGrid.get(j).getObjectType();
				//Just check what stuff is in the grid and paint the necessary stuff to the screen
				if (objectType == "null") {
					g2d.setColor(Color.black);
					g2d.fillRect(j*32, i*32, (j*32)+32, (i*32)+32);
				} else if (objectType == "ground") {
					g2d.drawImage(Digger.dirtImage,j*32,i*32,Color.orange,null);
				} else if (objectType == "emerald") {
					g2d.drawImage(Digger.emeraldImage,j*32,i*32,Color.green,null);
				} else if (objectType == "hero") {
					g2d.drawImage(Digger.playerImage,j*32,i*32,Color.magenta,null);
				} else if (objectType == "monster") {
					g2d.drawImage(Digger.monsterImage,j*32,i*32,Color.red,null);
				} else if (objectType == "moneybag") {
					g2d.drawImage(Digger.moneybagImage,j*32,i*32,Color.yellow,null);
				} else if (objectType == "gold") {
					g2d.drawImage(Digger.goldImage,j*32,i*32,Color.yellow,null);
				} else if (objectType == "moneybag_lethal") {
					g2d.drawImage(Digger.moneybagspaceImage,j*32,i*32,Color.yellow,null);
				} else if (objectType == "monster2") {
					g2d.drawImage(Digger.monster2Image,j*32,i*32,Color.blue,null);
				}
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
}
