import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class TitlePanel extends JPanel {
	
 private BufferedImage pic; 
 
	public TitlePanel() {
		this.pic = Digger.titlescreen1Image;
 		this.setLayout(null);
	}
	
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(Digger.titlescreen1Image,0,0,1188,450,this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);         
	}
	
}
