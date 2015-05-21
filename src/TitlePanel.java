import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class TitlePanel extends JPanel{
	
 private BufferedImage pic; 
 
 public TitlePanel() {
    this.pic = Digger.titlescreen1Image;
    this.setLayout(null);
  }

  @Override
  protected void paintComponent(Graphics g) {
	//  Graphics2D g2d = (Graphics2D) g;
	  
      super.paintComponent(g);
      draw(g); // see javadoc for more info on the parameters            
  }
  
  public void draw(Graphics g){
	  g.drawImage(this.pic, 0, 0, null);
  }


}
