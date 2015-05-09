
public class objectMovable {
	
	protected int xPos;
	protected int yPos;
	
	public boolean checkForCollisionAtCoordinate(int x_position,int y_position) {
		if (gameGrid.yGrid.get(y_position).get(x_position).getObjectType() != "null") {
			return true;
		} else {
			return false;
		}
	}
	public String checkCoordinate(int x_position,int y_position) {
		return gameGrid.yGrid.get(y_position).get(x_position).getObjectType(); 	
	}
	
	public void shiftToCoordinate(int x_position,int y_position,String objectType) {
		gameGrid.yGrid.get(this.yPos).get(this.xPos).setObjectType("null");
		xPos = x_position;
		yPos = y_position;
		gameGrid.yGrid.get(this.yPos).get(this.xPos).setObjectType(objectType);
		
		
	}
	
}
