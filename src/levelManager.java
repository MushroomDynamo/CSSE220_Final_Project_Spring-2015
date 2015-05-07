import java.awt.Color;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public abstract class levelManager {
	
	private static String path;
	
	public static void readLevelFile(String levelName) {
		try {
			path = new java.io.File(".").getCanonicalPath();
			path = path + "\\levels\\" + levelName;
			Reader reader = new FileReader(path);
			for (int i=0;i<gameGrid.yGrid.size();i++) {
				ArrayList<objectDrawable> xGrid = gameGrid.yGrid.get(i);
				for (int j=0;j<xGrid.size();j++) {
					objectDrawable gameObject = xGrid.get(j);
					int nextChar = reader.read();
					switch (nextChar) {
						case 1: nextChar = 48;
							gameObject.setObjectType("null");
							break;
						case 2: nextChar = 49;
							gameObject.setObjectType("not-null");
							break;
						case 3: nextChar = -1;
							break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
