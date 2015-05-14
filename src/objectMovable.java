
public class objectMovable {
	
	protected int tickActionInterval = Digger.frameInterval;
	protected int xPos;
	protected int yPos;
	
	
	public boolean checkForCollisionAtCoordinate(int x_position,int y_position) {
		if (gameGrid.yGrid.get(y_position).get(x_position).getObjectType() != "null") {
			return true;
		} 
			return false;
		
	}
	public String checkCoordinate(int x_position,int y_position) {
		return gameGrid.yGrid.get(y_position).get(x_position).getObjectType(); 	
	}
	
	public void shiftToCoordinate(int x_position,int y_position,String objectType) {
		gameGrid.yGrid.get(this.yPos).get(this.xPos).setObjectType("null");
		if(x_position <0){
		this.xPos = Digger.gameWidth -1;
		} else if (x_position >= Digger.gameWidth ) {
			this.xPos = 0;
		} else {
			this.xPos = x_position;
		}
		if (y_position < 0) {
			this.yPos = Digger.gameHeight - 1;
		} else if (y_position > Digger.gameHeight -1) {
			this.yPos = 0;
		} else {
			this.yPos = y_position;
		}
		gameGrid.yGrid.get(this.yPos).get(this.xPos).setObjectType(objectType);
	}
	
	public int returnTickActionInterval() {
		return this.tickActionInterval;
	}
	
	public int[] returnCoordinates() {
		int[] coordinatePair = {this.xPos,this.yPos};
		return coordinatePair;
	}
	
}
