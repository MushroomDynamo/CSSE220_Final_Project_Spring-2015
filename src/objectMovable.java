
public class objectMovable {
	
	//The protodermis from which all creation comes!
	//Well, at least all *moving* creations. Has basic methods and fields to govern how objects work.
	
	//Coordinates and how fast the object should tick (in milliseconds)
	//Default setting for tickActionInterval is incredibly fast to remind you to override it
	protected int tickActionInterval = Digger.frameInterval;
	protected int xPos;
	protected int yPos;
	
	//Just a more refined version of checkCoordinate that specifically checks for null spaces
	//Helps cut down on code bloat a little since we have to do this a lot
	public boolean checkForCollisionAtCoordinate(int x_position,int y_position) {
		if (gameGrid.yGrid.get(y_position).get(x_position).getObjectType() != "null") {
			return true;
		} 
			return false;
		
	}
	
	//In case we want to check what kind of object, if any, is at a provided coordinate pair
	public String checkCoordinate(int x_position,int y_position) {
		return gameGrid.yGrid.get(y_position).get(x_position).getObjectType(); 	
	}
	
	//Basic coordinate-shift code to move from one spot to the next
	//Technically could be used for teleporting but never is except for screen-wrapping
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
	
	//Probably want to see how fast this should be moving, shouldn't we?
	public int returnTickActionInterval() {
		return this.tickActionInterval;
	}
	
	//Dump an array with current coordinates for program usage
	public int[] returnCoordinates() {
		int[] coordinatePair = {this.xPos,this.yPos};
		return coordinatePair;
	}
	
}
