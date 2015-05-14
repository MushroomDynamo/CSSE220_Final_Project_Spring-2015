
public class objectHero extends objectMovable {
	
	protected int tickActionInterval = 200;

	
	public objectHero(int x,int y) {
		Digger.Hero = this;
		Digger.insertObjectIntoTickableRegistry(this);
		this.xPos = x;
		this.yPos = y;
	}
	

	
	

}
