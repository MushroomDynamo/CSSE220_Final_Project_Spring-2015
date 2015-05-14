
public class objectMoneyBag extends objectMovable {
	
	public objectMoneyBag(int x,int y) {
		Digger.insertObjectIntoTickableRegistry(this);
		this.xPos = x;
		this.yPos = y;
	}
}
