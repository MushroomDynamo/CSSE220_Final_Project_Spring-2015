
public class objectHero extends objectMovable {
	
	protected int tickActionInterval = 200;
	public boolean dead;
	
	public objectHero(int x,int y) {
		Digger.Hero = this;
		Digger.insertObjectIntoTickableRegistry(this);
		this.xPos = x;
		this.yPos = y;
	}
	
	public void setHeroDead(boolean state){
		this.dead = state;
	}
	
	public boolean getHeroDead(){
		return this.dead;
	}

}
