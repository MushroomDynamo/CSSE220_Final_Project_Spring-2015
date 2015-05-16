
public class objectHero extends objectMovable {
	
	protected int tickActionInterval = 300;
	private int lives;
	private boolean dead;

	
	public objectHero(int x,int y) {
		Digger.Hero = this;
		Digger.insertObjectIntoTickableRegistry(this);
		this.xPos = x;
		this.yPos = y;
	}

	public int returnTickActionInterval() {
		return this.tickActionInterval;
	}
	
//	public void setHeroDead(boolean state){
//		this.dead = state;
//	}
//	
//	public void removeHeroLives(){
//		this.lives = this.lives - 1;
//	}
//	
//	public boolean getHeroDead(){
//		return this.dead;
//	}
//	
//	public int getHeroLives(){
//		return this.lives;
//	}

}
