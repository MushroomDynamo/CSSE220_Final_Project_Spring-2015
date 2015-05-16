import java.util.ArrayList;
import java.util.Random;

public class gameClock implements Runnable {
	
	private int measuredTickClock = 0;
	private boolean doGameTicks = true;
	public Random randomGenerator = new Random();
	private int points = 0;
	private boolean allEmeraldsCollected = true;
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			this.measuredTickClock = this.measuredTickClock + Digger.frameInterval;
			if (this.measuredTickClock > 1000) {
				this.measuredTickClock = 0;
			}
			if (this.doGameTicks == true) {
				
				Digger.menu.setLifeLabel(Digger.getHeroLives());
				Digger.menu.setgameScore(this.points);

				for (int i=0;i<Digger.dumpTickableRegistry().size();i++) {
					//Monster logic in here
					Object objectToTick = Digger.dumpTickableRegistry().get(i);
					if (objectToTick instanceof objectMonster) {
						int tickInterval = ((objectMonster) objectToTick).returnTickActionInterval();
						if (this.measuredTickClock % tickInterval == 0) {
							int[] heroCoordinates = Digger.returnHeroCoordinates();
							int[] monsterCoordinates = ((objectMonster) objectToTick).returnCoordinates();
							//int points = score.score(monsterCoordinates[0],monsterCoordinates[1]);
							//points = score.score(points, monsterCoordinates[0],monsterCoordinates[1]); //////fix the points variable that is to the right;
							//System.out.println(points);
							this.allEmeraldsCollected = true;
							for (int b=0;b<gameGrid.yGrid.size();b++) {
								ArrayList<objectDrawable> xGrid = gameGrid.yGrid.get(b);
								for (int j=0;j<xGrid.size();j++) {
									String objectType = xGrid.get(j).getObjectType();
									if (objectType == "emerald") {
										this.allEmeraldsCollected = false;
									}}} 
							if (this.allEmeraldsCollected) {int levelPosition = Digger.advanceLevelPosition();
									String levelList[] = Digger.returnLevelList();
									if (levelPosition >= levelList.length){
										System.out.println("YOU WIN!");
									}else{
									System.out.println("level "+levelPosition);
									Digger.clearTickableRegistry();
									Digger.clearTickableRegistry();


									levelManager.readLevelFile(levelList[levelPosition]);
									

									}
									}
						
								
							if (objectToTick instanceof objectMonsterNonDigging) {
								objectMonsterNonDigging monsterNonDigging = ((objectMonsterNonDigging) objectToTick);
								this.randomGenerator.setSeed((long) Digger.seed+monsterCoordinates[0]*monsterCoordinates[1]+heroCoordinates[0]*heroCoordinates[1]);
								if (heroCoordinates[0] == monsterCoordinates[0] && heroCoordinates[1] == monsterCoordinates[1]) {
									//System.out.println("You have been dingbatted");
									Digger.setHeroDead(true);
									Digger.removeHeroLives();
									System.out.println(Digger.getHeroLives());

									if(Digger.getHeroLives()<=0){
										System.out.println(Digger.getHeroLives());
										Digger.closeGame();
									}
								
								}
								while (true) {
									if (monsterNonDigging.movementDirection == 4) {
										monsterNonDigging.movementDirection = 0;
									}
									if (monsterNonDigging.movementDirection == 0) {
										if (monsterCoordinates[1]+1 != Digger.gameHeight) {
											if (monsterNonDigging.checkCoordinate(monsterCoordinates[0],monsterCoordinates[1]+1) == "null" || monsterNonDigging.checkCoordinate(monsterCoordinates[0],monsterCoordinates[1]+1) == "hero") {
												monsterNonDigging.shiftToCoordinate(monsterCoordinates[0],monsterCoordinates[1]+1,"monster2");
												break;
											} else {
												monsterNonDigging.movementDirection = monsterNonDigging.movementDirection + 1;
												break;
											} 
										} else {
											monsterNonDigging.movementDirection = monsterNonDigging.movementDirection - 1;
											break;
										}
									} else if (monsterNonDigging.movementDirection == 1) {
										if (monsterCoordinates[0]+1 != Digger.gameWidth) {
											if (monsterNonDigging.checkCoordinate(monsterCoordinates[0]+1,monsterCoordinates[1]) == "null" || monsterNonDigging.checkCoordinate(monsterCoordinates[0]+1,monsterCoordinates[1]) == "hero") {
												monsterNonDigging.shiftToCoordinate(monsterCoordinates[0]+1,monsterCoordinates[1],"monster2");
												break;
											} else {
												monsterNonDigging.movementDirection = monsterNonDigging.movementDirection + 1;
												break;
											}
										} else {
											monsterNonDigging.movementDirection = monsterNonDigging.movementDirection - 1;
											break;
										}
									} else if (monsterNonDigging.movementDirection == 2) {
										if (monsterCoordinates[1]-1 != -1) {
											if (monsterNonDigging.checkCoordinate(monsterCoordinates[0],monsterCoordinates[1]-1) == "null" || monsterNonDigging.checkCoordinate(monsterCoordinates[0],monsterCoordinates[1]-1) == "hero") {
												monsterNonDigging.shiftToCoordinate(monsterCoordinates[0],monsterCoordinates[1]-1,"monster2");
												break;
											} else {
												monsterNonDigging.movementDirection = monsterNonDigging.movementDirection + 1;
												break;
											}
										} else {
											monsterNonDigging.movementDirection = monsterNonDigging.movementDirection - 1;
											break;
										}
									} else {
										if (monsterCoordinates[0]-1 != -1) {
											if (monsterNonDigging.checkCoordinate(monsterCoordinates[0]-1,monsterCoordinates[1]) == "null" || monsterNonDigging.checkCoordinate(monsterCoordinates[0]-1,monsterCoordinates[1]) == "hero") {
												monsterNonDigging.shiftToCoordinate(monsterCoordinates[0]-1,monsterCoordinates[1],"monster2");
												break;
											} else {
												monsterNonDigging.movementDirection = monsterNonDigging.movementDirection + 1;
												break;
											}
										} else {
											monsterNonDigging.movementDirection = monsterNonDigging.movementDirection - 1;
											break;
										}
									}
								}
							} else {
								if (heroCoordinates[0] > monsterCoordinates[0]) {
									((objectMonster) objectToTick).shiftToCoordinate(monsterCoordinates[0]+1,monsterCoordinates[1],"monster");
								} else if (heroCoordinates[0] < monsterCoordinates[0]) {
									((objectMonster) objectToTick).shiftToCoordinate(monsterCoordinates[0]-1,monsterCoordinates[1],"monster");
								} else {
									if (heroCoordinates[1] > monsterCoordinates[1]) {
										((objectMonster) objectToTick).shiftToCoordinate(monsterCoordinates[0],monsterCoordinates[1]+1,"monster");
									} else if (heroCoordinates[1] < monsterCoordinates[1]) {
										((objectMonster) objectToTick).shiftToCoordinate(monsterCoordinates[0],monsterCoordinates[1]-1,"monster");
									} else {
										//System.println("You have been dingbatted");
										Digger.setHeroDead(true);
										Digger.removeHeroLives();
										if(Digger.getHeroLives()<=0){
											System.out.println(Digger.getHeroLives());
											Digger.closeGame();
										}
									
									}
								}
							}
						}
					} else if (objectToTick instanceof objectHero) {
						int tickInterval = ((objectHero) objectToTick).returnTickActionInterval();
						if (this.measuredTickClock % tickInterval == 0) {
							String bufferedAction = Digger.bufferedAction;
							Digger.bufferedAction = "null";
							if (bufferedAction == "right") {
								if (Digger.Hero.xPos == Digger.gameWidth-1){
									if(gameGrid.yGrid.get(Digger.Hero.yPos).get(0).getObjectType() == "emerald"){
										this.points = this.points + 50;
										System.out.println(this.points);
									}
									else if(gameGrid.yGrid.get(Digger.Hero.yPos).get(0).getObjectType() == "gold"){
										this.points = this.points + 200;
										System.out.println(this.points);
									}
								}
									else if (gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.Hero.xPos+1).getObjectType() == "emerald"){
									this.points = this.points + 50;
									System.out.println(this.points);
									}
									else if (gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.Hero.xPos+1).getObjectType() == "gold"){
										this.points = this.points + 200;
										System.out.println(this.points);
								}
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos+1,Digger.Hero.yPos,"hero");
							} else if (bufferedAction == "left") {
								if (Digger.Hero.xPos == 0){
									if(gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.gameWidth-1).getObjectType() == "emerald"){
										this.points = this.points + 50;
										System.out.println(this.points);
									}
									if(gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.gameWidth-1).getObjectType() == "gold"){
										this.points = this.points + 200;
										System.out.println(this.points);
									}
								}
								else if (gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.Hero.xPos-1).getObjectType() == "emerald"){
									this.points = this.points + 50;
									System.out.println(this.points);
								}
								else if (gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.Hero.xPos-1).getObjectType() == "gold"){
									this.points = this.points + 200;
									System.out.println(this.points);
								}
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos-1,Digger.Hero.yPos,"hero");
							} else if (bufferedAction == "up") {
								if (Digger.Hero.yPos == 0){
									if(gameGrid.yGrid.get(Digger.gameHeight-1).get(Digger.Hero.xPos).getObjectType() == "emerald"){
										this.points = this.points + 50;
										System.out.println(this.points);
									}
									else if(gameGrid.yGrid.get(Digger.gameHeight-1).get(Digger.Hero.xPos).getObjectType() == "gold"){
										this.points = this.points + 200;
										System.out.println(this.points);
									}
								}
								else if (gameGrid.yGrid.get(Digger.Hero.yPos-1).get(Digger.Hero.xPos).getObjectType() == "emerald"){
									this.points = this.points + 50;
									System.out.println(this.points);
								}
								else if (gameGrid.yGrid.get(Digger.Hero.yPos-1).get(Digger.Hero.xPos).getObjectType() == "gold"){
									this.points = this.points + 200;
									System.out.println(this.points);
								}
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos,Digger.Hero.yPos-1,"hero");
							} else if (bufferedAction == "down") {
								if (Digger.Hero.yPos == Digger.gameHeight-1){
									if(gameGrid.yGrid.get(0).get(Digger.Hero.xPos).getObjectType() == "emerald"){
										this.points = this.points + 50;
										System.out.println(this.points);
									}
									if(gameGrid.yGrid.get(0).get(Digger.Hero.xPos).getObjectType() == "gold"){
										this.points = this.points + 200;
										System.out.println(this.points);
									}
								}
								else if (gameGrid.yGrid.get(Digger.Hero.yPos+1).get(Digger.Hero.xPos).getObjectType() == "emerald"){
									this.points = this.points + 50;
									System.out.println(this.points);
								}
								else if (gameGrid.yGrid.get(Digger.Hero.yPos+1).get(Digger.Hero.xPos).getObjectType() == "gold"){
									this.points = this.points + 200;
									System.out.println(this.points);
								}
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos,Digger.Hero.yPos+1,"hero");
							} else if (bufferedAction == "attack") {
								for (int j=0;j<Digger.dumpTickableRegistry().size();j++) {
									Object objectToTick1 = Digger.dumpTickableRegistry().get(j);
									if (objectToTick1 instanceof objectMonster) {
										int[] hC = Digger.returnHeroCoordinates();
										int[] mC = ((objectMonster) objectToTick1).returnCoordinates();
										System.out.println(Digger.facing);
										//Digger.facing == "right" &&								
//										if (heroCoordinates[0] == monsterCoordinates[0]-1 && heroCoordinates[1] == monsterCoordinates[1]) {
//											gameGrid.yGrid.get(monsterCoordinates[1]).get(monsterCoordinates[0]).setObjectType("null");
//											Digger.dumpTickableRegistry().remove(j);
//										} else if (heroCoordinates[0] == monsterCoordinates[0]+2 && heroCoordinates[1] == monsterCoordinates[1]) {
//											gameGrid.yGrid.get(monsterCoordinates[1]).get(monsterCoordinates[0]).setObjectType("null");
//											Digger.dumpTickableRegistry().remove(j);
//										} else if (heroCoordinates[1] == monsterCoordinates[1]-2 && heroCoordinates[0] == monsterCoordinates[0]) {
//											gameGrid.yGrid.get(monsterCoordinates[1]).get(monsterCoordinates[0]).setObjectType("null");
//											Digger.dumpTickableRegistry().remove(j);
//										} else if (heroCoordinates[1] == monsterCoordinates[1]+2 && heroCoordinates[0] == monsterCoordinates[0]) {
//											gameGrid.yGrid.get(monsterCoordinates[1]).get(monsterCoordinates[0]).setObjectType("null");
//											Digger.dumpTickableRegistry().remove(j);
//										}
										
										if(Math.abs(hC[0]-mC[0])<= 1 && hC[1] == mC[1]){
											gameGrid.yGrid.get(mC[1]).get(mC[0]).setObjectType("null");
											Digger.dumpTickableRegistry().remove(j);
										}else if(Math.abs(hC[1]-mC[1])<= 1 && hC[0] == mC[0]){
											gameGrid.yGrid.get(mC[1]).get(mC[0]).setObjectType("null");
											Digger.dumpTickableRegistry().remove(j);
										}
										
										
										
									}
								}
							}
						}
					} else if (objectToTick instanceof objectMoneyBag) {
						int tickInterval = ((objectMoneyBag) objectToTick).returnTickActionInterval();
						if (this.measuredTickClock % tickInterval == 0) {
//							int[] bagCoordinates = ((objectMoneyBag) objectToTick).returnCoordinates();
//							if (((objectMoneyBag) objectToTick).fall(bagCoordinates[0], bagCoordinates[1])){
//								((objectMoneyBag) objectToTick).shiftToCoordinate(bagCoordinates[0], bagCoordinates[1]+1, "moneybag");
//								if (((objectMoneyBag) objectToTick).willBreak(bagCoordinates[0], bagCoordinates[1])){
//									if(!((objectMoneyBag) objectToTick).fall(bagCoordinates[0], bagCoordinates[1])){
//										((objectMoneyBag) objectToTick).shiftToCoordinate(bagCoordinates[0], bagCoordinates[1], "gold");
//									}
//								}
//							}
						}
					}
				}
			}
			
			if(Digger.getHeroDead()){
				this.doGameTicks = false;
				try {
					Thread.sleep(Digger.frameInterval);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Digger.clearTickableRegistry();
				Digger.clearTickableRegistry();
				
				//Kind of kludgey, but we'll do this for now
				gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.Hero.xPos).setObjectType("null");
				
				levelManager.refresh(Digger.returnLevelList()[Digger.returnLevelPosition()]);
	
				Digger.setHeroDead(false);
				this.doGameTicks = true;
				}
			
			try {
				Thread.sleep(Digger.frameInterval);
			} catch (InterruptedException e) {
				System.out.println("Thread sleep interrupted");
			}
		}
	}
	
}
