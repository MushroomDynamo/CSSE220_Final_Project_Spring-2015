
public class objectMonsterNonDigging extends objectMonster {
	
	//These things are FAST. Why? They're dumb as bricks!
	protected int tickActionInterval = 160;
	public int lastAction = 0;
	public boolean foundMovement = false;
	public int movementDirection = 0;
	
	public objectMonsterNonDigging(int x, int y) {
		super(x,y);
	}
	
	public int returnTickActionInterval() {
		return this.tickActionInterval;
	}
	
}
