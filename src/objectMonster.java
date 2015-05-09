
public class objectMonster extends objectMovable {
	
	public objectMonster(int x,int y) {
		Digger.insertObjectIntoTickableRegistry(this);
		this.xPos = x;
		this.yPos = y;
	}
	
	public int[] returnCoordinates() {
		int[] coordinatePair = {this.xPos,this.yPos};
		return coordinatePair;
	}
}
