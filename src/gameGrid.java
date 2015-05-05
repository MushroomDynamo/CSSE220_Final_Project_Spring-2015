import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;

public class gameGrid {
	ArrayList<Object> yGrid = new ArrayList<Object>();
	
	public gameGrid(JFrame gameFrame,int width, int height) {
		for (int i=0;i<height;i++) {
			ArrayList<objectDrawable> xGrid = new ArrayList<objectDrawable>();
			yGrid.add(xGrid);
			for (int j=0;j<width;j++) {
				xGrid.add(new objectDrawable("null"));
			}
		}
	}
}
