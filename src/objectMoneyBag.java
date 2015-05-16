
public class objectMoneyBag extends objectMovable {
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
	
//	public boolean willBreak(int x,int y){
//		if(fall(x,y)==true && fall(x,y+1)==true){
//			return true;
//		}
//			return false;
//	
//	}
//	public boolean fall(int x,int y){
//		if(checkCoordinate(x, y+1)=="dirt" ||  checkCoordinate(x, y+1)=="emerald" || checkCoordinate(x, y+1)=="moneyBag"){
//			return false;
//		}
//			return true;
//		
//	}
}

