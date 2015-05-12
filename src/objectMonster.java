
public class objectMonster extends objectMovable {
	
	protected int tickActionInterval = 100;
	
	public objectMonster(int x,int y) {
		Digger.insertObjectIntoTickableRegistry(this);
		this.xPos = x;
		this.yPos = y;
	}
	
	public int[] returnCoordinates() {
		int[] coordinatePair = {this.xPos,this.yPos};
		return coordinatePair;
	}
	
	
	
	public int returnTickActionInterval() {
		return this.tickActionInterval;
	}
}
