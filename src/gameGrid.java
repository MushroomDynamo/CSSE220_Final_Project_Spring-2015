import java.util.ArrayList;

import javax.swing.JFrame;

public abstract class gameGrid {
	
	static ArrayList<ArrayList<objectDrawable>> yGrid = new ArrayList<ArrayList<objectDrawable>>();
	
	//Set up an ArrayList of ArrayLists to handle game coordinates
	public static void instantiateGameGrid(JFrame gameFrame,int width,int height) {
		for (int i=0;i<height;i++) {
			ArrayList<objectDrawable> xGrid = new ArrayList<objectDrawable>();
			yGrid.add(xGrid);
			for (int j=0;j<width;j++) {
				xGrid.add(new objectDrawable("null"));
			}
		}
	}
}
