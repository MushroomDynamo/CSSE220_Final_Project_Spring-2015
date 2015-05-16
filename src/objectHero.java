
public class objectHero extends objectMovable {
	
	@SuppressWarnings("hiding")
	protected int tickActionInterval = 200; //Movement speed


	
	public objectHero(int x,int y) {
		Digger.Hero = this;
		Digger.insertObjectIntoTickableRegistry(this);
		this.xPos = x;
		this.yPos = y;
	}

	@Override
	public int returnTickActionInterval() {
		return this.tickActionInterval;
	}

}
