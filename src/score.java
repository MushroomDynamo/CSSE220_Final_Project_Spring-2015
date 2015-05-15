
public class score extends Digger{
	
	public static int score(int x, int y){
		//for (int i=0;i<numEmeralds;i++){
		if (gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.Hero.xPos).getObjectType() == "emerald"){
			//remove emerald in list
			return 50;
		} else if (gameGrid.yGrid.get(y).get(x).getObjectType() == "emerald"){
			//remove emerald in list
			return -25;
		}
		return 0;
	}

	public static int score(int points,int x, int y){
		//for (int i=0;i<numEmeralds;i++){
		if (gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.Hero.xPos).getObjectType() == "emerald"){
			//remove emerald in list
			return points + 50;
		} else if (gameGrid.yGrid.get(y).get(x).getObjectType() == "emerald"){
			//remove emerald in list
			return points - 25;
		}
		return points;
	}
}
