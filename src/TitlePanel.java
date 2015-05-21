import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class TitlePanel extends JPanel{
	
 private BufferedImage pic; 
 
 public TitlePanel() {
    this.pic = Digger.titlescreen1Image;
  }

  @Override
  protected void paintComponent(Graphics g) {
	  Graphics2D g2d = (Graphics2D) g;
      super.paintComponent(g);
      g2d.drawImage(this.pic, 0, 0, null); // see javadoc for more info on the parameters            
  }

}
