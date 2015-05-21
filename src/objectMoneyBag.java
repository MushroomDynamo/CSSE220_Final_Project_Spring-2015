
public class objectMoneyBag extends objectMovable {
	//A bit slow, but the hero PROBABLY wants time to get out of the way, yes?
	protected int tickActionInterval = 500;
	public boolean falling = false;
	
	public objectMoneyBag(int x,int y) {
		Digger.insertObjectIntoTickableRegistry(this);
		this.xPos = x;
		this.yPos = y;
	}
	
	public int returnTickActionInterval() {
		return this.tickActionInterval;
	}
	
	public int[] returnCoordinates() {
		int[] coordinatePair = {this.xPos,this.yPos};
		return coordinatePair;
	}
	
	public void updateTickActionInterval(int interval) {
		this.tickActionInterval = interval;
	}

}

