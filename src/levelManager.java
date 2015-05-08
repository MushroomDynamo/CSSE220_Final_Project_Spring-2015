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
					if (nextChar == 48) {
						gameObject.setObjectType("null");
					} else if (nextChar == 49) {
						gameObject.setObjectType("ground");
					} else if (nextChar == 50) {
						gameObject.setObjectType("emerald");
					} else if (nextChar == 51) {
						gameObject.setObjectType("hero");
					} else if (nextChar == -1) {
						System.out.println("eof");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
