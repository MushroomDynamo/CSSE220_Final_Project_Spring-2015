
public class objectMonsterNonDigging extends objectMonster {
	
	protected int tickActionInterval = 100;
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
