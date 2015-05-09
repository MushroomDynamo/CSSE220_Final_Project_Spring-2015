
public class objectHero extends objectMovable {
	
	public boolean dead;
	
	public objectHero(int x,int y) {
		Digger.Hero = this;
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
