
public class moneyBag extends objectMovable {
	
	public moneyBag(int x,int y) {
		//Digger.moneyBag = this;
		this.xPos = x;
		this.yPos = y;
	}
	
	public boolean willBreak(int x,int y){
		if(fall(x,y)==true && fall(x,y+1)==true){
			return true;
		}
			return false;
	}
	
	public boolean fall(int x,int y){
		if(checkCoordinate(x, y+1)=="dirt" ||  checkCoordinate(x, y+1)=="emerald" || checkCoordinate(x, y+1)=="moneyBag") {
			return false;
		}
			return true;
	}
}
